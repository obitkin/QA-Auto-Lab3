package hw2.ex1;

import hw2.TestData;
import hw2.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ex1TestSteps implements TestData {

    private WebDriver driver;

    HomePage homePage;

    @BeforeClass
    public void setup() {
//        System.setProperty("webdriver.chrome.driver", "/home/robert/IdeaProjects/AutoTestPolis/ChromeDriver/chromedriver");
//
//        driver = new ChromeDriver();
//        homePage = new HomePage(driver);
//
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("I open the page")
    public void openSiteTest() {
        System.setProperty("webdriver.chrome.driver", "/home/robert/IdeaProjects/AutoTestPolis/ChromeDriver/chromedriver");

        driver = new ChromeDriver();
        homePage = new HomePage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

    @And("I log as \"Roman\" - \"Jdi1234\"")
    public void performLoginTest(String login, String password) {
        homePage.header.signInWithoutClear(login, password);
        Assert.assertNotEquals(homePage.header.getName(),""); //Useless
    }

    @Then("Browser title is \"Home Page\"")
    public void assertBrowserTitleTest(String title) {
        Assert.assertEquals(homePage.getTitle(),title);
    }

    @Then("Username is \"ROMAN IOVLEV\"")
    public void assertUserNameTest(String name) {
        Assert.assertEquals(homePage.header.getName(),name);
    }

    @Then("4 items of header section have proper texts")
    public void assertHeaderSectionNavigation() {
        Assert.assertEquals(
                homePage.header.getNavigationList().size(),
                navigation.size());
        Assert.assertEquals(
                homePage.header.getNavigationList().stream().map(WebElement::getText).toArray(),
                navigation.toArray());
    }

    @Then("Images are displayed")
    public void assertImages() {
        Assert.assertEquals(
                homePage.getImages().size(),
                expectedImages);
        homePage.getImages().forEach(x -> Assert.assertTrue(x.isDisplayed()));
    }

    @Then("Texts blow images are displayed")
    public void assertTextOfImages() {
        Assert.assertEquals(
                homePage.getTextOfImages().size(),
                textOfImages.size());
        homePage.getTextOfImages().forEach(x -> Assert.assertTrue(x.isDisplayed()));
    }

    @And("They have proper text")
    public void assertTextOfImages2() {
        Assert.assertEquals(
                homePage.getTextOfImages().stream().map(WebElement::getText).toArray(),
                textOfImages.toArray());
    }

    @Then("First paragraph of main header has proper text")
    public void assertTextOfMainHeaders() {
        Assert.assertEquals(homePage.getMainTitle(), mainTitle);
    }

    @Then("Second paragraph of main header has proper text")
    public void assertTextOfMainHeaders2() {
        Assert.assertEquals(homePage.getMainText(), mainText);
    }

    @Then("The iframe exists")
    public void assertIframeInCenter() {
        Assert.assertNotNull(homePage.getCentralFrameWebElement());
        Assert.assertTrue(homePage.getCentralFrameWebElement().isDisplayed());
    }

    @When("Switch to the iframe")
    public void assertEpamLogoInFrame() {
        Assert.assertTrue(homePage.getCentralFrameWebElement().isDisplayed());
    }

    @Then("Check EPAM logo")
    public void assertEpamLogoInFrame2() {
        Assert.assertNotNull(homePage.getCentralFrame().getLogo());
    }

    @And("Switch to home page")
    public void getToDefault() {
        homePage.getBackFromFrameToDefault();
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

    @Then("Text has proper value")
    public void assertTextSubHeader() {
        Assert.assertEquals(homePage.getSubHeader().getText(),subHeader);
    }

    @Then("JDI GITHUB is a Link")
    public void assertLinkSubHeader() {
        Assert.assertTrue(homePage.getSubHeader().isDisplayed());
    }

    @And("It has proper value")
    public void assertLinkSubHeader2() {
        Assert.assertEquals(homePage.getSubHeader().getAttribute("href"),JDI_GITHUB_URL);
    }

    @Then("Left Section exists")
    public void assertLeftSection() {
        Assert.assertTrue(homePage.leftSection.isDisplayed());
    }

    @Then("Footer exists")
    public void assertFooter() {
        Assert.assertTrue(homePage.getFooter().isDisplayed());
    }

    @Then("Close browser")
    public void assertQuit() {
        homePage.quit();
    }
}
