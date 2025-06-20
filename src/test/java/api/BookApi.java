package api;

import models.AddBookModel;
import models.DeleteBookModel;
import models.IsbnModel;
import helpers.LoginExtension;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.BooksSpecs.bookRequestSpecs;
import static specs.BooksSpecs.bookResponseSpecs;

public class BookApi {
    public void deleteAllBooks() {
        given(bookRequestSpecs)
                .header("Authorization", "Bearer " + LoginExtension.getLoginResponse().getToken())
                .queryParam("UserId", LoginExtension.getLoginResponse().getUserId())
        .when()
                .delete("/BookStore/v1/Books")
        .then()
                .spec(bookResponseSpecs)
                .statusCode(204);
    }

    public void addBook(AddBookModel bookList) {
        IsbnModel isbn = new IsbnModel("9781491904244");
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbn);

        bookList.setUserId(LoginExtension.getLoginResponse().getUserId());
        bookList.setCollectionOfIsbns(isbnList);

        given(bookRequestSpecs)
                .header("Authorization", "Bearer " + LoginExtension.getLoginResponse().getToken())
                .body(bookList)
        .when()
                .post("/BookStore/v1/Books")
        .then()
                .spec(bookResponseSpecs)
                .statusCode(201);
    }

    public void deleteBook(String isbn) {
        DeleteBookModel deleteBook = new DeleteBookModel();
        deleteBook.setUserId(LoginExtension.getLoginResponse().getUserId());
        deleteBook.setIsbn(isbn);

        given(bookRequestSpecs)
                .header("Authorization", "Bearer " + LoginExtension.getLoginResponse().getToken())
                .body(deleteBook)
        .when()
                .delete("/BookStore/v1/Book")
        .then()
                .spec(bookResponseSpecs)
                .statusCode(204);
    }
}
