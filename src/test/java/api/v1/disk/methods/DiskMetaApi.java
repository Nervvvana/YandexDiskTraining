package api.v1.disk.methods;

import api.v1.disk.core.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

public class DiskMetaApi {

    @Step("GET запрос метаинформации о диске")
    public Response diskMeta() {
        return ApiClient.get("", Map.of());
    }

}
