package api.v1.disk.tests.functional.trash;

import api.v1.disk.methods.ResourcesApi;
import api.v1.disk.methods.TrashApi;
import api.v1.disk.tests.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetTrashTest extends BaseApiTest {

    @Nested
    public class Positive {

        @Epic("Корзина")
        @Feature("Получить содержимое корзины")
        @Test
        public void getNonEmptyTrash() {
            String path = testData.createResource("file").getPath();

            resourcesApi.deleteResource(path)
                    .then()
                    .statusCode(204);

            assertFalse(trashApi.getTrash("/")
                    .getBody()
                    .jsonPath()
                    .getList("_embedded.items")
                    .isEmpty()
            );

        }

        @Epic("Корзина")
        @Feature("Получить содержимое корзины")
        @Test
        public void getEmptyTrash() {
            assertTrue(trashApi.getTrash("/")
                    .getBody()
                    .jsonPath()
                    .getList("_embedded.items")
                    .isEmpty()
            );

        }

    }

    @Nested
    public class Negative {

        @Epic("Корзина")
        @Feature("Получить содержимое корзины")
        @Test
        public void getNotExistingResourceInTrash() {
            trashApi.getTrash("/not/exist.txt")
                    .then()
                    .statusCode(404);
        }

    }

}
