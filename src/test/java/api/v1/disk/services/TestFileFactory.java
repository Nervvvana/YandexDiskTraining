package api.v1.disk.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class TestFileFactory {

    public static File smallTextFile() {

        try {

            File file = File.createTempFile("test-", ".txt");

            try (FileWriter writer = new FileWriter(file)) {
                writer.write("autotest-file-" + UUID.randomUUID());
            }

            file.deleteOnExit();

            return file;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}