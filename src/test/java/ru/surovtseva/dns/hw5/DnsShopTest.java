package ru.surovtseva.dns.hw5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class DnsShopTest {
    private static WebDriver driver;
    private static final Map<String, Object> elementParameters = new HashMap<>();
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
    private static final String userNamePath = "//a[@class='header-profile__username']";
    private static final String userName = "Mydiary";
    private static final String desktopTitle = "//span[@class='desktop-title']";
    private static final String titleText = "Кабинет покупателя";
    private static final String standardListSectionPath = "//h3[contains(.,'Стандартные списки')]";
    private static final String buttonCreateWishlist = "//div[@class='create-wishlist-button']";
    private static final String favouriteGoods = "//ul[@class='user__menu']/li[contains(.,'Избранные товары')]";
    private static final String titleOnFormCreateList = "//div[@class='modal-header' and contains(.,'Создать список избранных товаров')]";
    private static final String inputNameOfList = "//input[@name='title']";
    private static final String nameOfList = "Список № 3";
    private static final String buttonCreate = "//span[contains(@class, 'submit-changes')]";
    private static final String nameOfCreatedList = "//div[@class='name' and contains(.,'"+nameOfList+"')]";
    private static final String inputSearchPath = "//input[@placeholder='Поиск по сайту']";
    private static final String requestText = "LED Samsung LT24H390SIX";
    private static final String pageGoodTitle = "//h1[contains(@class, 'page-title')]";
    private static final String buyButtonPath = "//button[contains(.,'Купить')]";
    private static final String modalTitleText = "Основные товары";
    private static final String buttonMoveToCartPath = "//a[contains(.,'Перейти в корзину')]";
    private static final String productNamePath = "//a[@class = 'cart-items__product-name-link' and contains(.,'"+requestText+"')]";
    private static final String placeOrderButtonPath = "//button[contains(.,'Оформить заказ')]";
    private static final String generalListSubsectionPath = "//div[@class='name']";
    private static final String createdListsSection = "//h3[contains(.,'Созданные списки')]";
    private static final String goodPositionPath = "//span[@class='name' and contains(.,'"+requestText+"')]";

    @BeforeAll
    static void setUpWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        setUpDriverSession();
        login();
    }

    @Test
    void userAuthorisationTest(){
        authorisation();
    }

    @Test
    void createWishlistTest() {
        authorisation();
        pageCustomerCabinetIsOpen();
        formCreateWishlistIsOpen();
        wishListIsCreated();
    }

    @Test
    void addGoodToCart() {
        authorisation();
        sendSearchRequest();
        buyButtonIsClick();
        moveToCartButtonIsClick();
    }

    @Test
    void addGoodToFavourite() {
        authorisation();
        sendRequest();
        toFavouriteButtonIsClick();
        moveToCustomerCabinet();
    }

    @AfterEach
    void tearDown() {
        if(driver !=null){
            driver.quit();
        }
    }

//*** Методы теста Добавление товара в Избранное ***
    private void moveToCustomerCabinet() {
        authorisation();
        pageCustomerCabinetIsOpen();
        WebElement standardListSection = driver.findElement(By.xpath(standardListSectionPath));
        WebElement generalListSubsection = driver.findElement(By.xpath(generalListSubsectionPath));
        WebElement goodPosition = driver.findElement(By.xpath(goodPositionPath));

        Assertions.assertAll(
                ()-> assertThat(standardListSection.isDisplayed()).
                        as("Присутствует раздел Стандартные списки").isTrue(),
                ()-> assertThat(generalListSubsection.isDisplayed()).
                        as("Присутствует секция Общий список").isTrue(),
                ()-> assertThat(goodPosition.isDisplayed()).
                        as("В общем списке отображается товар " + requestText).isTrue()
        );
    }

    private void toFavouriteButtonIsClick() {
        driver.findElement(By.className("wishlist__icon-add")).click();

        new WebDriverWait(driver, 7).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.className("wishlist__icon-add_added"))));

        elementParameters.put("inFavouriteAfterContent", ((JavascriptExecutor) driver).
                executeScript("return window.document.defaultView.getComputedStyle(" +
                        "window.document.getElementsByClassName('wishlist__icon-add')[0],':after')" +
                        ".getPropertyValue('content')"));

        elementParameters.put("inFavouriteIconColor", ((JavascriptExecutor) driver).
                executeScript("return window.document.defaultView.getComputedStyle(" +
                        "window.document.getElementsByClassName('wishlist__icon-add')[0])" +
                        ".getPropertyValue('color')"));

        Assertions.assertAll(
                ()->assertThat(elementParameters.get("inFavouriteAfterContent").toString().equals("\"В избранном\"")).
                        as("Присутствует кнопка В избранном").isTrue(),
                ()->assertThat(elementParameters.get("inFavouriteIconColor").toString().equals("rgb(252, 133, 7)")).
                        as("Иконка В избранном имеет цвет rgb(252, 133, 7)").isTrue()
        );
    }

    private void sendRequest() {
        sendSearchRequest();
        new WebDriverWait(driver, 7).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.className("wishlist__icon-add"))));

        elementParameters.put("favouriteAfterContent", ((JavascriptExecutor) driver).
                executeScript("return window.document.defaultView.getComputedStyle(" +
                        "window.document.getElementsByClassName('wishlist__icon-add')[0],':after')" +
                        ".getPropertyValue('content')"));

        assertThat(elementParameters.get("favouriteAfterContent").toString().equals("\"В избранное\"")).
                        as("Присутствует кнопка В избранное").isTrue();

    }

//*** Методы теста Добавление товара в корзину ***

    private void moveToCartButtonIsClick() {
        driver.findElement(By.xpath(buttonMoveToCartPath)).click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.className("cart-title"))));

        WebElement productName = driver.findElement(By.xpath(productNamePath));
        WebElement placeOrderButton = driver.findElement(By.xpath(placeOrderButtonPath));

        Assertions.assertAll(
                ()->assertThat(driver.findElement(By.className("cart-title")).getText().equals("Корзина")).
                        as("Открыта страница Корзина").isTrue(),
                ()->assertThat(productName.isDisplayed()).
                        as("Присутствует секция с наименованием товара " + requestText).isTrue(),
                ()->assertThat(placeOrderButton.isDisplayed()).as("присутствует кнопка Оформить заказ").isTrue()
        );
    }

    private void buyButtonIsClick() {
        driver.findElement(By.xpath(buyButtonPath)).click();

        //Присутствует кнопка Перейти в корзину
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(buttonMoveToCartPath))));

        Assertions.assertAll(
                ()->assertThat(driver.findElement(By.className("cart-summary")).getText().
                        contains(modalTitleText)).as("Заголовок окна Основные товары").isTrue(),
                ()->assertThat(driver.findElement(By.className("cart-product__name")).getText().
                        contains(requestText)).as("В окне присутствует позиция с товаром").isTrue()
        );
    }

//*** Методы теста Создание списка избранных товаров ***

    private void wishListIsCreated() {
        //Заполнение поля Название списка
        driver.findElement(By.xpath(inputNameOfList)).sendKeys(nameOfList);
        driver.findElement(By.xpath(buttonCreate)).click();

        new WebDriverWait(driver, 3).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(nameOfCreatedList))));

        assertThat(driver.findElement(By.xpath(nameOfCreatedList)).getText().equals(nameOfList)).
                as("В разделе Созданные списки отображается новый список").isTrue();

    }

    private void formCreateWishlistIsOpen() {
        driver.findElement(By.xpath(buttonCreateWishlist)).click();

        //Присутствует заголовок Создать список избранных товаров
        new WebDriverWait(driver, 3).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(titleOnFormCreateList))));
    }

//*** Общие методы ***

    private void sendSearchRequest() {
        WebElement inputSearch = driver.findElement(By.xpath(inputSearchPath));
        inputSearch.sendKeys(requestText);
        inputSearch.sendKeys(Keys.ENTER);

        //Присутствует кнопкка Купить
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(buyButtonPath))));

        WebElement pageTitle = driver.findElement(By.xpath(pageGoodTitle));

        Assertions.assertAll(
                ()->assertThat(pageTitle.getText().contains(requestText)).
                        as("Заголовок страницы содержит текст "+requestText).isTrue()
        );
    }

    private void pageCustomerCabinetIsOpen() {
        driver.findElement(By.xpath(favouriteGoods)).click();

        WebElement titleOnPage = driver.findElement(By.xpath(desktopTitle));
        WebElement listSection = driver.findElement(By.xpath(createdListsSection));
        WebElement createListButton = driver.findElement(By.xpath(buttonCreateWishlist));

        Assertions.assertAll(
                ()-> assertThat(titleOnPage.getText().equals(titleText)).
                        as("Отображается заголовок Кабинет покупателя").isTrue(),
                ()-> assertThat(listSection.isDisplayed()).
                        as("Отображается раздел Созданные списки").isTrue(),
                ()-> assertThat(createListButton.isDisplayed()).
                        as("Присутствует кнопка Создать новый список").isTrue()
        );
    }

    private void authorisation() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(imgAvatarHeader))));

        WebElement avatar = driver.findElement(By.xpath(imgAvatarHeader));
        Actions actionHeader = new Actions(driver);
        actionHeader.moveToElement(avatar).perform();

        WebElement user = driver.findElement(By.xpath(userNamePath));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(user));

        Assertions.assertAll(
                ()->assertThat(user.getText().equals(userName)).
                        as("В заголовке меню пользователя указано имя "+ userName).isTrue()
        );
    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        driver.manage().window().maximize();

        new WebDriverWait(driver, 3).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(buttonEntry))));
        driver.findElement(By.xpath(buttonEntry)).click();

        new WebDriverWait(driver, 3).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.xpath(buttonEntryWithPass))));
        driver.findElement(By.xpath(buttonEntryWithPass)).click();

        WebElement formEntry = driver.findElement(By.xpath(formEntryWithPass));
        assertThat(formEntry.isDisplayed()).as("Открыта форма Войти с паролем").isTrue();

        driver.findElement(By.xpath(loginInput)).sendKeys(USER_LOGIN);
        driver.findElement(By.xpath(passwordInput)).sendKeys(USER_PASSWORD);

        driver.findElement(By.xpath(buttonEntryOnForm)).click();
    }
    private static void setUpDriverSession() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
