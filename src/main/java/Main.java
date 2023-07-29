import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import javax.xml.parsers.*;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
//    static List<Employee> employeess = null;
//    static Employee yyyy = null;

    public static void main(String[] args) throws IOException, SAXException {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        List<Employee> list = parseCSV(columnMapping, fileName);
        System.out.println(list);
        String json = listToJson(list);
        writeString(json,"data.json");

        List<Employee> listXML = parseXML("data.xml");
        System.out.println("hugioi "+ listXML);
        String json2 = listToJson(listXML);
        writeString(json2,"data2.json");
    }

    private static List<Employee> read(Node node, List<Employee> listts) {
 //
        List<Employee> employeess = null;
     //   Employee listp = null;
        Employee ppppp = null;
        int id = 0;
        String firstName = "";
        String lastName = "";
        String country = "";
        int age = 0;
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
        //    System.out.println(node_);
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        if(node_ != null) {


    //        }
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                Element element = (Element) node_;

                if ("id" == node_.getNodeName()) {
                   id = Integer.parseInt(node_.getTextContent());
                    //   System.out.println(id);
                }
                if ("firstName" == node_.getNodeName()) {
                   firstName = node_.getTextContent();
                    //   System.out.println(id + "_" + firstName);
                }
                if ("lastName" == node_.getNodeName()) {
                    lastName = node_.getTextContent();
                    //   System.out.println(id + "_" + firstName + lastName);
                }
                if ("country" == node_.getNodeName()) {
                   country = node_.getTextContent();
                    //  System.out.println(id + "_" + firstName + lastName + country);
                }
                if ("age" == node_.getNodeName()) {

                   age = Integer.parseInt(node_.getTextContent());
                    //   System.out.println(id + "_" + firstName + lastName + country + age);
                    System.out.println("kloji " + id + "_" + firstName + "_" + lastName + "_" + country + "_" + age);
              //      System.out.println(i);
                    Employee yyyy = new Employee(id, firstName, lastName, country, age);
                    System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyy   " + yyyy);
                //    if(yyyy != null){
                    System.out.println("yyyyyyyyyyyyyyyyyyyyyypppp1   " + listts);
                    ppppp = yyyy;
              //      listts.add(yyyy);
                    System.out.println("yyyyyyyyyyyyyyyyyyyyyypppp2   " + listts);
               //     break;
              //      return listts;
                //    }
                  //  listts.add(yyyy);
                  //  return yyyy;
                 //   listp.add(yyyy);
 //                   employeess.add(employee);
//                    if(yyyy != null){
//                        employeess.add(yyyy);
//                    }
  //                  System.out.println(yyyy);

                    //return employee;
//                    if(employee != null){
//                        list.add(employee);
//                    }
                    //        list.add(employee);

  //                  System.out.println("list " + list);
                    }
            }

         //   }






            //000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
         //   System.out.println(employee);
  //          if (yyyy != null) {
         //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!       read(node_, null);
       //     read(node_, listts);
   //         }
    //      if(node_ != null) {
         //     listts =
              listts =  read(node_, listts);

  //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        }
      //    listts = read(node_, listts);


            // listp.add(read(node_));
  //          System.out.println("999999999999999999999   " + yyyy);
        }
    //    System.out.println("oiooooiiioio   " + yyyy);
//        if(employee != null) {
//            employeess.add(employee);
//        }
       // read(node_, listts);

  //      System.out.println("oiooooiiioio   " + yyyy);

//        if(yyyy != null){
//            return listts;
//        }
       // return listts;
        //return listp;
        if(ppppp != null){
            listts.add(ppppp);
        }

        return listts;
    }

    private static List<Employee> parseXML(String s)  {

        List<Employee> employees = new ArrayList<Employee>();
        List<Employee> uuuiuiu = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        int id = 0;
        String firstName = "";
        String lastName = "";
        String country = "";
        int age = 0;
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
        Node root =  doc.getDocumentElement();

//        if(read(root, employees) != null) {
//            employees.add(read(root, employees));
//        }

     //   System.out.println(read(root));
     //     if (read(root, employees) != null ) {
              uuuiuiu = read(root, employees);
     //     }


 //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!       read(root);
//
//            NodeList nodeList = root.getChildNodes();
//            for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//
//             if (Node.ELEMENT_NODE == node.getNodeType()) {
//                Element employee = (Element) node;
//                NodeList employeeList = employee.getChildNodes();
//                for (int j = 0; j < employeeList.getLength(); j++) {
//                    Node n = employeeList.item(j);
//                    if (Node.ELEMENT_NODE == n.getNodeType()) {
//         //               System.out.println("Node name " + n.getNodeName());
//                        if ("id" == n.getNodeName()) {
//                    id = Integer.parseInt(n.getTextContent());
//         //             System.out.println(id);
//                        }
//
//                        if ("firstName" == n.getNodeName()) {
//                    firstName = n.getTextContent();
//         //              System.out.println(id + "_" + firstName);
//                }
//                if ("lastName" == n.getNodeName()) {
//                    lastName = n.getTextContent();
//                    //   System.out.println(id + "_" + firstName + lastName);
//                }
//                if ("country" == n.getNodeName()) {
//                    country = n.getTextContent();
//                    //  System.out.println(id + "_" + firstName + lastName + country);
//                }
//                        if ("age" == n.getNodeName()) {
////
//                    age = Integer.parseInt(n.getTextContent());
////                    //   System.out.println(id + "_" + firstName + lastName + country + age);
//                    System.out.println("kloji " + id + "_" + firstName + "_" + lastName + "_" + country + "_" + age);
//                   }
//
//                    }
//
//                }
//
////////                id = Integer.parseInt(element.getAttribute("id"));
////////                firstName = element.getAttribute("firstName");
////////                lastName = element.getAttribute("lastName");
////////                country = element.getAttribute("country");
//////                  age = Integer.parseInt(element.getAttribute("age"));
//
//            }
//
//              //   Employee employee = new Employee(id, firstName, lastName, country, age);
//               //  employees.add(employee);
//        }
//
//
//





        System.out.println("908098 " + employees);
        return uuuiuiu;
        //return employees;
    }

    private static void writeString(String json,String path) {
        try (FileWriter file = new
                FileWriter(path)) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String listToJson(List<Employee> list) {

        Type listType = new TypeToken<List<Employee>>() {
        }.getType();

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
