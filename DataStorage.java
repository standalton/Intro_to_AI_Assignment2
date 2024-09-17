import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private static final String FILE_NAME = "survey_data.csv";

    public static void saveResponse(String[] responses, String party) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            for (int i = 0; i < responses.length; i++) {
                writer.write(responses[i].trim());
                if (i < responses.length - 1) {
                    writer.write(", ");
                }
            }
            writer.write(", " + party.trim());
            writer.newLine();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static List<String[]> loadResponses() {
        List<String[]> data = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("The file does not exist. No data to load.");
            return data;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.split(",\\s*"));
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return data;
    }
}
