package es.severo.manuelamoros.persistence.util;

import es.severo.manuelamoros.persistence.exceptions.CriticalException;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.ServiceException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private HibernateUtil(){}
    private static SessionFactory builderSessionFactory(){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(new File("src/main/resources/META-INF/hibernate.cfg.xml")).build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
    public static void tryConexion() throws CriticalException{
        try {
            builderSessionFactory();
        }catch (ServiceException e){
            throw new CriticalException(e.getMessage(),"Error al acceder a la base de datos, por favor vuelva a introducir los datos correctamente",CriticalException.CriticalType.Database);
        }
    }
    public static SessionFactory getSessionFactory(){
        if (sessionFactory ==null){
            sessionFactory = builderSessionFactory();
        }
        return sessionFactory;
    }
    public static void closeSessionFactory(){
        getSessionFactory().close();
    }

    public static void setConectionsSeting(String user, String pass) throws CriticalException {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new File("src/main/resources/META-INF/hibernate.cfg.xml"));
            NodeList items = doc.getElementsByTagName("property");
            for (int ix = 0; ix < items.getLength(); ix++) {
                Element element = (Element) items.item(ix);
                // elejir un elemento especifico por algun atributo
                if (element.getAttribute("name").equals("connection.username"))
                    element.setTextContent(user);
                if (element.getAttribute("name").equals("connection.password"))
                    element.setTextContent(pass);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File("src/main/resources/META-INF/hibernate.cfg.xml"));
            Source input = new DOMSource(doc);
            transformer.transform(input, output);

        } catch (ParserConfigurationException e) {
            throw new CriticalException(e.getMessage(),"Error al leer hibernate.cfg.xml", CriticalException.CriticalType.Hibernate);
        } catch (IOException e) {
            throw new CriticalException(e.getMessage(),"Error al acceder a hibernate.cfg.xml", CriticalException.CriticalType.Hibernate);
        } catch (SAXException e) {
            throw new CriticalException(e.getMessage(),"Error al escribir en hibernate.cfg.xml", CriticalException.CriticalType.Hibernate);
        } catch (TransformerConfigurationException e) {
            throw new CriticalException(e.getMessage(),"Error al grabar hibernate.cfg.xml", CriticalException.CriticalType.Hibernate);
        } catch (TransformerException e) {
            throw new CriticalException(e.getMessage(),"Error al grabar hibernate.cfg.xml", CriticalException.CriticalType.Hibernate);
        }
    }
    public static Boolean checkValues() throws CriticalException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("src/main/resources/META-INF/hibernate.cfg.xml"));
            NodeList items = doc.getElementsByTagName("property");
            for (int ix = 0; ix < items.getLength(); ix++) {
                Element element = (Element) items.item(ix);
                // elejir un elemento especifico por algun atributo
                if (element.getAttribute("name").equals("connection.username"))
                    if (element.getTextContent().isEmpty())
                        return false;
                if (element.getAttribute("name").equals("connection.password"))
                    if (element.getTextContent().isEmpty())
                        return false;
            }
            return true;
        } catch (ParserConfigurationException e) {
            throw new CriticalException(e.getMessage(),"Error al leer hibernate.cfg.xml", CriticalException.CriticalType.Hibernate);
        } catch (IOException e) {
            throw new CriticalException(e.getMessage(),"Error al acceder a hibernate.cfg.xml", CriticalException.CriticalType.Hibernate);
        } catch (SAXException e) {
            throw new CriticalException(e.getMessage(),"Error al escribir en hibernate.cfg.xml", CriticalException.CriticalType.Hibernate);
        }

    }
}