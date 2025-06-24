package tests;

import models.AddBookModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import helpers.WithLogin;

import static io.qameta.allure.Allure.step;

public class DeleteBookTests extends BaseTest {

    @Test
    @WithLogin
    @DisplayName("Удаление книги из профиля")
    void deleteBookTest() {
        String isbn = "9781491904244";

        step("Авторизоваться в профиле с пустой корзиной", () ->
                bookApi.deleteAllBooks());

        step("Добавить книгу в профиль", () ->
                bookApi.addBook(new AddBookModel()));

        step("Удалить книгу из профиля", () ->
                bookApi.deleteBook(isbn));

        step("Открыть UI и убедиться, что книга отсутствует", () -> {
            profile.openProfile()
                    .checkExistenceOfBook(isbn);
        });
    }
}

