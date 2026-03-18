package api.v1.disk.methods;

import api.v1.disk.core.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

public class TrashApi {

    private static final String BASE = "/trash/resources";
    private static final String RESTORE = "/trash/resources/restore";

    @Step("DELETE запрос очистки корзины с параметрами")
    public Response clearTrash(Map<String, Object> params) {
        return ApiClient.delete(BASE, params);
    }

    @Step("GET запрос получения содержимого корзины")
    public Response getTrash(String path) {
        return ApiClient.get(BASE, Map.of(
                "path", path
        ));
    }

    @Step("GET запрос получения содержимого корзины с параметрами")
    public Response getTrash(Map<String, Object> params) {
        return ApiClient.get(BASE, params);
    }

    @Step("PUT запрос восстановления файла из корзины")
    public Response restoreResource(String path) {
        return ApiClient.put(RESTORE, Map.of(
                "path", path
        ));
    }

    @Step("PUT запрос восстановления файла из корзины с рараметрами")
    public Response restoreResource(Map<String, Object> params) {
        return ApiClient.put(RESTORE, params);
    }

}
