package com.example.marlenakauer.wmbuild1;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;


public class QRscannerPage extends Activity {

    public class Light {
    public String lightId = "";
    public String red = "";
    public String blue = "";
    public String green = "";
    public String intensity = "";
    public String propagate = "";
    }

    public String red = "{\"lights\":\"[{\"lightId\":1,\"red\":255,\"blue\":0,\"green\":0,\"intensity\":.3}]}";
    public String green = "{\"lights\":\"[{\"lightId\":1,\"red\":0,\"blue\":0,\"green\":255,\"intensity\":.3}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_qrscanner_page);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
        Intent intent = getIntent();
        String message = intent.getStringExtra(Build1.EXTRA_MESSAGE);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        // Set the text view as the activity layout
        setContentView(textView);

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
