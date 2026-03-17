package api.v1.disk.methods;

import api.v1.disk.core.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

public class DiskMetaApi {

    public Response diskMeta() {
        return ApiClient.get("", Map.of());
    }

}
