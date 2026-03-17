package api.v1.disk.methods;

import api.v1.disk.core.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

public class TrashApi {

    private static final String BASE = "/trash/resources";
    private static final String RESTORE = "/trash/resources/restore";

    public Response clearTrash(Map<String, Object> params) {
        return ApiClient.delete(BASE, params);
    }

    public Response getTrash(String path) {
        return ApiClient.get(BASE, Map.of(
                "path", path
        ));
    }

    public Response getTrash(Map<String, Object> params) {
        return ApiClient.get(BASE, params);
    }

    public Response restoreResource(String path) {
        return ApiClient.put(RESTORE, Map.of(
                "path", path
        ));
    }

    public Response restoreResource(Map<String, Object> params) {
        return ApiClient.put(RESTORE, params);
    }

}
