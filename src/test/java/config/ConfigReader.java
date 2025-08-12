package config;

import config.api.ApiConfig;
import config.web.WebConfig;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {

    @Getter
    private static final WebConfig webConfig =
            ConfigFactory.create(
                    WebConfig.class,
                    System.getProperties()
            );

    @Getter
    private static final ApiConfig apiConfig =
            ConfigFactory.create(
                    ApiConfig.class,
                    System.getProperties()
            );
}
