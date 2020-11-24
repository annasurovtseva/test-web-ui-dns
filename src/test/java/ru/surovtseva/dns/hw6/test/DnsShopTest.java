package ru.surovtseva.dns.hw6.test;

import org.junit.jupiter.api.Test;
import ru.surovtseva.dns.hw6.base.BaseTest;
import ru.surovtseva.dns.hw6.common.SubmenuButtons;
import ru.surovtseva.dns.hw6.pages.FavouriteGoodsPage;
import ru.surovtseva.dns.hw6.pages.HomePage;

import static ru.surovtseva.dns.hw6.common.Configuration.*;

public class DnsShopTest extends BaseTest {

    @Test
    void authorisationTest(){
        new HomePage(driver)
                .getMainMenu()
                .clickOnButtonEntry()
                .checkPageTitle()
                .clickOnButtonEntryWithPass()
                .checkPageTitle()
                .authorisationWithPass(USER_LOGIN,USER_PASSWORD)
                .getMainMenu()
                .openUserMenu()
                .checkUserName(USER_NAME);
    }

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
                .createWishlist(LIST_NAME)
                .checkCreatedListIsDisplayed(LIST_NAME);
    }

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
