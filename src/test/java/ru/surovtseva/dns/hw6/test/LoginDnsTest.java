package ru.surovtseva.dns.hw6.test;

import org.junit.jupiter.api.Test;
import ru.surovtseva.dns.hw6.base.BaseTest;
import ru.surovtseva.dns.hw6.pages.HomePage;

import static ru.surovtseva.dns.hw6.common.Configuration.*;

public class LoginDnsTest extends BaseTest {

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
