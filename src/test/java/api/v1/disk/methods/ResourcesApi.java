package api.v1.disk.methods;

import io.qameta.allure.Step;
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

    @Step("DELETE запрос удаления ресурса в корзину")
    public Response deleteResource(String path) {
        return ApiClient.delete(BASE, Map.of("path", path));
    }

    @Step("DELETE запрос удаления ресурса с параметрами")
    public Response deleteResource(Map<String, Object> params) {
        return ApiClient.delete(BASE, params);
    }

    @Step("GET запрос метаинформации о ресурсе")
    public Response getResourceMeta(String path) {
        return ApiClient.get(BASE, Map.of("path", path));
    }

    @Step("GET запрос метаинформации о ресурсе с параметрами")
    public Response getResourceMeta(Map<String, Object> params) {
        return ApiClient.get(BASE, params);
    }

    @Step("PUT запрос создания папки")
    public Response createFolder(String path) {
        return ApiClient.put(BASE, Map.of("path", path));
    }

    @Step("POST запрос копирования ресурса из FROM в PATH")
    public Response copyResource(String from, String path) {
        return ApiClient.post(COPY, Map.of(
                "from", from,
                "path", path
        ));
    }

    @Step("POST запрос копирования ресурса с параметрами")
    public Response copyResource(Map<String, Object> params) {
        return ApiClient.post(COPY, params);
    }

    @Step("GET запрос ссылки на скачивание ресурса")
    public Response getDownloadLink(String path) {
        return ApiClient.get(DOWNLOAD, Map.of("path", path));
    }

    @Step("GET запрос списка файлов, упорядоченного по имени с параметрами")
    public Response listFiles(Map<String, Object> params) {
        return ApiClient.get(FILES, params);
    }

    @Step("GET запрос списка файлов, упорядоченного по дате загрузки с параметрами")
    public Response listLastUploaded(Map<String, Object> params) {
        return ApiClient.get(LAST_UPLOADED, params);
    }

    @Step("POST запрос перемещения ресурса из FROM в PATH")
    public Response moveResource(String from, String path) {
        return ApiClient.post(MOVE, Map.of(
                "from", from,
                "path", path
        ));
    }

    @Step("POST запрос перемещения ресурса с параметрами")
    public Response moveResource(Map<String, Object> params) {
        return ApiClient.post(MOVE, params);
    }

    @Step("GET запрос списка опубликованных ресурсов с параметрами")
    public Response listPublishedResources(Map<String, Object> params) {
        return ApiClient.get(PUBLIC, params);
    }

    @Step("PUT запрос публикации ресурса")
    public Response publishResource(String path) {
        return ApiClient.put(PUBLISH, Map.of("path", path));
    }

    @Step("GET запрос проверки доступа пользователя к публичному ресурсу")
    public Response getUserAuthorities(String path) {
        return ApiClient.get(SHORT_INFO, Map.of("path", path));
    }

    @Step("PUT запрос отмены публикации ресурса")
    public Response unpublishResource(String path) {
        return ApiClient.put(UNPUBLISH, Map.of("path", path));
    }

    @Step("GET запрос ссылки на загрузку файла")
    public Response getUploadLink(String path) {
        return ApiClient.get(UPLOAD, Map.of("path", path));
    }

    @Step("PUT запрос загрузки файла по полученной ссылке")
    public Response uploadByLink(String link, File file) {
        return ApiClient.putFile(link, file);
    }

    @Step("POST запрос загрузки файла по внешней ссылке")
    public Response uploadByUrl(String path, String url) {
        return ApiClient.post(UPLOAD, Map.of(
                "path", path,
                "url", url
        ));
    }
}