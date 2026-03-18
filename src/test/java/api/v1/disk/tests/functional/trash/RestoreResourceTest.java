package api.v1.disk.tests.functional.trash;

import api.v1.disk.dto.Resource;
import api.v1.disk.tests.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class RestoreResourceTest extends BaseApiTest {

    @Nested
    public class Positive {

        @Epic("Корзина")
        @Feature("Восстановить файл из корзины")
        @Test
        public void restoreResourceWithOverwriting() {

            Resource resource = testData.createResource("file");

            resourcesApi.deleteResource(resource.getPath())
                    .then()
                    .statusCode(anyOf(is(202), is(204)));

            testData.createNamedFile(resource.getName());

            String trashPath = trashApi.getTrash("/")
                    .getBody()
                    .jsonPath()
                    .get("_embedded.items[0].path");

            trashApi.restoreResource(Map.of(
                    "path", trashPath,
                    "overwriting", true
            ))
                    .then()
                    .statusCode(anyOf(is(201), is(202)));

        }

    }

    @Nested
    public class Negative {

        @Epic("Корзина")
        @Feature("Восстановить файл из корзины")
        @Test
        public void restoreNotExistingResource() {

            trashApi.restoreResource("/not/exist.txt")
                    .then()
                    .statusCode(404);

        }

    }

}
