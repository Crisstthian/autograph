package com.abianic;

public class Main {

    public static void main(String[] args) {
        System.out.println("Autograph!");

        String xmlPath = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><factura  id=\"comprobante\" version=\"1.0.0\"><infoTributaria><ambiente>1</ambiente></infoTributaria><infoFactura><fechaEmision>01/01/2000</fechaEmision><totalConImpuestos><totalImpuesto><codigo>2</codigo></totalImpuesto></totalConImpuestos><pagos><pago><formaPago>01</formaPago></pago></pagos></infoFactura><detalles><detalle><codigoPrincipal>codigoPrincipal0</codigoPrincipal><detallesAdicionales><detAdicional nombre=\"nombre0\" valor=\"valor0\"/></detallesAdicionales><impuestos><impuesto><codigo>2</codigo></impuesto></impuestos></detalle></detalles><infoAdicional><campoAdicional nombre=\"nombre4\">campoAdicional0</campoAdicional></infoAdicional></factura>";
        //String xmlPath = "C:\\facturaelectronica\\generadas\\factura V1.0.0.xml";
        String pathSignature = "C:\\certificates\\Generacom\\leonardo_enrique_rodrigo_villarreal.p12";
        String passSignature = "Gene2018";
        String pathOut = "C:\\firmados\\factura_sign.xml";
        String nameFileOut = "factura_sign.xml";

        System.out.println("Ruta del XML de entrada: " + xmlPath);
        System.out.println("Ruta Certificado: " + pathSignature);
        System.out.println("Clave del Certificado: " + passSignature);
        System.out.println("Ruta de salida del XML: " + pathOut);
        System.out.println("Nombre del archivo salido: " + nameFileOut);

        //XAdESBESFileSignature famoso = new XAdESBESFileSignature(xmlPath);

        XAdESBESStringSignature famoso2 = new XAdESBESStringSignature(xmlPath);

        String resul = null;

        try {

            //resul = famoso.firmarFile(xmlPath, pathSignature, passSignature, pathOut, nameFileOut);

            resul = famoso2.firmarString(xmlPath, pathSignature, passSignature, pathOut, nameFileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("RESULT: " + resul);

    }
}
