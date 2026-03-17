package api.v1.disk.methods;

import api.v1.disk.core.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

public class PublicResourcesApi {

    private static final String BASE = "/public/resources";

    private static final String DOWNLOAD = BASE + "/download";
    private static final String SAVE = BASE + "/save-to-disk";

    public Response getResourceMeta(String key) {
        return ApiClient.get(BASE, Map.of(
                "public_key", key
        ));
    }

    public Response getResourceMeta(Map<String, Object> params) {
        return ApiClient.get(BASE, params);
    }

    public Response getDownloadLink(String key) {
        return ApiClient.get(DOWNLOAD, Map.of(
                "public_key", key
        ));
    }

    public Response saveResourceToDownloads(String key) {
        return ApiClient.post(SAVE, Map.of(
                "public_key", key
        ));
    }

    public Response saveResourceToDownloads(Map<String, Object> params) {
        return ApiClient.post(SAVE, params);
    }

}
