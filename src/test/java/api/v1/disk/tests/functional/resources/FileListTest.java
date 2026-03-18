package api.v1.disk.tests.functional.resources;

import api.v1.disk.tests.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class FileListTest extends BaseApiTest {

    @Epic("Ресурсы (файлы и папки)")
    @Feature("Получить список файлов")
    @Test
    public void listFilesSortedByName() {

        testData.createNamedFile("b.txt");
        testData.createNamedFile("a.txt");

        resourcesApi.listFiles(Map.of())
                .then()
                .body("items[0].name", equalTo("a.txt"));
    }

    @Epic("Ресурсы (файлы и папки)")
    @Feature("Получить список файлов")
    @Test
    public void listFilesSortedByUploadDate() {

        testData.createNamedFile("1.txt");
        testData.createNamedFile("2.txt");

        resourcesApi.listLastUploaded(Map.of())
                .then()
                .body("items[0].name", equalTo("2.txt"));
    }

}
