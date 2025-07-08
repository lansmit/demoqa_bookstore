package helpers;

import api.AuthorizationRequests;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;
import tests.TestData;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {
    private static LoginResponseModel loginResponse;

    public static LoginResponseModel getLoginResponse() {
        return loginResponse;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        loginResponse = AuthorizationRequests.login(TestData.credentials);

        // Проверяем, что все необходимые поля не null
        if (loginResponse.getUserId() == null || loginResponse.getToken() == null || loginResponse.getExpires() == null) {
            throw new IllegalStateException("Login response contains null values: userId=" + loginResponse.getUserId() + 
                ", token=" + loginResponse.getToken() + ", expires=" + loginResponse.getExpires());
        }

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId(), "/"));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken(), "/"));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires(), "/"));
    }
}
