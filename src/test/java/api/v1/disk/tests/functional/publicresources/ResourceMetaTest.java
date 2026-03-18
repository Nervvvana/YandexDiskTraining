package api.v1.disk.tests.functional.publicresources;

import api.v1.disk.dto.Resource;
import api.v1.disk.tests.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ResourceMetaTest extends BaseApiTest {

    @Nested
    public class Positive {

        @Epic("Публичные ресурсы")
        @Feature("Получение метаинформации о публичном ресурсе")
        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void getMetadataWithValidPublicKey(String type) {
            Resource resource = testData.createResource(type);

            resourcesApi.publishResource(resource.getPath())
                    .then()
                    .statusCode(200);

            String publicKey = resourcesApi.getResourceMeta(resource.getPath())
                    .getBody()
                    .jsonPath()
                    .get("public_key");

            publicResourcesApi.getResourceMeta(publicKey)
                    .then()
                    .statusCode(200);
        }

        @Epic("Публичные ресурсы")
        @Feature("Получение метаинформации о публичном ресурсе")
        @ParameterizedTest
        @ValueSource(strings = {"file", "folder"})
        public void getMetadataWithValidPublicUrl(String type) {
            Resource resource = testData.createResource(type);

            resourcesApi.publishResource(resource.getPath())
                    .then()
                    .statusCode(200);

            String publicUrl = resourcesApi.getResourceMeta(resource.getPath())
                    .getBody()
                    .jsonPath()
                    .get("public_url");

            publicResourcesApi.getResourceMeta(publicUrl)
                    .then()
                    .statusCode(200);
        }

    }

    @Nested
    public class Negative {

        @Epic("Публичные ресурсы")
        @Feature("Получение метаинформации о публичном ресурсе")
        @Test
        public void getMetadataWithInvalidPublicKey() {
            publicResourcesApi.getResourceMeta("invalid_key")
                    .then()
                    .statusCode(404);
        }

    }

}
