package QKART_TESTNG.testcases;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener{
    public static RemoteWebDriver driver; // Static WebDriver


    private void captureScreenshot(String methodName, String status) {
        try {
            File theDir = new File("/screenshots");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            String timestamp = String.valueOf(java.time.LocalDateTime.now());
            String fileName = String.format("screenshot_%s_Test%s_%s.png", timestamp, status, methodName);
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("screenshots/" + fileName);
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        captureScreenshot(result.getMethod().getMethodName(), "Start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        captureScreenshot(result.getMethod().getMethodName(), "Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot(result.getMethod().getMethodName(), "Failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        captureScreenshot(result.getMethod().getMethodName(), "Skipped");
    }

}