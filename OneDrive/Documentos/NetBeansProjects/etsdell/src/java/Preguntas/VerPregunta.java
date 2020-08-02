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
public class VerPregunta extends HttpServlet {

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
        /*Servlet que no mostrara la vista donde el usuario podra resolver la pregunta HotSpot segun lo almacenado en el documento 
        preguntas.xml y hotspot.xml*/
        
        processRequest(request, response);
        String ruta = request.getRealPath("");
        String np = request.getParameter("nombre");
        HttpSession sesion = request.getSession();
        ServletContext context = request.getServletContext();
        //objetos de la clase administrador para obtener los datos de la pregunta a visualizar
        Administrador admin = new Administrador(ruta,"preguntas.xml");
        Administrador admin2 = new Administrador(ruta,"hotspot.xml");
    try (PrintWriter out = response.getWriter()) {
        //arrylist que contendra los datos de la pregunta quje habia en el archivo preguntas.xml
            ArrayList<String> datosP =admin.leerPregunta(ruta, np);
        //JSON que contendra los datos de la pregunta que habia en el archivo hotspot.xml
            JSONObject datosH =admin2.leerHotSpot(ruta, np);
        
            /* TODO output your page here. You may use following sample code. */
            //Comienza el codigo de la pagina web
            //Cuando aparece un +datosP.get()+ es la manera en la que estamos mostrando los datos de la pregunta en la imagen
out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("    <meta charset='utf-8'>");
out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
out.println("    <title>"+datosP.get(3)+"</title>");
out.println("    <link href='css/bootstrap.min.css' rel='stylesheet'>");
out.println("    <link href='font-awesome/css/font-awesome.css' rel='stylesheet'>");
out.println("    <link href='css/plugins/iCheck/custom.css' rel='stylesheet'>");
out.println("    <link href='css/animate.css' rel='stylesheet'>");
out.println("    <link href='css/style.css' rel='stylesheet'>");
out.println("    <link href='css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css' rel='stylesheet'>");
out.println("</head>");
out.println("<body>");   
out.println("        <div class='gray-bg'>");
out.println("        <div class=''>");       
out.println("        </div>");
out.println("            <div class='row wrapper border-bottom white-bg page-heading'>");
out.println("                <div class='col-lg-10'>");
out.println("                    <h2>"+datosP.get(3)+"</h2>");                   
out.println("                </div>");
out.println("                <div class='col-lg-2'>");
out.println("                </div>");
out.println("            </div>");
out.println("        <div class='wrapper wrapper-content animated fadeInRight'>");            
out.println("            <div class='row'>");
out.println("                <div class='col-lg-12'>");
out.println("                    <div class='ibox float-e-margins'>");
out.println("                        <div class='ibox-content'>");
out.println("                                <p>"+datosP.get(4)+"</p>");                                
out.println("                                <div class='hr-line-dashed'></div>");
out.println("                                <div class='form-group'><label class='col-sm-2 control-label'>"+datosP.get(1)+"</label>");

int num = Integer.parseInt(datosP.get(0));
String img = (String) datosH.get("1");
//variable par poder mostrar la imagen en la pagina web
String subimg=img.substring(ruta.length()+1,img.length());
out.println("                <img src="+subimg+" name='image' id='file-preview' ismap usemap='#image-map' class='center' >");
out.println("                                                    <map name='image-map'>");
//ciclo for en el que iremos agregando las secciones de la imagen definidas en la pregunta
for(int d = 1;d<=num;d++){
String a = "link"+String.valueOf(d);
String b = "title"+String.valueOf(d);
String c = "coords"+String.valueOf(d);
out.println("<area onclick='Evaluation(`"+datosH.get(a)+"`);' alt='sdsd' title='"+datosH.get(b)+"'  coords='"+datosH.get(c)+"' shape='rect'>");
}
out.println("                                                    </map>");
out.println("                     </div>");
out.println("<form action='GuardaHS' method='post'>");

out.println("<input type='text' value='"+datosP.get(3)+"'id='ombrep' name='nombrep' hidden>");
out.println("<input type='text' id='respuesta' name='respuesta' hidden>");
out.println("<input type='text' id='intentos' name='intentos' hidden>");
out.println("<div class='form-group'><div class='col-sm-4 col-sm-offset-2'><input type='submit' class='btn btn-primary' value='Enviar respuesta'></div></div>");
out.println("</form>");
out.println("                        </div>");

out.println("                    </div>");

out.println("                </div>");
out.println("            </div>");

out.println("        </div>");
out.println("        </div>");

out.println("</body>");
out.println("<style>");
out.println(".center {");
out.println("  display: block;");
out.println("  margin-left: auto;");
out.println("  margin-right: auto;");
out.println("  width: 50%;");
out.println("  height: 50%;");
out.println("}");
out.println("    </style>");
//Funcion JS donde se muestra la calificacion de la pregunta y rellena los campos ocultos del formulario para que se pueda hacer el
//envio de la calificacion y guardarse al darle click al boton de Enviar respuesta
out.println("<script>"); 
out.println("    r = "+datosP.get(2)+";");
out.println("    console.log(r);");
out.println("    function Evaluation(valor){");
out.println("respfield = document.getElementById('respuesta');");
out.println("intfield = document.getElementById('intentos');");
out.println("        if(r != 0){");
out.println("        if(valor == 'incorrecto'){");
out.println("            alert('"+datosP.get(7)+"');");
out.println("respfield.value='incorrecta'");
out.println("            r = r-1;");
out.println("        }else if (valor == 'correcto'){");
out.println("respfield.value='correcta'");
out.println("            alert('"+datosP.get(6)+"');");
out.println("            r = r-1;");
out.println("        }");            
out.println("        }else{");
out.println("        alert('"+datosP.get(8)+"');");
out.println("    }");
out.println("    console.log(r);");
out.println("intfield.value=r");
out.println("}");    
out.println("</script>");
out.println("    <script src='js/jquery-3.1.1.min.js'></script>");
out.println("    <script src='js/bootstrap.min.js'></script>");
out.println("    <script src='js/plugins/metisMenu/jquery.metisMenu.js'></script>");
out.println("    <script src='js/plugins/slimscroll/jquery.slimscroll.min.js'></script>");
out.println("    <!-- Custom and plugin javascript -->");
out.println("    <script src='js/inspinia.js'></script>");
out.println("    <script src='js/plugins/pace/pace.min.js'></script>");
out.println("    <!-- iCheck -->");
out.println("    <script src='js/plugins/iCheck/icheck.min.js'></script>");
out.println("        <script>");
out.println("            $(document).ready(function () {");
out.println("                $('.i-checks').iCheck({");
out.println("                    checkboxClass: 'icheckbox_square-green',");
out.println("                    radioClass: 'iradio_square-green',");
out.println("                });");
out.println("            });");
out.println("        </script>");

out.println("</html>");
        String baba = request.getParameter("tipo").toString();
if(baba.equals("hotspot")){
}
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
