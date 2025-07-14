package api;

import models.CredentialsModel;
import models.LoginResponseModel;
import io.restassured.filter.log.LogDetail;

import static io.restassured.RestAssured.given;
import static specs.BaseSpecs.getRequestSpec;
import static specs.BaseSpecs.responseSpec;

public class AuthorizationRequests {
    public static LoginResponseModel login(CredentialsModel credentials) {
        System.out.println("Attempting to login with credentials: " + credentials);
        
        return given()
                .spec(getRequestSpec())
                .log().all()  // логируем весь запрос
                .body(credentials)
        .when()
                .post("/Account/v1/Login")
        .then()
                .log().all()  // логируем весь ответ
                .spec(responseSpec(200))
                .extract().as(LoginResponseModel.class);
    }
}
