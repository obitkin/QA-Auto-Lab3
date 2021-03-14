package hw2.ex1;

import hw2.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ex1Test {

    String url = "https://jdi-testing.github.io/jdi-light/index.html";

    LoginTest loginTest;

    List<String> navigation = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
    int expectedImages = 4;
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
    String mainTitle = "EPAM FRAMEWORK WISHES…";
    String mainText = "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, " +
            "SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
            "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
            "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
            "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.";
    String subHeader = "JDI GITHUB";
    String JDI_GITHUB_URL = "https://jdi-testing.github.io/jdi-light/index.html";

    HomePage homePage;
    WebDriver driver;

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
     *  5. Assert Browser title
     */
    @Test(priority = 5)
    public void assertBrowserTitleTest2() {
        loginTest.assertBrowserTitleTest();
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
        Assert.assertEquals(
                homePage.getImages().size(),
                expectedImages);
        homePage.getImages().forEach(x -> Assert.assertTrue(x.isDisplayed()));
    }

    /**
     *  8. Assert that there are 4 texts on the Index Page under icons and they have proper text
     */
    @Test(priority = 8)
    public void assertTextOfImages() {
        Assert.assertEquals(
                homePage.getTextOfImages().size(),
                textOfImages.size());
        homePage.getTextOfImages().forEach(x -> Assert.assertTrue(x.isDisplayed()));
        Assert.assertEquals(
                homePage.getTextOfImages().stream().map(WebElement::getText).toArray(),
                textOfImages.toArray());
    }

    /**
     *  9. Assert a text of the main headers
     */
    @Test(priority = 9)
    public void assertTextOfMainHeaders() {
        Assert.assertEquals(homePage.getMainTitle(), mainTitle);
        Assert.assertEquals(homePage.getMainText(), mainText);
    }

    /**
     *  10. Assert that there is the iframe in the center of page
     */
    @Test(priority = 10)
    public void assertIframeInCenter() {
        Assert.assertNotNull(homePage.getCentralFrameWebElement());
        Assert.assertTrue(homePage.getCentralFrameWebElement().isDisplayed());
    }

    /**
     *  11. Switch to the iframe and check that there is Epam logo in the left top conner of iframe
     */
    @Test(priority = 11)
    public void assertEpamLogoInFrame() {
        Assert.assertTrue(homePage.getCentralFrameWebElement().isDisplayed());
        Assert.assertNotNull(homePage.getCentralFrame().getLogo());
    }

    /**
     *  12. Switch to original window back
     */
    @Test(priority = 12)
    public void getToDefault() {
        homePage.getBackFromFrameToDefault();
    }

    /**
     *  13. Assert a text of the sub header
     */
    @Test(priority = 13)
    public void assertTextSubHeader() {
        Assert.assertEquals(homePage.getSubHeader().getText(),subHeader);
    }

    /**
     *  14. Assert that JDI GITHUB is a link and has a proper URL
     */
    @Test(priority = 14)
    public void assertLinkSubHeader() {
        Assert.assertTrue(homePage.getSubHeader().isDisplayed());
        Assert.assertEquals(homePage.getSubHeader().getAttribute("href"),JDI_GITHUB_URL);
    }

    /**
     *  15. Assert that there is Left Section
     */
    @Test(priority = 15)
    public void assertLeftSection() {
        Assert.assertTrue(homePage.getSideBarMenu().isDisplayed());
    }

    /**
     *  16. Assert that there is Footer
     */
    @Test(priority = 16)
    public void assertFooter() {
        Assert.assertTrue(homePage.getFooter().isDisplayed());
    }

    /**
     *  17. Assert that there is Footer
     */
    @Test(priority = 17)
    public void assertQuit() {
        homePage.quit();
    }

}
