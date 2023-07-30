import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.google.gson.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.json.simple.JSONValue.parse;

public class Main {

    public static void main(String[] args) throws IOException, SAXException {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        List<Employee> list = parseCSV(columnMapping, fileName);
     //   System.out.println("print list " + list);
        String json = listToJson(list);
        writeString(json, "data.json");

        List<Employee> listXML = parseXML("data.xml");
        String json2 = listToJson(listXML);
        writeString(json2, "data2.json");

        String jsonNew = readString("data3.json");
        System.out.println(jsonNew);
    //    List<Employee> listm = jsonToList(jsonNew);
    }

    private static String readString(String s) {

        JSONParser parser = new JSONParser();


        JSONArray message = null;
       // Object obj = null;
        try {
          Object  obj = parser.parse(new FileReader(s));
//         //   JSONObject jsonObject = (JSONObject) obj;
            message = (JSONArray) obj;
                    System.out.println(message);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
                    for(int i = 0; i < message.size(); i++){
                        message.get(i);
             Employee empl = gson.fromJson(String.valueOf(message.get(i)), Employee.class);
             System.out.println(empl);
      /////                  System.out.println(message.get(i));
                    }


//         //   System.out.println("Oblect" + jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

 //       GsonBuilder builder = new GsonBuilder();
  //      Gson gson = builder.create();
       // Employee empl = gson.fromJson(message, Employee.class);
//>>        Employee empl = gson.fromJson(String.valueOf(message), Employee.class);
//>>        System.out.println(empl);
      //  JsonObject jsonObject = (JsonObject) obj;
//        for (int i = 0; i < message.size(); i++) {
//            message.get(i);
//            System.out.println(message.get(i));
//        }
//        for(Object o: message) {
//            if (o instanceof JSONObject) {
//             String u =  parse(String.valueOf((JSONObject)o));
//                Employee emp = o.fromJson(, Employee.class);
//            }
//        }


     //   return message.toJSONString();
        return "...";
    }

    private static List<Employee> read(Node node, List<Employee> listts) {

        Employee ppppp = null;
        int id = 0;
        String firstName = "";
        String lastName = "";
        String country = "";
        int age = 0;

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);

            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                Element element = (Element) node_;

                if ("id" == node_.getNodeName()) {
                    id = Integer.parseInt(node_.getTextContent());
                }
                if ("firstName" == node_.getNodeName()) {
                    firstName = node_.getTextContent();
                }
                if ("lastName" == node_.getNodeName()) {
                    lastName = node_.getTextContent();
                }
                if ("country" == node_.getNodeName()) {
                    country = node_.getTextContent();
                }
                if ("age" == node_.getNodeName()) {
                    age = Integer.parseInt(node_.getTextContent());
                    Employee yyyy = new Employee(id, firstName, lastName, country, age);
                    ppppp = yyyy;
                }

            }
            listts = read(node_, listts);
        }

        if (ppppp != null) {
            listts.add(ppppp);
        }

        return listts;
    }

    private static List<Employee> parseXML(String s) {

        List<Employee> employees = new ArrayList<Employee>();
        List<Employee> uuuiuiu = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
//        int id = 0;
//        String firstName = "";
//        String lastName = "";
//        String country = "";
//        int age = 0;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc = null;
        try {
            doc = builder.parse(new File("data.xml"));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Node root = doc.getDocumentElement();

        uuuiuiu = read(root, employees);

    //    System.out.println("908098 " + employees);
        return uuuiuiu;
    }

    private static void writeString(String json, String path) {
        try (FileWriter file = new
                FileWriter(path)) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String listToJson(List<Employee> list) {

        Type listType = new TypeToken<List<Employee>>() {}.getType();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String json = gson.toJson(list, listType);

        return json;
    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {

        List<Employee> employees = null;

        try (CSVReader reader = new CSVReader(new FileReader("data.csv"))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();

            employees = csv.parse();

            // Массив считанных строк
            String[] nextLine;
            // Читаем CSV построчно
            while ((nextLine = reader.readNext()) != null) {
                // Работаем с прочитанными данными.
                System.out.println(Arrays.toString(nextLine));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
