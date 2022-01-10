package com.techwithbeskat.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Browser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> THREAD_LOCAL_DRIVER = new ThreadLocal<>();

    private static List<String> BROWSER_LIST;
    private static boolean randomizeBrowser;
    private static String defaultBrowser;

    private static final Random random = new Random();

    @Value("#{'${test.browser.list}'.split(',')}")
    public void setBrowserList(List<String> browserList) {
        BROWSER_LIST = browserList;
    }

    @Value("${test.browser.randomize}")
    public void setRandomizeBrowser(boolean randomizeBrowser) {
        WebDriverFactory.randomizeBrowser = randomizeBrowser;
    }

    @Value("${test.browser.default}")
    public void setDefaultBrowser(String defaultBrowser) {
        WebDriverFactory.defaultBrowser = defaultBrowser;
    }

    public static WebDriver getDriver() {
        if (THREAD_LOCAL_DRIVER.get() != null) {
            return THREAD_LOCAL_DRIVER.get();
        } else {
            log.error("Webdriver is null and it shouldn't be.");
            throw new RuntimeException("Webdriver is null and it shouldn't be.");
        }
    }

    public static void cleanUpDriver() {
        WebDriverFactory.quitDriver();
        WebDriverFactory.removeDriver();
    }

    public static void quitDriver() {
        if (THREAD_LOCAL_DRIVER.get() != null) {
            THREAD_LOCAL_DRIVER.get().quit();
        }
    }

    public static void removeDriver() {
        if (THREAD_LOCAL_DRIVER.get() != null) {
            THREAD_LOCAL_DRIVER.remove();
        }
    }

    public static void createDriver() {
        String browserType = defaultBrowser;

        if (randomizeBrowser) {
            int randomItem = random.nextInt(BROWSER_LIST.size());
            browserType = BROWSER_LIST.get(randomItem);
        }

        log.info("Using browser type: {}", browserType);

        if (Browser.CHROME.is(browserType)) {
            THREAD_LOCAL_DRIVER.set(createLocalChromeDriver());
        } else { // The other browser types can be added in this class as well
            log.error("Unknown browser type entered.");
            throw new RuntimeException("Unknown browser type entered.");
        }
    }

    private static WebDriver createLocalChromeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();

        WebDriver webDriver = new ChromeDriver(chromeOptions);
        setBasicWebDriverProperties(webDriver);

        return webDriver;
    }

    private static void setBasicWebDriverProperties(WebDriver webDriver) {
        webDriver.manage().window().maximize();
    }

}
