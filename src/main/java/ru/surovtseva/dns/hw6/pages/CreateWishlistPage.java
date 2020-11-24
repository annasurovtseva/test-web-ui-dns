package ru.surovtseva.dns.hw6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    public CreateWishlistPage checkPageTitle(){
        assertThat(pageTitle.isDisplayed())
                .as("Открыта страница Создать список избранных товаров").isTrue();
        return this;
    }

    public CreateWishlistPage enterListName (String listName){
        inputNameOfList.sendKeys(listName);
        return this;
    }

    public FavouriteGoodsPage clickOnButtonCreateList (){
        buttonCreateList.click();
        return new FavouriteGoodsPage(driver);
    }

    public FavouriteGoodsPage createWishlist(String listName){
        enterListName(listName);
        clickOnButtonCreateList();
        return new FavouriteGoodsPage(driver);
    }
}
