package api.v1.disk.tests.functional.trash;

import api.v1.disk.tests.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearTrashTest extends BaseApiTest {

    @BeforeEach
    public void cleanupTrash() {
        trashApi.clearTrash(Map.of())
                .then()
                .statusCode(anyOf(is(202), is(204)));
    }

    @Nested
    public class Positive {

        @Epic("Корзина")
        @Feature("Очистить корзину")
        @Test
        public void clearNonEmptyTrash() {
            String path = testData.createResource("file").getPath();

            resourcesApi.deleteResource(path)
                    .then()
                    .statusCode(anyOf(is(202), is(204)));

            trashApi.clearTrash(Map.of())
                    .then()
                    .statusCode(anyOf(is(202), is(204)));

            assertTrue(trashApi.getTrash("/")
                    .getBody()
                    .jsonPath()
                    .getList("_embedded.items")
                    .isEmpty()
            );
        }

        @Epic("Корзина")
        @Feature("Очистить корзину")
        @Test
        public void clearEmptyTrash() {
            assertTrue(trashApi.getTrash("/")
                    .getBody()
                    .jsonPath()
                    .getList("_embedded.items")
                    .isEmpty()
            );

            trashApi.clearTrash(Map.of())
                    .then()
                    .statusCode(204);
        }

    }

    @Nested
    public class Negative {

        @Epic("Корзина")
        @Feature("Очистить корзину")
        @Test
        public void clearTrashWithInvalidResourcePath() {
            trashApi.clearTrash(Map.of("path", "invalid_path"))
                    .then()
                    .statusCode(404);
        }
    }

}
