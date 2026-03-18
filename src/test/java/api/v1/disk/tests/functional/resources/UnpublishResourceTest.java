package api.v1.disk.tests.functional.resources;

import api.v1.disk.dto.Resource;
import api.v1.disk.tests.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnpublishResourceTest extends BaseApiTest {

    @Nested
    public class Positive {

        @Epic("Ресурсы (файлы и папки)")
        @Feature("Отменить публикацию ресурса")
        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void unpublishPublishedResource(String type) {
            Resource resource = testData.createResource(type);

            resourcesApi.publishResource(resource.getPath());

            resourcesApi.unpublishResource(resource.getPath())
                    .then()
                    .statusCode(200);

            assertTrue(resourcesApi.listPublishedResources(Map.of())
                    .getBody()
                    .jsonPath()
                    .getList("items")
                    .isEmpty()
            );
        }
    }

    @Nested
    public class Negative {

        @Epic("Ресурсы (файлы и папки)")
        @Feature("Отменить публикацию ресурса")
        @Test
        public void unpublishNotExistingResource() {
            resourcesApi.unpublishResource("/not/exist.txt")
                    .then()
                    .statusCode(404);
        }

    }
}
