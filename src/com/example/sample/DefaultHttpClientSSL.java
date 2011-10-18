package com.example.sample;

import java.net.HttpURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class DefaultHttpClientSSL extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /**
         * httpsスキームの登録
         */
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
        schemeRegistry.register(new Scheme("https", sslSocketFactory, 443));

        HttpUriRequest uri = new HttpGet("https://www.example.com/");
        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response = client.execute(uri);
            StatusLine status_line = response.getStatusLine();
            if (status_line.getStatusCode() != HttpURLConnection.HTTP_OK) {
                Log.w("DefaultHttpClientSSL", "status code " + status_line.getStatusCode());
            } else {
                Log.i("DefaultHttpClientSSL", "status code " + status_line.getStatusCode());
                Log.i("DefaultHttpClientSSL", "status code " + response.getHeaders("Last-Modified")[0]);
            }
        } catch (Exception e) {
            Log.e("DefaultHttpClientSSL", "error", e);
        }
    }
}