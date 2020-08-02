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
public class Opciones {
    
    private JSONObject opciones = new JSONObject();
    
    public Opciones(JSONObject opciones) {
        this.opciones = opciones;
    }

    public JSONObject getOpciones() {
        return opciones;
    }

    public void setOpciones(JSONObject opciones) {
        this.opciones = opciones;
    }
    
}
