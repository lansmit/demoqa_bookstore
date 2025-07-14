package tests;

import api.BookRequests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ui.ProfilePage;

import java.util.Map;


public class BaseTest {

    public BookRequests bookRequests = new BookRequests();
    public ProfilePage profile = new ProfilePage();

    @BeforeAll
    public static void setupBaseTestConfiguration() {
        System.setProperty("env", System.getProperty("env", "local"));
        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

        Configuration.browserSize = config.getBrowserSize();
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowserName();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.remote = config.getServerAddress();
        
        // Добавляем настройки для стабильности
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";

        // Настройки для Selenoid
        Configuration.browserCapabilities.setCapability("selenoid:options", Map.of(
            "enableVNC", true,
            "enableVideo", true
        ));

        RestAssured.baseURI = config.getBaseUrl();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
    }

}
