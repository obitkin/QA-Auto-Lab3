package hw2.pages;

import hw2.inner.Header;
import hw2.inner.HomeFrame;
import hw2.inner.LeftSection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

public class HomePage {

    public WebDriver driver;

    public Header header;
    public LeftSection leftSection;

    HomeFrame homeCentralFrame;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)),this);
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

    @FindBy(tagName = "footer")
    private WebElement footer;

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
     * метод для получения подзаголовка
     */
    public WebElement getSubHeader() {
        return subHeader;
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
}
