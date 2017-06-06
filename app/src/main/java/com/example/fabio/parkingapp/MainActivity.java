package com.example.fabio.parkingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button enviarDados;
    private EditText rfid;
    private SharedPreferences myPrefs;
    private static final String CONFIGURACAO_VAGA = "configurqacao_vaga";
    private static final String VAGA_DESTINADA = "vaga";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;
        myPrefs = this.getSharedPreferences(CONFIGURACAO_VAGA, Context.MODE_PRIVATE);

        enviarDados = (Button) findViewById(R.id.enviar_dados);
        rfid = (EditText) findViewById(R.id.rfid);

        enviarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPlayServices()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("vagas");
                    Intent intent = new Intent(getApplicationContext(), SendTokenService.class);
                    intent.putExtra("rfid", rfid.getText().toString());
                    startService(intent);
                }
            }
        });


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
                TextView mensagem = (TextView) findViewById(R.id.vaga_destinada_numero);
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

    public void dadosEnviados() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Os Dados do seu carro foram enviados corretamente!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        estacionamento = (HashMap<String, String>) MapaHelper.recuperarMapa();
        MapaHelper.processarMapa(estacionamento);
        TextView mensagem = (TextView) findViewById(R.id.vaga_destinada_numero);
        mensagem.setText(myPrefs.getString(VAGA_DESTINADA, null));
    }

    @Override
    protected void onPause() {
        super.onPause();
        TextView mensagem = (TextView) findViewById(R.id.vaga_destinada_numero);

        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putString(VAGA_DESTINADA, mensagem.getText().toString());
        prefsEditor.commit();

    }
}