package ru.surovtseva.dns.hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CreateWishList {
    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL= "http://dns-shop.ru/";
    private static final String USER_LOGIN = "mydiary00@mail.ru";
    private static final String USER_PASSWORD = "MMyyDD20!";
    private static final String buttonEntry = "//button[contains(.,'Войти')]";
    private static final String buttonEntryWithPass = "//div[@class='block-other-login-methods__password-caption']";
    private static final String loginInput = "//div[@class='form-entry-with-password__input']/div/input";
    private static final String passwordInput = "//div[@class='form-entry-with-password__password']/div/input";
    private static final String buttonEntryOnForm = "//div[contains(@class, 'base-ui-button')]";
    private static final String imgAvatar = "//div[@class='header__login']/img[@class='header-profile__avatar loaded']";
    private static final String favouriteGoods = "//ul[@class='user__menu']/li[contains(.,'Избранные товары')]";
    private static final String desktopTitle = "//span[@class='desktop-title']";
    private static final String createdListsSection = "//h3[contains(.,'Созданные списки')]";
    private static final String buttonCreateWishlist = "//div[@class='create-wishlist-button']";
    private static final String titleOnFormCreateList = "//div[@class='modal-header' and contains(.,'Создать список избранных товаров')]";
    private static final String inputNameOfList = "//input[@name='title']";
    private static final String buttonCreate = "//span[contains(@class, 'submit-changes')]";
    private static final String nameOfList = "Список № 2";
    private static final String  nameOfCreatedList = "//div[@class='name' and contains(.,'"+nameOfList+"')]";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        login();

        //Переход в Меню пользователя - Избранные товары
        new WebDriverWait(driver, 3).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(imgAvatar))));
        driver.findElement(By.xpath(imgAvatar)).click();
        driver.findElement(By.xpath(favouriteGoods)).click();

        //Проверка: Открыта страница Кабинет покупателя
        System.out.println("На странице отображается заголовок Кабинет покупателя: " +
                driver.findElement(By.xpath(desktopTitle)).isDisplayed());
        System.out.println("------------------------");

        System.out.println("Отображается раздел Созданные списки: " +
                driver.findElement(By.xpath(createdListsSection)).isDisplayed());
        System.out.println("------------------------");

        WebElement buttonCreateNewList = driver.findElement(By.xpath(buttonCreateWishlist));

        System.out.println("Присутствует кнопка Создать новый список: " +
                buttonCreateNewList.isDisplayed());
        System.out.println("------------------------");

        //click on button Создать новы список
        buttonCreateNewList.click();

        new WebDriverWait(driver, 3).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(titleOnFormCreateList))));
        System.out.println("Открыта форма Создать список избранных товаров: " +
                driver.findElement(By.xpath(titleOnFormCreateList)).isDisplayed());
        System.out.println("------------------------");

        //Заполняем поле Название списка
        driver.findElement(By.xpath(inputNameOfList)).sendKeys(nameOfList);

        //click on button Создать
        driver.findElement(By.xpath(buttonCreate)).click();

        System.out.println("Отображается раздел Созданные списки: " +
                driver.findElement(By.xpath(createdListsSection)).isDisplayed());
        System.out.println("------------------------");

        new WebDriverWait(driver, 3).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(nameOfCreatedList))));

        System.out.println("В разделе Созданные списки отображается новый список: " +
                driver.findElement(By.xpath(nameOfCreatedList)).isDisplayed());
        System.out.println("------------------------");

        tearDown();
    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        driver.manage().window().maximize();

        driver.findElement(By.xpath(buttonEntry)).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(buttonEntryWithPass))));

        driver.findElement(By.xpath(buttonEntryWithPass)).click();

        driver.findElement(By.xpath(loginInput)).sendKeys(USER_LOGIN);
        driver.findElement(By.xpath(passwordInput)).sendKeys(USER_PASSWORD);

        driver.findElement(By.xpath(buttonEntryOnForm)).click();
    }

    private static void tearDown() {
        System.out.println();
        System.out.println("Test is completed!");
        driver.quit();
    }
}
