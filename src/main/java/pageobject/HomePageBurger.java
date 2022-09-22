package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePageBurger {

    //кнопка "Личный кабинет"
    private final By personalAccountButton = By.xpath("//*[@href='/account']");

    //Кнопка "Войти в аккаунт"
    private final By enterButton = By.xpath(".//button[text()='Войти в аккаунт']");

    //кнопка "Оформить заказ"
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");

    //кнопка Лого
    private final By logoButton = By.xpath("//*[@class='AppHeader_header__logo__2D0X2']");

    //кнопка "Конструктор"
    private final By constructorButton = By.xpath("//*[text()='Конструктор']");

    //кнопка "Булки"
    private final By bunButton = By.xpath(".//span[@class = 'text text_type_main-default' and text()='Булки']/parent::div");

    //кнопка "Соусы"
    private final By sauceButton = By.xpath(".//span[@class = 'text text_type_main-default' and text()='Соусы']/parent::div");

    //кнопка "Начинки"
    private final By fillingButton = By.xpath(".//span[@class = 'text text_type_main-default' and text()='Начинки']/parent::div");

    private final WebDriver driver;

    public HomePageBurger(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageBurger clickPersonalAccountButtonForLoginUser() {
        driver.findElement(personalAccountButton).click();
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[@class ='Account_button__14Yp3 text text_type_main-medium text_color_inactive' and text()='Выход']")));
        return new LoginPageBurger(driver);
    }

    public LoginPageBurger clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
        return new LoginPageBurger(driver);
    }

    public String getTextOrderButton() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver.findElement(orderButton).getText();
    }

    public LoginPageBurger clickEnterButton() {
        driver.findElement(enterButton).click();
        return new LoginPageBurger(driver);
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    public void clickBunButton() {
        driver.findElement(bunButton).click();

    }

    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    public String getClassNameBun() {
        return driver.findElement(bunButton).getAttribute("class");
    }

    public String getClassNameSauce() {
        return driver.findElement(sauceButton).getAttribute("class");
    }

    public String getClassNameFilling() {
        return driver.findElement(fillingButton).getAttribute("class");
    }

}

