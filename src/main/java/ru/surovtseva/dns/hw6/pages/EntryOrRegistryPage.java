package ru.surovtseva.dns.hw6.pages;

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

    public EntryOrRegistryPage checkPageTitle(){
        wait30second.until(ExpectedConditions.visibilityOf(pageTitle));
        assertThat(pageTitle.getText().contains("Войти\nили зарегистрироваться"))
                .as("Открыта форма Войти или зарегистрироваться").isTrue();
        return this;
    }

    public EntryOrRegistryPage checkButtonEntryWithPass(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonEntryWithPass));
        assertThat(buttonEntryWithPass.isDisplayed()).as("Присутствует кнопка Воти с паролем").isTrue();
        return this;
    }

    public EntryOrRegistryPage checkEntryOrRegistryPage(){
        checkPageTitle();
        checkButtonEntryWithPass();
        return this;
    }

    public EntryWithPassPage clickOnButtonEntryWithPass(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonEntryWithPass));
        buttonEntryWithPass.click();
        return new EntryWithPassPage(driver);
    }
}
