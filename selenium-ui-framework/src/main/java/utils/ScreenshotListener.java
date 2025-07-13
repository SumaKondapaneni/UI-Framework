package utils;

/**
 * @author Suma Kondapaneni
 * @created 13 Jul 2025
 */

import org.testng.ITestListener;
import org.testng.ITestResult;
import base.TestBase;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();

        if (testClass instanceof TestBase) {
            TestBase base = (TestBase) testClass;
            base.captureScreenshotOnFailure(result.getName());
        }
    }
}


