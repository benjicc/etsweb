/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import org.json.simple.JSONObject;

/**
 *
 * @author benji
 */
public class Secciones {
    private JSONObject secciones = new JSONObject();

    public Secciones(JSONObject secciones) {
        this.secciones = secciones;
    }

    public JSONObject getSecciones() {
        return secciones;
    }

    public void setSecciones(JSONObject secciones) {
        this.secciones = secciones;
    }
}
