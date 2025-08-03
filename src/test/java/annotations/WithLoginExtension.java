package annotations;

import api.account.AccountApi;
import api.models.AuthResponseModel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WithLoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        if (context.getTestMethod().isPresent() &&
                context.getTestMethod().get().isAnnotationPresent(WithLogin.class)) {

            authorizeAndSetCookies();
        }
    }

    @Step("Authorizing via API and setting up cookies")
    private void authorizeAndSetCookies() {
        AuthResponseModel auth = AccountApi.getAuthorization();

        open("/favicon.ico"); 

        getWebDriver().manage().addCookie(new Cookie("userID", auth.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", auth.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", auth.getExpires()));
    }

}
