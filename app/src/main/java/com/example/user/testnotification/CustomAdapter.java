package com.example.user.testnotification;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<AlertMessages> alertmessages;

    public CustomAdapter(Context c, ArrayList<AlertMessages> alertmessages) {
        this.c = c;
        this.alertmessages = alertmessages;
    }

    @Override
    public int getCount() {
        return alertmessages.size();
    }

    @Override
    public Object getItem(int i) {
        return alertmessages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT;

        LinearLayout.LayoutParams paramsLEFT = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsLEFT.gravity = Gravity.LEFT;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.list_item,parent,false);

        }



        final TextView alarmName= (TextView) convertView.findViewById(R.id.tvAlarmName);
        final TextView dateTime= (TextView) convertView.findViewById(R.id.tvDateTime);
        final TextView acknowledgeBy= (TextView) convertView.findViewById(R.id.tvAckBy);



        final AlertMessages am= (AlertMessages) this.getItem(position);

        alarmName.setText(am.getAlarmName());
        dateTime.setText(am.getDateTime());
        acknowledgeBy.setText("Acknowledge By: "+ am.getUserName());

//        Toast.makeText(c,Integer.toString(position),Toast.LENGTH_SHORT).show();
        if(am.getAcknowledge().equals("false")){
            alarmName.setTextColor(Color.RED);
            dateTime.setTextColor(Color.BLACK);
            alarmName.setGravity(Gravity.LEFT);
            dateTime.setGravity(Gravity.LEFT);
            acknowledgeBy.setLayoutParams(paramsLEFT);

        }
        else if(am.getAcknowledge().equals("")){
            alarmName.setTextColor(Color.LTGRAY);
            dateTime.setTextColor(Color.LTGRAY);
            alarmName.setGravity(Gravity.RIGHT);
            dateTime.setGravity(Gravity.RIGHT);
            acknowledgeBy.setLayoutParams(params);
            isEnabled(position);
        }
        else{
            alarmName.setTextColor(Color.GREEN);
            dateTime.setTextColor(Color.BLACK);
            alarmName.setGravity(Gravity.RIGHT);
            dateTime.setGravity(Gravity.RIGHT);


            acknowledgeBy.setLayoutParams(params);
        }



        //ONITECLICK
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(c,am.getAlarmName(),Toast.LENGTH_SHORT).show();
//
//            }
//        });
////
//        convertView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//
//                if(acknowledgeBy.getText() != ""){
//                    if ( acknowledgeBy.getVisibility() == View.GONE)
//                    {
//                        //expandedChildList.set(arg2, true);
//                        acknowledgeBy.setVisibility(View.VISIBLE);
//                    }
//                    else
//                    {
//                        //expandedChildList.set(arg2, false);
//                        acknowledgeBy.setVisibility(View.GONE);
//                    }
//                }
//
//
//                return false;
//            }
//        });


        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
