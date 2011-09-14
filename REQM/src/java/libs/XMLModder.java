/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Moncho
 */
public class XMLModder {

    public static Document JoinDocs(Document principal, String secundario) throws ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        try {
            Document sec = builder.parse(new InputSource(new StringReader(secundario)));
            Node nodo = principal.importNode(sec.getDocumentElement(), true);
            principal.getDocumentElement().appendChild(nodo);
            /*System.out.println("============================================");
            for (int i = 0; i < principal.getDocumentElement().getChildNodes().getLength(); i++) {
                System.out.println(principal.getDocumentElement().getChildNodes().item(i).getNodeName());
            }*/
            return principal;
        } catch (SAXException ex) {
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static Document JoinDocs(String principal, String secundario) throws ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        try {
            //System.out.println(principal);
            //System.out.println(secundario);
            Document prin;
            if(principal == null || principal.length()==0)
                prin = builder.parse(new InputSource(new StringReader("<root></root>")));
            else
                prin = builder.parse(new InputSource(new StringReader(principal)));
            return JoinDocs(prin, secundario);
        } catch (SAXException ex) {
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static Document JoinDocs(String raiz,String[] xmls) throws ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        try {
            /*System.out.println("=================  DOCUMENTO  ====================");
            System.out.println("Raiz:\t\t"+raiz);
            for (String string : xmls) {
                System.out.println("Extras:\t\t"+string);
            }*/
            Document prin;
            if(raiz == null || raiz.length()==0)
                prin = builder.parse(new InputSource(new StringReader("<root></root>")));
            else
                prin = builder.parse(new InputSource(new StringReader(raiz)));
            for (String string : xmls) {
                Document sec = builder.parse(new InputSource(new StringReader(string)));
                Node nodo = prin.importNode(sec.getDocumentElement(), true);
                prin.getDocumentElement().appendChild(nodo);
            }
            //System.out.println("===================  FIN DOCUMENTO  =========================");
            for (int i = 0; i < prin.getDocumentElement().getChildNodes().getLength(); i++) {
                System.out.println(prin.getDocumentElement().getChildNodes().item(i).getNodeName());
            }
            return prin;
        } catch (SAXException ex) {
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static Document JoinDocs(Document raiz,String[] xmls) throws ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        try {
            /*System.out.println("=================  DOCUMENTO  ====================");
            System.out.println("Raiz:\t\t"+raiz);
            for (String string : xmls) {
                System.out.println("Extras:\t\t"+string);
            }*/
            if(raiz == null)
                raiz = builder.parse(new InputSource(new StringReader("<root></root>")));
            for (String string : xmls) {
                Document sec = builder.parse(new InputSource(new StringReader(string)));
                Node nodo = raiz.importNode(sec.getDocumentElement(), true);
                raiz.getDocumentElement().appendChild(nodo);
            }
            //System.out.println("===================  FIN DOCUMENTO  =========================");
            /*for (int i = 0; i < prin.getDocumentElement().getChildNodes().getLength(); i++) {
                System.out.println(prin.getDocumentElement().getChildNodes().item(i).getNodeName());
            }*/
            return raiz;
        } catch (SAXException ex) {
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static StringWriter XSLTransform(Document xml, String xslpath) {
        try {
            Source xmlSource = new DOMSource(xml);
            StringWriter cadenaSalida = new StringWriter();
            StreamResult bufferResultado = new StreamResult(cadenaSalida);
            TransformerFactory factoriaTrans = TransformerFactory.newInstance();
            Transformer transformador = factoriaTrans.newTransformer(new StreamSource(xslpath));
            transformador.transform(xmlSource, bufferResultado);
            return cadenaSalida;
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException et){
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, et);
        }
        return null;
    }
    public static StringWriter XSLTransform(String xml, String xslpath) {
        try {
            Source xmlSource = new StreamSource(new StringReader(xml));
            StringWriter cadenaSalida = new StringWriter();
            StreamResult bufferResultado = new StreamResult(cadenaSalida);
            TransformerFactory factoriaTrans = TransformerFactory.newInstance();
            Transformer transformador = factoriaTrans.newTransformer(new StreamSource(xslpath));
            transformador.transform(xmlSource, bufferResultado);
            return cadenaSalida;
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException et){
            Logger.getLogger(XMLModder.class.getName()).log(Level.SEVERE, null, et);
        }
        return null;
    }
}
