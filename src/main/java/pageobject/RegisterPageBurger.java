package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegisterPageBurger {

    //Поле "Имя"
    private final By nameField = By.xpath(".//fieldset[1]//input");

    //Поле "Email"
    private final By emailField = By.xpath(".//fieldset[2]//input");

    //Поле "Пароль"
    private final By passwordField = By.xpath(".//fieldset[3]//input");

    //Ошибка поля "Пароль" - "Некорректный пароль"
    private final By errorPassword = By.xpath(".//*[@class='input__error text_type_main-default']");

    //Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Зарегистрироваться']");

    //кнопка Лого
    private final By logoButton = By.xpath("//*[@class='AppHeader_header__logo__2D0X2']");

    //Кнопка "Войти"
    private final By enterButton = By.xpath(".//a[text()='Войти']");

    //кнопка "Восстановить пароль"
    private final By recoverPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");

    //кнопка "Войти" в востановлении пароля
    private final By recoverEnterButton = By.xpath(".//a[text()='Войти']");

    private final WebDriver driver;

    public RegisterPageBurger(WebDriver driver) {
        this.driver = driver;
    }

    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void setRegisterForm(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
        clickRegisterButton();
    }

    public LoginPageBurger clickRegisterButton() {
        driver.findElement(registerButton).click();
        return new LoginPageBurger(driver);
    }

    public String getTextErrorPassword() {
        return driver.findElement(errorPassword).getText();
    }

    public HomePageBurger clickLogoButton() {
        driver.findElement(logoButton).click();
        return new HomePageBurger(driver);
    }

    public LoginPageBurger clickEnterButton() {
        driver.findElement(enterButton).click();
        return new LoginPageBurger(driver);
    }

    public LoginPageBurger clickRecoverEnterButton() {
        driver.findElement(registerButton).click();
        driver.findElement(recoverEnterButton).click();
        return new LoginPageBurger(driver);
    }
}
