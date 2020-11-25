package ru.surovtseva.dns.hw6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.surovtseva.dns.hw6.base.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateWishlistPage extends BasePage {
    public CreateWishlistPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//div[@class='modal-header' and contains(.,'Создать список избранных товаров')]")
    private WebElement pageTitle;

    @FindBy (xpath = "//input[@name='title']")
    private WebElement inputNameOfList;

    @FindBy (xpath = "//span[contains(@class, 'submit-changes')]")
    private WebElement buttonCreateList;

    @Step("Открыта форма Создать список избранных товаров")
    public CreateWishlistPage checkPageTitle(){
        wait30second.until(ExpectedConditions.visibilityOf(pageTitle));
        assertThat(pageTitle.isDisplayed())
                .as("Открыта форма Создать список избранных товаров").isTrue();
        return this;
    }

    @Step("Введено название списка")
    public CreateWishlistPage enterListName (String listName){
        inputNameOfList.sendKeys(listName);
        return this;
    }

    @Step("Нажата кнопка Создать")
    public FavouriteGoodsPage clickOnButtonCreateList (){
        wait30second.until(ExpectedConditions.visibilityOf(buttonCreateList));
        buttonCreateList.click();
        return new FavouriteGoodsPage(driver);
    }

    @Step("Создание нового списка избранных товаров")
    public FavouriteGoodsPage createWishlist(String listName){
        enterListName(listName);
        clickOnButtonCreateList();
        return new FavouriteGoodsPage(driver);
    }
}
