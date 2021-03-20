package hw2.ex2;

import hw2.inner.Main;
import hw2.pages.DifferentElementsPage;
import hw2.pages.HomePage;
import hw2.ex1.LoginTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex2Test {

    String URL = "https://jdi-testing.github.io/jdi-light/index.html";
    String diffURL = "https://jdi-testing.github.io/jdi-light/different-elements.html";
    List<String> serviceList = Arrays.asList("Support", "Dates", "Complex Table", "Simple Table", "Tables With Pages", "Different Elements");

    WebDriver driver;

    HomePage homePage;
    DifferentElementsPage diffPage;

    LoginTest loginTest;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/robert/IdeaProjects/AutoTestPolis/ChromeDriver/chromedriver");

        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        loginTest = new LoginTest(homePage);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }

    /**
     *  1. Open test site by URL
     */
    @Test(priority = 1)
    public void openSiteTest() {
        driver.get(URL);
        Assert.assertEquals(driver.getCurrentUrl(),URL);
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

    /**
     *  6. Click on Service subcategory in the left section and check that drop down contains options
     */
    @Test(priority = 6)
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

    /**
     *  7. Open through the header menu Service -> Different Elements Page
     */
    @Test(priority = 7)
    public void openNewServicePage() {
        diffPage = homePage.header.goTo(
                "Service",
                "Different elements",
                driver,
                DifferentElementsPage::new);
        Assert.assertEquals(driver.getCurrentUrl(),diffURL);
    }

    /**
     *  8. Check interface on Different elements page, it contains all needed elements
     */
    @Test(priority = 8, timeOut = 10000)
    public void checkMain() {
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(diffPage.main.getRadio().size(),4);
        softAssertion.assertEquals(diffPage.main.getCheckBoxes().size(),4);
        softAssertion.assertEquals(diffPage.main.getButtons().size(),2);
        softAssertion.assertEquals(diffPage.main.getDropDown().size(),1);
        softAssertion.assertAll();
    }

    /**
     *  9. Assert that there is Right Section
     */
    @Test(priority = 9, timeOut = 500)
    public void assertRightSection() {
        diffPage.rightSection.checkSelf();
    }

    /**
     *  10. Assert that there is Left Section
     */
    @Test(priority = 10, timeOut = 1000)
    public void assertLeftSection() {
        diffPage.leftSection.checkSelf();
    }

    /**
     *  11. Select checkboxes
     */
    @Test(priority = 11)
    public void selectCheckboxes() {
        SoftAssert softAssertion = new SoftAssert();
        diffPage.main.getCheckBoxes("Water").select();
        diffPage.main.getCheckBoxes("Wind").select();
        softAssertion.assertTrue(diffPage.main.getCheckBoxes("Water").isSelected());
        softAssertion.assertTrue(diffPage.main.getCheckBoxes("Wind").isSelected());
        softAssertion.assertAll();
    }

    /**
     *  12. Assert that for each checkbox there is an individual log row and
     *  value is corresponded to the status of checkbox.
     */
    @Test(priority = 12)
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

    /**
     *  13. Select radio
     */
    @Test(priority = 13)
    public void selectRadio() {
        diffPage.main.getRadio("Selen").click();
        Assert.assertTrue(diffPage.main.getRadio("Selen").isSelected());
    }

    /**
     *  14. Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton.
     */
    @Test(priority = 14)
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

    /**
     *  15. Select in dropdown
     */
    @Test(priority = 15)
    public void selectDropDown() {
        diffPage.main.getDropDown().get(0).selectByVisibleText("Yellow");
        Assert.assertTrue(diffPage.rightSection.getLog().get(0).getText().contains("Yellow"));
    }

    /**
     *  16. Assert that for dropdown there is a log row and value is corresponded to the selected value.
     */
    @Test(priority = 16)
    public void assertDropDown() {
        SoftAssert softAssertion = new SoftAssert();
        for (WebElement option : diffPage.main.getDropDown().get(0).getOptions()) {
            diffPage.main.getDropDown().get(0).selectByVisibleText(option.getText());
            softAssertion.assertTrue(diffPage.rightSection.getLog().get(0).getText().contains(option.getText()));
        }
        softAssertion.assertAll();
    }

    /**
     *  17. Unselect and assert checkboxes
     */
    @Test(priority = 17)
    public void unselectCheckboxes() {
        diffPage.main.getCheckBoxes("Water").click();
        diffPage.main.getCheckBoxes("Wind").click();
        diffPage.main.getCheckBoxes("Water").click();
        diffPage.main.getCheckBoxes("Wind").click();
        Assert.assertFalse(diffPage.main.getCheckBoxes("Water").isSelected());
        Assert.assertFalse(diffPage.main.getCheckBoxes("Wind").isSelected());
    }

    /**
     *  18. Assert that for each checkbox there is an individual
     *  log row and value is corresponded to the status of checkbox.
     */
    @Test(priority = 18)
    public void assertCheckbox2() {
        assertCheckbox();
    }

    @AfterTest
    public void assertQuit() {
        homePage.quit();
    }
}