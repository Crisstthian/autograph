package com.abianic;

public class Autograph {

    public static void main(String[] args) {
        String pathSignature = args[0];
        String passSignature = args[1];
        String pathOut = args[2];
        String nameFileOut = args[3];
        String xmlPath = args[4];

        System.out.println("Ruta del XML de entrada: " + xmlPath);
        System.out.println("Ruta Certificado: " + pathSignature);
        System.out.println("Clave del Certificado: " + passSignature);
        System.out.println("Ruta de salida del XML: " + pathOut);
        System.out.println("Nombre del archivo salido: " + nameFileOut);

        XAdESBESFileSignature famoso = new XAdESBESFileSignature(xmlPath);

        String resul = null;

        try {
            resul = famoso.firmarFile(xmlPath, pathSignature, passSignature, pathOut, nameFileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resul);
        //return resul;
    }
}
