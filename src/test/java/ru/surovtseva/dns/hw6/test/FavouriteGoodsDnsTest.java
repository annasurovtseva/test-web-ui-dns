package ru.surovtseva.dns.hw6.test;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.surovtseva.dns.hw6.base.BaseTest;
import ru.surovtseva.dns.hw6.common.SubmenuButtons;
import ru.surovtseva.dns.hw6.pages.FavouriteGoodsPage;
import ru.surovtseva.dns.hw6.pages.HomePage;

import static ru.surovtseva.dns.hw6.common.Configuration.*;

@Feature("Избранные товары")
public class FavouriteGoodsDnsTest extends BaseTest {
    @DisplayName("Создание списка избранных товаров")
    @Test
    void createWishlistTest(){
        FavouriteGoodsPage favouriteGoodsPage = (FavouriteGoodsPage) (new HomePage(driver)
                .getMainMenu()
                .clickOnButtonEntry()
                .clickOnButtonEntryWithPass()
                .authorisationWithPass(USER_LOGIN,USER_PASSWORD)
                .getMainMenu()
                .openUserMenu())
                .moveCursorToSubmenuButton(SubmenuButtons.FAVOURITE_GOODS);

        favouriteGoodsPage
                .checkFavouriteGoodsPage()
                .clickOnButtonCreateWishlist()
                .checkPageTitle()
                .createWishlist(LIST_NAME)
                .checkCreatedListIsDisplayed(LIST_NAME);
    }

    @DisplayName("Добавление товара в Избранное")
    @Test
    void addProductToFavouriteTest() {
        FavouriteGoodsPage favouriteGoodsPage = (FavouriteGoodsPage) (new HomePage(driver)
                .getMainMenu()
                .clickOnButtonEntry()
                .clickOnButtonEntryWithPass()
                .authorisationWithPass(USER_LOGIN,USER_PASSWORD)
                .getMainMenu()
                .sendSearchRequest(SEARCH_REQUEST)
                .checkProductPage(SEARCH_REQUEST)
                .clickOnButtonToFavourite()
                .checkButtonInFavourite()
                .getMainMenu()
                .openUserMenu())
                .moveCursorToSubmenuButton(SubmenuButtons.FAVOURITE_GOODS);

        favouriteGoodsPage
                .checkPageTitle()
                .checkSubsectionGeneralList()
                .checkProductInGeneralList(SEARCH_REQUEST);
    }
}
