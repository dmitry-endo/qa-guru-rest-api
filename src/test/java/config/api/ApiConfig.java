package config.api;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/${env}.properties"
})
public interface ApiConfig extends Config {

    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();

    @Key("baseUri")
    @DefaultValue("https://demoqa.com")
    String getBaseUri();
}
