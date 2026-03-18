package api.v1.disk.tests;

import api.v1.disk.methods.*;
import api.v1.disk.services.PathGenerator;
import api.v1.disk.services.TestDataFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;
import java.util.UUID;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseApiTest {

    protected String ROOT;

    protected ResourcesApi resourcesApi = new ResourcesApi();

    protected PublicResourcesApi publicResourcesApi = new PublicResourcesApi();

    protected TrashApi trashApi = new TrashApi();

    protected OperationStatusApi operationStatusApi = new OperationStatusApi();

    protected DiskMetaApi diskMetaApi = new DiskMetaApi();

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
                ))
                .then()
                .statusCode(anyOf(is(202), is(204)));

        trashApi.clearTrash(Map.of())
                .then()
                .statusCode(anyOf(is(202), is(204)));
    }

}