package api.v1.disk.tests.functional.diskmeta;

import api.v1.disk.methods.DiskMetaApi;
import api.v1.disk.config.TestConfig;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class DiskMetaTest {

    private final DiskMetaApi api = new DiskMetaApi();

    @Epic("Получить метаинформацию о диске")
    @Test
    public void getDiskMeta() {
        api.diskMeta()
                .then()
                .body(
                        "total_space",
                        equalTo(Long.parseLong(TestConfig.DISK_TOTAL_SPACE))
                );
    }

}
