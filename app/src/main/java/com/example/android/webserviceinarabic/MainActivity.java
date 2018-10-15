package com.example.android.webserviceinarabic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentTextView = findViewById(R.id.contentTV);

    }

    public void loadData(View view) throws IOException {


        new Thread(new Runnable() {
            @Override
            public void run() {
                // link that I should get data from it
                String link = "https://jsonplaceholder.typicode.com/posts";

                URL url = null;
                try {
                    url = new URL(link);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();

                    int c = 0;
                    StringBuffer buffer = new StringBuffer();
                    while ((c = inputStream.read()) != -1) {
                        buffer.append((char) c);
                    }

                    final String result = buffer.toString();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            contentTextView.setText(result);
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

}
