package hw2.ex2;

import hw2.HomePage;
import hw2.ex1.LoginTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Ex2Test {

    String url = "https://jdi-testing.github.io/jdi-light/index.html";

    WebDriver driver;
    HomePage homePage;
    LoginTest loginTest;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/robert/IdeaProjects/AutoTestPolis/ChromeDriver/chromedriver");

        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        loginTest = new LoginTest(homePage);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     *  1. Open test site by URL
     */
    @Test(priority = 1)
    public void openSiteTest() {
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

    /**
     *  2. Assert Browser title
     */
    @Test(priority = 2)
    public void assertBrowserTitleTest() {
        loginTest.assertBrowserTitleTest();
    }

    /**
     *  3. Perform login
     */
    @Test(priority = 3)
    public void performLoginTest() {
        loginTest.performLoginTest();
    }

    /**
     *  4. Assert User name in the left-top side of screen that user is loggined
     */
    @Test(priority = 4)
    public void assertUserNameTest() {
        loginTest.assertUserNameTest();
    }

    /**
     *  5. Click on "Service" subcategory in the header and check that drop down contains options
     */
    @Test(priority = 5)
    public void assertBrowse() {

    }

    @AfterTest
    public void assertQuit() {
        homePage.quit();
    }
}