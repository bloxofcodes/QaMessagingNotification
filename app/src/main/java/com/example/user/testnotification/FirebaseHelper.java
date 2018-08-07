package com.example.user.testnotification;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved=null;
    ArrayList<AlertMessages> alertmsg = new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE
    public Boolean save(AlertMessages alertmsg)
    {
        if(alertmsg==null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("alarm-items").push().setValue(alertmsg);
                saved=true;

            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }

        return saved;
    }

    //UPDATE
    public Boolean update(String id)
    {
        if(alertmsg==null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("alarm-items/"+id+"/"+"acknowledge").setValue("true");
                db.child("alarm-items/"+id+"/"+"userName").setValue("Jayvee");
                saved=true;

            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }

        return saved;
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        alertmsg.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {

            AlertMessages alertMessages = ds.getValue(AlertMessages.class);
            alertMessages.setKey(ds.getKey());
            alertmsg.add(alertMessages);

            Log.d("DATASHIT",ds.toString());
//            String alarmName=ds.getValue(AlertMessages.class).getAlarmName();
//            Log.d("ACKNOWLEDGE",alarmName);
//            alertmsg.add(alarmName);
//            Log.d("LISTALERTS",alertmsg.toString());
        }
    }

    //READ
    public ArrayList<AlertMessages> retrieve()
    {

//
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d("DATACHANGES","XXX");
//                fetchData(dataSnapshot);
//
//
////                Log.d("RETURNALERTS",alertmsg.toString());
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



        db.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("CHILDADDEDWASTRIGGERED","XXX");
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("CHILDCHANGEDWASTRIGGERED","XXX");
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return alertmsg;

    }



}
