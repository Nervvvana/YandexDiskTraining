package api.v1.disk.config;

import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = TestConfig.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (is != null) {
                props.load(is);
            }

        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки файла application.properties", e);
        }
    }

    private TestConfig() {}

    private static String get(String key, String defaultValue) {
        String value = System.getProperty(key);
        if (value != null && !value.isBlank()) {
            return value;
        }

        value = props.getProperty(key);
        if (value != null && !value.isBlank()) {
            return value;
        }

        return defaultValue;
    }

    public static final String BASE_URI =
            get("base.url", "https://storage.net");

    public static final String BASE_PATH =
            get("base.path", "/v1/disk");

    public static final String DISK_TOTAL_SPACE =
            get("disk.total.space", "5368709120");

    public static final String OAUTH_TOKEN =
            get("oauth.token", null);
}