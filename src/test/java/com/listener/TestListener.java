package com.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.base.BaseClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestListener extends BaseClass implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriver();
        if (driver != null) {
            // Capture screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "target/surefire-reports/screenshots/" + result.getName() + ".png";
            try {
                // Save the screenshot
                Files.createDirectories(new File("target/surefire-reports/screenshots").toPath());
                Files.copy(screenshot.toPath(), new File(screenshotPath).toPath());
                System.out.println("Screenshot saved at: " + screenshotPath);

                // Add screenshot path to the test result
                result.setAttribute("screenshotPath", screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestStart(ITestResult result) {}
    @Override
    public void onTestSuccess(ITestResult result) {}
    @Override
    public void onTestSkipped(ITestResult result) {}
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}

}
