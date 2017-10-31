/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_ejercicio16;

import serializacion2.Product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author otorradomiguez
 */
public class Ad_ejercicio16 {

    static String ruta = "/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/serializacion2/serial.otm";
    static String rutaXML="/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/ad_ejercicio16/produtos.xml";
    
    static XMLOutputFactory xmlof;
    static XMLStreamWriter xmlsw;
    
    public static void main(String[] args) {
        conectar();
        leerYCrearXML(ruta);
        
    }
    
    public static void conectar(){
        xmlof=XMLOutputFactory.newInstance();
        try {
            xmlsw=xmlof.createXMLStreamWriter(new FileWriter(rutaXML));            
        } catch (IOException ex) {
            Logger.getLogger(Ad_ejercicio16.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(Ad_ejercicio16.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void leerYCrearXML(String ruta) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
            //La clase Product tiene que estar guardada en un paquete con el mismo nombre 
            //en el que estaba en el otro proyecto, sino daria un error de classNotFoundEx
            Product tempProduct = (Product) ois.readObject();
            xmlsw.writeStartDocument("1.0");
            xmlsw.writeStartElement("Products");
            while (tempProduct != null) {
                System.out.println(tempProduct.toString());
                escribirXML(tempProduct);
                tempProduct = (Product) ois.readObject();
            }
            xmlsw.writeEndDocument();
            xmlsw.close();
            ois.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ad_ejercicio16.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Ad_ejercicio16.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(Ad_ejercicio16.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void escribirXML(Product p){                   
        try {
            /*Esta linea no la puedo poner aqui porque se ejecuta el metodo con cada objeto nuevo
            xmlsw.writeStartDocument("1.0");
            */
            xmlsw.writeStartElement("Product");
            
            xmlsw.writeStartElement("codigo");
            xmlsw.writeCharacters(p.getCodigo());
            xmlsw.writeEndElement();
            
            xmlsw.writeStartElement("descricion");
            xmlsw.writeCharacters(p.getDescricion());
            xmlsw.writeEndElement();
            
            xmlsw.writeStartElement("prezo");
            xmlsw.writeCharacters(""+p.getPrezo());
            xmlsw.writeEndElement();
            
            xmlsw.writeEndElement();
        } catch (XMLStreamException ex) {
            Logger.getLogger(Ad_ejercicio16.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
