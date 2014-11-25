package com.example.marlenakauer.wmbuild1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
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
               Intent intent = new Intent(MainActivity.this, eventConfirmation.class);
               DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
               HttpGet httpget = new HttpGet("https://www.eventbriteapi.com/v3/events/14581147605/attendees/?token=F3N6WOE7BNL46UKIRVBU");
// Depends on your web service
               httpget.setHeader("Content-type", "application/json");

               InputStream inputStream = null;
               String result = null;
               try {
                   HttpResponse response = httpclient.execute(httpget);
                   HttpEntity entity = response.getEntity();

                   inputStream = entity.getContent();
                   // json is UTF-8 by default
                   BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                   StringBuilder sb = new StringBuilder();

                   String line = null;
                   while ((line = reader.readLine()) != null)
                   {
                       sb.append(line + "\n");
                   }
                   result = sb.toString();
                   System.out.println(result);
                   JSONObject jObject = new JSONObject(result);
               } catch (Exception e) {
                   // Oops
               }
               finally {
                   try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
               }
               startActivity(intent);
               finish();
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
