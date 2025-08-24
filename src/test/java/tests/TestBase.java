package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import config.ProjectConfig;
import config.api.ApiConfig;
import config.web.WebConfig;
import helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    public static final WebConfig webConfig = ConfigReader.getWebConfig();
    public static final ApiConfig apiConfig = ConfigReader.getApiConfig();

    @BeforeAll
    static void setupBaseConfiguration() {
        ProjectConfig projectConfig = new ProjectConfig(webConfig, apiConfig);
        projectConfig.setProjectConfig();
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
        if (webConfig.isRemote()) {
            Attachments.addVideo();
        }
        closeWebDriver();
    }
}
