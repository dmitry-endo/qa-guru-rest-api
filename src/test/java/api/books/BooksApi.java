package api.books;

import api.models.AccountBooksRequestModel;
import api.models.AccountBooksRequestModel.IsbnDataModel;
import api.models.AccountBooksResponseModel;
import api.specs.DefaultSpecs;

import java.util.List;

import static api.ApiEndpoints.BOOKS_PATH;
import static api.ApiEndpoints.USER_BY_UUID_PATH;
import static io.restassured.RestAssured.given;

public class BooksApi extends DefaultSpecs {

    public AccountBooksResponseModel getAccountBooksById(String userId) {
        return given(defaultRequestSpec)
                .pathParam("UUID", userId)
                .when()
                .get(USER_BY_UUID_PATH)
                .then()
                .spec(defaultResponseSpec(200))
                .extract().as(AccountBooksResponseModel.class);
    }

    public void addBookToProfile(String userId, String isbn) {
        AccountBooksRequestModel bookData =
                new AccountBooksRequestModel(userId, List.of(new IsbnDataModel(isbn)));

        given(defaultRequestSpec)
                .body(bookData)
                .when()
                .post(BOOKS_PATH)
                .then()
                .spec(defaultResponseSpec(201));
    }

    public void deleteAllBooksFromProfileById(String userId) {
        given(defaultRequestSpec)
                .queryParam("UserId", userId)
                .when()
                .delete(BOOKS_PATH)
                .then()
                .spec(defaultResponseSpec(204));
    }
}
