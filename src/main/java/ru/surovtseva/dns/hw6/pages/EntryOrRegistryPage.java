package ru.surovtseva.dns.hw6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.surovtseva.dns.hw6.base.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

public class EntryOrRegistryPage extends BasePage {
    public EntryOrRegistryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[class='form-entry-or-registry__head']")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@class='block-other-login-methods__password-caption']")
    private WebElement buttonEntryWithPass;

    @Step("Открыта форма Войти или зарегистрироваться")
    public EntryOrRegistryPage checkPageTitle(){
        wait30second.until(ExpectedConditions.visibilityOf(pageTitle));
        assertThat(pageTitle.getText().contains("Войти\nили зарегистрироваться"))
                .as("Открыта форма Войти или зарегистрироваться").isTrue();
        return this;
    }

    @Step("Присутствует кнопка Войти с паролем")
    public EntryOrRegistryPage checkButtonEntryWithPass(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonEntryWithPass));
        assertThat(buttonEntryWithPass.isDisplayed()).as("Присутствует кнопка Войти с паролем").isTrue();
        return this;
    }

    @Step("Проверка формы Войти или зарегистрироваться")
    public EntryOrRegistryPage checkEntryOrRegistryPage(){
        checkPageTitle();
        checkButtonEntryWithPass();
        return this;
    }

    @Step("Нажата кнопка Войти с паролем")
    public EntryWithPassPage clickOnButtonEntryWithPass(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonEntryWithPass));
        buttonEntryWithPass.click();
        return new EntryWithPassPage(driver);
    }
}
