/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preguntas;

import Controlador.Administrador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.JDOMException;
import org.json.simple.JSONObject;

/**
 *
 * @author benji
 */
public class ModificarPreguntaD extends HttpServlet {

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
        //Servlet que dibuja la vista para modificar preguntacon los campos pre-cargados
        try (PrintWriter out = response.getWriter()) { 
        String ruta = request.getRealPath("");
        String np = request.getParameter("nombre");
        HttpSession sesion = request.getSession();
        ServletContext context = request.getServletContext();
       //Objetos de la clase administrador
        Administrador admin = new Administrador(ruta,"preguntas.xml");
        Administrador admin2 = new Administrador(ruta,"HotObjects.xml");
       
        //Arraylist con los datos de la pregunta provenintes del archivo preguntas.xml
            ArrayList<String> datosP =admin.leerPreguntaH(ruta, np);
            //JSON con los datos de la pregunta provenientes del archivo HotObjects.xml
            JSONObject datosH =admin2.leerHotObjects(ruta, np);
            //comienza el codigo de la pagina web
            out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<title>Pregunta Hot Spot</title>");
out.println("<meta charset='UTF-8'>");
out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>");
out.println("<link href='css/bootstrap.min.css' rel='stylesheet'>");
out.println("<link href='font-awesome/css/font-awesome.css' rel='stylesheet'>");
out.println("<link href='css/plugins/iCheck/custom.css' rel='stylesheet'>");
out.println("<link href='css/animate.css' rel='stylesheet'>");
out.println("<link href='css/style.css' rel='stylesheet'>");
out.println("</head>");
out.println("<body>");
out.println("<div  class='gray-bg'>");
out.println("<div class='row border bottom'>");
out.println("<form method='post' class='form-horizontal' action='ModifHotobjectsP' enctype='multipart/form-data'>");
out.println("<div class='col-lg-12'>");
out.println("<div class='ibox float-e-margins'>");
out.println("<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hot Spot</h3>");
out.println("<div class='ibox-title'>");
out.println("<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pregunta</h4>");
out.println("<div class='ibox-tools'>");
out.println("<a class='collapse-link'>");
out.println("<i class='fa fa-chevron-up'></i>");
out.println("</a>");
out.println("</div>");
out.println("</div>");
out.println("<div class='ibox-content' style='display:none;'>");
out.println("<div id='divInputLoad'>");
out.println("<div id='divFileUpload'><form method='post' class='form-horizontal' action='ModifHotobjectsP' enctype='multipart/form-data'>");
out.println("<div class='form-group'><label class='col-sm-2 control-label'>nombre de la pregunta</label>");
out.println("<div class='col-sm-9'>");
out.println("<input hidden name='nombrepregunta' required type='text' value='"+datosP.get(3)+"' placeholder='Escribe la pregunta' class='form-control'>");
out.println("</div>");
out.println("</div>");
out.println("<div class='form-group'><label class='col-sm-2 control-label'>Pregunta</label>");
out.println("<div class='col-sm-9'><input  required value='"+datosP.get(1)+"' name='pregunta' type='text' placeholder='Escribe la pregunta' class='form-control'>");
out.println("<input type='text' value="+datosP.get(3)+" name='nombreo' id='nombreo' hidden>");
out.println("<input type='text' value='1' name='conteo' id='conteo'>");
out.println("</div>");
out.println("</div>");
out.println("<div class='wrapper wrapper-content animated fadeInRight'>");
out.println("<div class='row'>");
out.println("<div class='col-lg-12'>");
out.println("<div class='ibox float-e-margins'>");
out.println("<div class='ibox-content'>");
out.println("<div id='table'></div>");
out.println("<table class='table table-striped' id='tabms'>");
out.println("<thead>");
out.println("<tr>");
out.println("<th>Objetos Drag</th>");
out.println("<th>Objetos Drop</th>");
out.println("<th>Accion</th>");
out.println("</tr>");
out.println("</thead>");
out.println("<tbody><tr id='fila1'><td  id='ctipo1'><select name='lctipo1' id='lctipo1' onchange='Pintardrag(this.value,1);' name='tipo1' id='tipo1'><option selected value='--------'>-------</option><option value='imagen'>imagen</option></select></td><td id='dtipo1'><select name='ldtipo1' id='ldtipo1' onchange='Pintardrop(this.value,1);' name='tipo1' id='tipo1'><option selected value='--------'>-------</option><option value='imagen'>imagen</option></select></td><td><span class='input-group-addon'><button id ='button1' name='buttton1' class='btn btn-primary  btn-circle' type='button' onclick='Nuevasect(1);'><i id ='id_icono1' class='fa fa-plus'></i></button></span></td></tr>");
out.println("</tbody>");
out.println("</table>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("<div class='col-lg-12'>");
out.println("<div class='ibox float-e-margins'>");
out.println("<div class='ibox-title'>");
out.println("<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Intentos</h5>");
out.println("<div class='ibox-tools'>");
out.println("<a class='collapse-link'>");
out.println("<i class='fa fa-chevron-up'></i>");
out.println("</a>                                ");
out.println("</div>");
out.println("</div>");
out.println("<div class='ibox-content' style='display:none;'>");
out.println("<div class='form-group'><label class='col-sm-2 control-label'>Intentos</label>");
out.println("<div class='col-lg-3'>");
out.println("<input value='"+datosP.get(2)+"' required name='intentos' type='number' min='1' placeholder='Numero de intentos' class='form-control'>");
out.println("</div>");
out.println("</div>");
out.println("<div class='form-group'><label class='col-sm-2 control-label'>Frase Inicial</label>");
out.println("<div class='col-lg-9'>");
out.println("<input value='"+datosP.get(4)+"' required name='frinicial' type='text' placeholder='Frase inicial' class='form-control'>");
out.println("</div>");
out.println("</div>");
out.println("<div class='form-group'><label class='col-sm-2 control-label'>Instruccion de evaluacion</label>");
out.println("<div class='col-lg-9'>");
out.println("<input value='"+datosP.get(5)+"' required name='freval' type='text' placeholder='Escribe la instruccion de evaluacion' class='form-control'>");
out.println("</div>");
out.println("</div>");
out.println("<div class='form-group'><label class='col-sm-2 control-label'>Frase de respuesta corecta</label>");
out.println("<div class='col-lg-9'>");
out.println("<input value='"+datosP.get(6)+"' required name='frrc' type='text' placeholder='Escribe la frase de respuesta correcta' class='form-control'>");
out.println("</div>");
out.println("</div>");
out.println("<div class='form-group'><label class='col-sm-2 control-label'>Frase de respuesta incorrecta</label>");
out.println("<div class='col-lg-9'>");
out.println("<input value='"+datosP.get(7)+"' required name='frri' type='text' placeholder='Escribe la frase para respuesta inocrrecta' class='form-control'>");
out.println("</div>");
out.println("</div>");
out.println("<div class='form-group'><label class='col-sm-2 control-label'>Frase de intentos</label>");
out.println("<div class='col-lg-9'>");
out.println("<input value='"+datosP.get(8)+"' required name='frintent' type='text' placeholder='Escribe la frase para intentos' class='form-control'>");
out.println("</div>");
out.println("</div>");
out.println("<div class='form-group'>");
out.println("<div class='col-sm-4 col-sm-offset-2'>");
out.println("<button class='fa fa-save btn btn-primary center' type='submit' value='Guardar pregunta'> Guardar pregunta</button>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</form>        ");
out.println("</div>");
out.println("</div>");
out.println("<style>.center {");
out.println("display: block;");
out.println("margin-left: auto;");
out.println("margin-right: auto;");
out.println("width: 50%;");
out.println("height: 50%;");
out.println("}");
out.println(".col-lg-12{");
out.println("padding-left: 0px!important;");
out.println("padding-right: 0px !important;");
out.println("}");
out.println("</style>");
out.println("<script src='fdd.js'></script>");
out.println("<!-- Mainly scripts -->");
out.println("<script src='js/jquery-3.1.1.min.js'></script>");
out.println("<script src='js/bootstrap.min.js'></script>");
out.println("<script src='js/plugins/metisMenu/jquery.metisMenu.js'></script>");
out.println("<script src='js/plugins/slimscroll/jquery.slimscroll.min.js'></script>");
out.println("<!-- Custom and plugin javascript -->");
out.println("<script src='js/inspinia.js'></script>");
out.println("<!-- iCheck -->");
out.println("<script src='js/plugins/iCheck/icheck.min.js'></script>");
out.println("<script>");
out.println("$(document).ready(function () {");
out.println("$('.i-checks').iCheck({");
out.println("checkboxClass: 'icheckbox_square-green',");
out.println("radioClass: 'iradio_square-green',");
out.println("});");
out.println("});");
out.println("</script>");
out.println("</body>");
out.println("</html>");
            
            
            
            
    }   catch (JDOMException ex) {
            Logger.getLogger(ModificarPreguntaD.class.getName()).log(Level.SEVERE, null, ex);
        }
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
