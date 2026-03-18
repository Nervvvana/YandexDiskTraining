package api.v1.disk.methods;

import api.v1.disk.core.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

public class OperationStatusApi {

    private static final String BASE = "/operations";

    @Step("GET запрос статуса операции")
    public Response operationStatus(String operationId) {
        return ApiClient.get(BASE + "/" + operationId, Map.of());
    }

}
