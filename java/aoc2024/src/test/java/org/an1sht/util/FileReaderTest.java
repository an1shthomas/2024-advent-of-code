package org.an1sht.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class FileReaderTest {
    
    @Test
    void testInvalidDayThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> FileReader.readLines(0, "test.txt"));
        assertThrows(IllegalArgumentException.class, () -> FileReader.readLines(26, "test.txt"));
        assertThrows(IllegalArgumentException.class, () -> FileReader.readLinesStream(0, "test.txt"));
        assertThrows(IllegalArgumentException.class, () -> FileReader.readLinesStream(26, "test.txt"));
    }
    
    @Test
    void testReadExistingFile() throws IOException {
        // This test assumes you have day06/example-input.txt in your input directory
        List<String> lines = FileReader.readLines(7, "example-input.txt");
        assertNotNull(lines);
        assertFalse(lines.isEmpty());
        
        try (Stream<String> linesStream = FileReader.readLinesStream(7, "example-input.txt")) {
            assertTrue(linesStream.count() > 0);
        }
    }
    
    @Test
    void testNonExistentFileThrowsException() {
        assertThrows(IOException.class, () -> FileReader.readLines(1, "non-existent.txt"));
        assertThrows(IOException.class, () -> FileReader.readLinesStream(1, "non-existent.txt"));
    }
}
