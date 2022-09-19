import DataAPI.UserClient;
import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class RegistrationTest {

    private static UserClient userClient;
    static String accessToken;
    static String userToken;


    private static WebDriver driver;

    public static Faker faker = new Faker();
    static String email = faker.name().lastName() + "@mail.ru";
    static String password = faker.internet().password();
    static String passwordInvalid = faker.internet().password(1,5);
    static String name = faker.name().firstName();

    @Before
    public void openBrowser() {
        System.setProperty(BaseData.KEY, BaseData.VALUE);
        driver = new ChromeDriver();
        driver.get(BaseData.BASE_URL);
    }

    @Test
    public void registrationWithCorrectData() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        pageobject.LoginPageBurger objLoginPage = new pageobject.LoginPageBurger(driver);
        pageobject.RegisterPageBurger objRegisterPage = new pageobject.RegisterPageBurger(driver);
        objHomePage.clickPersonalAccountButton();
        objLoginPage.clickRegisterButton();
        objRegisterPage.setRegisterForm(name, email, password);
        objLoginPage.setEnterForm(email,password);
        String actual = objHomePage.getTextOrderButton();
        String expected = "Оформить заказ";
        Assert.assertEquals(expected, actual);
        userClient = new UserClient();
        ValidatableResponse response = userClient.login(email, password);
        accessToken = response.extract().path("accessToken");
        userToken = accessToken.substring(7);
        userClient.deleteUser(userToken);



    }
 @Test
 public void registrationWithInCorrectData() {
     pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
     pageobject.LoginPageBurger objLoginPage = new pageobject.LoginPageBurger(driver);
     pageobject.RegisterPageBurger objRegisterPage = new pageobject.RegisterPageBurger(driver);
     objHomePage.clickPersonalAccountButton();
     objLoginPage.clickRegisterButton();
     objRegisterPage.setRegisterForm(name, email, passwordInvalid);
     String actualError = objRegisterPage.getTextErrorPassword();
     String expectedError = "Некорректный пароль";
     Assert.assertEquals(expectedError, actualError);


 }

   @After
    public void tearDown() {
       driver.quit();
    }
}
