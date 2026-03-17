package api.v1.disk.tests.functional.resources;

import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CreateFolderTest extends BaseApiTest {

    @Nested
    public class Positive {

        @Test
        public void createFolderValidPath() {
            String path = pathGenerator.resource("folder").getPath();

            resourcesApi.createFolder(path)
                    .then()
                    .statusCode(201);

            resourcesApi.getResourceMeta(path)
                    .then()
                    .statusCode(200);
        }
    }

    @Nested
    public class Negative {

        @Test
        public void createFolderEmptyPath() {
            resourcesApi.createFolder("")
                    .then()
                    .statusCode(400);
        }

        @Test
        public void createExistingFolder() {
            String path = pathGenerator.resource("folder").getPath();

            resourcesApi.createFolder(path);

            resourcesApi.createFolder(path)
                    .then()
                    .statusCode(409);
        }
    }
}
