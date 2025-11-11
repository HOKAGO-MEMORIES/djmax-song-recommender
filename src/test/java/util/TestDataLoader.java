package util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestDataLoader {

    private TestDataLoader() {
    }

    public static String loadJson(String fileName) {
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

            if (is == null) {
                throw new RuntimeException("Test file not found: " + fileName);
            }

            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test file: " + fileName, e);
        }
    }
}
