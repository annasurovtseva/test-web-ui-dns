package ru.surovtseva.dns.hw6.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.surovtseva.dns.hw6.common.SubmenuButtons;
import ru.surovtseva.dns.hw6.pages.FavouriteGoodsPage;
import ru.surovtseva.dns.hw6.pages.NotifyListPage;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMenu extends BasePage {
    public UserMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//a[@class='header-profile__username']")
    private WebElement userName;

    public BasePage moveCursorToSubmenuButton(SubmenuButtons button) {
        driver.findElement((button.getBy())).click();

        switch (button) {
            case FAVOURITE_GOODS:
                return new FavouriteGoodsPage(driver);
            case NOTIFY_LIST:
                return new NotifyListPage(driver);
            default:
                throw new IllegalArgumentException
                        ("Another tabs currently not implemented");
        }
    }

    public UserMenu checkUserName(String name){
        wait30second.until(ExpectedConditions.visibilityOf(userName));
        assertThat(userName.getText().equals(name))
                .as("В заголовке меню пользователя указано имя "+ name).isTrue();
        return this;
    }

}
