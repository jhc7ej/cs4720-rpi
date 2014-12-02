package com.example.marlenakauer.wmbuild1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup; //hehehehehe

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import java.util.ArrayList;

public class QRscannerPage extends Activity {

//    public static ArrayList<String> attendeestable = MainActivity.attendees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner_page);

//        Button myButton = (Button) findViewById(R.id.success);
//        Button myButton2 = (Button) findViewById(R.id.fail);
        Button myButton3 = (Button) findViewById(R.id.scan);
        System.out.println(MainActivity.attendees.size());
        for (int i = 0; i<MainActivity.attendees.size(); i++){
            System.out.println("this is from arraylist");
            System.out.println(MainActivity.attendees.get(i)); }


//        myButton.setOnClickListener(new OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               //Toast.makeText(QRscannerPage.this, "Button Clicked", Toast.LENGTH_SHORT).show();
//               //new MyAsyncTask().execute(message, "true");
//
//               Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//               startActivityForResult(intent, 0);
//           }
//        });
//
//        myButton2.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(QRscannerPage.this, "Another Button Clicked", Toast.LENGTH_SHORT).show();
//                new MyAsyncTask().execute(message, "false");
//            }
//        });

        myButton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(QRscannerPage.this);
                integrator.addExtra("SCAN_WIDTH", 640);
                integrator.addExtra("SCAN_HEIGHT", 480);
                integrator.addExtra("SCAN_FORMATS", "QR_CODE");
                //customize the prompt message before scanning
                integrator.addExtra("PROMPT_MESSAGE", "Scanner Start!");
                integrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qrscanner_page, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_qrscanner_page, container, false);
            return rootView;
        }
    }

    public static void POST(String url, boolean color) {
        url = "http://" + url + "/rpi";

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            JSONObject jsonObj = new JSONObject();
            if (color == true) {
                JSONArray blah = new JSONArray("[{\"intensity\":.8,\"red\":0,\"blue\":0,\"green\":255,\"lightId\":1}]");
                jsonObj.accumulate("lights", blah);
                jsonObj.accumulate("propagate", true);
            }

            if (color == false) {
                JSONArray blah2 = new JSONArray("[{\"intensity\":.8,\"red\":255,\"blue\":0,\"green\":0,\"lightId\":1}]");
                jsonObj.accumulate("lights", blah2);
                jsonObj.accumulate("propagate", true);

            }
            StringEntity se = new StringEntity(jsonObj.toString());
            post.setEntity(se);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(post);

        } catch (Exception e) {

            Log.e("WMBuild1", Log.getStackTraceString(e));

        }

    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {
        @Override
        protected Double doInBackground(String... params) {
            POST(params[0], Boolean.valueOf(params[1]));
            return null;

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Intent intent2 = getIntent();
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        final String message = intent2.getStringExtra(Build1.EXTRA_MESSAGE);
        //if (requestCode == 0) {
        String validAttendee = "false";
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                //for (int i =0; i < MainActivity.attendees.size(); i++) {
                if (MainActivity.attendees.contains(result.getContents())) {
                    validAttendee = "true";
                    System.out.println("color true");
                }
                else {
                    validAttendee = "false";
                    System.out.println("color false");
                }

                //}
                // Handle successful scan
//                if (contents.equals("true")) {
                   // new MyAsyncTask().execute(message, "true");
                //}

               // else {

                //$$$$$
                //have database check if id string is in, if in new asynctask.execute(message, "true") else false
                    new MyAsyncTask().execute(message, validAttendee);

                //}
                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format , Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Toast toast = Toast.makeText(this, "Scan was Cancelled!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();

            }
        //}
    }

}
