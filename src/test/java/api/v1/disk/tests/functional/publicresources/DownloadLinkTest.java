package api.v1.disk.tests.functional.publicresources;

import api.v1.disk.dto.Resource;
import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class DownloadLinkTest extends BaseApiTest {

    @Nested
    public class Positive {

        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void getDownloadLinkWithValidPublicKey(String type) {
            Resource resource = testData.createResource(type);

            resourcesApi.publishResource(resource.getPath())
                    .then()
                    .statusCode(200);

            String publicKey = resourcesApi.getResourceMeta(resource.getPath())
                    .getBody()
                    .jsonPath()
                    .get("public_key");

            publicResourcesApi.getDownloadLink(publicKey)
                    .then()
                    .statusCode(200);
        }

        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void getDownloadLinkWithValidPublicUrl(String type) {
            Resource resource = testData.createResource(type);

            resourcesApi.publishResource(resource.getPath())
                    .then()
                    .statusCode(200);

            String publicUrl = resourcesApi.getResourceMeta(resource.getPath())
                    .getBody()
                    .jsonPath()
                    .get("public_url");

            publicResourcesApi.getDownloadLink(publicUrl)
                    .then()
                    .statusCode(200);
        }

    }

    @Nested
    public class Negative {

        @Test
        public void getDownloadLinkWithInvalidPublicKey() {
            publicResourcesApi.getDownloadLink("invalid_key")
                    .then()
                    .statusCode(404);
        }

    }

}
