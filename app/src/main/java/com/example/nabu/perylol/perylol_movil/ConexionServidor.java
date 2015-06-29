package com.example.nabu.perylol.perylol_movil;

/**
 * Created by Luiis Plata on 10/06/2015.
 */


import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by LUIS on 14/04/2015.
 * este objeto nos servira para la consulta de los datos
 */
public class ConexionServidor {

    private InputStream is = null;
    private JSONObject jsonobject = null;
    private String result = "";

    public ConexionServidor() {
    }

    public JSONObject getGet(String url) {

    /*
    No entendi porque hay que coonvertirlo en String para poder manejarlo
    */
        // Conectarse al servidor, enviar datos y recibir respuesta.
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httppost = new HttpGet(url);
            //httppost.setEntity(new UrlEncodedFormEntity(parametros));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.e("Error", "Error al enviar datos");
        }

        // Convertir respuesta a String.
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("Error", "Error al recibir respuesta");
        }

        // Convertir resultado obtenido a JSONObject que sera lo que retornara este metodo.
        try {
            jsonobject = new JSONObject(result);
        } catch (Exception e) {
            Log.e("Error", "Error al convertir respuesta");
        }

        return jsonobject;
    }

    public JSONObject getpost(String url) {

    /*
    No entendi porque hay que coonvertirlo en String para poder manejarlo
    */
        // Conectarse al servidor, enviar datos y recibir respuesta.
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            //httppost.setEntity(new UrlEncodedFormEntity(parametros));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.e("Error", "Error al enviar datos");
        }

        // Convertir respuesta a String.
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("Error", "Error al recibir respuesta");
        }

        // Convertir resultado obtenido a JSONObject que sera lo que retornara este metodo.
        try {
            jsonobject = new JSONObject(result);
        } catch (Exception e) {
            Log.e("Error", "Error al convertir respuesta");
        }

        return jsonobject;
    }

}

