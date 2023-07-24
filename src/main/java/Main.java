import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = listToJson(list);
        writeString(json);
    }

    private static void writeString(String json) {
        try (FileWriter file = new
                FileWriter("data.json")) {
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

        List<Employee> employee = null;

        try (CSVReader reader = new CSVReader(new FileReader("data.csv"))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();

            employee = csv.parse();

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
        return employee;
    }
}
