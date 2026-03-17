package api.v1.disk.tests.functional.resources;

import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.notNullValue;

public class DownloadLinkTest extends BaseApiTest {

    @ParameterizedTest
    @ValueSource(strings = {"file", "folder"})
    public void getDownloadLinkExistingFile(String type) {
        String path = testData.createResource(type).getPath();

        resourcesApi.getDownloadLink(path)
                .then()
                .statusCode(200)
                .body("href", notNullValue());
    }

    @Test
    public void downloadLinkNotExistingFile() {
        resourcesApi.getDownloadLink("/not/exist.txt")
                .then()
                .statusCode(404);
    }
}
