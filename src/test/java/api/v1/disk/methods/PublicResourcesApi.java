package api.v1.disk.methods;

import api.v1.disk.core.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

public class PublicResourcesApi {

    private static final String BASE = "/public/resources";

    private static final String DOWNLOAD = BASE + "/download";
    private static final String SAVE = BASE + "/save-to-disk";

    @Step("GET запрос метаинформации о публичном ресурсе с public_key")
    public Response getResourceMeta(String key) {
        return ApiClient.get(BASE, Map.of(
                "public_key", key
        ));
    }

    @Step("GET запрос метаинформации о публичном ресурсе с параметрами")
    public Response getResourceMeta(Map<String, Object> params) {
        return ApiClient.get(BASE, params);
    }

    @Step("GET запрос ссылки на загрузку публичного ресурса с public_key")
    public Response getDownloadLink(String key) {
        return ApiClient.get(DOWNLOAD, Map.of(
                "public_key", key
        ));
    }

    @Step("POST запрос на сохранение публичного ресурса в Загрузки с public_key")
    public Response saveResource(String key) {
        return ApiClient.post(SAVE, Map.of(
                "public_key", key
        ));
    }

    @Step("POST запрос на сохранение публичного ресурса в параметрами")
    public Response saveResource(Map<String, Object> params) {
        return ApiClient.post(SAVE, params);
    }

}
