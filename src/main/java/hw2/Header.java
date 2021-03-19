package hw2;

import jdk.jfr.Name;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@FindBy(tagName = "header")
public class Header extends HtmlElement {

    @FindBy(id = "name")
    private WebElement loginField;

    @FindBy(id = "password")
    private WebElement passwdField;

    @FindBy(css = "ul.navbar-right")
    private WebElement loginPasswdMenu;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    @FindBy(id = "user-name")
    private WebElement name;

    @FindBy(css = "ul.nav")
    private WebElement navigation;

    /**
     * метод для ввода логина
     */
    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    /**
     * метод для ввода пароля
     */
    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd);
    }

    /**
     * метод для осуществления нажатия кнопки входа в аккаунт
     */
    public void clickLoginBtn() {
        loginBtn.click();
    }

    /**
     * метод для осуществления открытия меню ввода логина и пароля
     */
    public void openMenu() {
        loginPasswdMenu.click();
    }

    /**
     * метод для чтения имени пользователя
     */
    public String getName() {
        return name.getAttribute("innerText");
    }

    /**
     * метод для получения 4-х навигационных элементов в хедере
     */
    public WebElement getNavigation() {
        return navigation;
    }

    /**
     * метод для входа в аккаунт
     */
    public void signInWithoutClear(String login, String passwd) {
        loginPasswdMenu.click();
        loginField.sendKeys(login);
        passwdField.sendKeys(passwd);
        loginBtn.click();
    }

    /**
     * метод для получения элемента из навигации хедера
     *
     * подходит для извлечения навигационных элементов Home, Service, Contact Form, Metal & Colors
     * а также для извлечения элементов из выпадающего списка элементов Service
     */
    public WebElement getElementFromList(WebElement List, String refNameOfElement) {
        return List.findElement(
                By.xpath("//a[contains(text(),\"" +
                        refNameOfElement +
                        "\")]/.."
                )
        );
    }
}
