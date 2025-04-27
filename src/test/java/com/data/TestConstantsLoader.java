package com.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TestConstantsLoader {
    private static Map<String, String> constants;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            constants = mapper.readValue(
                    new File("src/test/resources/test-constants.json"),
                    Map.class
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test constants", e);
        }
    }

    public static String get(String key) {
        return constants.get(key);
    }
}