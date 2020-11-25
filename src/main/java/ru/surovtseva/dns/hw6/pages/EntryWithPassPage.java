package ru.surovtseva.dns.hw6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.surovtseva.dns.hw6.base.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

public class EntryWithPassPage extends BasePage {
    public EntryWithPassPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (css = "div[class='form-entry-with-password__title']")
    private WebElement pageTitle;

    @FindBy (xpath = "//div[@class='form-entry-with-password__input']/div/input")
    private WebElement inputLogin;

    @FindBy (xpath = "//div[@class='form-entry-with-password__password']/div/input")
    private WebElement inputPassword;

    @FindBy (xpath = "//div[contains(@class, 'base-ui-button')]")
    private WebElement buttonEntryOnForm;

    @Step("Открыта форма Войти c паролем")
    public EntryWithPassPage checkPageTitle(){
        assertThat(pageTitle.getText().equals("Войти c паролем"))
                .as("Открыта форма Войти c паролем").isTrue();
        return this;
    }

    @Step("Введен логин")
    public EntryWithPassPage enterLogin(String login) {
        inputLogin.sendKeys(login);
        return this;
    }

    @Step("Введен пароль")
    public EntryWithPassPage enterPassword(String password) {
        inputPassword.sendKeys(password);
        return this;
    }

    @Step("Нажата кнопка Войти на форме Войти с паролем")
    public HomePage clickOnButtonEntry(){
        buttonEntryOnForm.click();
        return new HomePage(driver);
    }

    @Step("Авторизация по логин/пароль")
    public HomePage authorisationWithPass(String login, String password){
        enterLogin(login);
        enterPassword(password);
        clickOnButtonEntry();
        return new HomePage(driver);
    }
}
