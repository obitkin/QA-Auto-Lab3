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

    @FindBy(id = "user-icon")
    private WebElement loginPasswdMenu;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    @FindBy(id = "user-name")
    private WebElement name;

    @FindBy(css = "ul.nav>li>a")
    private List<WebElement> navigationTop;

    @FindBy(css = "ul.nav .dropdown")
    private WebElement serviceTop;

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
     * метод для нахождения 4-х навигационных элементов в хедере
     */
    public List<WebElement> getHeaderSectionNavigation() {
        return navigationTop;
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
     * метод для получения элемента Service из хедера
     */
    public WebElement getServiceTop() {
        return serviceTop;
    }

    /**
     * метод для получения выпадающего списка Service из хедера
     */
    public List<WebElement> getServiceListTop() {
        return getServiceTop().findElements(By.cssSelector(".dropdown-menu li"));
    }

    /**
     * метод для перехода на  страницу serviceListElement в Service в хедере
     */
    public void goToServiceElement(String serviceListElement) {
        getServiceTop().click();
        //getServiceTop().findElement(By.cssSelector("a[href=\"different-elements.html\"]")).click();
        getServiceTop().findElement(By.linkText(serviceListElement)).click();
    }
}
