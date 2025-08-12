package config.web;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
//        "system:properties",
        "classpath:config/remote.properties"
//        "classpath:config/${env}.properties"
})
public interface WebConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();

    @Key("selenoidLogin")
    @DefaultValue("user1")
    String getSelenoidLogin();

    @Key("selenoidPassword")
    @DefaultValue("1234")
    String getSelenoidPassword();

    @Key("selenoidRemoteHost")
    @DefaultValue("selenoid.autotests.cloud")
    String getSelenoidRemoteHost();

    @Key("browser")
    @DefaultValue("chrome")
    Browser getBrowser();

    @Key("browserVersion")
    @DefaultValue("138")
    String getBrowserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("isRemote")
    @DefaultValue("false")
    Boolean isRemote();
}
