package com.example.nabu.perylol.perylol_movil.Modelos;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luiis Plata on 10/06/2015.
 */
public class Invocador {
    private String nombre;
    private String id;
    private String icono;
    private String level;
    private List<Rankerd> rankerd;
    private List<Partida> partidas;






    public Invocador() {
        this.rankerd = new ArrayList<Rankerd>();
        this.partidas = new ArrayList<Partida>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Rankerd> getRankerd() {
        return rankerd;
    }

    public void setRankerd(List<Rankerd> rankerd) {
        this.rankerd = rankerd;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public void obtenerInvocador(JSONObject invocador){
        this.nombre = invocador.optString("name");
        this.id = invocador.optString("id");
        this.level = invocador.optString("summonerLevel");
        this.icono = invocador.optString("profileIconId");
    }

    public void optenerLiga(JSONObject jsonObject) {
            Rankerd rankerd = new Rankerd();
            //obtenemos los datos del objetoo hasta la liga
            rankerd.setNombre(jsonObject.optString("name"));
            rankerd.setLiga(jsonObject.optString("tier"));
            rankerd.setTipo_liga(jsonObject.optString("queue"));
            //ahora los detalles de la liga
            JSONArray detalles = jsonObject.optJSONArray("entries");
            for (int o = 0; o< detalles.length();o++){
                //detalles de la liga
                JSONObject jsonObject_detalle = detalles.optJSONObject(o);
                rankerd.setNombre_equipo(jsonObject_detalle.optString("playerOrTeamName"));
                rankerd.setId_equipo(jsonObject_detalle.optString("playerOrTeamId"));
                rankerd.setDivicion(jsonObject_detalle.optString("division"));
                rankerd.setPl(jsonObject_detalle.optString("leaguePoints"));
                rankerd.setGanadas(jsonObject_detalle.optString("wins"));
                rankerd.setPerdidas(jsonObject_detalle.optString("losses"));
                rankerd.setInactivo(jsonObject_detalle.optString("isInactive"));
            }
            this.rankerd.add(rankerd);
    }


    public void obtenerHistorial(JSONObject partida){
        //Log.i("partida",partida+"");
        JSONArray partida_individual = partida.optJSONArray("games");
        for (int i = 0; i < partida_individual.length();i++){

            Partida partida_obj = new Partida();

            JSONObject partida_unica = partida_individual.optJSONObject(i);
            JSONObject estadisticas = partida_unica.optJSONObject("stats");
            //String campeon_nombre = campeon(partida_unica.optString("championId"));
            partida_obj.setAssist(estadisticas.optString("assists"));
            partida_obj.setCampeon(partida_unica.optString("championId"));
            partida_obj.setDead(estadisticas.optString("numDeaths"));
            partida_obj.setItem1(estadisticas.optString("item0"));

            partida_obj.setItem2(estadisticas.optString("item1"));

            partida_obj.setItem3(estadisticas.optString("item2"));

            partida_obj.setItem4(estadisticas.optString("item3"));

            partida_obj.setItem5(estadisticas.optString("item4"));

            partida_obj.setItem6(estadisticas.optString("item5"));

            partida_obj.setKill(estadisticas.optString("championsKilled"));
            partida_obj.setResultado(estadisticas.optBoolean("win"));
            partida_obj.setSpell1(partida_unica.optString("spell1"));
            partida_obj.setSpell2(partida_unica.optString("spell2"));

            this.partidas.add(partida_obj);
            //Log.i("datos",estadisticas+"");
        }
    }



}
