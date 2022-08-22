package Driver;

import Utilities.CapabilitiesReader;
import Utilities.ReadProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import lombok.extern.slf4j.Slf4j;
import resources.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DriverManager {
    public static AppiumDriverLocalService service;
    public static AppiumDriver driver;
    public static URL url;
    public static int port = Constants.APPIUM_PORT;
    public static ReadProperties properties = ReadProperties.getInstance();

    public static AppiumDriverLocalService startAppiumServer() {
        boolean flag = checkIfServerIsRunnning(port);
        if (!flag) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withArgument(() -> "--base-path", "/wd/hub").usingPort(port));
            service.start();
        }
        return service;
    }

    public static boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    private static synchronized AppiumDriver setup() {
        String cap_path = Constants.CAPABILITIES_FILE_PATH;
        String username = System.getenv("SAUCE_USERNAME");
        String accessKey = System.getenv("SAUCE_ACCESS_KEY");
        String region = Constants.SAUCE_LABS_REGION;
        String SAUCE_LABS_REMOTE_URL = "https://" + username + ":" + accessKey + region + "/wd/hub";

        try {
            if (properties.getValue("RUN_ON_CLOUD").equalsIgnoreCase("YES")) {
                url = new URL(SAUCE_LABS_REMOTE_URL);
                driver = new AndroidDriver(url, CapabilitiesReader.getDesiredCapabilities("SauceLabs", cap_path));
            } else {
                startAppiumServer();
                url = new URL(Constants.APPIUM_URL_LOCAL);
                driver = new AppiumDriver<MobileElement>(url, CapabilitiesReader.getDesiredCapabilities("Emulator", cap_path));
            }
            driver.manage().timeouts().implicitlyWait(Constants.STANDARD_TIMEOUT, TimeUnit.SECONDS);
        } catch (Exception exp) {
            log.info("Cause is :" + exp.getCause());
            log.info("Message is :" + exp.getMessage());
            exp.printStackTrace();
        }
        return driver;
    }

    public static void stopAppiumServer() {
        if (service != null) {
            service.stop();
            log.info("Server stopped successfully");
        }
    }

    public static AppiumDriver getDriver() {
        if (driver == null) {
            setup();
        }
        return driver;
    }
}
