package hw2.ex1;

import hw2.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ex1Test {

    String url = "https://jdi-testing.github.io/jdi-light/index.html";
    String login = "Roman";
    String passwd = "Jdi1234";
    String name = "ROMAN IOVLEV";
    String title = "Home Page";
    List<String> navigation = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
    List<String> textOfImages = Arrays.asList(
            "To include good practices\n" +
                    "and ideas from successful\n" +
                    "EPAM project",
            "To be flexible and\n" +
                    "customizable",
            "To be multiplatform",
            "Already have good base\n" +
                    "(about 20 internal and\n" +
                    "some external projects),\n" +
                    "wish to get more…");

    int expectedImages = 4;

    HomePage homePage;
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/robert/IdeaProjects/AutoTestPolis/ChromeDriver/chromedriver");

        driver = new ChromeDriver();
        homePage = new HomePage(driver);

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
        Assert.assertEquals(homePage.getTitle(),title);
    }

    /**
     *  3. Perform login
     */
    @Test(priority = 3)
    public void performLoginTest() {
        homePage.openMenu();
        homePage.inputLogin(login);
        homePage.inputPasswd(passwd);
        homePage.clickLoginBtn();
    }

    /**
     *  4. Assert User name in the left-top side of screen that user is loggined
     */
    @Test(priority = 4)
    public void assertUserNameTest() {
        Assert.assertEquals(homePage.getName(),name);
    }

    /**
     *  5. Assert Browser title
     */
    @Test(priority = 5)
    public void assertBrowserTitleTest2() {
        Assert.assertEquals(homePage.getName(),name);
    }

    /**
     *  6. Assert that there are 4 items on the header section are displayed and they have proper texts
     */
    @Test(priority = 6)
    public void assertHeaderSectionNavigation() {
        Assert.assertEquals(
                homePage.getHeaderSectionNavigation().size(),
                navigation.size());
        Assert.assertEquals(
                homePage.getHeaderSectionNavigation().stream().map(WebElement::getText).toArray(),
                navigation.toArray());
    }

    /**
     *  7. Assert that there are 4 images on the Index Page and they are displayed
     */
    @Test(priority = 7)
    public void assertImages() {
        Assert.assertEquals(homePage.getImages().size(),expectedImages);
        homePage.getImages().forEach(x -> Assert.assertTrue(x.isDisplayed()));
    }

    /**
     *  8. Assert that there are 4 texts on the Index Page under icons and they have proper text
     */
    @Test(priority = 8)
    public void assertTextOfImages() {
        Assert.assertEquals(homePage.getTextOfImages().size(), textOfImages.size());
        homePage.getTextOfImages().forEach(x -> Assert.assertTrue(x.isDisplayed()));
        Assert.assertEquals(
                homePage.getTextOfImages().stream().map(WebElement::getText).toArray(),
                textOfImages.toArray());
    }



    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
