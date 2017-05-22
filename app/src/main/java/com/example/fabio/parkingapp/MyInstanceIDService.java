package com.example.fabio.parkingapp;

import android.content.Intent;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Fabio on 5/20/2017.
 */

/**
 * Classe que lida com os eventos de atualizacao dos Tokens
 */
public class MyInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyInstanceIDService";

    // Esse metodo eh chamado quando o sistema determina que o token precisa ser atualizado. A aplicacao chama getToken() e envia o token para todas as aplicacoes servidoras.
    @Override
    public void onTokenRefresh() {

        //Iniciando o servico de envio de app ao servidor de aplicacao
        Intent intent = new Intent(this, SendTokenService.class);
        startService(intent);

    }
}
