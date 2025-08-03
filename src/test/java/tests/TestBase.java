package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setupBaseConfiguration() {
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
//        Configuration.browser = System.getProperty("browserName", "chrome");
//        Configuration.browserVersion = System.getProperty("browserVersion", "127.0");
//        Configuration.browserSize = System.getProperty("screenResolution", "1920x1080");
//        Configuration.remote = String.format(
//                "https://%s:%s@%s/wd/hub",
//                System.getProperty("selenoidLogin", "user1"),
//                System.getProperty("selenoidPassword", "1234"),
//                System.getProperty("selenoidRemoteHost", "selenoid.autotests.cloud")
//        );
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void allureListenerSetup() {
        SelenideLogger.addListener("allureListener", new AllureSelenide());
    }

    @AfterEach
    void shutDown() {
        Attachments.screenshotAs("Final test step screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
        closeWebDriver();
    }
}
