package tests;

import api.BookRequests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ui.ProfilePage;


public class BaseTest {

    public BookRequests bookRequests = new BookRequests();
    public ProfilePage profile = new ProfilePage();

    @BeforeAll
        public static void setupBaseTestConfiguration() {
        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

        Configuration.browser = config.getBrowserName();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.remote = config.getServerAddress();
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
