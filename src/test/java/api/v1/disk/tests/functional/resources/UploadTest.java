package api.v1.disk.tests.functional.resources;

import api.v1.disk.services.TestFileFactory;
import api.v1.disk.tests.BaseApiTest;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class UploadTest extends BaseApiTest {

    @Test
    public void uploadFileByUploadLink() {

        String path = pathGenerator.resource("file").getPath();

        String uploadLink = resourcesApi.getUploadLink(path)
                .then()
                .extract()
                .path("href");

        File file = TestFileFactory.smallTextFile();

        resourcesApi.uploadByLink(uploadLink, file)
                .then()
                .statusCode(anyOf(is(201), is(202)));
    }

    @Test
    public void uploadFileByExternalUrl() {

        resourcesApi.uploadByUrl(
                        pathGenerator.namedFile("kitten.png").getPath(),
                        "https://img.freepik.com/premium-vector/cute-black-cat-sitting-vector_1163677-4913.jpg?semt=ais_hybrid"
                )
                .then()
                .statusCode(202);
    }
}
