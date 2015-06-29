package com.example.nabu.perylol.perylol_movil.Modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Luiis Plata on 11/06/2015.
 */
public class Rankerd {
    private String nombre;
    private String liga;
    private String tipo_liga;
    private String nombre_equipo;
    private String id_equipo;
    private String divicion;
    private String pl;
    private String ganadas;
    private String perdidas;
    private String inactivo;

    public Rankerd() {
    }

    public Rankerd(String nombre, String liga, String tipo_liga, String nombre_equipo, String id_equipo, String divicion, String pl, String ganadas, String perdidas, String inactivo) {
        this.nombre = nombre;
        this.liga = liga;
        this.tipo_liga = tipo_liga;
        this.nombre_equipo = nombre_equipo;
        this.id_equipo = id_equipo;
        this.divicion = divicion;
        this.pl = pl;
        this.ganadas = ganadas;
        this.perdidas = perdidas;
        this.inactivo = inactivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getTipo_liga() {
        return tipo_liga;
    }

    public void setTipo_liga(String tipo_liga) {
        this.tipo_liga = tipo_liga;
    }

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }

    public String getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(String id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getDivicion() {
        return divicion;
    }

    public void setDivicion(String divicion) {
        this.divicion = divicion;
    }

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }

    public String getGanadas() {
        return ganadas;
    }

    public void setGanadas(String ganadas) {
        this.ganadas = ganadas;
    }

    public String getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(String perdidas) {
        this.perdidas = perdidas;
    }

    public String getInactivo() {
        return inactivo;
    }

    public void setInactivo(String inactivo) {
        this.inactivo = inactivo;
    }

}
