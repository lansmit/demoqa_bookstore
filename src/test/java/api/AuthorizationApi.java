package api;

import models.CredentialsModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.AuthorizationSpecs.authorizationRequestSpecs;
import static specs.AuthorizationSpecs.authorizationResponseSpecs;

public class AuthorizationApi {

    public static LoginResponseModel login(CredentialsModel credentials) {
        return given(authorizationRequestSpecs)
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(authorizationResponseSpecs)
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }
}
