package org.an1sht.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReader {
    private static final Logger logger = LoggerFactory.getLogger(FileReader.class);
    private static final String INPUT_DIR = "input";
    
    /**
     * Reads all lines from a file in the input directory for a specific day
     * @param day the day number (1-25)
     * @param fileName the name of the input file
     * @return List of strings, each representing a line in the file
     * @throws IOException if the file cannot be read
     * @throws IllegalArgumentException if day is not between 1 and 25
     */
    public static List<String> readLines(int day, String fileName) throws IOException {
        validateDay(day);
        String resourcePath = getResourcePath(day, fileName);
        logger.debug("Reading all lines from resource: {}", resourcePath);
        
        try (InputStream is = FileReader.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            if (is == null) {
                String message = "Resource not found: " + resourcePath;
                logger.error(message);
                throw new IOException(message);
            }
            
            List<String> lines = reader.lines().collect(Collectors.toList());
            logger.debug("Read {} lines from resource: {}", lines.size(), resourcePath);
            return lines;
        }
    }
    
    /**
     * Returns a Stream of lines from a file in the input directory for a specific day.
     * The returned stream MUST be used within a try-with-resources block to ensure proper resource cleanup.
     * 
     * Example usage:
     * try (Stream<String> lines = readLinesStream(7, "input.txt")) {
     *     lines.forEach(System.out::println);
     * }
     * 
     * @param day the day number (1-25)
     * @param fileName the name of the input file
     * @return Stream of strings, each representing a line in the file
     * @throws IOException if the file cannot be read
     * @throws IllegalArgumentException if day is not between 1 and 25
     */
    public static Stream<String> readLinesStream(int day, String fileName) throws IOException {
        validateDay(day);
        String resourcePath = getResourcePath(day, fileName);
        logger.debug("Opening stream for resource: {}", resourcePath);
        
        InputStream is = FileReader.class.getClassLoader().getResourceAsStream(resourcePath);
        if (is == null) {
            String message = "Resource not found: " + resourcePath;
            logger.error(message);
            throw new IOException(message);
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        return reader.lines().onClose(() -> {
            try {
                reader.close();
                is.close();
            } catch (IOException e) {
                logger.warn("Error closing resources: {}", e.getMessage());
            }
        });
    }
    
    private static void validateDay(int day) {
        if (day < 1 || day > 25) {
            logger.error("Invalid day number: {}. Day must be between 1 and 25", day);
            throw new IllegalArgumentException("Day must be between 1 and 25");
        }
    }
    
    private static String getResourcePath(int day, String fileName) {
        String dayDir = String.format("day%02d", day); // formats day as "day01", "day02", etc.
        String resourcePath = String.format("%s/%s/%s", INPUT_DIR, dayDir, fileName);
        logger.trace("Constructed resource path: {}", resourcePath);
        return resourcePath;
    }
}
