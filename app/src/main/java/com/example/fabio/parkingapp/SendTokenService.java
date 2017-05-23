package com.example.fabio.parkingapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Fabio on 5/20/2017.
 */

public class SendTokenService extends IntentService {

    public static final String URL_BASE = "http://ioteam.azurewebsites.net/parkingapp/services/vagas/associarDevice";
    private static final String TAG = "SendTokenService";

    public SendTokenService(){
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String FCM_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "FCM Registration Token: " + FCM_token);
        String resposta = enviarDadosUsuario(FCM_token, "teste");
        Log.d(TAG, "Resposta: " + resposta);
    }

    public String enviarDadosUsuario(String token, String rfid){
        String responseResult = "";

        try {
            URL url = new URL(URL_BASE);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput (true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches (false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("token", token));
            params.add(new BasicNameValuePair("tag", rfid));

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(getQuery(params));
            wr.flush();
            wr.close();

            int responseCode=httpURLConnection.getResponseCode();
            responseResult = responseCode == HttpsURLConnection.HTTP_OK ? "Os dados foram enviados corretamente" : "Falha ao enviar os dados";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseResult;
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
