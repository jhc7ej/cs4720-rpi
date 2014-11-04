package com.example.marlenakauer.wmbuild1;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONObject;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class scanPage extends Activity {

    public String red = "{\"lights\":\"[{\"lightId\":1,\"red\":255,\"blue\":0,\"green\":0,\"intensity\":.3}]}";
    public String green = "{\"lights\":\"[{\"lightId\":1,\"red\":0,\"blue\":0,\"green\":255,\"intensity\":.3}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_page);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.scan_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //POST, open HTTP connection,
    // create HTTP obj passing the url,
    // take JSON string, add to POST and send request
    //
    public static void POST(String url, String light) {
        url = "192.168.20.125/rpi/";

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            JSONObject jsonObj = new JSONObject(light);
          //  StringEntity se = new StringEntity();

          //  post.setEntity(se);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            client.execute(post);
        } catch (Exception e) {

            System.out.println("Uh-oh there's an error!");


        }


    }
}

