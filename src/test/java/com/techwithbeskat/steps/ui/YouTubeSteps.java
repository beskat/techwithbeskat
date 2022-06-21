package com.techwithbeskat.steps.ui;

import com.techwithbeskat.ui.BaseAutomationPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YouTubeSteps extends BaseAutomationPage {

    @When("I go to page {string}")
    public void iGoToPage(String pageUrl) {
        getDriver().get(pageUrl);
    }

    @Then("I verify YouTube channel name {string}")
    public void iVerifyYouTubeChannelName(String expectedChannelName) {
        String actualChannelName = findElementWaitForVisible(By.id("channel-name")).getText();

        assertEquals(expectedChannelName, actualChannelName);
    }
}
