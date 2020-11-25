package ru.surovtseva.dns.hw6.test;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.surovtseva.dns.hw6.base.BaseTest;
import ru.surovtseva.dns.hw6.pages.HomePage;

import static ru.surovtseva.dns.hw6.common.Configuration.*;

@Feature("Корзина")
public class CartDnsTest extends BaseTest {
    @DisplayName("Добавление товара в корзину")
    @Test
    void addProductToCartTest() {
        new HomePage(driver)
                .getMainMenu()
                .clickOnButtonEntry()
                .clickOnButtonEntryWithPass()
                .authorisationWithPass(USER_LOGIN,USER_PASSWORD)
                .getMainMenu()
                .sendSearchRequest(SEARCH_REQUEST)
                .checkProductPage(SEARCH_REQUEST)
                .clickOnButtonToBuy()
                .checkCartModalPopup(SEARCH_REQUEST)
                .clickOnButtonMoveToCart()
                .checkCartPage(SEARCH_REQUEST);
    }
}
