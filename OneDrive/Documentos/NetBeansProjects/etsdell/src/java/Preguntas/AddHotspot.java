/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preguntas;
import Controlador.Administrador;

import Modelo.Preguntas;
import Modelo.HotSpot;
import Modelo.Secciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.JDOMException;
import org.json.simple.JSONObject;
import java.util.*;
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;


/**
 *
 * @author benji
 */
public class AddHotspot extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Variables para hacer upload del archivo
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 5 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;
    private static final long serialVersionUID = 1L;
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "image_upload";
    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    ArrayList<String> respuestas = new ArrayList<String>();
    JSONObject obj = new JSONObject();
    JSONObject secciones = new JSONObject();
    JSONObject sects = new JSONObject();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //Servlet para guardar la pregunta hacer upolad de archivos
        // Checa si trae algun archivo para subir
        if (!ServletFileUpload.isMultipartContent(request)) {
            // Si no, lo detiene
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        // Configuracion para la ubida de archivos
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // Definimos la carpeta a donde se subiran las imagenes
        String uploadPath = getServletContext().getRealPath("/imagenes/");

        // Si no existe la carpeta la crea
        File uploadDir = new File(uploadPath);
        int n=0;
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            @SuppressWarnings("unchecked")
            //Empieza a trabajar con los campos del formulario
            List<FileItem> formItems = upload.parseRequest(request);
            String fileName1 = "";
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    // trabaja con los campos que son de archivo
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        fileName1+=fileName;
                        String filePath = uploadPath + File.separator + fileName;
                        String imagenpath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // saves the file on disk
                        item.write(storeFile);
                        respuestas.add(imagenpath);
                        obj.put("imagen",imagenpath);
                    } else {
                            //trabaja con los campos diferentes de archivo
                            String fieldname = item.getFieldName();
                            String fieldvalue = item.getString();
                            if (fieldname.equals("nombrepregunta")) {
                                obj.put("nombrepregunta", fieldvalue);
                            } else if (fieldname.equals("pregunta")) {
                                obj.put("pregunta", fieldvalue);
                            }else if (fieldname.equals("conteo")) {
                                obj.put("conteo", fieldvalue);
                                secciones.put("conteo", Integer.parseInt(fieldvalue));
                                n=Integer.parseInt(fieldvalue);
                                secciones.put("conteo", fieldvalue);
                            }else if (fieldname.equals("intentos")) {
                                obj.put("intentos", fieldvalue);
                            }else if (fieldname.equals("frinicial")) {
                                obj.put("frase_inicial", fieldvalue);
                            }else if (fieldname.equals("freval")) {
                                obj.put("frase_evaluacion", fieldvalue);
                            }else if (fieldname.equals("frrc")) {
                                obj.put("frase_respcorrecta", fieldvalue);
                            }else if (fieldname.equals("frri")) {
                                obj.put("frase_respincorrecta", fieldvalue);
                            }else if (fieldname.equals("frintent")) {
                                obj.put("frase_intentos", fieldvalue);
                            }

                            for(int i =1;i<=n;i++){
                                
                            if (fieldname.equals("tipo"+String.valueOf(i))) {
                                secciones.put("tipo"+String.valueOf(i), fieldvalue);
                            }else if (fieldname.equals("link"+String.valueOf(i))) {
                                secciones.put("link"+String.valueOf(i), fieldvalue);
                            }else if (fieldname.equals("title"+String.valueOf(i))) {
                                secciones.put("title"+String.valueOf(i), fieldvalue);
                            }else if (fieldname.equals("target"+String.valueOf(i))) {
                                secciones.put("target"+String.valueOf(i), fieldvalue);
                            }else if (fieldname.equals("coordenadas"+String.valueOf(i))) {
                                secciones.put("coordenadas"+String.valueOf(i), fieldvalue);
                            }
                            sects.put(String.valueOf(i), secciones);
                            }
                            
                            obj.put("secciones",sects);
                        }
                        
                    }
                }
                out.println(obj);
                //Objetos de clase preguntas, administrador y secciones
                Preguntas pregunta = new Preguntas((String) obj.get("nombrepregunta"),(String) obj.get("pregunta"),"hotspot", (String) obj.get("conteo"),(String) obj.get("intentos"),(String) obj.get("frase_inicial"),(String) obj.get("frase_evaluacion"),(String) obj.get("frase_respcorrecta"),(String) obj.get("frase_respincorrecta"),(String) obj.get("frase_intentos"));
                Secciones seccion = new Secciones(sects);
                HotSpot hotspot = new HotSpot((String) obj.get("imagen"),(String)secciones.get("coordenadas1"),(String) obj.get("nombrepregunta"),(String) obj.get("pregunta"),"hotspot",(String) secciones.get("conteo"),(String) obj.get("intentos"),(String) obj.get("frase_inicial"),(String) obj.get("frase_evaluacion"),(String) obj.get("frase_respcorrecta"),(String) obj.get("frase_respincorrecta"),(String) obj.get("frase_intentos"));
                Administrador admin = new Administrador(uploadPath,"wewweewe");
                //Registra la pregunta
                admin.registraPregunta(pregunta, hotspot, request.getRealPath(""),seccion);
                //Regresa a la inicial
                response.sendRedirect("Inicial");
        } catch (Exception ex) {
            System.out.println("sdsdsdsdsdsd " +ex.getMessage());
        }

    }

}
