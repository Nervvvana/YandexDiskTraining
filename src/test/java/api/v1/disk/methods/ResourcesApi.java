package api.v1.disk.methods;

import io.restassured.response.Response;
import api.v1.disk.core.ApiClient;

import java.io.File;
import java.util.Map;

public class ResourcesApi {

    private static final String BASE = "/resources";

    private static final String COPY = BASE + "/copy";
    private static final String MOVE = BASE + "/move";
    private static final String DOWNLOAD = BASE + "/download";
    private static final String FILES = BASE + "/files";
    private static final String LAST_UPLOADED = BASE + "/last-uploaded";
    private static final String PUBLIC = BASE + "/public";
    private static final String PUBLISH = BASE + "/publish";
    private static final String UNPUBLISH = BASE + "/unpublish";
    private static final String SHORT_INFO = BASE + "/short-info";
    private static final String UPLOAD = BASE + "/upload";

    public Response deleteResource(String path) {
        return ApiClient.delete(BASE, Map.of("path", path));
    }

    public Response deleteResource(Map<String, Object> params) {
        return ApiClient.delete(BASE, params);
    }

    public Response getResourceMeta(String path) {
        return ApiClient.get(BASE, Map.of("path", path));
    }

    public Response getResourceMeta(Map<String, Object> params) {
        return ApiClient.get(BASE, params);
    }

    public Response createFolder(String path) {
        return ApiClient.put(BASE, Map.of("path", path));
    }

    public Response copyResource(String from, String path) {
        return ApiClient.post(COPY, Map.of(
                "from", from,
                "path", path
        ));
    }

    public Response copyResource(Map<String, Object> params) {
        return ApiClient.post(COPY, params);
    }

    public Response getDownloadLink(String path) {
        return ApiClient.get(DOWNLOAD, Map.of("path", path));
    }

    public Response listFiles(Map<String, Object> params) {
        return ApiClient.get(FILES, params);
    }

    public Response listLastUploaded(Map<String, Object> params) {
        return ApiClient.get(LAST_UPLOADED, params);
    }

    public Response moveResource(String from, String path) {
        return ApiClient.post(MOVE, Map.of(
                "from", from,
                "path", path
        ));
    }

    public Response moveResource(Map<String, Object> params) {
        return ApiClient.post(MOVE, params);
    }

    public Response listPublishedResources(Map<String, Object> params) {
        return ApiClient.get(PUBLIC, params);
    }

    public Response publishResource(String path) {
        return ApiClient.put(PUBLISH, Map.of("path", path));
    }

    public Response getUserAuthorities(String path) {
        return ApiClient.get(SHORT_INFO, Map.of("path", path));
    }

    public Response unpublishResource(String path) {
        return ApiClient.put(UNPUBLISH, Map.of("path", path));
    }

    public Response getUploadLink(String path) {
        return ApiClient.get(UPLOAD, Map.of("path", path));
    }

    public Response uploadByLink(String link, File file) {
        return ApiClient.putFile(link, file);
    }

    public Response uploadByUrl(String path, String url) {
        return ApiClient.post(UPLOAD, Map.of(
                "path", path,
                "url", url
        ));
    }
}