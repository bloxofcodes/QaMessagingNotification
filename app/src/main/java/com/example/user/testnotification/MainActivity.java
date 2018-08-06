package com.example.user.testnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.RemoteMessage;
import com.pusher.pushnotifications.PushNotifications;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView listMsg;
    private Toolbar mTopToolbar;
    DatabaseReference db;
    FirebaseHelper helper;
    ArrayAdapter<String> adapter;

    ArrayList<String> festStr = new ArrayList<>();


    String[] festivals = {
            "AHU-1001-TRIP",
            "AHU-1002-TRIP",
            "AHU-1003-TRIP",
            "AHU-1001-TRIP",
            "AHU-1002-TRIP",
            "AHU-1003-TRIP",
            "AHU-1001-TRIP",
            "AHU-1002-TRIP",
            "AHU-1003-TRIP",
            "AHU-1001-TRIP",
            "AHU-1002-TRIP",
            "AHU-1003-TRIP",
            "AHU-1001-TRIP",
            "AHU-1002-TRIP",
            "AHU-1003-TRIP",
            "AHU-1001-TRIP",
            "AHU-1002-TRIP",
            "AHU-1003-TRIP",
            "AHU-1001-TRIP",
            "AHU-1002-TRIP",
            "AHU-1003-TRIP",
            "AHU-1001-TRIP",
            "AHU-1002-TRIP",
            "AHU-1003-TRIP",
            "AHU-1001-TRIP",
            "AHU-1002-TRIP",
            "AHU-1003-TRIP",

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(mTopToolbar);

        listMsg = (ListView) findViewById(R.id.listMsg);

        //SETUP FIREBASE
        db= FirebaseDatabase.getInstance().getReference();
        helper=new FirebaseHelper(db);
//        festStr.add("HEY");
//        festStr.add("HEY");
//        festStr.add("HEY");
//        Log.d("JAYVEE",festStr.toString());



        //        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.textView,festStr);
//        final ArrayAdapter adapter = new ArrayAdapter(this,
//                R.layout.list_item, R.id.textView, festivals);
        try
        {

            Log.d("SHOWARRAY",helper.retrieve().toString());
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Log.d("SHOWARRAY",helper.retrieve().toString());

                    adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item,R.id.textView,helper.retrieve());

                    listMsg.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });





            listMsg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    // TODO Auto-generated method stub

                    /* appending Happy with festival name */
                    String value = "Happy " + adapter.getItem(position);
                    /* Display the Toast */
                    Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                }
            });



        }catch(Exception e){
            Log.d("ERR", e.toString());
        }


        PushNotifications.start(getApplicationContext(), "5fb00808-3b8d-4533-9079-9fec45c7d781");
        PushNotifications.subscribe("debug-hello");



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
        if (id == R.id.action_favorite) {
//            Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure " +
                    "for acknowledge?");
                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(MainActivity.this,"You clicked yes " +
                                            "button",Toast.LENGTH_LONG).show();
                                }
                            });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}
