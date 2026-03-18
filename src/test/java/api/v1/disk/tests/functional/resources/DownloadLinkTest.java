package api.v1.disk.tests.functional.resources;

import api.v1.disk.tests.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.notNullValue;

public class DownloadLinkTest extends BaseApiTest {

    @Nested
    public class Positive {
        @Epic("Ресурсы (файлы и папки)")
        @Feature("Получить ссылку на скачивание ресурса")
        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void getDownloadLinkExistingFile(String type) {
            String path = testData.createResource(type).getPath();

            resourcesApi.getDownloadLink(path)
                    .then()
                    .statusCode(200)
                    .body("href", notNullValue());
        }

    }

    @Nested
    public class Negative {

        @Epic("Ресурсы (файлы и папки)")
        @Feature("Получить ссылку на скачивание ресурса")
        @Test
        public void downloadLinkNotExistingFile() {
            resourcesApi.getDownloadLink("/not/exist.txt")
                    .then()
                    .statusCode(404);
        }

    }

}
