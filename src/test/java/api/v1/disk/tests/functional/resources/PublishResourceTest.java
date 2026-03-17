package api.v1.disk.tests.functional.resources;

import api.v1.disk.dto.Resource;
import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class PublishResourceTest extends BaseApiTest {

    @Nested
    public class Positive {

        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void publishExistingResource(String type) {
            Resource resource = testData.createResource(type);

            resourcesApi.publishResource(resource.getPath())
                    .then()
                    .statusCode(200);

            resourcesApi.listPublishedResources(Map.of())
                    .then()
                    .statusCode(200)
                    .body("items[0].name", equalTo(resource.getName()));
        }
    }

    @Nested
    public class Negative {

        @Test
        public void publishNotExistingResource() {
            resourcesApi.publishResource("/not/exist.txt")
                    .then()
                    .statusCode(404);
        }
    }
}
