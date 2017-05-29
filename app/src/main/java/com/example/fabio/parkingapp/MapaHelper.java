package com.example.fabio.parkingapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fabio on 5/29/2017.
 */

public class MapaHelper {

    private static HashMap<String, String> estacionamentoMapa = new HashMap<>();

    public static void montarMapa(String vaga, String texto){
        estacionamentoMapa.put(vaga, texto);
        switch (vaga) {
            case "vaga1":
                MainActivity.mainActivity.popularMapa(R.id.vaga1, "Vaga #1:" + "\n" + texto);
                break;
            case "vaga2":
                MainActivity.mainActivity.popularMapa(R.id.vaga2, "Vaga #2:" + "\n" + texto);
                break;
            case "vaga3":
                MainActivity.mainActivity.popularMapa(R.id.vaga3, "Vaga #3:" + "\n" + texto);
                break;
            case "vaga4":
                MainActivity.mainActivity.popularMapa(R.id.vaga4, "Vaga #4:" + "\n" + texto);
                break;
            case "vaga5":
                MainActivity.mainActivity.popularMapa(R.id.vaga5, "Vaga #5:" + "\n" + texto);
                break;
            case "vaga6":
                MainActivity.mainActivity.popularMapa(R.id.vaga6, "Vaga #6:" + "\n" + texto);
                break;
            case "vaga7":
                MainActivity.mainActivity.popularMapa(R.id.vaga7, "Vaga #7:" + "\n" + texto);
                break;
            case "vaga8":
                MainActivity.mainActivity.popularMapa(R.id.vaga8, "Vaga #8:" + "\n" + texto);
                break;
            case "vaga9":
                MainActivity.mainActivity.popularMapa(R.id.vaga9, "Vaga #9:" + "\n" + texto);
                break;
            case "vaga10":
                MainActivity.mainActivity.popularMapa(R.id.vaga10, "Vaga #10:" + "\n" + texto);
                break;
            case "vaga11":
                MainActivity.mainActivity.popularMapa(R.id.vaga11, "Vaga #11:" + "\n" + texto);
                break;
            case "vaga12":
                MainActivity.mainActivity.popularMapa(R.id.vaga12, "Vaga #12:" + "\n" + texto);
                break;
            default:
                MainActivity.mainActivity.ToastNotify("Sem valor!");
        }
    }

    public static Map<String, String> recuperarMapa(){
        return estacionamentoMapa;
    }

    public static void processarMapa(HashMap<String, String> estacionamento){
        for(String vaga : estacionamento.keySet()){
            MapaHelper.montarMapa(vaga, estacionamento.get(vaga));
        }
    }
}
