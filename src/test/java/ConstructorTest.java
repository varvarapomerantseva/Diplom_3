import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ConstructorTest {

    private static WebDriver driver;
    private final String expectedClassName = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

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

    @Test
    public void bunSection() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        objHomePage.clickSauceButton();
        objHomePage.clickBunButton();
        Assert.assertEquals(expectedClassName, objHomePage.getClassNameBun());
    }

    @Test
    public void sauceSection() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        objHomePage.clickSauceButton();
        Assert.assertEquals(expectedClassName, objHomePage.getClassNameSauce());
    }

    @Test
    public void fillingSection() {
        pageobject.HomePageBurger objHomePage = new pageobject.HomePageBurger(driver);
        objHomePage.clickFillingButton();
        Assert.assertEquals(expectedClassName, objHomePage.getClassNameFilling());
    }
}
