package api.v1.disk.tests.functional.resources;

import api.v1.disk.dto.Resource;
import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteResourceTest extends BaseApiTest {

    @Nested
    public class Positive {

        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void deleteExistingResource(String type) {
            Resource resource = testData.createResource(type);

            resourcesApi.deleteResource(resource.getPath())
                    .then()
                    .statusCode(anyOf(is(202), is(204)));

            assertTrue(trashApi.getTrash(Map.of(
                    "path", "/",
                    "fields", "_embedded.items.name"
                    ))
                    .jsonPath()
                    .getList("_embedded.items.name")
                    .contains(resource.getName()));
        }

        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void deleteResourcePermanently(String type) {
            String path = testData.createResource(type).getPath();

            resourcesApi.deleteResource(Map.of(
                    "path", path,
                    "permanently", true
            ));

            resourcesApi.getResourceMeta(path)
                    .then()
                    .statusCode(404);
        }
    }

    @Nested
    public class Negative {

        @Test
        public void deleteNotExistingResource() {
            resourcesApi.deleteResource("/not/exist.txt")
                    .then()
                    .statusCode(404);
        }
    }
}
