package com.example.fabio.parkingapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Fabio on 5/20/2017.
 */

/**
 * Classe responsavel por escutar as mensagens que chegam do servidor de aplicacao.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    //Metodo que escuta as mensagens do servidor de aplicacao
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //verificar se a mensagem tem payload de dado
        if (remoteMessage.getData().size() > 0) {

            for(String vaga : remoteMessage.getData().keySet()){
                montarMapa(vaga, remoteMessage.getData().get(vaga));
            }
        }

//        MainActivity.mainActivity.updateMap("oi", "oi", "oi", "oi", "oi", "oi", "oi", "oi", "oi", "oi", "oi", "oi");

        //verificar se a mensagem tem uma notificacao
        if (remoteMessage.getNotification() != null) {

            String click_action = remoteMessage.getNotification().getClickAction();
//            MainActivity.mainActivity.ToastNotify(remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle(), click_action);

            String teste = remoteMessage.getNotification().getBody();
            MainActivity.mainActivity.ToastNotify(teste);
        }

    }

    private void sendNotification(String messageBody, String messageTitle, String click_action) {
        Intent intent = new Intent(click_action);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }

    private void montarMapa(String vaga, String texto){
        switch (vaga) {
            case "vaga1":
                MainActivity.mainActivity.popularMapa(R.id.vaga1, texto);
                break;
            case "vaga2":
                MainActivity.mainActivity.popularMapa(R.id.vaga2, texto);
                break;
            case "vaga3":
                MainActivity.mainActivity.popularMapa(R.id.vaga3, texto);
                break;
            case "vaga4":
                MainActivity.mainActivity.popularMapa(R.id.vaga4, texto);
                break;
            case "vaga5":
                MainActivity.mainActivity.popularMapa(R.id.vaga5, texto);
                break;
            case "vaga6":
                MainActivity.mainActivity.popularMapa(R.id.vaga6, texto);
                break;
            case "vaga7":
                MainActivity.mainActivity.popularMapa(R.id.vaga7, texto);
                break;
            case "vaga8":
                MainActivity.mainActivity.popularMapa(R.id.vaga8, texto);
                break;
            case "vaga9":
                MainActivity.mainActivity.popularMapa(R.id.vaga9, texto);
                break;
            case "vaga10":
                MainActivity.mainActivity.popularMapa(R.id.vaga10, texto);
                break;
            case "vaga11":
                MainActivity.mainActivity.popularMapa(R.id.vaga11, texto);
                break;
            case "vaga12":
                MainActivity.mainActivity.popularMapa(R.id.vaga12, texto);
                break;
            default:
                MainActivity.mainActivity.ToastNotify("Sem valor!");
        }
    }
}
