package com.abianic;

import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.FirmaXML;
import org.w3c.dom.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public abstract class GenericXMLSignature {

    private String pathSignature;
    private String passSignature;

    private Document execute () throws Exception {

        KeyStore keyStore = this.getKeyStore();
        if (keyStore == null)
        {
            throw new Exception("No se pudo obtener almacen de firma.");
        }

        String alias = getAlias(keyStore);

        X509Certificate certificate = null;

        certificate = (X509Certificate)keyStore.getCertificate(alias);

        if (certificate == null)
        {
            throw new Exception("No existe ningun certificado para firmar.");
        }

        PrivateKey privateKey = null;
        KeyStore tmpKs = keyStore;

        try {
            privateKey = (PrivateKey)tmpKs.getKey(alias, this.passSignature.toCharArray());
        } catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new IOException("No existe clave privada para firmar.");
        }

        Provider provider = keyStore.getProvider();
        DataToSign dataToSign = createDataToSign();
        FirmaXML firma = new FirmaXML();
        Document docSigned = null;

        try {
            Object[] res = firma.signFile(certificate, dataToSign, privateKey, provider);
            docSigned = (Document)res[0];
        } catch (Exception e) {
            throw new Exception("Error realizando la firma: " + e.getMessage());
        }
         return docSigned;
    }

    protected abstract DataToSign createDataToSign();

    protected abstract String getSignatureFileName();

    protected abstract String getPathOut();

    private KeyStore getKeyStore() throws Exception {

        KeyStore ks = null;

        try {
            ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(this.pathSignature), this.passSignature.toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new Exception("Error: " + e.getMessage());
        }

        return ks;
    }

    private String getAlias(KeyStore keyStore) throws Exception {

        String alias = null;

        try {
            Enumeration nombres = keyStore.aliases();

            while (nombres.hasMoreElements()) {
                String tmpAlias = (String)nombres.nextElement();
                if (keyStore.isKeyEntry(tmpAlias)) {
                    alias = tmpAlias;
                }
            }
        } catch (KeyStoreException e) {
            throw new Exception("Error: " + e.getMessage());
        }

        return alias;
    }


}
