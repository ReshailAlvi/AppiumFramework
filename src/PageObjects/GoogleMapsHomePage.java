package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class GoogleMapsHomePage extends BasePage {

    public GoogleMapsHomePage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "search_omnibox_text_box")
    WebElement searchBox;

    @AndroidFindBy(id = "search_omnibox_edit_text")
    WebElement editSearchField;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='SKIP']")
    WebElement skipButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='The Dubai Mall']")
    WebElement locationSuggestion;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Shopping mall']")
    WebElement shoppingCategory;

    @AndroidFindBy(id = "title")
    WebElement locationTitle;

    public void clickSkipButton() {
        helper.click(skipButton);
    }

    public void searchLocation(String location) {
        helper.click(searchBox);
        helper.setText(editSearchField, location);
    }

    public void clickRecommendation() {
        helper.click(locationSuggestion);
    }

    public boolean verifyShoppingCategory() {
        return helper.isDisplayed(shoppingCategory);
    }

    public String getTitle() {
        return helper.getText(locationTitle);
    }
}
