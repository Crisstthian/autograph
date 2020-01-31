package com.abianic;

import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.XAdESSchemas;
import es.mityc.javasign.EnumFormatoFirma;
import es.mityc.javasign.xml.refs.InternObjectToSign;
import es.mityc.javasign.xml.refs.ObjectToSign;
import org.w3c.dom.Document;

public abstract class XAdESBESSignature extends GenericXMLSignature{

    private String fileToSign;

    @Override
    protected DataToSign createDataToSign() {
        DataToSign datosAFirmar = new DataToSign();
        datosAFirmar.setXadesFormat(EnumFormatoFirma.XAdES_BES);
        datosAFirmar.setEsquema(XAdESSchemas.XAdES_132);
        datosAFirmar.setXMLEncoding("UTF-8");
        datosAFirmar.setEnveloped(true);
        datosAFirmar.addObject(new ObjectToSign(new InternObjectToSign("comprobante"), "contenido comprobante", null, "text/xml", null));
        datosAFirmar.setParentSignNode("comprobante");

        Document docToSign = null;

        try {
            docToSign = getDocument(this.fileToSign);
        } catch (Exception e) {
            System.out.println("Error al obtener documento: " + e.getMessage());
        }

        datosAFirmar.setDocument(docToSign);

        return datosAFirmar;
    }

    protected abstract Document getDocument(String xmlString);

    @Override
    protected String getSignatureFileName() {
        return null;
    }

    @Override
    protected String getPathOut() {
        return null;
    }

    public XAdESBESSignature(String fileToSign)
    {
        this.fileToSign = fileToSign;
    }
}
