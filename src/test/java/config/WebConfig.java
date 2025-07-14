package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.config"
})
public interface WebConfig extends Config {
    @Key("browser.size")
    String getBrowserSize();

    @Key("baseUrl")
    String getBaseUrl();

    @Key("browser.name")
    String getBrowserName();

    @Key("browser.version")
    String getBrowserVersion();

    @Key("server.address")
    String getServerAddress();
}
