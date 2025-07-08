package tests;

import api.AuthorizationRequests;
import api.BookRequests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.BaseConfig;
import config.WebConfig;
import config.WebProvider;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ui.ProfilePage;

import java.util.Map;

public class BaseTest {


    public static final WebConfig webConfig = WebProvider.getWebConfig();
    public BookRequests bookRequests = new BookRequests();
    public ProfilePage profile = new ProfilePage();

    @BeforeAll
        public static void setupBaseTestConfiguration() {
            BaseConfig baseConfig = new BaseConfig(webConfig);
            baseConfig.setUp();
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
        if (webConfig.isRemote()) {
            Attach.addVideo();
        }
    }

}
