package com.campusdual.classroom;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Exercise27 {

    public static void main(String[] args) {
         Map<String, Integer> listaCompra = new HashMap<>();

        listaCompra = rellenaLista (listaCompra, "Manzana", 2);
        listaCompra = rellenaLista (listaCompra, "Leche", 1);
        listaCompra = rellenaLista (listaCompra, "Pan", 3);
        listaCompra = rellenaLista (listaCompra, "Huevo", 2);
        listaCompra = rellenaLista (listaCompra, "Queso", 1);
        listaCompra = rellenaLista (listaCompra, "Cereal", 1);
        listaCompra = rellenaLista (listaCompra, "Agua", 4);
        listaCompra = rellenaLista (listaCompra, "Yogur", 6);
        listaCompra = rellenaLista (listaCompra, "Arroz", 2);

        System.out.println(listaCompra.size());

        createXML(listaCompra);

        createJSON(listaCompra);

    }
    private static Map<String, Integer> rellenaLista (Map<String, Integer> listaCompra, String key, Integer value){

        listaCompra.put(key, value);

        return  listaCompra;

    }

    private static void createXML(Map<String, Integer> listaCompra) {
        String strFileName = "src/main/resources/shoppingList.xml";

        String jsonXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";

        jsonXML += "<shoppinglist>\n";

        for (Map.Entry<String, Integer> entry : listaCompra.entrySet()){
            jsonXML += "<items>\n";
            jsonXML += "<item quantity=\"" + entry.getValue().toString() + "\">" + entry.getKey() + "</item>\n";
            jsonXML += "</items>\n";
        }

        jsonXML += "</shoppinglist>\n";

        try (FileWriter fileWriter = new FileWriter(strFileName)) {

            fileWriter.write(jsonXML);

        } catch (IOException e) {

            throw new RuntimeException("File not found: " + strFileName);

        }

    }


    private static void createJSON(Map<String, Integer> listaCompra){
        String strFileName = "src/main/resources/shoppingList.json";

        int contador=0;

        String jsonString = "{\"items\":[\n";

        for (Map.Entry<String, Integer> entry : listaCompra.entrySet()){
            if (contador>0){
                jsonString +=",";
            }
            jsonString += "{\"quantity\":\"" + entry.getValue().toString() + "\",";
            jsonString += "\"text\":\""  + entry.getKey().toString() + "\"}\n";
            contador++;

        }

        jsonString += "]}";

        try (FileWriter fileWriter = new FileWriter(strFileName)) {

            fileWriter.write(jsonString);

        } catch (IOException e) {

            throw new RuntimeException("File not found: " + strFileName);

        }

    }

}
