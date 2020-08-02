/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preguntas;
import Controlador.Administrador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.JDOMException;

/**
 *
 * @author benji
 */
public class EliminarPregunta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //servlet que realizara la eliminacion de la pregunta seleccionada
        String ruta = request.getRealPath("");
        //obtiene los valores de los campos ocultos del servlet Inicial para asi hacer el borrado.
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        //objeto de la clase administrador
        Administrador admin = new Administrador(ruta, nombre);
        try {
            if(tipo.equals("hotspot")){
                //accedemos al metodo de elimnacion para las preguntas hotspot
           admin.eliminaHotspot(ruta, nombre);
        }else{
                //accedemos al metodo de elimnacion para las preguntas hotobjects
                admin.eliminaHotobjects(ruta, nombre);
                }
            }catch (JDOMException ex) {
                System.out.println(ex.getMessage());
            }
        
        
        response.sendRedirect("Inicial");
        }
        
        
    }

