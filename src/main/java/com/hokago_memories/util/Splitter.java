package com.hokago_memories.util;

import java.util.Arrays;
import java.util.List;

public class Splitter {

    private static final String DELIMITER = ",";

    private Splitter() {
    }

    public static List<String> split(String input) {
        return Arrays.stream(input.split(DELIMITER)).toList();
    }
}
