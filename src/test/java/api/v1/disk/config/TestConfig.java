package api.v1.disk.config;

public final class TestConfig {

    private TestConfig() {}

    private static String getProperty(String key, String defaultValue) {

        String value = System.getProperty(key);

        if (value == null || value.isBlank()) {
            value = System.getenv(key);
        }

        return value != null ? value : defaultValue;
    }

    public static final String BASE_URI =
            getProperty("BASE_URL", "https://cloud-api.yandex.net");

    public static final String BASE_PATH =
            getProperty("BASE_PATH", "/v1/disk");

    public static final String OAUTH_TOKEN =
            getProperty("OAUTH_TOKEN", "");

    public static final String DISK_TOTAL_SPACE =
            getProperty("DISK_TOTAL_SPACE", "5368709120");

}
