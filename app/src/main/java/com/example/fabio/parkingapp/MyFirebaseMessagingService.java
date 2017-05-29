package com.example.fabio.parkingapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

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
                MapaHelper.montarMapa(vaga, remoteMessage.getData().get(vaga));
            }
        }

        //verificar se a mensagem tem uma notificacao
        if (remoteMessage.getNotification() != null) {

            String click_action = remoteMessage.getNotification().getClickAction();
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

}
