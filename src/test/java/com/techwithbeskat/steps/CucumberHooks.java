package com.techwithbeskat.steps;

import com.techwithbeskat.webdriver.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class CucumberHooks {

    /**
     * Runs before each tests(features) with tag '@ui'
     */
    @Before("@ui")
    public void beforeEach() throws Exception {
        WebDriverFactory.createDriver();
    }

    /**
     * Runs after each tests(features) with tag '@ui' whether pass or fail
     */
    @After("@ui")
    public void afterEach(Scenario scenario) {
        // To take screenshot and add it to Allure report if test fails
        if (scenario.isFailed()) {
            Allure.addAttachment(scenario.getName(), "image/png", new ByteArrayInputStream(
                    ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES)), "png");
        }

        WebDriverFactory.cleanUpDriver();
    }
}
