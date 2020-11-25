package ru.surovtseva.dns.hw6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.surovtseva.dns.hw6.base.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1[class='cart-title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//button[contains(.,'Оформить заказ')]")
    private WebElement buttonPlaceOrder;

    @Step("Открыта страница Корзина")
    public CartPage checkPageTitle() {
        wait30second.until(ExpectedConditions.visibilityOf(pageTitle));
        assertThat(pageTitle.getText().equals("Корзина")).as("Открыта страница Корзина").isTrue();
        return this;
    }

    @Step("Присутствует секция с наименованием товара")
    public CartPage checkProductDisplayInCart(String searchRequest) {
        By productNamePath = By.xpath("//a[@class = 'cart-items__product-name-link' and contains(.,'"+searchRequest+"')]");
        WebElement productName = driver.findElement(productNamePath);
        assertThat(productName.isDisplayed())
                .as("Присутствует секция с наименованием товара " + searchRequest).isTrue();
        return this;
    }

    @Step("Присутствует кнопка Оформить заказ")
    public CartPage checkButtonPlaceOrder() {
        assertThat(buttonPlaceOrder.isDisplayed()).as("Присутствует кнопка Оформить заказ").isTrue();
        return this;
    }

    @Step("Проверка страницы Корзина")
    public CartPage checkCartPage(String searchRequest) {
        checkPageTitle();
        checkProductDisplayInCart(searchRequest);
        checkButtonPlaceOrder();
        return this;
    }

}

