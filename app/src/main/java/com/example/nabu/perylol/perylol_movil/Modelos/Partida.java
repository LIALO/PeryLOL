package com.example.nabu.perylol.perylol_movil.Modelos;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luiis Plata on 16/06/2015.
 */
public class Partida {

    private String campeon;
    private boolean resultado;
    private String kill;
    private String dead;
    private String assist;
    private String item1;
    private String nom_item1;
    private String item2;
    private String nom_item2;
    private String item3;
    private String nom_item3;
    private String item4;
    private String nom_item4;
    private String item5;
    private String nom_item5;
    private String item6;
    private String nom_item6;
    private String spell1;
    private String spell2;
    private List<Item> items;

    public Partida() {
        this.items = new ArrayList<Item>();
    }

    public String getCampeon() {
        return campeon;
    }

    public void setCampeon(String campeon) {
        this.campeon = campeon;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public String getKill() {
        return kill;
    }

    public void setKill(String kill) {
        this.kill = kill;
    }

    public String getDead() {
        return dead;
    }

    public void setDead(String dead) {
        this.dead = dead;
    }

    public String getAssist() {
        return assist;
    }

    public void setAssist(String assist) {
        this.assist = assist;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public String getItem6() {
        return item6;
    }

    public void setItem6(String item6) {
        this.item6 = item6;
    }

    public String getSpell1() {
        return spell1;
    }

    public void setSpell1(String spell1) {
        this.spell1 = spell1;
    }

    public String getSpell2() {
        return spell2;
    }

    public void setSpell2(String spell2) {
        this.spell2 = spell2;
    }

    public String getNom_item1() {
        return nom_item1;
    }

    public void setNom_item1(String nom_item1) {
        this.nom_item1 = nom_item1;
    }

    public String getNom_item2() {
        return nom_item2;
    }

    public void setNom_item2(String nom_item2) {
        this.nom_item2 = nom_item2;
    }

    public String getNom_item3() {
        return nom_item3;
    }

    public void setNom_item3(String nom_item3) {
        this.nom_item3 = nom_item3;
    }

    public String getNom_item4() {
        return nom_item4;
    }

    public void setNom_item4(String nom_item4) {
        this.nom_item4 = nom_item4;
    }

    public String getNom_item5() {
        return nom_item5;
    }

    public void setNom_item5(String nom_item5) {
        this.nom_item5 = nom_item5;
    }

    public String getNom_item6() {
        return nom_item6;
    }

    public void setNom_item6(String nom_item6) {
        this.nom_item6 = nom_item6;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
