package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPageBurger {

    //кнопка "Зарегистрироваться
    private final By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");

    //поле "email"
    private final By emailField = By.xpath(".//fieldset[1]//input");

    //поле "Пароль"
    private final By passwordField = By.xpath(".//fieldset[2]//input");

    //кнопка "Войти"
    private final By enterButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']");

    //кнопка "Выход"
    private final By exitButton = By.xpath(".//button[@class ='Account_button__14Yp3 text text_type_main-medium text_color_inactive' and text()='Выход']");


    private final WebDriver driver;

    public LoginPageBurger(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageBurger clickRegisterButton() {

        driver.findElement(registerButton).click();
        return new RegisterPageBurger(driver);
    }

    public void setEmailField(String email) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPasswordField(String password) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
    }

    public HomePageBurger clickEnterButton() {
        driver.findElement(enterButton).click();
        return new HomePageBurger(driver);
    }

    public void setEnterForm(String email, String password) {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']")));
        setEmailField(email);
        setPasswordField(password);
        clickEnterButton();
    }

    public void clickExitButton() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[@class ='Account_button__14Yp3 text text_type_main-medium text_color_inactive' and text()='Выход']")));
        driver.findElement(exitButton).click();
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']")));
    }
}

