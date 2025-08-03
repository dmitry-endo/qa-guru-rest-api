package tests;

import annotations.WithLogin;
import api.account.AccountApi;
import api.books.BooksApi;
import api.models.AccountBooksResponseModel;
import api.models.AuthResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfileTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();
    BooksApi booksApi = new BooksApi();

    @Test
    @WithLogin
    @DisplayName("Check successful book deletion from profile's booklist")
    void successfulProfileBookDeletionTest() {
        AuthResponseModel authResponseModel = step("Authorizing via API", AccountApi::getAuthorization);

        String userId = authResponseModel.getUserId();

        step("Deleting all books from profile via API", () ->
                booksApi.deleteAllBooksFromProfileById(userId)
        );

        step("Checking an empty booklist via API", () -> {
            AccountBooksResponseModel response = booksApi.getAccountBooksById(userId);
            assertThat(response.getBooks())
                    .as("Booklist must be empty after deletion")
                    .isEmpty();
        });

        step("Adding a test book", () ->
                booksApi.addBookToProfile(userId, "9781449325862")
        );

        step("Opening profile page", () ->
                open("/profile")
        );

        step("Checking book title", () ->
                profilePage.bookShouldHaveTitle("Git Pocket Guide")
        );

        step("Deleting book via UI", () -> {
            profilePage.clickDeleteButton();
            profilePage.clickOkModalButton();
        });

        step("Checking that booklist is empty via UI", () ->
                profilePage.noRowsFoundMessageShown()
        );

        step("Checking an empty booklist via API", () -> {
            AccountBooksResponseModel response = booksApi.getAccountBooksById(userId);
            assertThat(response.getBooks())
                    .as("Booklist must be empty after deletion")
                    .isEmpty();
        });

    }
}
