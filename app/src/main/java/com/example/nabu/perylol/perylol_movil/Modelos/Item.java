package com.example.nabu.perylol.perylol_movil.Modelos;

/**
 * Created by Luiis Plata on 16/06/2015.
 */
public class Item {
    private String id;
    private String name;
    private String descripcion;
    private String plainText;

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }
}
