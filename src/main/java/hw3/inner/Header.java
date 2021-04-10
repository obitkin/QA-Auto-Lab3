package hw3.inner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;
import java.util.function.Function;

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

    @FindBy(css = "ul.nav>li")
    private List<WebElement> navigationList;

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
     * метод для входа в аккаунт
     */
    public void signInWithoutClear(String login, String passwd) {
        loginPasswdMenu.click();
        loginField.sendKeys(login);
        passwdField.sendKeys(passwd);
        loginBtn.click();
    }

    /**
     * метод для получения 4-х навигационных элементов в хедере
     */
    public List<WebElement> getNavigationList() {
        return navigationList;
    }

    /**
     * метод для получения 1-го из 4-х навигационных элементов в хедере по названию
     */
    public WebElement getNavigationElement(String navigationName) {
        return navigation.findElement(By.xpath("//li/a[contains(text(),\""+navigationName+"\")]/.."));
    }

    /**
     * метод для получения всех элементов из выпадающего списка элементов навигационного элемента
     */
    public List<WebElement> takeElementsFromList(WebElement navigationElement) {
        return navigationElement.findElements(By.tagName("li"));
    }

    /**
     * метод для извлечения элементов из выпадающего списка элементов навигационного элемента
     */
    public WebElement getElementFromList(WebElement navigationElement, String refNameOfElement) {
        // return navigationElement.findElement(By.linkText(refNameOfElement));
        // not working, because ref has name: DIFFERENT ELEMENTS, not Different Elements like in html
        //return navigationElement.findElement(By.linkText(refNameOfElement.toUpperCase()));
        //working, but horrible 1
        return navigationElement.findElement(By.xpath("//li/a[contains(text(),\""+refNameOfElement+"\")]/.."));
        //working, but horrible 2
    }

    /**
     * метод для перехода на страницу по названию навигационного элемента и элемента из выпадающего списка(если есть)
     */
    public <Page> Page goTo(String refNameOfNavigationElement,
                            String refNameOfElement,
                            WebDriver driver,
                            Function<WebDriver, Page> pageCreator) {
        WebElement navigationElement = getNavigationElement(refNameOfNavigationElement);
        navigationElement.click();
        if (refNameOfElement != null) {
            getElementFromList(navigationElement, refNameOfElement).click();
        }
        return pageCreator.apply(driver);
    }

    public void checkSelf() {
        self.isEnabled();
    }

    @FindBy(xpath = ".")
    private WebElement self;
}
