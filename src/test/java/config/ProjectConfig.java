package config;

import com.codeborne.selenide.Configuration;
import config.api.ApiConfig;
import config.web.WebConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.UUID;

public class ProjectConfig {

    private final WebConfig webConfig;
    private final ApiConfig apiConfig;

    public ProjectConfig(WebConfig webConfig, ApiConfig apiConfig) {
        this.webConfig = webConfig;
        this.apiConfig = apiConfig;
    }

    public String getUsername() {
        return apiConfig.getUsername();
    }

    public String getPassword() {
        return apiConfig.getPassword();
    }

    public void setProjectConfig() {
        RestAssured.baseURI = apiConfig.getBaseUri();
        Configuration.baseUrl = webConfig.getBaseUrl();
        Configuration.browser = webConfig.getBrowser().toString();
        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        if (webConfig.isRemote()) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "name", "Test: " + UUID.randomUUID()
            ));
            Configuration.remote = String.format(
                    "https://%s:%s@%s/wd/hub",
                    webConfig.getSelenoidLogin(),
                    webConfig.getSelenoidPassword(),
                    webConfig.getSelenoidRemoteHost()
            );
            Configuration.browserCapabilities = capabilities;
        }
    }
}
