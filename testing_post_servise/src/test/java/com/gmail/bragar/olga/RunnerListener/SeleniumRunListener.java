package com.gmail.bragar.olga.RunnerListener;

import com.gmail.bragar.olga.tests.PostServiceTest;
import org.apache.commons.io.FileUtils;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class SeleniumRunListener extends RunListener {

    @Override
    public void testRunStarted(Description description) throws Exception {
        System.out.println("Before tests run: " + description);
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        System.out.println("Result of tests run: ");
        System.out.println("Run time: " + result.getRunTime() + "ms");
        System.out.println("Run count: " + result.getRunCount());
        System.out.println("Failure cout: " + result.getFailureCount());
    }

    @Override
    public void testStarted(Description description) throws Exception {
        System.out.println("Test starts: " + description);

    }

    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println("Test finished: " + description);
        System.out.println("---------------------------------------------");
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        System.out.println("Test failed whith: " + failure.getException());
        captureScreenshot(PostServiceTest.driver);
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        System.out.println("Test asumes: " + failure.getException());
        try {
            captureScreenshot(PostServiceTest.driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    void captureScreenshot(WebDriver driver) throws Exception{
         try {
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("screen-" + System.currentTimeMillis() + ".png"));
        }catch(Exception e1) {
            e1.printStackTrace();
        }
    }


}
