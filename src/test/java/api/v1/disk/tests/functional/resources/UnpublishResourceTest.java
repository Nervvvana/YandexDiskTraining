package api.v1.disk.tests.functional.resources;

import api.v1.disk.dto.Resource;
import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnpublishResourceTest extends BaseApiTest {

    @Nested
    public class Positive {

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



    }
}
