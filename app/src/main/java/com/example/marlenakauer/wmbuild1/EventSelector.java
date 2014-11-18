package com.example.marlenakauer.wmbuild1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;



public class EventSelector extends ListActivity {

    static final String[] COUNTRIES = new String[] {

            "Camera"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_selector);
//       Button dateButton = (Button) findViewById(R.id.date);
//
//        dateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(QRscannerPage.this, "Button Clicked", Toast.LENGTH_SHORT).show();
//                //new MyAsyncTask().execute(message, "true");
//
//                Intent intent = new Intent(EventSelector.this, SearchbyDate.class);
//                startActivity(intent);
//            }
//        });
        setListAdapter(new ArrayAdapter < String >(this,
                android.R.layout.simple_list_item_1, COUNTRIES));
        getListView().setTextFilterEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_selector, menu);
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);

//        new AlertDialog.Builder(this)
//                .setMessage("Picture time! ")
//                .setPositiveButton("OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {}
//                        })
//                .show();

        Toast.makeText(EventSelector.this,
                "Camera goes here",
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);

    }

}
