package com.example.marlenakauer.wmbuild1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONArray;

public class Build1 extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.marlenakauer.wmbuild1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.build1, menu);
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

    public static void POST(String url, boolean color) {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            JSONObject jsonObj = new JSONObject();
            if (color == true) {
                JSONArray blah = new JSONArray("[{\"intensity\":.3,\"red\":0,\"blue\":0,\"green\":255,\"lightId\":1}]");
                jsonObj.accumulate("lights", blah);
                jsonObj.accumulate("propagate", true);
            }

            if (color == false) {
                JSONArray blah2 = new JSONArray("[{\"intensity\":.3,\"red\":255,\"blue\":0,\"green\":0,\"lightId\":1}]");
                jsonObj.accumulate("lights", blah2);
                jsonObj.accumulate("propagate", true);

            }
            StringEntity se = new StringEntity(jsonObj.toString());
            post.setEntity(se);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            client.execute(post);
        } catch (Exception e) {
            System.out.println("Uh-oh there's an error!");
            Log.e("MYAPP", "exception", e);

        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, QRscannerPage.class);
        EditText ipText = (EditText) findViewById(R.id.editText);
        String message = ipText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}





