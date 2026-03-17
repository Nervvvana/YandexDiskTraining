package api.v1.disk.tests.functional.operationstatus;

import api.v1.disk.dto.Resource;
import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class OperationStatusTest extends BaseApiTest {

    @Nested
    public class Positive {

        @Test
        public void getExistingOperationStatus() {
            Resource resource = testData.createResource("file");
            String copyPath = pathGenerator.namedFile("copy_" + resource.getName()).getPath();

            String href = resourcesApi.copyResource(Map.of(
                    "from", resource.getPath(),
                    "path", copyPath,
                    "force_async", true
                    ))
                    .getBody()
                    .jsonPath()
                    .get("href");

            String operationId = href.substring(href.lastIndexOf('/') + 1);

            operationStatusApi.operationStatus(operationId)
                    .then()
                    .statusCode(200);
        }

    }

    @Nested
    public class Negative {

        @Test
        public void getNotExistingOperationStatus() {
            operationStatusApi.operationStatus("not_existing_id")
                    .then()
                    .statusCode(404);
        }

    }

}
