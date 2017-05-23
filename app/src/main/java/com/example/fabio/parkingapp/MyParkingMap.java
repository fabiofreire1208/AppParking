package com.example.fabio.parkingapp;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by Fabio on 5/22/2017.
 */

public class MyParkingMap {

    private Context context;

    public MyParkingMap(Context context){
        this.context = context;
    }

    public void updateMap(String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8, String v9, String v10, String v11, String v12){
        TextView vaga1 = (TextView) ((Activity)context).findViewById(R.id.vaga1);
        vaga1.setText(v1);

        TextView vaga2 = (TextView) ((Activity)context).findViewById(R.id.vaga2);
        vaga2.setText(v2);

        TextView vaga3 = (TextView) ((Activity)context).findViewById(R.id.vaga3);
        vaga3.setText(v3);

        TextView vaga4 = (TextView) ((Activity)context).findViewById(R.id.vaga4);
        vaga4.setText(v4);

        TextView vaga5 = (TextView) ((Activity)context).findViewById(R.id.vaga5);
        vaga5.setText(v5);

        TextView vaga6 = (TextView) ((Activity)context).findViewById(R.id.vaga6);
        vaga6.setText(v6);

        TextView vaga7 = (TextView) ((Activity)context).findViewById(R.id.vaga7);
        vaga7.setText(v7);

        TextView vaga8 = (TextView) ((Activity)context).findViewById(R.id.vaga8);
        vaga8.setText(v8);

        TextView vaga9 = (TextView) ((Activity)context).findViewById(R.id.vaga9);
        vaga9.setText(v9);

        TextView vaga10 = (TextView) ((Activity)context).findViewById(R.id.vaga10);
        vaga10.setText(v10);

        TextView vaga11 = (TextView) ((Activity)context).findViewById(R.id.vaga11);
        vaga11.setText(v11);

        TextView vaga12 = (TextView) ((Activity)context).findViewById(R.id.vaga12);
        vaga12.setText(v12);

    }
}
