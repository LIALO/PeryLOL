package com.example.nabu.perylol.perylol_movil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nabu.perylol.perylol_movil.Modelos.Conexion;
import com.example.nabu.perylol.perylol_movil.Modelos.Invocador;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class perfilInvocador extends ActionBarActivity {
   boolean tiene_divicion = false;
    ImageView  ivd;
    /*
    Inflando el layout y colocando layout dinamicos
     */
    ViewGroup contenedor_rankers;

    TextView perfil_nombre;
    TextView divicion_escrita;
    TextView nivel;
    ImageView icono_invocador;
    public String nombreInvocador;
    Invocador invocador = new Invocador();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_invocador);

       ivd = (ImageView)findViewById(R.id.divicion);

        icono_invocador = (ImageView)findViewById(R.id.icono_invocador);
        icono_invocador.setVisibility(View.GONE);
        perfil_nombre = (TextView) findViewById(R.id.perfil_nombre);
        // Font path
        String fontPath = "fonts/Friz Quadrata Regular.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        // Applying font
        perfil_nombre.setTypeface(tf);
        perfil_nombre.setText(getIntent().getStringExtra("nombre"));
        //divicion_escrita = (TextView) findViewById(R.id.divicion_escrita);
        nivel = (TextView) findViewById(R.id.invocador_level);
        nombreInvocador = getIntent().getStringExtra("nombre").toLowerCase().replaceAll(" ", "");
        pop_up(nombreInvocador);

        //inflando
        contenedor_rankers = (ViewGroup) findViewById(R.id.rankers_invocador);

        Conexion.get("https://lan.api.pvp.net/api/lol/lan/v1.4/summoner/by-name/" + nombreInvocador + "?api_key=5dd8e058-3f90-4f1b-9e8f-be016dba394c", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                //Pasar los datos a un JSONObjetc para sacar despues sus datos
                JSONObject invocadorJson = response.optJSONObject(nombreInvocador);
                invocador.obtenerInvocador(invocadorJson);
                String icon = invocador.getIcono();
               // UrlImageViewHelper.setUrlDrawable(icono_invocador, "http://ddragon.leagueoflegends.com/cdn/5.2.1/img/profileicon/"+icon+".png");

                perfil_nombre.setText(invocador.getNombre());

                nivel.setText("Lv: "+ invocador.getLevel());
                //pop_up(invocador.getIcono());
                //Log.i("ID ", invocador.getId());
                Conexion.get("https://lan.api.pvp.net/api/lol/lan/v2.5/league/by-summoner/" + invocador.getId() + "/entry?api_key=5dd8e058-3f90-4f1b-9e8f-be016dba394c", null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        tiene_divicion = true;
                        // / If the response is JSONObject instead of expected JSONArray
                        JSONArray arrayLiga = response.optJSONArray(invocador.getId());
                        //Log.i("datos",arrayLiga+"");
                        for (int i = 0; i < arrayLiga.length(); i++) {
                            invocador.optenerLiga(arrayLiga.optJSONObject(i));
                            //Log.i("Ranker's",invocador.getRankerd().get(i).getLiga()+" "+invocador.getRankerd().get(i).getDivicion());
                            /*
                            Podemos hacer lo del adaptador del layout aqui mismo para que pueda verse enseguida
                             */
                            LayoutInflater gordito = LayoutInflater.from(perfilInvocador.this);
                            LinearLayout pantalla_ranker = (LinearLayout) gordito.inflate(R.layout.ranker, null, false);
                            //Aqui ya tenemos que crear todos los wigyet que vallamos a usar para cambiarlos
                            TextView tipo_ranker = (TextView) pantalla_ranker.findViewById(R.id.tipo_ranker);
                            TextView nombre_equipo = (TextView) pantalla_ranker.findViewById(R.id.nombre_equipo);
                            //TextView nombre_liga = (TextView) pantalla_ranker.findViewById(R.id.nombre_liga);
                            TextView liga_divicion = (TextView) pantalla_ranker.findViewById(R.id.liga_divicion);
                            TextView ganadas = (TextView) pantalla_ranker.findViewById(R.id.ganadas);
                            TextView perdidas = (TextView) pantalla_ranker.findViewById(R.id.perdidas);
                            TextView pl = (TextView) pantalla_ranker.findViewById(R.id.pl);
                            ImageView div_ranked = (ImageView)pantalla_ranker.findViewById(R.id.iv_ranked_div);
                                                        //ahora llenamos los datos
                            if(i == 0)
                            {
                                String division = invocador.getRankerd().get(i).getLiga().toLowerCase();
                                int id = getResources().getIdentifier("com.example.nabu.perylol.perylol_movil:drawable/"+division, null, null);
                                ivd.setImageResource(id);
                            }

                            String tipo_ranked = ReplaceChar(invocador.getRankerd().get(i).getTipo_liga(),"_"," ");
                            tipo_ranker.setText(tipo_ranked);
                            setImagen(div_ranked,invocador.getRankerd().get(i).getLiga() + "_" + invocador.getRankerd().get(i).getDivicion());
                            nombre_equipo.setText(invocador.getRankerd().get(i).getNombre_equipo());
                            //nombre_liga.setText(invocador.getRankerd().get(i).getLiga());
                            liga_divicion.setText(invocador.getRankerd().get(i).getLiga() + " " + invocador.getRankerd().get(i).getDivicion());
                            ganadas.setText(invocador.getRankerd().get(i).getGanadas());
                            perdidas.setText(invocador.getRankerd().get(i).getPerdidas());
                            pl.setText(invocador.getRankerd().get(i).getPl());
                            //probamos
                            Log.i("contenido ", invocador.getRankerd().get(i).getGanadas());
                            //agregando
                            contenedor_rankers.addView(pantalla_ranker);
                        }

                        //invocador.obtenerLiga(arrayLiga);
                        //invocador.obtenerLiga(response);
                            /*
                            for (int i = 0; i < arrayLiga.length(); i++){
                                JSONObject ligaJSONObject = arrayLiga.optJSONObject(i);
                                Log.i("Objeto ",ligaJSONObject+"");

                                invocador.setNombre_liga(ligaJSONObject.optString("name"));
                                invocador.setLiga(ligaJSONObject.optString("tier"));
                                invocador.setTipo_liga(ligaJSONObject.optString("queue"));
                                //ahora recorremos datos mas especificos
                                JSONArray detalleLiga =ligaJSONObject.optJSONArray("entries");
                                for (int o = 0; o < detalleLiga.length();o++){
                                    JSONObject detalleLigaActual = detalleLiga.getJSONObject(o);
                                    Log.i("Objeto ",detalleLigaActual+"");
                                    invocador.setDivicion(detalleLigaActual.optString("division"));
                                    invocador.setPl(detalleLigaActual.optString("leaguePoints"));
                                    invocador.setGanadas(detalleLigaActual.optString("wins"));
                                    invocador.setPerdidas(detalleLigaActual.optString("losses"));
                                }
                            }
                            */

                        pop_up("Colocando divicion");
                        //divicion_escrita.setText(invocador.getLiga()+" "+invocador.getDivicion());
                    }
                });
                if(tiene_divicion==false)
                {

                    int id = getResources().getIdentifier("com.example.nabu.perylol.perylol_movil:drawable/provisional", null, null);
                    ivd.setImageResource(id);
                }
            }
        });

    }

    // Metodo quitar espacios nombres
    public String ReplaceChar(String cam,String chara,String replace)
    {
        String[] camp = cam.split(chara);
        cam = "";
        for (int h = 0; h < camp.length-1; h++) {
            cam = cam +replace+ camp[h];
        }

        return cam;

    }
    public void setImagen(ImageView imagen,String division)
    {
        division =  division.toLowerCase();
        int id = getResources().getIdentifier("com.example.nabu.perylol.perylol_movil:drawable/"+division, null, null);
        imagen.setImageResource(id);
    }

public void addRanker(){

}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perfil_invocador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void pop_up(String mensaje){
        Context context = getApplicationContext();
        CharSequence text = mensaje;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void historial(View view){
        Intent historial = new Intent(this, Historial.class);
        historial.putExtra("invocador_id",invocador.getId());
        startActivity(historial);
    }

}
