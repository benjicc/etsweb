/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preguntas;

import Controlador.Administrador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static javax.swing.UIManager.get;

/**
 *
 * @author benji
 */
public class Inicial extends HttpServlet {

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
        /*Servlet que nos va a presentar la vista inicial del proyecto accedemos a las clases y libreriras necesarias para
        poder obtener los datos que se mostraran en la tabla de manera dinamica.*/
        
        
        response.setContentType("text/html;charset=UTF-8");
        
        String ruta = request.getRealPath("");
        
        HttpSession sesion = request.getSession();
        ServletContext context = request.getServletContext();
        //Objeto de la clase Administrador, clase donde estan los codigos para la gestion de los archivos XML
        Administrador admin = new Administrador(ruta,"preguntas.xml");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //Comienza la escritura del codigo para la pagina web
            out.println("<!DOCTYPE html>");
out.println("<html>");

out.println("<head>");

out.println("    <meta charset='utf-8'>");
out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");

out.println("    <title>Proyecto ETS</title>");

out.println("    <link href='css/bootstrap.min.css' rel='stylesheet'>");
out.println("    <link href='font-awesome/css/font-awesome.css' rel='stylesheet'>");
out.println("    <link href='css/plugins/iCheck/custom.css' rel='stylesheet'>");
out.println("    <link href='css/animate.css' rel='stylesheet'>");
out.println("    <link href='css/style.css' rel='stylesheet'>");

out.println("</head>");

out.println("<body>");

out.println("    <div id='wrapper'>");
out.println("        <div class='row wrapper border-bottom white-bg page-heading'>");
out.println("                <div class='col-lg-10 center'>");
out.println("                    <h2>Creacion de Preguntas</h2>");
out.println("                    <ol class='breadcrumb'>");
                        
out.println("                        <li>");
out.println("                            <a href='preguntanueva.html'>Crear Nueva Pregunta</a>");
out.println("                        </li>");
                        
out.println("                    </ol>");
out.println("                </div>");
out.println("                <div class='col-lg-2'>");

out.println("                </div>");
out.println("            ");
out.println("        </div>");
out.println("        <div class='wrapper wrapper-content animated fadeInRight'>");
out.println("            <div class='row'>");
out.println("                <div class='col-lg-12'>");
                    
out.println("                    <div class='ibox-content'>");

out.println("                        <table class='table'>");
out.println("                            <thead>");
out.println("                            <tr>");
out.println("                                <th>#</th>");
out.println("                                <th>Nombre de la Pregunta</th>");
out.println("                                <th>Tipo Pregunta</th>");
out.println("                                <th>Acciones</th>");
out.println("                            </tr>");
out.println("                            </thead>");
out.println("                            <tbody>");
int i;
//Con el objeto de la clase administrador accedemos al metodo obtenerTablaPreguntas, elcual nos devolvera un arraylis
//el cual vamos a poder ir recorriendo para obtener las preguntas guardadas y acceder a ellas
int j = admin.obtenerTablaPreguntas(ruta).size();
//condicion para mostrar tabla vacia
if(j !=0){
//ciclo for uqe dibuja una tabla dinamica
for(i=0;i<j;i++){
    /*Dentro de este ciclo vamos a ir mostrando en la tabla las preguntas guardadasa asi
    mismo se pondran formularios con campos ocultos para las acciones de cada pregunta. Un formulario por cada accion
    y asi acceder a cierto Servlet donde se mostrara la siguiente vista de la accion elegida.*/
    int z = 1 + i;
    out.println("                            <tr>");
    out.println("                                <td>"+z+"</td>");
    out.println("                                <td>"+admin.obtenerTablaPreguntas(ruta).get(i).nombre+ "</td>");
    out.println("                                <td>"+admin.obtenerTablaPreguntas(ruta).get(i).tipo+ "</td>");
    out.println("                                <td>");
    String b = admin.obtenerTablaPreguntas(ruta).get(i).tipo;
    String a = "hotspot";
    if(a.equals(b)){
    out.println("                                    <form method='post' action='VerPregunta'><input name='nombre' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).nombre+"' hidden><input name='tipo' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).tipo+"' hidden><button type='submit' class='btn btn-w-m btn-warning col-sm-4'>Ver Pregunta</button></form>");
    out.println("                                    <form method='post' action='ModificarPregunta'><input name='nombre' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).nombre+"' hidden><input name='tipo' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).tipo+"' hidden><button type='submit' class='btn btn-w-m btn-primary col-sm-4'>Modificar Pregunta</button></form>");
    out.println("                                    <form method='post' action='EliminarPregunta'><input name='nombre' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).nombre+"' hidden><input name='tipo' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).tipo+"' hidden><button type='submit' class='btn btn-w-m btn-success col-sm-4'>Eliminar Pregunta</button></form>");
    out.println("                                </td>");
    }else{
    out.println("                                    <form method='post' action='VerPreguntaD'><input name='nombre' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).nombre+"' hidden><input name='tipo' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).tipo+"' hidden><button type='submit' class='btn btn-w-m btn-warning col-sm-4'>Ver Pregunta</button></form>");
    out.println("                                    <form method='post' action='ModificarPreguntaD'><input name='nombre' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).nombre+"' hidden><input name='tipo' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).tipo+"' hidden><button type='submit' class='btn btn-w-m btn-primary col-sm-4'>Modificar Pregunta</button></form>");
    out.println("                                    <form method='post' action='EliminarPregunta'><input name='nombre' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).nombre+"' hidden><input name='tipo' type='text' value='"+admin.obtenerTablaPreguntas(ruta).get(i).tipo+"' hidden><button type='submit' class='btn btn-w-m btn-success col-sm-4'>Eliminar Pregunta</button></form>");
    }
    out.println("                            </tr>");
}
//Si no hay ningna pregunta guardada se muestra 'NO HAY PREGUNTAS' EN LA TABLA
}else{
    out.println("                                <td><h4>NO HAY PREGUNTAS</h4></td>");
}
out.println("                            </tbody>");
out.println("                        </table>");
out.println("");
out.println("                    </div>");
out.println("                </div>");
out.println("            </div>");
out.println("        </div>");
out.println("    </div>");
                    
out.println("    <!-- Mainly scripts -->");
out.println("    <script src='js/jquery-3.1.1.min.js'></script>");
out.println("    <script src='js/bootstrap.min.js'></script>");
out.println("    <script src='js/plugins/metisMenu/jquery.metisMenu.js'></script>");
out.println("    <script src='js/plugins/slimscroll/jquery.slimscroll.min.js'></script>");

out.println("    <!-- Peity -->");
out.println("    <script src='js/plugins/peity/jquery.peity.min.js'></script>");

out.println("    <!-- Custom and plugin javascript -->");
out.println("    <script src='js/inspinia.js'></script>");
out.println("    <script src='js/plugins/pace/pace.min.js'></script>");

out.println("    <!-- iCheck -->");
out.println("    <script src='js/plugins/iCheck/icheck.min.js'></script>");

out.println("    <!-- Peity -->");
out.println("    <script src='js/demo/peity-demo.js'></script>");

out.println("    <script>");
out.println("        $(document).ready(function(){");
out.println("            $('.i-checks').iCheck({");
out.println("                checkboxClass: 'icheckbox_square-green',");
out.println("                radioClass: 'iradio_square-green',");
out.println("            });");
out.println("        });");
out.println("    </script>");

out.println("</body>");

out.println("</html>");
        }
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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
