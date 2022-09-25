import dataapi.UserClient;
import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserAccountTest {
    private static UserClient userClient;
    private static String accessToken;
    private static String userToken;
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
        pageobject.LoginPageBurger objLoginPage = new pageobject.LoginPageBurger(driver);
        pageobject.RegisterPageBurger objRegisterPage = new pageobject.RegisterPageBurger(driver);
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        objHomePage.clickEnterButton();
        objLoginPage.setEnterForm(email, password);
        objRegisterPage.clickLogoButton();
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
    public void openUserProfile() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        objHomePage.clickPersonalAccountButtonForLoginUser();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://stellarburgers.nomoreparties.site/account/profile";
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void openConstructorOnClickConstructorButton() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        objHomePage.clickPersonalAccountButtonForLoginUser();
        objHomePage.clickConstructorButton();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void openConstructorOnClickLogoButton() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        objHomePage.clickPersonalAccountButtonForLoginUser();
        objHomePage.clickLogoButton();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void exitUserProfile() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        pageobject.LoginPageBurger objLoginPage = new pageobject.LoginPageBurger(driver);
        objHomePage.clickPersonalAccountButtonForLoginUser();
        objLoginPage.clickExitButton();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}
