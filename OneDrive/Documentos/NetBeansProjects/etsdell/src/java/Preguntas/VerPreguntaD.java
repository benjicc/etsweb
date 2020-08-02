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
public class VerPreguntaD extends HttpServlet {

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
        //Servlet con la vista Ver Pregunta HotObjects
        processRequest(request, response);
        String ruta = request.getRealPath("");
        String np = request.getParameter("nombre");
        HttpSession sesion = request.getSession();
        ServletContext context = request.getServletContext();
        //Obtenemos la informacion de la pregunta de los archivos preguntas.xml y HotObjects.xml
        Administrador admin = new Administrador(ruta,"preguntas.xml");
        Administrador admin2 = new Administrador(ruta,"HotObjects.xml");
        
        try (PrintWriter out = response.getWriter()) {
            //Arraylist con los datos de la pregunta del archivo preguntas.xml
            ArrayList<String> datosP =admin.leerPreguntaH(ruta, np);
            //JSON con los datos de la pregunta del archivo HotObjects.xml
            JSONObject datosH =admin2.leerHotObjects(ruta, np);
            //empieza a dibujar la pagina web
out.println("<!DOCTYPE html>");
out.println("<html lang='en'>");
out.println("<head>");
out.println("    <meta charset='UTF-8'>");
out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
out.println("    <meta http-equiv='X-UA-Compatible' content='ie=edge'>");
out.println("    <title>Pregunta HotObjects/"+datosP.get(1)+"</title>");
out.println("        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>");
out.println("        <link href='css/bootstrap.min.css' rel='stylesheet'>");
out.println("        <link href='font-awesome/css/font-awesome.css' rel='stylesheet'>");
out.println("        <link href='css/plugins/iCheck/custom.css' rel='stylesheet'>");
out.println("        <link href='css/animate.css' rel='stylesheet'>");
out.println("       <link href='css/style.css' rel='stylesheet'>");
out.println("    <link rel='stylesheet' href='main.css' />");
out.println("</head>");
out.println("<body>");
out.println("    <div class='app'>");
out.println("        <header>");
out.println("            <p><h1>Pregunta HotObjects: "+datosP.get(3)+"</h1></p>");
out.println("        </header><br><h3 class='text-center'>"+datosP.get(1)+"</h3>");
out.println("        <div class='lists gray-bg'>");
out.println("            <div class='list col-sm-6'>");
int n = Integer.parseInt(datosP.get(0));
//for con las opciones de drag definidas al guardar la imagen
for(int e=1;e<=n;e++){
    String drag = (String) datosH.get("drag"+String.valueOf(e));
    String dragt = (String) datosH.get("drag"+String.valueOf(e));
    try{
        String subimg=drag.substring(ruta.length()+1,drag.length());
    
    if(drag.startsWith("C:")){

        out.println("<div class='list-item col-sm-12' id='drag"+String.valueOf(e)+"' ondragstart='drag(event)' draggable='true'>");
        out.println("<img id='drop"+e+"' name='"+e+"' src='"+subimg+"' width='150' height='150'>");
        out.println("</div>");
        
    }else{
    out.println("<div class='list-item col-sm-6' id='drag"+String.valueOf(e)+"' ondragstart='drag(event)' draggable='true'><p id=drop"+String.valueOf(e)+">"+dragt+"</div>");
    }
}catch(Exception ex){
}

}for(int e=1;e<=n;e++){
    String drag = (String) datosH.get("drag"+String.valueOf(e));
    String dragt = (String) datosH.get("drag"+String.valueOf(e));
    try{
    
    if(drag.startsWith("C:")){
        System.out.println("eeeeeee");
        
    }else{
    out.println("<div class='list-item col-sm-6' id='drag"+String.valueOf(e)+"' ondragstart='drag(event)' draggable='true'><p id=drop"+String.valueOf(e)+">"+dragt+"</p></div>");
    }
}catch(Exception ex){
        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeefefefefwefwefwef");
}

}
out.println("            </div>");
out.println("                    <div class='list' id='div1' ondrop='drop(event);' ondragover='allowDrop(event);'>");
out.println("                        <table class='table' id='tabladrop'>");
int nr = Integer.parseInt(datosP.get(0));
//for que dibuja una tabla dinamica, hay una fila por cada opcion
for(int e=nr;e>=1;e--){
    try{
        String drop = (String) datosH.get("drop"+String.valueOf(e));
    
    String subimg=drop.substring(ruta.length()+1,drop.length());
    if(drop.startsWith("C:")){
out.println("                            <tr id="+String.valueOf(e)+">");
out.println("                                <td>");
out.println("                                    <div class='list-item col-sm-12' >");
out.println("                                        <img  name ='"+e+"'id='drop"+String.valueOf(e)+"' src='"+subimg+"' width='150' height='150'>");
out.println("                                    </div>");
out.println("                                </td>");
out.println("                                <td>");
out.println("                                    <nav class='list-item col-sm-12' id='rdrop"+String.valueOf(e)+"'>");
out.println("                                        respuesta");
out.println("                                    </nav>");
out.println("                                </td>");
out.println("                            </tr>");
   
    }}
    catch(Exception eq){
            System.out.println("dddddddddddddd");
            }
}

for(int e=1;e<=nr;e++){
    String drop = (String) datosH.get("drop"+String.valueOf(e));
    
    if(drop.startsWith("C:")){
}else{
out.println("                           <tr>");
out.println("                                <td>");
out.println("                                    <nav class='list-item col-sm-12' id='rdrop"+String.valueOf(e)+"'>");
out.println("                                        <p id='drop"+String.valueOf(e)+"'>"+drop+"</p>");
out.println("                                    </nav>");
out.println("                                </td>");
out.println("                                <td>");
out.println("                                    <nav class='list-item col-sm-12' id='rdrop"+String.valueOf(e)+"'>");
out.println("                                        respuesta");
out.println("                                    </nav>");
out.println("                                </td>");
out.println("                            </tr>");   
    }
}

out.println("                        </table>");
out.println("                    </div>");
out.println("        </div>");
out.println("    </div>");
out.println("<div class='hr-line-dashed'></div>");
out.println("                                <div class='form-group'>");
out.println("                                    <div class='col-sm-4 col-sm-offset-2'>");
out.println("<button class='btn btn-primary' type='button' onclick='Califica();'>Califica</button>");
out.println("                                    </div>");
out.println("                                </div>");
out.println("<form action='GuardaHS' method='post'>");

out.println("<input type='text' value='"+datosP.get(3)+"'id='ombrep' name='nombrep' hidden>");
out.println("<input type='text' id='respuesta' name='respuesta' hidden>");
out.println("<input type='text' id='intentos' name='intentos' hidden>");
out.println("<div class='form-group'><div class='col-sm-4 col-sm-offset-2'><input type='submit' class='btn btn-primary' value='Enviar respuesta'></div></div>");
out.println("</form>");
out.println("    </div>");
out.println("    <style>");
out.println("        .lists .list{");
out.println("            max-width: 100% !important;");
out.println("        }");
out.println("    </style>");
//Funcion en JS que muestra la claificacion, el mensaje correspondiente y llena los cmapos del formulario oculto
out.println("    <script>");
out.println("function Califica(){");
out.println("respfield = document.getElementById('respuesta');");
out.println("intfield = document.getElementById('intentos');");
out.println("            opc = "+datosH.get("3")+";");
out.println("            cal = 0;");
out.println("            var tables = document.getElementById('tabladrop');");
out.println("            for(w=1;w<=opc;w++){");
out.println("                inc = 'drop'+w.toString();");
out.println("                resp = dictdr['drop'+w.toString()];");
out.println("                if(inc == resp){");
out.println("                    cal++;");
out.println("                }");
out.println("            }");
out.println("            console.log(cal)");
out.println("        evaluation(opc,cal);");
out.println("        }");
out.println("    r = "+datosP.get(2)+";");
out.println("    console.log(r);");
out.println("    function evaluation(opc,cal){");
out.println("t=opc;");
out.println("    console.log(r);");
out.println("        if(r != 0){");
out.println("        if(opc == cal){");
out.println("            alert(cal+' Respuestas correctas, "+datosP.get(6)+"');");
out.println("respfield.value=cal");
out.println("            r = r-1;"); 
out.println("        }else if(cal<opc){");
out.println("            alert(cal+' Respuestas correctas, "+datosP.get(7)+"');");
out.println("respfield.value=cal");
out.println("            r = r-1;"); 
out.println("    }");
out.println("        }else{");
out.println("        alert('"+datosP.get(8)+"');");
out.println("    }");
out.println("    console.log(r);");
out.println("intfield.value=r");
out.println("}");    
out.println("</script>");

out.println("    <script src='cal.js'></script>");
out.println("<script>");
out.println("</body>");
out.println("</html>");
            } catch (JDOMException ex) {
            Logger.getLogger(VerPregunta.class.getName()).log(Level.SEVERE, null, ex);
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
