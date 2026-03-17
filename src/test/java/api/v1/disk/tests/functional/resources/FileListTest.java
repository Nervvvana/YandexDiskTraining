package api.v1.disk.tests.functional.resources;

import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class FileListTest extends BaseApiTest {

    @Test
    public void listFilesSortedByName() {

        testData.createNamedFile("b.txt");
        testData.createNamedFile("a.txt");

        resourcesApi.listFiles(Map.of())
                .then()
                .body("items[0].name", equalTo("a.txt"));
    }

    @Test
    public void listFilesSortedByUploadDate() {

        testData.createNamedFile("1.txt");
        testData.createNamedFile("2.txt");

        resourcesApi.listLastUploaded(Map.of())
                .then()
                .body("items[0].name", equalTo("2.txt"));
    }
}
