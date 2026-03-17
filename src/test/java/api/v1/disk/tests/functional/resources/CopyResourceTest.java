package api.v1.disk.tests.functional.resources;

import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;


public class CopyResourceTest extends BaseApiTest {

    @Nested
    public class Positive {

        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void copyResourceDifferentPath(String type) {
            String source = testData.createResource(type).getPath();
            String target = pathGenerator.resource(type).getPath();

            resourcesApi.copyResource(source, target)
                    .then()
                    .statusCode(anyOf(is(201), is(202)));

            resourcesApi.getResourceMeta(target)
                    .then()
                    .statusCode(200);
        }
    }

    @Nested
    public class Negative {

        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void copySamePathWithoutOverwrite(String type) {
            String path = testData.createResource(type).getPath();

            resourcesApi.copyResource(path, path)
                    .then()
                    .statusCode(409);
        }

        @Test
        public void copyNotExistingResource() {
            resourcesApi.copyResource("/not/exist.txt", "/target.txt")
                    .then()
                    .statusCode(404);
        }
    }
}