package api.v1.disk.tests.functional.resources;

import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.equalTo;

public class MetadataTest extends BaseApiTest {

    @Nested
    public class Positive {

        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void getMetadataExistingResource(String type) {
            String path = testData.createResource(type).getPath();

            resourcesApi.getResourceMeta(path)
                    .then()
                    .statusCode(200)
                    .body("path", equalTo("disk:/" + path));
        }
    }

    @Nested
    public class Negative {

        @Test
        public void metadataNotExistingResource() {
            resourcesApi.getResourceMeta("/not/exist.txt")
                    .then()
                    .statusCode(404);
        }
    }
}
