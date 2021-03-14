package hw2.ex1;

import hw2.HomePage;
import org.testng.Assert;

public class LoginTest {

    String login = "Roman";
    String passwd = "Jdi1234";
    String name = "ROMAN IOVLEV";
    String title = "Home Page";

    HomePage homePage;

    public LoginTest(HomePage homePage) {
        this.homePage = homePage;
    }

    public void assertBrowserTitleTest() {
        Assert.assertEquals(homePage.getTitle(),title);
    }

    public void performLoginTest() {
        homePage.signInWithoutClear(login,passwd);
    }

    public void assertUserNameTest() {
        Assert.assertEquals(homePage.getName(),name);
    }
}
