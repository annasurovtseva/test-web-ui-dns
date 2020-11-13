package ru.surovtseva.dns.hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AuthorisationUser {
    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL= "http://dns-shop.ru/";
    private static final String USER_LOGIN = "mydiary00@mail.ru";
    private static final String USER_PASSWORD = "MMyyDD20!";
    private static final String buttonEntry = "//button[contains(.,'Войти')]";
    private static final String buttonEntryWithPass = "//div[@class='block-other-login-methods__password-caption']";
    private static final String formEntryWithPass = "//div[@class='form-entry-with-password__title']";
    private static final String loginInput = "//div[@class='form-entry-with-password__input']/div/input";
    private static final String passwordInput = "//div[@class='form-entry-with-password__password']/div/input";
    private static final String buttonEntryOnForm = "//div[contains(@class, 'base-ui-button')]";
    private static final String imgAvatarHeader = "//div[@class='header__login']/img[@class='header-profile__avatar loaded']";
    private static final String userName = "//a[@class='header-profile__username']";


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get(LOGIN_PAGE_URL);
        driver.manage().window().maximize();

        //click on button Войти
        new WebDriverWait(driver, 3).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(buttonEntry))));
        System.out.println("Присутствует кнопка Войти: " +
                driver.findElement(By.xpath(buttonEntry)).isDisplayed());
        System.out.println("------------------------");

        driver.findElement(By.xpath(buttonEntry)).click();

        //click on button Войти с паролем
        new WebDriverWait(driver, 3).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(buttonEntryWithPass))));
        System.out.println("Присутствует кнопка Войти с паролем: " +
                driver.findElement(By.xpath(buttonEntryWithPass)).isDisplayed());
        System.out.println("------------------------");

        driver.findElement(By.xpath(buttonEntryWithPass)).click();

        //Проверка
        System.out.println("Открыта форма Войти с паролем: " +
                driver.findElement(By.xpath(formEntryWithPass)).isDisplayed());
        System.out.println("------------------------");

        //Заполнение полей e-mail и пароль
        driver.findElement(By.xpath(loginInput)).sendKeys(USER_LOGIN);
        driver.findElement(By.xpath(passwordInput)).sendKeys(USER_PASSWORD);

        //click on button Войти на форме Войти с паролем
        driver.findElement(By.xpath(buttonEntryOnForm)).click();

        //Разворачивается меню пользователя
        Actions actionHeader = new Actions(driver);
        WebElement avatar = driver.findElement(By.xpath(imgAvatarHeader));
        actionHeader.moveToElement(avatar).perform();

//        driver.findElement(By.xpath(imgAvatar)).click();

        //Проверка
        new WebDriverWait(driver, 3).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(userName))));
        System.out.println("В заголовке меню пользователя указано имя Mydiary: " +
                driver.findElement(By.xpath(userName)).getText().equals("Mydiary"));

//        tearDown();

    }

    private static void tearDown() {
        System.out.println();
        System.out.println("Test is completed!");
        driver.quit();
    }
}
