package src.main.java.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ValvetFileManager {

    private static final String FILE_PATH = "src/data/data.json";
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void saveBank(Valvet valv) {
        try {
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper.writeValue(new File(FILE_PATH), valv);
            System.out.println("Bank data saved to " + FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Valvet loadBank() {
        try {
            return objectMapper.readValue(new File(FILE_PATH), Valvet.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}