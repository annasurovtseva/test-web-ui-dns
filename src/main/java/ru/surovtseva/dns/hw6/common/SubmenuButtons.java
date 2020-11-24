package ru.surovtseva.dns.hw6.common;

import org.openqa.selenium.By;

public enum SubmenuButtons {
    FAVOURITE_GOODS("//ul[@class='user__menu']/li[contains(.,'Избранные товары')]"),
    NOTIFY_LIST("//ul[@class='user__menu']/li[contains(.,'Товары с уведомлением')]");

    private final By by;

    SubmenuButtons(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}

