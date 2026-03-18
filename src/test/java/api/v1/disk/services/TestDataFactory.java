package api.v1.disk.services;

import api.v1.disk.methods.ResourcesApi;
import api.v1.disk.dto.Resource;
import io.qameta.allure.Step;

import java.io.File;

public class TestDataFactory {

    private final ResourcesApi api;
    private final PathGenerator pathGenerator;

    public TestDataFactory(ResourcesApi api, PathGenerator pathGenerator) {
        this.api = api;
        this.pathGenerator = pathGenerator;
    }

    @Step("Создать ресурс с типом \"{type}\"")
    public Resource createResource(String type) {

        if ("folder".equals(type)) {
            return createFolder();
        }

        return createFile();
    }

    @Step("Создать именнованный файл \"{name}\"")
    public Resource createNamedFile(String name) {

        Resource resource = pathGenerator.namedFile(name);

        String uploadLink = api.getUploadLink(resource.getPath())
                .then()
                .extract()
                .path("href");

        File file = TestFileFactory.smallTextFile();

        api.uploadByLink(uploadLink, file);

        return resource;
    }

    private Resource createFile() {

        Resource resource = pathGenerator.resource("file");

        String uploadLink = api.getUploadLink(resource.getPath())
                .then()
                .statusCode(200)
                .extract()
                .path("href");

        File file = TestFileFactory.smallTextFile();

        api.uploadByLink(uploadLink, file);

        return resource;
    }

    private Resource createFolder() {

        Resource resource = pathGenerator.resource("folder");

        api.createFolder(resource.getPath());

        return resource;
    }
}
