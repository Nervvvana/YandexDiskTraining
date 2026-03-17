package api.v1.disk.tests.functional.trash;

import api.v1.disk.methods.ResourcesApi;
import api.v1.disk.methods.TrashApi;
import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetTrashTest extends BaseApiTest {

    private final ResourcesApi resourcesApi = new ResourcesApi();
    private final TrashApi trashApi = new TrashApi();

    @Nested
    public class Positive {

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

    }

}
