package hw3.steps;

import hw3.util.ConfProperties;
import hw3.util.TestData;
import hw3.inner.Main;
import hw3.pages.DifferentElementsPage;
import hw3.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StepDef implements TestData {

    private WebDriver driver;

    HomePage homePage;
    DifferentElementsPage diffPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.get("driver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments(List.of(
                "--no-sandbox",
                "--start-maximized",
                "--headless",            //should be enabled for Jenkins
                "--disable-dev-shm-usage"//should be enabled for Jenkins
        ));
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("I open the home page")
    public void openSiteTest() {
        driver.get(url);
        homePage = new HomePage(driver);
    }

    @Then("Home page is opened")
    public void checkOpenSiteTest() {
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

    @When("I log as {string} - {string}")
    public void performLoginTest(String login, String password) {
        homePage.header.signInWithoutClear(login, password);
        Assert.assertNotEquals(homePage.header.getName(),"");
    }

    @Then("Username is not null")
    public void checkLoginTest() {
        Assert.assertNotEquals(homePage.header.getName(),"");
    }

    @Then("Browser title is {string}")
    public void assertBrowserTitleTest(String title) {
        Assert.assertEquals(homePage.getTitle(),title);
    }

    @Then("Username is {string}")
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

    @Then("Texts block images are displayed")
    public void assertTextOfImages() {
        Assert.assertEquals(
                homePage.getTextOfImages().size(),
                textOfImages.size());
        homePage.getTextOfImages().forEach(x -> Assert.assertTrue(x.isDisplayed()));
    }

    @And("Blocks have proper text")
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

    @Then("Drop down options of top Service have proper values")
    public void assertHeaderService() {
        WebElement service = homePage.header.getNavigationElement("Service");
        service.click();
        List<String> foundElements = homePage.header.takeElementsFromList(service)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        SoftAssert softAssertion = new SoftAssert();
        for (String elem : serviceList) {
            softAssertion.assertTrue(foundElements.stream().anyMatch(elem::equalsIgnoreCase),
                    elem + " NOT FOUND");
        }
        softAssertion.assertAll();
    }

    @Then("Drop down options of left Service have proper values")
    public void assertLeftService() {
        WebElement service = homePage.leftSection.getNavigationElement("Service");
        service.click();
        List<String> foundElements = homePage.leftSection.takeElementsFromList(service)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        SoftAssert softAssertion = new SoftAssert();
        for (String elem : serviceList) {
            softAssertion.assertTrue(foundElements.stream().anyMatch(elem::equalsIgnoreCase),
                    elem + " NOT FOUND");
        }
        softAssertion.assertAll();
    }

    @Given("Open {string} Page")
    public void openNewServicePage(String str) {
        diffPage = homePage.header.goTo(
                "Service",
                str,
                driver,
                DifferentElementsPage::new);
    }

    @Then("Check current URL")
    public void openNewServicePage2() {
        Assert.assertEquals(driver.getCurrentUrl(),diffURL);
    }

    @And("There are 4 radios")
    public void checkMainRadios() {
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(diffPage.main.getRadio().size(),4);
        softAssertion.assertAll();
    }

    @And("There are 4 checkboxes")
    public void checkMainCheckboxes() {
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(diffPage.main.getCheckBoxes().size(),4);
        softAssertion.assertAll();
    }

    @And("There are 1 dropdown")
    public void checkMainDropdown() {
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(diffPage.main.getDropDown().size(),1);
        softAssertion.assertAll();
    }

    @And("There are 2 buttons")
    public void checkMainButtons() {
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(diffPage.main.getButtons().size(),2);
        softAssertion.assertAll();
    }

    @Then("Right Section displayed")
    public void assertRightSection() {
        diffPage.rightSection.checkSelf();
    }

    @And("Left Section displayed")
    public void assertLeftSectionDiffPage() {
        diffPage.leftSection.checkSelf();
    }

    @When("Select checkboxes {string} and {string}")
    public void selectCheckboxes(String str1, String str2) {
        diffPage.main.getCheckBoxes(str1).select();
        diffPage.main.getCheckBoxes(str2).select();
    }

    @Then("Checkboxes {string} and {string} are selected")
    public void checkSelectCheckboxes(String str1, String str2) {
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(diffPage.main.getCheckBoxes(str1).isSelected());
        softAssertion.assertTrue(diffPage.main.getCheckBoxes(str2).isSelected());
        softAssertion.assertAll();
    }

    @And("Status of checkboxes in Log row are displayed and corresponding")
    public void assertCheckbox() {
        diffPage.main.clear();
        SoftAssert softAssertion = new SoftAssert();
        for (WebElement checkBox : diffPage.main.getCheckBoxes()) {
            checkBox.click();
            softAssertion.assertTrue(Main.getInput(checkBox).isSelected());
            softAssertion.assertTrue(
                    diffPage.rightSection.getLog().get(0).getText().contains(checkBox.getText()) &&
                            diffPage.rightSection.getLog().get(0).getText().contains("true")
            );
            checkBox.click();
            softAssertion.assertFalse(Main.getInput(checkBox).isSelected());
            softAssertion.assertTrue(
                    diffPage.rightSection.getLog().get(0).getText().contains(checkBox.getText()) &&
                            diffPage.rightSection.getLog().get(0).getText().contains("false")
            );
            softAssertion.assertAll();
        }
    }

    @Then("Checkboxes {string} and {string} are unselected")
    public void unselectCheckboxes(String str1, String str2) {
        diffPage.main.clear();
        Assert.assertFalse(diffPage.main.getCheckBoxes(str1).isSelected());
        Assert.assertFalse(diffPage.main.getCheckBoxes(str2).isSelected());
    }

    @When("Click {string} radio")
    public void selectRadio(String str) {
        diffPage.main.getRadio(str).click();
    }

    @Then("Radio {string} is selected")
    public void checkRadio(String str) {
        Assert.assertTrue(diffPage.main.getRadio(str).isSelected());
    }

    @And("Status of radios in Log row is displayed and corresponding")
    public void assertRadio() {
        diffPage.main.clear();
        SoftAssert softAssertion = new SoftAssert();
        for (WebElement radio : diffPage.main.getRadio()) {
            radio.click();
            softAssertion.assertTrue(Main.getInput(radio).isSelected());
            softAssertion.assertTrue(diffPage.rightSection.getLog().get(0).getText().contains(radio.getText()));
        }
        softAssertion.assertAll();
    }

    @When("Select {string} in dropdown")
    public void selectDropDown(String str) {
        diffPage.main.getDropDown().get(0).selectByVisibleText(str);
    }

    @Then("{string} in dropdown is selected")
    public void checkDropDown(String str) {
        Assert.assertTrue(diffPage.rightSection.getLog().get(0).getText().contains(str));
    }

    @And("Status of dropdown in Log row is displayed and corresponding")
    public void assertDropDown() {
        SoftAssert softAssertion = new SoftAssert();
        for (WebElement option : diffPage.main.getDropDown().get(0).getOptions()) {
            diffPage.main.getDropDown().get(0).selectByVisibleText(option.getText());
            softAssertion.assertTrue(diffPage.rightSection.getLog().get(0).getText().contains(option.getText()));
        }
        softAssertion.assertAll();
    }

    @After
    public void assertQuit() {
        driver.quit();
    }
}
