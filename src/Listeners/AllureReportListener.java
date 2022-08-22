package Listeners;

import Driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.ByteArrayInputStream;

public class AllureReportListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static void saveTextLog(String message) {
        Reporter.log(message);
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("onStart: " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", DriverManager.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("onFinish: " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        saveTextLog("Test "+getTestMethodName(iTestResult)+" started");
        System.out.println("onTestStart: " + getTestMethodName(iTestResult) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        saveTextLog("Test "+getTestMethodName(iTestResult)+" was successful");
        System.out.println("onTestSuccess: " + getTestMethodName(iTestResult) + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("onTestFailure: " + getTestMethodName(iTestResult) + " failed");
        Object testClass = iTestResult.getInstance();

        WebDriver driver = DriverManager.driver;
        // Allure ScreenShotRobot and SaveTestLog
        if (driver instanceof AppiumDriver) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }
        // Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        Allure.addAttachment(getTestMethodName(iTestResult) + "failure screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        saveTextLog("Test skipped: "+getTestMethodName(iTestResult));
        System.out.println("onTestSkipped: " + getTestMethodName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}