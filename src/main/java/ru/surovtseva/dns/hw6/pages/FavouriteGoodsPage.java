package ru.surovtseva.dns.hw6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.surovtseva.dns.hw6.base.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

public class FavouriteGoodsPage extends BasePage {
    public FavouriteGoodsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//span[@class='desktop-title']")
    private WebElement pageTitle;

    @FindBy (xpath = "//h3[contains(.,'Стандартные списки')]")
    private WebElement sectionStandardList;

    @FindBy (xpath = "//div[@class='name']")
    private WebElement subsectionGeneralList;

    @FindBy (xpath = "//h3[contains(.,'Созданные списки')]")
    private WebElement sectionCreatedLists;

    @FindBy (xpath = "//h3[contains(.,'Архив')]")
    private WebElement sectionArchive;

    @FindBy (xpath = "//div[@class='create-wishlist-button']")
    private WebElement buttonCreateWishlist;

    @Step("Открыта страница Кабинет покупателя")
    public FavouriteGoodsPage checkPageTitle(){
        wait30second.until(ExpectedConditions.visibilityOf(pageTitle));
        assertThat(pageTitle.getText().equals("Кабинет покупателя"))
                .as("Открыта страница Кабинет покупателя").isTrue();
        return this;
    }

    @Step("Присутствует разел Стандартные списки")
    public FavouriteGoodsPage checkSectionStandardList(){
        assertThat(sectionStandardList.isDisplayed())
                .as("Присутствует разел Стандартные списки").isTrue();
        return this;
    }

    @Step("Присутствует подразел Общий список")
    public FavouriteGoodsPage checkSubsectionGeneralList(){
        assertThat(subsectionGeneralList.getText().equals("Общий список"))
                .as("Присутствует подразел Общий список").isTrue();
        return this;
    }

    @Step("Присутствует разел Созданные списки")
    public FavouriteGoodsPage checkSectionCreatedList(){
        assertThat(sectionCreatedLists.isDisplayed())
                .as("Присутствует разел Созданные списки").isTrue();
        return this;
    }

    @Step("Присутствует разел Архив")
    public FavouriteGoodsPage checkSectionArchiveList(){
        assertThat(sectionArchive.isDisplayed())
                .as("Присутствует разел Архив").isTrue();
        return this;
    }

    @Step("Присутсвуют все разделы страницы")
    public FavouriteGoodsPage checkSectionsOnPage(){
        checkSectionStandardList();
        checkSubsectionGeneralList();
        checkSectionCreatedList();
        checkSectionArchiveList();
        return this;
    }

    @Step("Присутствует кнопка Создать список")
    public FavouriteGoodsPage checkButtonCreateWishlist(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonCreateWishlist));
        assertThat(buttonCreateWishlist.isDisplayed())
                .as("Присутствует кнопка Создать список").isTrue();
        return this;
    }

    @Step("Проверка страницы Избранные товары")
    public FavouriteGoodsPage checkFavouriteGoodsPage(){
        checkPageTitle();
        checkSectionsOnPage();
        checkButtonCreateWishlist();
        return this;
    }

    @Step("Нажата кнопка Создать новый список")
    public CreateWishlistPage clickOnButtonCreateWishlist(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonCreateWishlist));
        buttonCreateWishlist.click();
        return new CreateWishlistPage(driver);
    }

    @Step("В разделе Созданные списки отображается новый список")
    public FavouriteGoodsPage checkCreatedListIsDisplayed(String listName){
        By pathCreatedList = By.xpath("//div[@class='name' and contains(.,'"+listName+"')]");
        WebElement nameOfCreatedList = driver.findElement(pathCreatedList);
        wait30second.until(ExpectedConditions.visibilityOf(nameOfCreatedList));

        assertThat(nameOfCreatedList.getText().equals(listName))
                .as("В разделе Созданные списки отображается список "+ listName).isTrue();
        return this;
    }

    @Step("В разделе Общие списки отображается товар")
    public FavouriteGoodsPage checkProductInGeneralList(String searchRequest){
        By pathProductPosition = By.xpath("//span[@class='name' and contains(.,'"+searchRequest+"')]");
        WebElement productPosition = driver.findElement(pathProductPosition);
        wait30second.until(ExpectedConditions.visibilityOf(productPosition));

        assertThat(productPosition.isDisplayed())
                .as("В общем списке отображается товар " + searchRequest).isTrue();
        return this;
    }
}
