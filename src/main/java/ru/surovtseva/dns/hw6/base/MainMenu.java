package ru.surovtseva.dns.hw6.base;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.surovtseva.dns.hw6.pages.EntryOrRegistryPage;
import ru.surovtseva.dns.hw6.pages.ProductPage;

public class MainMenu extends BasePage {
    public MainMenu(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//button[contains(.,'Войти')]")
    private WebElement buttonEntry;

    @FindBy (xpath = "//input[@placeholder='Поиск по сайту']")
    private WebElement inputSearch;

    @FindBy (xpath = "//div[@class='header__login']/img[@class='header-profile__avatar loaded']")
    private WebElement imgAvatarHeader;

    public EntryOrRegistryPage clickOnButtonEntry(){
        wait30second.until(ExpectedConditions.visibilityOf(buttonEntry));
        buttonEntry.click();
        return new EntryOrRegistryPage(driver);
    }

    public UserMenu openUserMenu(){
        wait30second.until(ExpectedConditions.visibilityOf(imgAvatarHeader));
        Actions actionHeader = new Actions(driver);
        actionHeader.moveToElement(imgAvatarHeader).perform();
        return new UserMenu(driver);
    }

    public ProductPage sendSearchRequest(String searchRequest){
        wait30second.until(ExpectedConditions.visibilityOf(imgAvatarHeader));
        inputSearch.sendKeys(searchRequest);
        inputSearch.sendKeys(Keys.ENTER);
        return new ProductPage(driver);
    }
}
