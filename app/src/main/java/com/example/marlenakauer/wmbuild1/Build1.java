package com.example.marlenakauer.wmbuild1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

public class Build1 extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.marlenakauer.wmbuild1";
    //Intent myIntent = new Intent(Build1.this, scanPage.class);

    //myIntent.putExtra("key", value);
    //Build1.this.startActivity(myIntent);
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
    public void sendMessage(View view) {
        Intent intent = new Intent(this, QRscannerPage.class);
        EditText ipText = (EditText) findViewById(R.id.editText);
        String message = ipText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        String red = "{\"lights\":\"[{\"lightId\":1,\"red\":255,\"blue\":0,\"green\":0,\"intensity\":.3}]}";
        JSONObject jsonObj;
        try {
            jsonObj = new JSONObject(red);
            System.out.println(jsonObj.toString());
        }
        catch (Exception e) {
        System.out.println("Uhoh no JSON");
        }

        }
        }





