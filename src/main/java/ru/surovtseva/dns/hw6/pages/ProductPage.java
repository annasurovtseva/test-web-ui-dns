package ru.surovtseva.dns.hw6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.surovtseva.dns.hw6.base.BasePage;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//h1[contains(@class, 'page-title')]")
    private WebElement pageTitle;

    @FindBy (xpath = "//button[contains(.,'Купить')]")
    private WebElement buttonToBuy;

    @FindBy (xpath = "//a[contains(.,'Перейти в корзину')]")
    private WebElement buttonMoveToCart;

    @FindBy (css = "div[class='cart-summary']")
    private WebElement titleCartPopup;

    @FindBy (css = "div[class='cart-product__name']")
    private WebElement productInCartModalPopup;

    @FindBy (css = "i[class='wishlist__icon-add']")
    private WebElement buttonToFavourite;

    @FindBy (css = "i[class='wishlist__icon-add wishlist__icon-add_added']")
    private WebElement buttonInFavourite;

    private Map<String, Object> elementParameters = new HashMap<>();

    @Step("Заголовок страницы содержит текст {searchRequest}")
    public ProductPage checkPageTitle(String searchRequest){
        wait30second.until(ExpectedConditions.visibilityOf(pageTitle));
        assertThat(pageTitle.getText().contains(searchRequest))
                .as("Заголовок страницы содержит текст "+searchRequest).isTrue();
        return this;
    }

    @Step("Присутствует кнопка Купить")
    public ProductPage checkButtonToBuy(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonToBuy));
        assertThat(buttonToBuy.isDisplayed()).as("Присутствует кнопка Купить").isTrue();
        return this;
    }

    @Step("Нажата кнопка Купить")
    public ProductPage clickOnButtonToBuy(){
        buttonToBuy.click();
        return this;
    }

    @Step("Заголовок окна - Основные товары")
    public ProductPage checkTitleCartPopup(){
        assertThat(titleCartPopup.getText().contains("Основные товары"))
                .as("Заголовок окна Основные товары").isTrue();
        return this;
    }

    @Step("В окне присутствует позиция с товаром")
    public ProductPage checkProductInCartModalPopup(String searchRequest){
        assertThat(productInCartModalPopup.getText().contains(searchRequest))
                .as("В окне присутствует позиция с товаром").isTrue();
        return this;
    }

    @Step("Присутствует кнопка В избранное")
    public ProductPage checkButtonToFavourite(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonToFavourite));

        elementParameters.put("favouriteAfterContent", ((JavascriptExecutor) driver).
                executeScript("return window.document.defaultView.getComputedStyle(" +
                        "window.document.getElementsByClassName('wishlist__icon-add')[0],':after')" +
                        ".getPropertyValue('content')"));

        assertThat(elementParameters.get("favouriteAfterContent").toString().equals("\"В избранное\""))
                .as("Присутствует кнопка В избранное").isTrue();
        return this;
    }

    @Step("Название кнопки изменилось на В Избранном")
    public ProductPage checkButtonInFavouriteTitle(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonInFavourite));

        elementParameters.put("inFavouriteAfterContent", ((JavascriptExecutor) driver).
                executeScript("return window.document.defaultView.getComputedStyle(" +
                        "window.document.getElementsByClassName('wishlist__icon-add')[0],':after')" +
                        ".getPropertyValue('content')"));

        assertThat(elementParameters.get("inFavouriteAfterContent").toString().equals("\"В избранном\""))
                .as("Присутствует кнопка В избранном").isTrue();
         return this;
    }

    @Step("Иконка кнопки В избранном имеет цвет rgb(252, 133, 7)")
    public ProductPage checkButtonInFavouriteColor(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonInFavourite));

        elementParameters.put("inFavouriteIconColor", ((JavascriptExecutor) driver).
                executeScript("return window.document.defaultView.getComputedStyle(" +
                        "window.document.getElementsByClassName('wishlist__icon-add')[0])" +
                        ".getPropertyValue('color')"));

        assertThat(elementParameters.get("inFavouriteIconColor").toString().equals("rgb(252, 133, 7)"))
                .as("Иконка В избранном имеет цвет rgb(252, 133, 7)").isTrue();
        return this;
    }

    @Step("Проверка изменений кнопки В Избранное")
    public ProductPage checkButtonInFavourite(){
        checkButtonInFavouriteTitle();
        checkButtonInFavouriteColor();
        return this;
    }

    @Step("Проверка страницы товара")
    public ProductPage checkProductPage(String searchRequest){
        checkPageTitle(searchRequest);
        checkButtonToBuy();
        checkButtonToFavourite();
        return this;
    }

    @Step("Проверка popup окна")
    public ProductPage checkCartModalPopup(String searchRequest){
        //Присутствует кнопка Перейти в корзину
        wait30second.until(ExpectedConditions.visibilityOf(buttonMoveToCart));
        checkTitleCartPopup();
        checkProductInCartModalPopup(searchRequest);
        return this;
    }

    @Step("Нажата кнопка Перейти в корзину")
    public CartPage clickOnButtonMoveToCart(){
        buttonMoveToCart.click();
        return new CartPage(driver);
    }

    @Step("Нажата кнопка В Избранное")
    public ProductPage clickOnButtonToFavourite(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonToFavourite));
        buttonToFavourite.click();
        return this;
    }
}
