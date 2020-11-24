package ru.surovtseva.dns.hw6.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static ru.surovtseva.dns.hw6.common.Configuration.LOGIN_PAGE_URL;

public abstract class BaseTest {
    public WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeTest() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(LOGIN_PAGE_URL);
    }

    @AfterEach
    void tearDown() {
        if (driver != null){
            driver.quit();
        }
    }
}
