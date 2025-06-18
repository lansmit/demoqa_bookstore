package tests;

import api.AuthorizationApi;
import api.BookApi;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import configuration.TestConfiguration;
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

    public AuthorizationApi authorizationApi = new AuthorizationApi();
    public BookApi bookApi = new BookApi();
    public ProfilePage profile = new ProfilePage();

    @BeforeAll
    static void setUp() {
        TestConfiguration.configure();
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
        Attach.addVideo();
    }

}
