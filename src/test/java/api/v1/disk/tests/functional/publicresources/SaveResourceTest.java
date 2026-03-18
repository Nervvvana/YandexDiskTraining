package api.v1.disk.tests.functional.publicresources;

import api.v1.disk.dto.Resource;
import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class SaveResourceTest extends BaseApiTest {

    @AfterEach
    public void cleanupDownloads() {
        resourcesApi.deleteResource(Map.of(
                "path","disk:/Загрузки",
                "permanently", true
                ))
                .then()
                .statusCode(anyOf(is(202), is(204)));
    }

    @Nested
    public class Positive {

        @Test
        public void savePublicResourceToDefaultPath() {
            Resource resource = testData.createResource("file");

            resourcesApi.publishResource(resource.getPath())
                    .then()
                    .statusCode(200);

            String publicKey = resourcesApi.getResourceMeta(resource.getPath())
                    .getBody()
                    .jsonPath()
                    .get("public_key");

            publicResourcesApi.saveResource(Map.of(
                    "public_key", publicKey
                    ))
                    .then()
                    .statusCode(anyOf(is(201), is(202)));

            resourcesApi.getResourceMeta("disk:/Загрузки/" + resource.getName())
                    .then()
                    .statusCode(200);
        }

        @Test
        public void savePublicResourceToCustomPath() {
            Resource resource = testData.createResource("file");

            resourcesApi.publishResource(resource.getPath())
                    .then()
                    .statusCode(200);

            String publicKey = resourcesApi.getResourceMeta(resource.getPath())
                    .getBody()
                    .jsonPath()
                    .get("public_key");

            String customPath = testData.createResource("folder").getPath();

            publicResourcesApi.saveResource(Map.of(
                    "public_key", publicKey,
                    "save_path", customPath
                    ))
                    .then()
                    .statusCode(anyOf(is(201), is(202)));

            resourcesApi.getResourceMeta(customPath + "/" + resource.getName())
                    .then()
                    .statusCode(200);
        }

    }

    @Nested
    public class Negative {

        @Test
        public void savePublicResourceWithInvalidPublicKey() {
            publicResourcesApi.saveResource("invalid_key")
                    .then()
                    .statusCode(404);
        }

    }

}
