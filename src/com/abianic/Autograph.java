package com.abianic;

public class Autograph {

    public static void main(String[] args) {
        String pathSignature = args[0];
        String passSignature = args[1];
        String xmlPath = args[2];
        String pathOut = args[3];
        String nameFileOut = args[4];

        //xmlPath = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><factura  id=\"comprobante\" version=\"1.0.0\"><infoTributaria><ambiente>1</ambiente></infoTributaria><infoFactura><fechaEmision>01/01/2000</fechaEmision><totalConImpuestos><totalImpuesto><codigo>2</codigo></totalImpuesto></totalConImpuestos><pagos><pago><formaPago>01</formaPago></pago></pagos></infoFactura><detalles><detalle><codigoPrincipal>codigoPrincipal0</codigoPrincipal><detallesAdicionales><detAdicional nombre=\"nombre0\" valor=\"valor0\"/></detallesAdicionales><impuestos><impuesto><codigo>2</codigo></impuesto></impuestos></detalle></detalles><infoAdicional><campoAdicional nombre=\"nombre4\">campoAdicional0</campoAdicional></infoAdicional></factura>";

        System.out.println("Ruta del XML de entrada: " + xmlPath);
        //System.out.println("Ruta Certificado: " + pathSignature);
        //System.out.println("Clave del Certificado: " + passSignature);
        //System.out.println("Ruta de salida del XML: " + pathOut);
        //System.out.println("Nombre del archivo salido: " + nameFileOut);

        XAdESBESStringSignature famoso = new XAdESBESStringSignature(xmlPath);

        String resul = null;

        try {
            resul = famoso.firmarString(xmlPath, pathSignature, passSignature, pathOut, nameFileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resul);
        //return resul;
    }
}
