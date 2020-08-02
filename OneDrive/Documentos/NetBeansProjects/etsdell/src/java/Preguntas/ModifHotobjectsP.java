/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preguntas;

import Controlador.Administrador;

import Modelo.Preguntas;
import Modelo.Opciones;

import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import java.util.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author benji
 */
public class ModifHotobjectsP extends HttpServlet {

    //Variables para la subida de archivos
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // checasi el formulario trae un archivo a hacer upload
        if (!ServletFileUpload.isMultipartContent(request)) {
            // si no lo regresa
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        // configuracion para el upload
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

        // Definimos la carpeta donde se almacenara el archivo
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("/imagenes/");

        // Si no existe la crea
        File uploadDir = new File(uploadPath);
        int n=0;
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            //empieza a trabajar con el formulario
            @SuppressWarnings("unchecked")
            int ir=1;
            List<FileItem> formItems = upload.parseRequest(request);
            String fileName1 = "";
            //CAMPO DE ARCHIVO
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fieldname = item.getFieldName();
                        String fileName = new File(item.getName()).getName();
                        fileName1+=fileName;
                        String filePath = uploadPath + File.separator + fileName;
                        String imagenpath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // saves the file on disk
                        item.write(storeFile);
                        respuestas.add(imagenpath);
                        
                        
                        for(int i =1;i<=n;i++){
                                
                                if (fieldname.equals("cofield"+String.valueOf(i))) {
                                    obj.put("drag"+String.valueOf(i),imagenpath);
                                }else if (fieldname.equals("dofield"+String.valueOf(i))) {
                                    obj.put("drop"+String.valueOf(i),imagenpath);
                                }
                            
                            }
                        
                    } else {
                            //CAMPOS DIFERENTRS A ARCHIVOS
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
                            }else if (fieldname.equals("nombreo")) {
                                obj.put("nombre_oculto", fieldvalue);
                            }

                            for(int i =1;i<=n;i++){
                                
                                if (fieldname.equals("cofield"+String.valueOf(i))) {
                                    obj.put("drag"+String.valueOf(i),fieldvalue);
                                }else if (fieldname.equals("dofield"+String.valueOf(i))) {
                                    obj.put("drop"+String.valueOf(i),fieldvalue);
                                }
                            
                            }
                            
                        }
                    }
                }
            //Objetos de clase Preguntas, Administrador y Opciones
                Preguntas pregunta = new Preguntas((String) obj.get("nombrepregunta"),(String) obj.get("pregunta"),"hotobjects", (String) obj.get("conteo"),(String) obj.get("intentos"),(String) obj.get("frase_inicial"),(String) obj.get("frase_evaluacion"),(String) obj.get("frase_respcorrecta"),(String) obj.get("frase_respincorrecta"),(String) obj.get("frase_intentos"));
                Opciones opcion = new Opciones(obj);
                Administrador admin = new Administrador(uploadPath,"wewweewe");
                //Accedemos al metodo modififcarPreguntaHo
                admin.modificarPreguntaHo(pregunta,(String) obj.get("nombre_oculto"),request.getRealPath(""), opcion);
                //Redirigimos al Servlet Inicial
                response.sendRedirect("Inicial");
        } catch (Exception ex) {
            System.out.println("sdsdsdsdsdsd " +ex.getMessage());
        }

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

}
