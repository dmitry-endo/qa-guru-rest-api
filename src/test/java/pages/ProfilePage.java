package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    private final SelenideElement deleteButton = $("#delete-record-undefined");
    private final SelenideElement okModalButton = $("#closeSmallModal-ok");
    private final SelenideElement bookTitle = $(".mr-2");
    private final SelenideElement noRowsFoundMessage = $(".rt-noData");

    public void clickDeleteButton() {
        deleteButton.click();
    }

    public void bookShouldHaveTitle(String title) {
        bookTitle.shouldHave(text(title));
    }

    public void noRowsFoundMessageShown() {
        noRowsFoundMessage.shouldHave(text("No rows found"));
    }

    public void clickOkModalButton() {
        okModalButton.click();
    }
}
