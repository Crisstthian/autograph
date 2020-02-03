package com.abianic;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XAdESBESFileSignature  extends XAdESBESSignature {
    public XAdESBESFileSignature(String fileToSign) {
        super(fileToSign);
    }

    @Override
    protected Document getDocument(String xmlString) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        File file = new File(xmlString);

        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(file);
        }
        catch (SAXException | IOException | IllegalArgumentException | ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        return doc;
    }

    @Override
    protected String saveDocument(Document documentoFirmado, String rutaSalida) {

        DOMSource source = new DOMSource(documentoFirmado);

        StreamResult result = new StreamResult(new File(rutaSalida));

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

        return rutaSalida;
    }


    public String firmarFile(String contenido, String rutaFirma, String passFirma, String pathOut, String nameFileOut) throws Exception {
        return this.firmar(contenido,rutaFirma,passFirma,pathOut,nameFileOut);
    }
}
