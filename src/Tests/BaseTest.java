package Tests;

import Driver.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

@Slf4j
public class BaseTest {

    BaseTest() {
        DriverManager.getDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownTest() {
        log.info("Closing Browser");
        DriverManager.driver.quit();
        DriverManager.stopAppiumServer();
    }
}
