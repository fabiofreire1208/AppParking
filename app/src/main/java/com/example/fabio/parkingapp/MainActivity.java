package com.example.fabio.parkingapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity;
    private static final String TAG = "MainActivity";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static MyParkingMap parkingMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

//        parkingMap.updateMap("oi", "oi", "oi", "oi", "oi", "oi", "oi", "oi", "oi", "oi", "oi", "oi");

        if (checkPlayServices()) {
//            FirebaseMessaging.getInstance().subscribeToTopic("vagas");
            Intent intent = new Intent(this, SendTokenService.class);
            startService(intent);
        }
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported by Google Play Services.");
                ToastNotify("This device is not supported by Google Play Services.");
                finish();
            }
            return false;
        }
        return true;
    }

    public void ToastNotify(final String notificationMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, notificationMessage, Toast.LENGTH_LONG).show();
                TextView mensagem = (TextView) findViewById(R.id.mensagem_received);
                mensagem.setText(notificationMessage);
            }
        });
    }

    public void updateMap(final String v1, final String v2, final String v3, final String v4, final String v5, final String v6, final String v7, final String v8, final String v9, final String v10, final String v11, final String v12) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView vaga1 = (TextView) findViewById(R.id.vaga1);
                vaga1.setText(v1);

                TextView vaga2 = (TextView) findViewById(R.id.vaga2);
                vaga2.setText(v2);

                TextView vaga3 = (TextView) findViewById(R.id.vaga3);
                vaga3.setText(v3);

                TextView vaga4 = (TextView) findViewById(R.id.vaga4);
                vaga4.setText(v4);

                TextView vaga5 = (TextView) findViewById(R.id.vaga5);
                vaga5.setText(v5);

                TextView vaga6 = (TextView) findViewById(R.id.vaga6);
                vaga6.setText(v6);

                TextView vaga7 = (TextView) findViewById(R.id.vaga7);
                vaga7.setText(v7);

                TextView vaga8 = (TextView) findViewById(R.id.vaga8);
                vaga8.setText(v8);

                TextView vaga9 = (TextView) findViewById(R.id.vaga9);
                vaga9.setText(v9);

                TextView vaga10 = (TextView) findViewById(R.id.vaga10);
                vaga10.setText(v10);

                TextView vaga11 = (TextView) findViewById(R.id.vaga11);
                vaga11.setText(v11);

                TextView vaga12 = (TextView) findViewById(R.id.vaga12);
                vaga12.setText(v12);
            }
        });

    }

    public void popularMapa(final int textView, final String texto){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView vaga = (TextView) findViewById(textView);
                vaga.setText(texto);
            }
        });
    }
}