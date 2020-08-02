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
public class ModificarPregunta extends HttpServlet {

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
        /*Servlet que muestra la vista de Modificar Pregunta HotSpot con los formularios ya pre-cargados en
        los archivos preguntas.xml y hotspot.xml*/
        processRequest(request, response);
         try (PrintWriter out = response.getWriter()) { 
        String ruta = request.getRealPath("");
        String np = request.getParameter("nombre");
        HttpSession sesion = request.getSession();
        ServletContext context = request.getServletContext();
       //Objeto de la clase admin para mostrar los  datos de la pregunta en la vista
        Administrador admin = new Administrador(ruta,"preguntas.xml");
        Administrador admin2 = new Administrador(ruta,"hotspot.xml");
       
        //arraylist que contendra los datos del archivo preguntas.xml
            ArrayList<String> datosP =admin.leerPregunta(ruta, np);
        //JSON que obtendra los datos de la pregunta del archivo hotspot.xml
            JSONObject datosH =admin2.leerHotSpot(ruta, np);
            //codigo de la pagina web
        out.println("<html>");
out.println("    <head>");
out.println("        <title>Pregunta Hot Spot</title>");
out.println("        <meta charset='UTF-8'>");
out.println("        <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
out.println("        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>");
out.println("        <link href='css/bootstrap.min.css' rel='stylesheet'>");
out.println("        <link href='font-awesome/css/font-awesome.css' rel='stylesheet'>");
out.println("        <link href='css/plugins/iCheck/custom.css' rel='stylesheet'>");
out.println("        <link href='css/animate.css' rel='stylesheet'>");
out.println("        <link href='css/style.css' rel='stylesheet'>");
out.println("    </head>");
out.println("    <body>        ");
out.println("            <div  class='gray-bg'>");
out.println("                <div class='row border bottom'>");
out.println("                    <form method='post' class='form-horizontal' action='ModifHotspotP' enctype='multipart/form-data'>");
out.println("                        <div class='col-lg-12'>");
out.println("                            <div class='ibox float-e-margins'>                                ");
out.println("<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hot Spot</h3>");                               
out.println("<div class='ibox-title'>");
out.println("<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pregunta</h4>");
out.println("<div class='ibox-tools'>");
out.println("<a class='collapse-link'>");
out.println("<i class='fa fa-chevron-up'></i>");
out.println("</a>");
out.println("</div>");
out.println("</div>                                ");
out.println("<div class='ibox-content' style='display:none;'>                                    ");
out.println("<div id='divInputLoad'>");
out.println("<div id='divFileUpload'><form method='post' class='form-horizontal' action='ModifHotspotP' enctype='multipart/form-data'>");
out.println("<div class='form-group'>");
out.println("<div class='col-sm-9'>");
out.println("<input hidden name='nombrepregunta' type='text' required value='"+datosP.get(3)+"' placeholder='Escribe la pregunta' class='form-control'>");
out.println("</div>");
out.println("</div>");
int f = Integer.parseInt((String) datosH.get("3"));
out.println("<div class='form-group'><label class='col-sm-2 control-label'>Pregunta</label>");
out.println("<div class='col-sm-9'><input  required value='"+datosP.get(1)+"' name='pregunta' type='text' placeholder='Escribe la pregunta' class='form-control'>");
out.println("<input type='text' value="+String.valueOf(f)+" name='conteo' id='conteo' >");
out.println("<input type='text' value="+datosP.get(3)+" name='nombreo' id='nombreo' hidden>");
out.println("<input type='text' name='path' id='path' hidden>");
out.println("</div>");
out.println("</div>");
out.println("<div class='form-group'><label class='col-sm-2 control-label'>Selecciona una imagen:</label>");
String img = (String) datosH.get("1");
String subimgs=img.substring(ruta.length()+1,img.length());
out.println("<div class='col-sm-10'><input value="+subimgs+" name='imagen' id='file-upload' type='file' accept='image/*' />");
out.println("</div>");
String subimg=img.substring(ruta.length()+1,img.length());
out.println("<img src="+subimg+" name='image' id='file-preview' ismap usemap='#image-map' class='center centers' >");                                                    
out.println("</div>");
out.println("<div id='file-preview-zone'>");
out.println("</div>");
out.println("<div id='mapsect'>");
out.println("<div class='wrapper wrapper-content animated fadeInRight'>");
out.println("<div class='row'>");
out.println("<div class='col-lg-12'>");
out.println("<div class='ibox float-e-margins'>");
out.println("<div class='ibox-content'>");
out.println("<div id='table'></div>");
out.println("<table class='table table-striped' id='tabms'>");
out.println("<thead>");
out.println("<tr>");
out.println("<th>Activo</th>");
out.println("<th>Tipo</th>");
out.println("<th>Tipo Opcion</th>");
out.println("<th>Title</th>");
out.println("<th>Coordenadas</th>");
out.println("<th>Accion</th>");
out.println("</tr>");
out.println("</thead>");
out.println("<tbody>");

String r =(String) datosH.get("3");
for(int g= 1;g<=f;g++){
out.println("<tr id='fila"+g+"'><td><input type='radio' value="+g+" checked id='activo"+g+"' name='activo'></td><td><select name='tipo"+g+"' id='tipo"+g+"'>");
if(datosH.get("tipo"+String.valueOf(g)).equals("rect")){
out.println("<option value='circle'>Circulo</option><option selected value='rect'>Rectangulo</option>");
}else{
out.println("<option selected value='circle'>Circulo</option><option value='rect'>Rectangulo</option>");    
}
out.println("</select></td><td><select name='link"+g+"' id='link"+g+"'>");
if(datosH.get("link"+String.valueOf(g)).equals("correcto")){
out.println("<option selected value='correcto'>Correcta</option><option value='incorrecto'>Incorrecta</option>");
}else{
out.println("<option value='correcto'>Correcta</option><option selected value='incorrecto'>Incorrecta</option>");
}
out.println("</select></td><td><input id='title"+g+"' value='"+datosH.get("title"+String.valueOf(g))+"' type='text' class='form-control' name='title"+g+"'></td><td><input type='text' id='coordenadas"+g+"' value='"+datosH.get("coords"+String.valueOf(g))+"' class='form-control' name='coordenadas"+g+"'></td>");
if(g<f){
out.println("<td><span class='input-group-addon'><button id ='button"+g+"' name='buttton"+g+"' class='btn btn-danger  btn-circle' type='button' onclick='EliminarSect("+g+");'><i id ='id_icono"+g+"' class='fa fa-trash'></i></button></span></td></tr>");
}else if(g==f){
out.println("<td><span class='input-group-addon'><button id ='button"+g+"' name='buttton"+g+"' class='btn btn-primary  btn-circle' type='button' onclick='Nuevasect("+g+");'><i id ='id_icono"+g+"' class='fa fa-plus'></i></button></span></td></tr>");
}}
out.println("</tbody>");
out.println("</table>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>                                    ");
out.println("</div>                    ");
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
out.println("</form>        ");
out.println("</div>");
out.println("</div>");
out.println("</body>");
out.println("<style>.centers {");
out.println("display: block;");
out.println("margin-left: auto;");
out.println("margin-right: auto;");
out.println("width: 50%;");
out.println("height: 100%;");
out.println("}");
out.println(".col-lg-12{");
out.println("padding-left: 0px!important;");
out.println("padding-right: 0px !important;");
out.println("}");
out.println("</style>");
out.println("<!-- Mainly scripts -->");
out.println("<script src='js/jquery-3.1.1.min.js'>var conteo= "+datosH.get("3")+"</script>");
out.println("<script src='js/bootstrap.min.js'></script>");
out.println("<script src='js/functs.js'></script>");
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
out.println("</html>");
        
    }   catch (JDOMException ex) {
            Logger.getLogger(ModificarPregunta.class.getName()).log(Level.SEVERE, null, ex);
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
