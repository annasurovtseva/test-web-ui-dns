package ru.surovtseva.dns.hw6.test;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.surovtseva.dns.hw6.base.BaseTest;
import ru.surovtseva.dns.hw6.pages.HomePage;

import static ru.surovtseva.dns.hw6.common.Configuration.*;

@Feature("Авторизация")
public class LoginDnsTest extends BaseTest {

    @DisplayName("Авторизация с использованием логин/пароль")
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
}
