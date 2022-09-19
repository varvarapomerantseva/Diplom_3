import DataAPI.UserClient;
import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LoginTest {

    private static UserClient userClient;
    static String accessToken;
    static String userToken;


    private static WebDriver driver;

    public static Faker faker = new Faker();
    static String email = faker.name().lastName() + "@mail.ru";
    static String password = faker.internet().password();
    static String name = faker.name().firstName();

    @BeforeClass
    public static void createUser() {
        userClient = new UserClient();
        ValidatableResponse response = userClient.create(name, email, password);
        accessToken = response.extract().path("accessToken");
        userToken = accessToken.substring(7);

    }

    @Before
    public void openBrowser() {
        System.setProperty(BaseData.KEY, BaseData.VALUE);
        driver = new ChromeDriver();
        driver.get(BaseData.BASE_URL);
    }


    @After
    public void tearDown() {
        driver.quit();
    }

    @AfterClass
    public static void deleteUser() {
        userClient.deleteUser(userToken);
    }


    @Test
    public void loginClickEnterButton() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        pageobject.LoginPageBurger objLoginPage = new pageobject.LoginPageBurger(driver);
        objHomePage.clickEnterButton();
        objLoginPage.setEnterForm(email, password);
        String actual = objHomePage.getTextOrderButton();
        String expected = "Оформить заказ";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void loginClickPersonalAccountButton() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        pageobject.LoginPageBurger objLoginPage = new pageobject.LoginPageBurger(driver);
        objHomePage.clickPersonalAccountButton();
        objLoginPage.setEnterForm(email, password);
        String actual = objHomePage.getTextOrderButton();
        String expected = "Оформить заказ";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void loginClickEnterRegisterButton() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        pageobject.LoginPageBurger objLoginPage = new pageobject.LoginPageBurger(driver);
        pageobject.RegisterPageBurger objRegisterPage = new pageobject.RegisterPageBurger(driver);
        objHomePage.clickPersonalAccountButton();
        objLoginPage.clickRegisterButton();
        objRegisterPage.clickEnterButton();
        objLoginPage.setEnterForm(email, password);
        String actual = objHomePage.getTextOrderButton();
        String expected = "Оформить заказ";
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void loginClickEnterRecoverPasswordButton() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        pageobject.LoginPageBurger objLoginPage = new pageobject.LoginPageBurger(driver);
        pageobject.RegisterPageBurger objRegisterPage = new pageobject.RegisterPageBurger(driver);
        objHomePage.clickPersonalAccountButton();
        objLoginPage.clickRegisterButton();
        objRegisterPage.clickRecoverEnterButton();
        objLoginPage.setEnterForm(email, password);
        String actual = objHomePage.getTextOrderButton();
        String expected = "Оформить заказ";
        Assert.assertEquals(expected, actual);

    }
}
