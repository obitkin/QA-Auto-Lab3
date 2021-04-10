package hw3.pages;

import hw3.inner.LeftSection;
import hw3.inner.Main;
import hw3.inner.RightSection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class DifferentElementsPage {

    public WebDriver driver;

    public Main main;
    public LeftSection leftSection;
    public RightSection rightSection;

    public DifferentElementsPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)),this);
        this.driver = driver;
    }

}
