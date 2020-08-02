/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author benji
 */
import Modelo.HotObjects;
import Modelo.HotSpot;
import Modelo.Opciones;
import Modelo.Preguntas;
import Modelo.Secciones;
import Modelo.Tabla;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//import org.w3c.*;

public class Administrador {
    
    private String ruta;
    private String rutaCompleta;
    //Constructor de la clase
    public Administrador(String ruta, String archivo) {
        this.ruta = ruta;
        this.rutaCompleta = ruta + archivo;
    }

    //Metodo para registrar pregunta, recibe objetos de las clases Preguntas, HotSpot, Secciones y la ruta para encontrar los archivos
    public String registraPregunta(Preguntas pregunta,HotSpot hotspot, String ruta, Secciones seccion) throws IOException, JDOMException {
        
        SAXBuilder builder = new SAXBuilder();
        String cadena = "";
        //Se definen los archivos xml
        File xmlFile = new File(ruta + "\\preguntas.xml");
        File xmlFile2 = new File(ruta + "\\HotSpot.xml");
        int n = Integer.parseInt(pregunta.getContador());
        try {
            Document document = builder.build(xmlFile);
            Document document2 = builder.build(xmlFile2);
            
            //Comienzan a definirse las etiquetas de la pregunta en preguntas.xml   
            Element rootNode = document.getRootElement();
            document.getRootElement().addContent(new Element("pregunta")
                    .setAttribute("pregunta", pregunta.getPregunta())
                    .setAttribute("tipo", pregunta.getTipo())
                    .setAttribute("conteo", pregunta.getContador())
                    .setAttribute("nombre", pregunta.getNombre())
                    .addContent(new Element("intentos").setText(pregunta.getIntentos()))
                    .addContent(new Element("f_inicial").setText(pregunta.getF_inicial()))
                    .addContent(new Element("f_eval").setText(pregunta.getF_eval()))
                    .addContent(new Element("f_correcta").setText(pregunta.getF_respc()))
                    .addContent(new Element("f_incorrecta").setText(pregunta.getF_respi()))
                    .addContent(new Element("f_intentos").setText(pregunta.getF_intentos()))
            );
            JSONObject seccionesa = (JSONObject) seccion.getSecciones().get(String.valueOf(1));
            for(int k = 1;k<=n;k++){
                //Se definen las etiquetas para las secciones en hotpot.xml
                document2.getRootElement().addContent(new Element("HotSpot")
                    .setAttribute("imagen", hotspot.getImagen())
                    .setAttribute("conteo", pregunta.getContador())
                    .setAttribute("pregunta", pregunta.getNombre())
                    .addContent(new Element("sec"+String.valueOf(k))
                    .addContent(new Element("type").setText((String) seccionesa.get("tipo"+String.valueOf(k))))
                    .addContent(new Element("link").setText((String) seccionesa.get("link"+String.valueOf(k))))
                    .addContent(new Element("title").setText((String) seccionesa.get("title"+String.valueOf(k))))
                    .addContent(new Element("target").setText((String) seccionesa.get("target"+String.valueOf(k))))
                    .addContent(new Element("coordenadas").setText((String) seccionesa.get("coordenadas"+String.valueOf(k)))
                    
                    )));
            }
            
            

            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter(ruta + "\\preguntas.xml"));
            xmlOutput.output(document2, new FileWriter(ruta + "\\HotSpot.xml"));
            cadena = "registro exitoso";
        } catch (IOException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
    return cadena;
    }
    public ArrayList<Tabla> obtenerTablaPreguntas(String ruta) {
        //Metodo para obtener todas las preguntas del archivo preguntas.xml
        //Accedemos a este metodo en El servlet inicial y con este se dibuja la tabla de manera dinamica
        ArrayList<Tabla> tabla = new ArrayList<>();
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(ruta + "\\preguntas.xml");
            Document document = builder.build(xmlFile);
            Element rootNode = document.getRootElement();//Obtiene usuarios
            List list = rootNode.getChildren("pregunta");

            for (int i = 0; i < list.size(); i++) {
                Element pregunta = (Element) list.get(i);
                String tipoP = pregunta.getAttributeValue("tipo");
                String nombre = pregunta.getAttributeValue("nombre");
                Tabla t = new Tabla(nombre, tipoP);
                tabla.add(t);

            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return tabla;
    }
    public ArrayList<String> leerPregunta(String ruta, String nombreP) throws JDOMException, IOException {
        //Metodo que obtiene la pregunta del archivo preguntas.xml
        ArrayList<String> datosA = new ArrayList<>();
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(ruta + "\\preguntas.xml");
            Document document = builder.build(xmlFile);
            Element rootNode = document.getRootElement();//Obtiene usuarios
            List list = rootNode.getChildren("pregunta");

            for (int i = 0; i < list.size(); i++) {
                
                Element pregunta = (Element) list.get(i);
                String nombre = pregunta.getAttributeValue("nombre");
                if (nombreP.equals(nombre)) {
                String conteo = pregunta.getAttributeValue("conteo");
                String pregunta1 = pregunta.getAttributeValue("pregunta");
                String intentos = pregunta.getChildTextTrim("intentos");
                String f_inicial = pregunta.getChildTextTrim("f_inicial");
                String f_eval = pregunta.getChildTextTrim("f_eval");
                String f_correcta = pregunta.getChildTextTrim("f_correcta");
                String f_incorrecta = pregunta.getChildTextTrim("f_incorrecta");
                String f_intentos = pregunta.getChildTextTrim("f_intentos");
                    datosA.add(conteo);
                    datosA.add(pregunta1);
                    datosA.add(intentos);
                    datosA.add(nombre);
                    datosA.add(f_inicial);
                    datosA.add(f_eval);
                    datosA.add(f_correcta);
                    datosA.add(f_incorrecta);
                    datosA.add(f_intentos);
                }
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return datosA;
    
    }

    public JSONObject leerHotSpot(String ruta, String nombreP) {
        //Metodo que obtiene la informacion de la pregunta del archivo hotspot.xml
        ArrayList<String> datosA = new ArrayList<>();
        JSONObject cosas = new JSONObject();
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(ruta + "\\hotspot.xml");
            Document document = builder.build(xmlFile);
            Element rootNode = document.getRootElement();//Obtiene usuarios
            List list = rootNode.getChildren("HotSpot");
            for (int i = 0; i < list.size(); i++) {
                Element hotspot = (Element) list.get(i);

                String imagen = hotspot.getAttributeValue("imagen");
                String pregunta = hotspot.getAttributeValue("pregunta");
                String conteo = hotspot.getAttributeValue("conteo");
                if (nombreP.equals(pregunta)) {
                    
                    cosas.put("1",imagen);
                cosas.put("2",pregunta);
                cosas.put("3",conteo);
                    int n= Integer.parseInt(conteo);
                    
                    for(int k =0;k<= n ;k++){
                        try{
                            String sec = "sec"+String.valueOf(k);
                            String tipo;
                            tipo = hotspot.getChild(sec).getChildTextTrim("type");
                            String link = hotspot.getChild(sec).getChildTextTrim("link");
                            String title = hotspot.getChild(sec).getChildTextTrim("title");
                            String coords = hotspot.getChild(sec).getChildTextTrim("coordenadas");

                
                
                
                cosas.put("tipo"+String.valueOf(k),tipo);
                cosas.put("link"+String.valueOf(k),link);
                cosas.put("title"+String.valueOf(k),title);
                cosas.put("coords"+String.valueOf(k),coords);
                
                }catch(Exception e){
                    System.out.println("sdsdsdsd*/***/*/*//**/*/ "+e.getMessage());
                }
                }
                }
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return cosas;
    }
    
    public boolean eliminaPregunta(String ruta,String nombre) throws JDOMException, IOException {
        //Metodo que elimina pregunta del archivo preguntas.xml
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(ruta + "\\preguntas.xml");
        Document document = builder.build(xmlFile);
        Element rootNode = document.getRootElement();//Obtiene usuarios
        List list = rootNode.getChildren("pregunta");

        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            Element pregunta = (Element) list.get(i);
            String nombres = pregunta.getAttributeValue("nombre");

            if (nombres.equals(nombre)) {
                list.remove(pregunta);
                flag = true;
            }
        }

        XMLOutputter xmlOutput = new XMLOutputter();

        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(document, new FileWriter(ruta + "\\preguntas.xml"));

        return flag;
    }
    
    public boolean eliminaHotspot(String ruta,String nombre) throws JDOMException, IOException {
        boolean flag = false;
        //metodo que elimna preguntas del archivo hotspot.xml
        if (eliminaPregunta(ruta, nombre)) {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(ruta + "\\hotspot.xml");
            Document document = builder.build(xmlFile);
            Element rootNode = document.getRootElement();//Obtiene usuarios
            List list = rootNode.getChildren("HotSpot");
            for (int i = 0; i < list.size(); i++) {
                Element hotspot = (Element) list.get(i);
                String pregunta = hotspot.getAttributeValue("pregunta");
                if (pregunta.equals(nombre)) {
                    list.remove(i);
                    flag = true;
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(document, new FileWriter(ruta + "\\hotspot.xml"));

        } else {
            flag = false;
        }
        return flag;
    }
    
public boolean eliminaHotobjects(String ruta,String nombre) throws JDOMException, IOException {
        boolean flag = false;
        //metodo que elimna preguntas del archivo HotObjects.xml
        if (eliminaPregunta(ruta, nombre)) {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(ruta + "\\HotObjects.xml");
            Document document = builder.build(xmlFile);
            Element rootNode = document.getRootElement();//Obtiene usuarios
            List list = rootNode.getChildren("HotObjects");
            for (int i = 0; i < list.size(); i++) {
                Element hotspot = (Element) list.get(i);
                String pregunta = hotspot.getAttributeValue("pregunta");
                if (pregunta.equals(nombre)) {
                    list.remove(i);
                    flag = true;
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(document, new FileWriter(ruta + "\\HotObjects.xml"));

        } else {
            flag = false;
        }
        return flag;
    }

    
public String registraPreguntaHo(Preguntas pregunta, String ruta, Opciones opcion) throws IOException, JDOMException {
        //Metodo que registra preguntas en hotobjects.xml
        SAXBuilder builder = new SAXBuilder();
        String cadena = "";
        File xmlFile = new File(ruta + "\\preguntas.xml");
        File xmlFile2 = new File(ruta + "\\HotObjects.xml");
        int n = Integer.parseInt(pregunta.getContador());
        //ArrayList<Document> secxml = new ArrayList<Document>();
        try {
            Document document = builder.build(xmlFile);
            Document document2 = builder.build(xmlFile2);
            
                
            Element rootNode = document.getRootElement();
            document.getRootElement().addContent(new Element("pregunta")
                    .setAttribute("pregunta", pregunta.getPregunta())
                    .setAttribute("tipo", pregunta.getTipo())
                    .setAttribute("conteo", pregunta.getContador())
                    .setAttribute("nombre", pregunta.getNombre())
                    .addContent(new Element("intentos").setText(pregunta.getIntentos()))
                    .addContent(new Element("f_inicial").setText(pregunta.getF_inicial()))
                    .addContent(new Element("f_eval").setText(pregunta.getF_eval()))
                    .addContent(new Element("f_correcta").setText(pregunta.getF_respc()))
                    .addContent(new Element("f_incorrecta").setText(pregunta.getF_respi()))
                    .addContent(new Element("f_intentos").setText(pregunta.getF_intentos()))
            );
            JSONObject seccionesa = (JSONObject) opcion.getOpciones();
            for(int k = 1;k<=n;k++){
                document2.getRootElement().addContent(new Element("HotObjects")
                    .setAttribute("conteo", pregunta.getContador())
                    .setAttribute("pregunta", pregunta.getNombre())
                    .addContent(new Element("opc"+String.valueOf(k))
                    .addContent(new Element("drag"+String.valueOf(k)).setText((String) seccionesa.get("drag"+String.valueOf(k))))
                    .addContent(new Element("drop"+String.valueOf(k)).setText((String) seccionesa.get("drop"+String.valueOf(k))))
                    ));
            }
            
            

            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter(ruta + "\\preguntas.xml"));
            xmlOutput.output(document2, new FileWriter(ruta + "\\HotObjects.xml"));
            cadena = "registro exitoso";
        } catch (IOException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
    return cadena;
    }

public ArrayList<String> leerPreguntaH(String ruta, String nombreP) throws JDOMException, IOException {
    //Metodo para leer preguntas
        ArrayList<String> datosA = new ArrayList<>();
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(ruta + "\\preguntas.xml");
            Document document = builder.build(xmlFile);
            Element rootNode = document.getRootElement();//Obtiene usuarios
            List list = rootNode.getChildren("pregunta");

            for (int i = 0; i < list.size(); i++) {
                
                Element pregunta = (Element) list.get(i);
                String nombre = pregunta.getAttributeValue("nombre");
                if (nombreP.equals(nombre)) {
                String conteo = pregunta.getAttributeValue("conteo");
                String pregunta1 = pregunta.getAttributeValue("pregunta");
                String intentos = pregunta.getChildTextTrim("intentos");
                String f_inicial = pregunta.getChildTextTrim("f_inicial");
                String f_eval = pregunta.getChildTextTrim("f_eval");
                String f_correcta = pregunta.getChildTextTrim("f_correcta");
                String f_incorrecta = pregunta.getChildTextTrim("f_incorrecta");
                String f_intentos = pregunta.getChildTextTrim("f_intentos");
                    datosA.add(conteo);
                    datosA.add(pregunta1);
                    datosA.add(intentos);
                    datosA.add(nombre);
                    datosA.add(f_inicial);
                    datosA.add(f_eval);
                    datosA.add(f_correcta);
                    datosA.add(f_incorrecta);
                    datosA.add(f_intentos);
                }
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return datosA;
    
    }

public JSONObject leerHotObjects(String ruta, String nombreP) {
    //Metodo para leer preguntas de hotobjects.xml
        ArrayList<String> datosA = new ArrayList<>();
        JSONObject cosas = new JSONObject();
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(ruta + "\\HotObjects.xml");
            Document document = builder.build(xmlFile);
            Element rootNode = document.getRootElement();//Obtiene usuarios
            List list = rootNode.getChildren("HotObjects");
            for (int i = 0; i < list.size(); i++) {
                Element hotobjects = (Element) list.get(i);
                String pregunta = hotobjects.getAttributeValue("pregunta");
                String conteo = hotobjects.getAttributeValue("conteo");
                if (nombreP.equals(pregunta)) {
                    
                cosas.put("2",pregunta);
                cosas.put("3",conteo);
                    int n= Integer.parseInt(conteo);
                    
                    for(int k =1;k<= n ;k++){
                        try{
                            String sec = "opc"+String.valueOf(k);
                            String drag = hotobjects.getChild(sec).getChildTextTrim("drag"+String.valueOf(k));
                            String drop = hotobjects.getChild(sec).getChildTextTrim("drop"+String.valueOf(k));
           
                cosas.put("drag"+String.valueOf(k),drag);
                cosas.put("drop"+String.valueOf(k),drop);
                
                }catch(Exception e){
                    System.out.println("sdsdsdsd*/***/*/*//**/*/ "+e.getMessage());
                }
                }
                }
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return cosas;
    }

public boolean modificarPregunta(Preguntas pregunta,String nombre_oculto,HotSpot hotspot, String ruta, Secciones seccion) throws IOException, JDOMException {
        //Metodo para modificar pregunta hotspot
        JSONObject json = new JSONObject();
        Administrador admin = new Administrador(ruta,"preguntas.xml");
        ArrayList<Tabla> preguntas = admin.obtenerTablaPreguntas(ruta);
        int ex=0;
        int x;
        for(x=0;x<=preguntas.size();x++){
            if(preguntas.get(x).nombre.equals(nombre_oculto) && preguntas.get(x).tipo.equals("hotspot")){
                admin.eliminaHotspot(ruta, nombre_oculto);
                admin.registraPregunta(pregunta, hotspot, ruta, seccion);
                return true;
            }
        }
        
        return false;
        
    }

    public void modificarPreguntaHo(Preguntas pregunta,String nombre_oculto, String ruta, Opciones opcion) throws IOException, JDOMException {
        //metodo para modificar pregunta hotobjects
        JSONObject json = new JSONObject();
        Administrador admin = new Administrador(ruta,"preguntas.xml");
        ArrayList<Tabla> preguntas = admin.obtenerTablaPreguntas(ruta);
        int ex=0;
        int x;
        for(x=0;x<=preguntas.size();x++){
            if(preguntas.get(x).nombre.equals(nombre_oculto) && preguntas.get(x).tipo.equals("hotobjects")){
                admin.eliminaHotobjects(ruta, nombre_oculto);
                admin.registraPreguntaHo(pregunta, ruta, opcion);
                
            }
        }
    }
    
    public void registraRespuestas(String nombrep, String respuestas,String intentos) throws IOException, JDOMException {
        //metodo que registra respuestas en respuestas.xml
        SAXBuilder builder = new SAXBuilder();
        String cadena = "";
        File xmlFile = new File(ruta + "\\respuestas.xml");
        
        try {
            Document document = builder.build(xmlFile);
            
                
            Element rootNode = document.getRootElement();
            document.getRootElement().addContent(new Element("respuesta")
                    .setAttribute("pregunta", nombrep)
                    .setAttribute("respuestas", respuestas)
                    .setAttribute("intentos", intentos))
            ;
            
            
            

            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter(ruta + "\\respuestas.xml"));
            cadena = "registro exitoso";
        } catch (IOException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
    
}
