package Listeners;

import Utilities.ReadProperties;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    int counter = 0;
    ReadProperties readProperties = ReadProperties.getInstance();
    int retryLimit = Integer.parseInt(readProperties.getValue("RETRY_COUNT"));

    @Override
    public boolean retry(ITestResult result) {
        if (counter < retryLimit) {
            counter++;
            return true;
        }
        return false;
    }
}