package hw2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

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

    @FindBy(css = "div.benefit div.benefit-icon")
    private List<WebElement> images;

    @FindBy(css = "div.benefit span.benefit-txt")
    private List<WebElement> textOfImages;

    @FindBy(css = ".main-title")
    private WebElement mainTitle;

    @FindBy(css = ".main-txt")
    private WebElement mainText;

    @FindBy(id = "second_frame")
    private WebElement centralFrame;

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
     * метод для чтения заголовка страницы
     */
    public String getTitle() {
        return driver.getTitle();
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
     * метод для нахождения изображений
     */
    public List<WebElement> getImages() {
        return images;
    }

    /**
     * метод для нахождения текста изображений
     */
    public List<WebElement> getTextOfImages() {
        return textOfImages;
    }

    /**
     * метод для нахождения главного заголовка
     */
    public WebElement getMainTitle() {
        return mainTitle;
    }

    /**
     * метод для нахождения главного подзаголовка
     */
    public WebElement getMainText() {
        return mainText;
    }

    /**
     * метод для нахождения центрального(2-го по середине) фрейма в виде WebElement
     */
    public WebElement getCentralFrameWebElement() {
        return centralFrame;
    }

    class CentralFrame {

    }

    /**
     * метод для нахождения центрального(2-го по середине) фрейма
     */
    public CentralFrame getCentralFrame() {
        driver.switchTo().frame(getCentralFrameWebElement());
        return new CentralFrame();
    }
}
