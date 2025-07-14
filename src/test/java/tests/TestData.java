package tests;

import models.CredentialsModel;

public class TestData {
    public static final String LOGIN = "evgenii12341234",
            PASSWORD = "PassWord1234!";

    public static CredentialsModel credentials = new CredentialsModel(LOGIN, PASSWORD);

    static {
        System.out.println("Initializing test data with credentials:");
        System.out.println("Login: " + LOGIN);
        System.out.println("Password: " + PASSWORD);
    }
}
