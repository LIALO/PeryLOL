package com.example.nabu.perylol.perylol_movil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nabu.perylol.perylol_movil.Modelos.Conexion;
import com.example.nabu.perylol.perylol_movil.Modelos.Invocador;
import com.example.nabu.perylol.perylol_movil.Modelos.Partida;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;


public class Historial extends ActionBarActivity {
    // variables para metodo que quita acetnos
    private static final String ORIGINAL
            = "ÁáÉéÍíÓóÚúÑñÜü";
    private static final String REPLACEMENT
            = "AaEeIiOoUuNnUu";

    //inflando
    ViewGroup contenedor_historial;
    //invocador
    Invocador invocador = new Invocador();
    public String nombreCampeon;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        // Loader image - will be shown before loading image
        final int loader = R.drawable.champion;


        //inflando la pantalla
        contenedor_historial = (ViewGroup) findViewById(R.id.historial_historial);

        //Log.i("Invocador", getIntent().getStringExtra("invocador_id"));
        String invocador_id = getIntent().getStringExtra("invocador_id");
        //buscando el historial
        Conexion.get("https://lan.api.pvp.net/api/lol/lan/v1.3/game/by-summoner/" + invocador_id + "/recent?api_key=5dd8e058-3f90-4f1b-9e8f-be016dba394c", null, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //Aqui colocamo todo lo que passe cuando haya sido exitoso el json
                //Log.i("Resultado",response+"");
                nombreCampeon = "";
                invocador.obtenerHistorial(response);
                for (int i = 0; i< invocador.getPartidas().size();i++){
                    Partida part = invocador.getPartidas().get(i);

                    //Log.i("partida No #",i+"");
                    //inflando el layout :v
                    LayoutInflater inflacion = LayoutInflater.from(Historial.this);
                    //buscamos el layout que vamos a usar
                    LinearLayout historial = (LinearLayout) inflacion.inflate(R.layout.partida, null, false);
                    //llenamos con los datos
                    //creamos los wigyet para llenarlos
                    TextView resultado = (TextView) historial.findViewById(R.id.historial_resultado);
                    //final TextView campeon = (TextView) historial.findViewById(R.id.historial_campeon);
                    final ImageView campeonimg = (ImageView)historial.findViewById(R.id.historial_campeonImg);
                    final LinearLayout llResultado = (LinearLayout)findViewById(R.id.ll_resultado_historial);
                    TextView kill = (TextView) historial.findViewById(R.id.historial_kill);
                    TextView assists = (TextView) historial.findViewById(R.id.historial_assists);
                    TextView dead = (TextView) historial.findViewById(R.id.historial_dead);

                    final ImageView ivitem1 = (ImageView)historial.findViewById(R.id.IVitem1);
                    final ImageView ivitem2 = (ImageView)historial.findViewById(R.id.IVitem2);
                    final ImageView ivitem3 = (ImageView)historial.findViewById(R.id.IVitem3);
                    final ImageView ivitem4 = (ImageView)historial.findViewById(R.id.IVitem4);
                    final ImageView ivitem5 = (ImageView)historial.findViewById(R.id.IVitem5);
                    final ImageView ivitem6 = (ImageView)historial.findViewById(R.id.IVitem6);
                    final ImageView spell1 = (ImageView)historial.findViewById(R.id.h_spells1);
                    final ImageView spell2 = (ImageView)historial.findViewById(R.id.h_spells2);
                    final  String idItem = "";
                    //le damos valores a los textos con los datos que recojimos :v
                    if(invocador.getPartidas().get(i).isResultado())
                    {
                        resultado.setText("Victoria");
                        resultado.setTextColor(Color.parseColor("#1394d5"));
                    }
                    else
                    {
                        resultado.setText("Derrota");
                        resultado.setTextColor(Color.parseColor("#c31123"));
                    }


                    //obteniendo el campeon
                    Conexion.get("https://global.api.pvp.net/api/lol/static-data/lan/v1.2/champion/"+part.getCampeon()+"?api_key=5dd8e058-3f90-4f1b-9e8f-be016dba394c",null,new JsonHttpResponseHandler(){
                        String nombre_campeon;

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                           // campeon.setText(response.optString("name"));campeon.setTextColor(Color.parseColor("#F3D49E"));
                            String res = quitChar(response.optString("name"),"'");
                            campeonimg.setImageResource(quitSpaces(res));
                        }
                    });
                    Conexion.get("https://global.api.pvp.net/api/lol/static-data/lan/v1.2/summoner-spell/"+part.getSpell1()+"?locale=en_US&api_key=fe9d5d44-ed34-425c-95ad-f3813a96eb30",null,new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            String url_spells = "http://ddragon.leagueoflegends.com/cdn/5.2.1/img/spell/"+response.optString("key")+".png";
                            UrlImageViewHelper.setUrlDrawable(spell1, url_spells);
                        }
                    });
                    Conexion.get("https://global.api.pvp.net/api/lol/static-data/lan/v1.2/summoner-spell/"+part.getSpell2()+"?locale=en_US&api_key=fe9d5d44-ed34-425c-95ad-f3813a96eb30",null,new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            String url_spells = "http://ddragon.leagueoflegends.com/cdn/5.2.1/img/spell/"+response.optString("key")+".png";
                            UrlImageViewHelper.setUrlDrawable(spell2, url_spells);
                        }
                    });




                    //obteniendo el Item1
                    Conexion.get("https://global.api.pvp.net/api/lol/static-data/lan/v1.2/item/"+part.getItem1()+"?api_key=5dd8e058-3f90-4f1b-9e8f-be016dba394c",null,new JsonHttpResponseHandler(){
                        String nombre_campeon;

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                            String image_url ="http://ddragon.leagueoflegends.com/cdn/5.2.1/img/item/"+response.optString("id")+".png";
                            UrlImageViewHelper.setUrlDrawable(ivitem1, image_url);
                        }
                    });


                    //obteniendo el Item2
                    Conexion.get("https://global.api.pvp.net/api/lol/static-data/lan/v1.2/item/"+part.getItem2()+"?api_key=5dd8e058-3f90-4f1b-9e8f-be016dba394c",null,new JsonHttpResponseHandler(){
                        String nombre_campeon;

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            String image_url ="http://ddragon.leagueoflegends.com/cdn/5.2.1/img/item/"+response.optString("id")+".png";
                            UrlImageViewHelper.setUrlDrawable(ivitem2, image_url);
                        }
                    });
                    //obteniendo el Item3
                    Conexion.get("https://global.api.pvp.net/api/lol/static-data/lan/v1.2/item/"+part.getItem3()+"?api_key=5dd8e058-3f90-4f1b-9e8f-be016dba394c",null,new JsonHttpResponseHandler(){
                        String nombre_campeon;

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            String image_url ="http://ddragon.leagueoflegends.com/cdn/5.2.1/img/item/"+response.optString("id")+".png";
                            UrlImageViewHelper.setUrlDrawable(ivitem3, image_url);
                        }
                    });
                    //obteniendo el Item4
                    Conexion.get("https://global.api.pvp.net/api/lol/static-data/lan/v1.2/item/"+part.getItem4()+"?api_key=5dd8e058-3f90-4f1b-9e8f-be016dba394c",null,new JsonHttpResponseHandler(){
                        String nombre_campeon;

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            String image_url ="http://ddragon.leagueoflegends.com/cdn/5.2.1/img/item/"+response.optString("id")+".png";
                            UrlImageViewHelper.setUrlDrawable(ivitem4, image_url);
                        }
                    });
                    //obteniendo el Item5
                    Conexion.get("https://global.api.pvp.net/api/lol/static-data/lan/v1.2/item/"+part.getItem5()+"?api_key=5dd8e058-3f90-4f1b-9e8f-be016dba394c",null,new JsonHttpResponseHandler(){
                        String nombre_campeon;

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            String image_url ="http://ddragon.leagueoflegends.com/cdn/5.2.1/img/item/"+response.optString("id")+".png";
                            UrlImageViewHelper.setUrlDrawable(ivitem5, image_url);
                        }
                    });
                    //obteniendo el Item6
                    Conexion.get("https://global.api.pvp.net/api/lol/static-data/lan/v1.2/item/"+part.getItem6()+"?api_key=5dd8e058-3f90-4f1b-9e8f-be016dba394c",null,new JsonHttpResponseHandler(){
                        String nombre_campeon;

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            String image_url ="http://ddragon.leagueoflegends.com/cdn/5.2.1/img/item/"+response.optString("id")+".png";
                            UrlImageViewHelper.setUrlDrawable(ivitem6, image_url);
                        }
                    });

                    //llenando datos
                    //campeon.setText(part.getCampeon());
                    kill.setText(part.getKill());
                    assists.setText(part.getAssist());
                    dead.setText(part.getDead());
                    //Log.i("item",part.getItem1());
                    //adicionamos el layout con los datos
                    contenedor_historial.addView(historial);
                }
            }
        });
    }

    ///Metodo para transformar la cadena con el nombre del objeto quitar acetnos
    public static String stripAccents(String str) {
        if (str == null) {
            return null;
        }
        char[] array = str.toCharArray();
        for (int index = 0; index < array.length; index++) {
            int pos = ORIGINAL.indexOf(array[index]);
            if (pos > -1) {
                array[index] = REPLACEMENT.charAt(pos);
            }
        }
        return new String(array);
    }

    // Metodo quitar espacios nombres
    public int quitSpaces(String cam)
    {
        String []camp = cam.split(" ");
        cam="";
        for(int h =0; h<camp.length;h++)
        {
            cam = cam+camp[h];
        }
        cam = cam.toLowerCase();
        int id = getResources().getIdentifier("com.example.nabu.perylol.perylol_movil:drawable/" + cam, null, null);
        return id;
    }

    // Metodo quitar espacios nombres
    public String quitChar(String cam,String chara)
    {
        String[] camp = cam.split(chara);
        cam = "";
        for (int h = 0; h < camp.length; h++) {
            cam = cam + camp[h];
        }
        cam = cam.toLowerCase();
        return cam;

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
