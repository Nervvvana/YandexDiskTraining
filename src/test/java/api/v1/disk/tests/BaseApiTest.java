package api.v1.disk.tests;

import api.v1.disk.methods.ResourcesApi;
import api.v1.disk.methods.TrashApi;
import api.v1.disk.services.PathGenerator;
import api.v1.disk.services.TestDataFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseApiTest {

    protected String ROOT;

    protected ResourcesApi resourcesApi = new ResourcesApi();

    protected TrashApi trashApi = new TrashApi();

    protected PathGenerator pathGenerator;

    protected TestDataFactory testData;

    @BeforeAll
    public void initSuite() {
        ROOT = "tests-" + UUID.randomUUID();
        resourcesApi.createFolder(ROOT)
                .then()
                .statusCode(201);

        pathGenerator = new PathGenerator(ROOT);
        testData = new TestDataFactory(resourcesApi, pathGenerator);
    }

    @AfterAll
    public void cleanupSuite() {
        resourcesApi.deleteResource(Map.of(
                "path", ROOT,
                "permanently", true
        ));

        trashApi.clearTrash(Map.of());
    }

}