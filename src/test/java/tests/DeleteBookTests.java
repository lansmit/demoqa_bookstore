package tests;

import models.AddBookModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static tests.TestData.credentials;

public class DeleteBookTests extends BaseTest {

    @Test
    void deleteBookTest() {
        String isbn = "9781491904244";

        LoginResponseModel loginResponse = authorizationApi.login(credentials);

        step("Авторизоваться в профиле с пустой корзиной", () ->
                bookApi.deleteAllBooks(loginResponse));

        step("Добавить книгу в профиль", () ->
                bookApi.addBook(loginResponse, new AddBookModel()));

        step("Удалить книгу из профиля", () ->
                bookApi.deleteBook(loginResponse, isbn));

        step("Открыть UI и убедиться, что книга отсутствует", () -> {
            profile.setCookie(loginResponse)
                    .openProfile()
                    .checkExistenceOfBook(isbn);
        });
    }
}

