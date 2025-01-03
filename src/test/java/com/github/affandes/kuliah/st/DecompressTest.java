package com.github.affandes.kuliah.st;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecompressTest {

    @Test
    public void testRunLengthDecode() throws IOException {
        // File input dan output mock
        String mockInputPath = "mockCompressed.txt";
        String mockOutputPath = "mockDecompressed.txt";

        // Buat file input dengan data terkompresi
        try (FileWriter writer = new FileWriter(mockInputPath)) {
            writer.write("a3b2\nc1d4\n");
        }

        // Jalankan fungsi decompress
        Decompress.decompress(mockInputPath, mockOutputPath);

        // Baca hasil decompressed dan periksa kesesuaian
        StringBuilder outputContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(mockOutputPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                outputContent.append(line).append("\n");
            }
        }

        // Hasil yang diharapkan
        String expectedOutput = "aaabb\ncdddd\n";
        assertEquals(expectedOutput, outputContent.toString());

        // Hapus file mock setelah pengujian
        Files.delete(Paths.get(mockInputPath));
        Files.delete(Paths.get(mockOutputPath));
    }
}
