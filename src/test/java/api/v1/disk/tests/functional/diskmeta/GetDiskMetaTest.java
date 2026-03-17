package api.v1.disk.tests.functional.diskmeta;

import api.v1.disk.methods.DiskMetaApi;
import api.v1.disk.config.TestConfig;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetDiskMetaTest {

    private final DiskMetaApi api = new DiskMetaApi();

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
