package PageObjects;

import Utilities.CommonMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class BasePage {
    public AppiumDriver driver;
    CommonMethods helper;

    BasePage(AppiumDriver driver) {
        this.driver = driver;
        init();
        helper = new CommonMethods();

    }

    public void init() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }
}
