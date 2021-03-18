package hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

public class HomePage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;

    Header header;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)),this);
        //PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
        this.driver = driver;
    }

    @FindBy(css = "div.benefit-icon")
    private List<WebElement> images;

    @FindBy(css = "span.benefit-txt")
    private List<WebElement> textOfImages;

    @FindBy(className = "main-title")
    private WebElement mainTitle;

    @FindBy(className = "main-txt")
    private WebElement mainText;

    @FindBy(id = "second_frame")
    private WebElement centralFrame;

    @FindBy(css = ".text-center a")
    private WebElement subHeader;

    @FindBy(className = "sidebar-menu")
    private WebElement sideBarMenu;

    @FindBy(tagName = "footer")
    private WebElement footer;

    @FindBy(css = "li.menu-title[index=\"3\"]")
    private WebElement serviceLeft;


    /**
     * метод для входа в аккаунт
     */
    public void signInWithoutClear(String login, String passwd) {
        header.signInWithoutClear(login, passwd);
    }

    /**
     * метод для чтения заголовка страницы
     */
    public String getTitle() {
        return driver.getTitle();
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
    public String getMainTitle() {
        return mainTitle.getText();
    }

    /**
     * метод для нахождения главного текста
     */
    public String getMainText() {
        return mainText.getText();
    }

    /**
     * метод для нахождения центрального(2-го по середине) фрейма в виде WebElement
     */
    public WebElement getCentralFrameWebElement() {
        return centralFrame;
    }

    HomeFrame homeCentralFrame;
    /**
     * метод для нахождения центрального(2-го по середине) фрейма
     */
    public HomeFrame getCentralFrame() {
        driver.switchTo().frame(getCentralFrameWebElement());
        return (homeCentralFrame == null)
                ? homeCentralFrame = new HomeFrame(driver)
                : homeCentralFrame;
    }

    /**
     * метод для выхода из фрейма
     */
    public void getBackFromFrameToDefault() {
        driver.switchTo().defaultContent();
    }

    /**
     * метод для выхода из фрейма
     */
    public WebElement getSubHeader() {
        return subHeader;
    }

    /**
     * метод для нахождения бокового меню
     */
    public WebElement getSideBarMenu() {
        return sideBarMenu;
    }

    /**
     * метод для нахождения footer'а
     */
    public WebElement getFooter() {
        return footer;
    }

    /**
     * метод для закрытия всех страниц и выхода
     */
    public void quit() {
        driver.quit();
    }

    /**
     * метод для получения элемента Service из левого блока
     */
    public WebElement getServiceLeft() {
        return serviceLeft;
    }

    /**
     * метод для получения выпадающего списка Service из левого блока
     */
    public List<WebElement> getServiceListLeft() {
        return getServiceLeft().findElements(By.className("li"));
    }

    /**
     * метод для чтения имени пользователя
     */
    public String getName() {
        return header.getName();
    }

    /**
     * метод для нахождения 4-х навигационных элементов в хедере
     */
    public List<WebElement> getHeaderSectionNavigation() {
        return header.getHeaderSectionNavigation();
    }

    /**
     * метод для получения элемента Service из хедера
     */
    public WebElement getServiceTop() {
        return header.getServiceTop();
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
        header.goToServiceElement(serviceListElement);
    }
}
