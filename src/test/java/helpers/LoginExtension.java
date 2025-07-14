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
        try {
            loginResponse = AuthorizationRequests.login(TestData.credentials);
            
            System.out.println("Login response details: " + 
                "\nuserId: " + loginResponse.getUserId() +
                "\ntoken: " + loginResponse.getToken() +
                "\nexpires: " + loginResponse.getExpires() +
                "\nusername: " + loginResponse.getUsername());
            
            if (loginResponse.getUserId() == null || loginResponse.getToken() == null || loginResponse.getExpires() == null) {
                throw new IllegalStateException("Login failed - required fields are null: " +
                    "userId=" + loginResponse.getUserId() +
                    ", token=" + loginResponse.getToken() +
                    ", expires=" + loginResponse.getExpires());
            }

            open("/images/Toolsqa.jpg");
            getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId(), "/"));
            getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken(), "/"));
            getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires(), "/"));
        } catch (Exception e) {
            System.err.println("Login failed with error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
