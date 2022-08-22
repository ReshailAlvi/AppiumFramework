package Tests;

import Driver.DriverManager;
import PageObjects.GoogleMapsHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.Constants;

public class GoogleMapsHomeTests extends BaseTest {

    GoogleMapsHomePage googleMapsHome;

    GoogleMapsHomeTests() {
        super();
        googleMapsHome = new GoogleMapsHomePage(DriverManager.driver);
    }

    @Test
    public void searchLocationAndValidateTitleAndCategory() {
        googleMapsHome.clickSkipButton();
        googleMapsHome.searchLocation(Constants.location);
        googleMapsHome.clickRecommendation();
        Assert.assertTrue(googleMapsHome.verifyShoppingCategory(), "Validate the category for this location is shopping mall");
        Assert.assertEquals(googleMapsHome.getTitle(), Constants.location, "Validate the title of the location matches the location user searched");
    }

}
