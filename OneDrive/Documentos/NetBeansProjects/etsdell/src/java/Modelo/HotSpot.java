/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author benji
 */
public class HotSpot extends Preguntas {
    private String imagen;
    private String secciones;

    public HotSpot(String imagen, String secciones, String nombre, String pregunta, String tipo, String conteo, String intentos, String f_inicial, String f_eval, String f_respc, String f_respi, String f_intentos) {
        super(nombre, pregunta, tipo, conteo, intentos,f_inicial,f_eval, f_respc, f_respi,f_intentos);
        this.imagen = imagen;
        this.secciones = secciones;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getSecciones() {
        return secciones;
    }

    public void setSecciones(String secciones) {
        this.secciones = secciones;
    }
    
    
}
