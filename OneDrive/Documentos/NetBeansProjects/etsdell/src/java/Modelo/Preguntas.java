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
public class Preguntas {
    private String nombre;
    private String pregunta;
    private String tipo;
    private String contador;
    private String intentos;
    private String f_inicial;
    private String f_eval;
    private String f_respc;
    private String f_respi;
    private String f_intentos;

    public Preguntas(String nombre, String pregunta, String tipo, String contador, String intentos, String f_inicial, String f_eval, String f_respc, String f_respi, String f_intentos) {
        this.nombre = nombre;
        this.pregunta = pregunta;
        this.tipo = tipo;
        this.contador = contador;
        this.intentos = intentos;
        this.f_inicial = f_inicial;
        this.f_eval = f_eval;
        this.f_respc = f_respc;
        this.f_respi = f_respi;
        this.f_intentos = f_intentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public String getIntentos() {
        return intentos;
    }

    public void setIntentos(String intentos) {
        this.intentos = intentos;
    }

    public String getF_inicial() {
        return f_inicial;
    }

    public void setF_inicial(String f_inicial) {
        this.f_inicial = f_inicial;
    }

    public String getF_eval() {
        return f_eval;
    }

    public void setF_eval(String f_eval) {
        this.f_eval = f_eval;
    }

    public String getF_respc() {
        return f_respc;
    }

    public void setF_respc(String f_respc) {
        this.f_respc = f_respc;
    }

    public String getF_respi() {
        return f_respi;
    }

    public void setF_respi(String f_respi) {
        this.f_respi = f_respi;
    }

    public String getF_intentos() {
        return f_intentos;
    }

    public void setF_intentos(String f_intentos) {
        this.f_intentos = f_intentos;
    }


    
    
}
