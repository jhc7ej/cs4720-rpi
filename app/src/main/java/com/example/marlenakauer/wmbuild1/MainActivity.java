package com.example.marlenakauer.wmbuild1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = (Button) findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // Intent intent = new Intent(MainActivity.this, QRscannerPage.class);
               try {
                   HttpClient client = new DefaultHttpClient();
                   String targetUrl = "http://www.eventbriteapi.com/v3/events/14581147605/attendees/?token=F3N6WOE7BNL46UKIRVBU";
                   HttpGet httpGet = new HttpGet(targetUrl);
                   HttpResponse response = null;
                   response = client.execute(httpGet);
                   HttpEntity entity = response.getEntity();
                   if(entity!=null){
                       Log.v("GET RESPONSE", EntityUtils.toString(entity));
                   }
               }
               catch(Exception e){
                   e.printStackTrace();
               }
//               startActivity(intent);
//               finish();
           }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
