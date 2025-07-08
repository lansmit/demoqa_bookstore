package config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class BaseConfig {

    private final WebConfig webConfig;

    public BaseConfig(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void setUp() {
        // Browser config
        Configuration.baseUrl = webConfig.getBaseUrl();
        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();

        // Selenoid config
        String SELENOID_URL = System.getProperty("selenoid.url");
        String SELENOID_LOGIN = System.getProperty("selenoid.login");
        String SELENOID_PASSWORD = System.getProperty("selenoid.password");
        if (SELENOID_URL != null) {
            Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
                    SELENOID_LOGIN, SELENOID_PASSWORD, SELENOID_URL);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }

        // RestAssured config
        RestAssured.baseURI = "https://demoqa.com";
    }
}
