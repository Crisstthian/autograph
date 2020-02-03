package com.abianic;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XAdESBESStringSignature extends XAdESBESSignature {
    public XAdESBESStringSignature(String fileToSign) {
        super(fileToSign);
    }

    @Override
    protected Document getDocument(String xmlString) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(xmlString)));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }



        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("C:\\firmados\\temp.xml"));
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        File file = new File("C:\\firmados\\temp.xml");

        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(file);
        }
        catch (SAXException | IOException | IllegalArgumentException | ParserConfigurationException e)
        {
            e.printStackTrace();
        }

        //file.delete();

        return doc;
    }

    @Override
    protected String saveDocument(Document documentoFirmado, String rutaSalida) {

        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(documentoFirmado), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }

    }

    public String firmarString(String contenido, String rutaFirma, String passFirma, String pathOut, String nameFileOut) throws Exception {
        return this.firmar(contenido,rutaFirma,passFirma,pathOut,nameFileOut);
    }
}
