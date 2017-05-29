package com.example.fabio.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.Serializable;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity;
    private static final String TAG = "MainActivity";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private HashMap<String, String> estacionamento = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        if (checkPlayServices()) {
            FirebaseMessaging.getInstance().subscribeToTopic("vagas");
            Intent intent = new Intent(this, SendTokenService.class);
            startService(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putSerializable("estacionamentoVaga", (Serializable) MapaHelper.recuperarMapa());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.v(TAG, "Inside of onRestoreInstanceState");
        estacionamento = (HashMap<String, String>) savedInstanceState.getSerializable("estacionamentoVaga");
        MapaHelper.processarMapa(estacionamento);
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

    public void popularMapa(final int textView, final String texto){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView vaga = (TextView) findViewById(textView);
                vaga.setText(texto);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        estacionamento = (HashMap<String, String>) MapaHelper.recuperarMapa();
        MapaHelper.processarMapa(estacionamento);
    }
}