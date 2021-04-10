package hw3.inner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@FindBy(css = "ul.sidebar-menu")
public class LeftSection extends HtmlElement {

    @FindBy(css = "li")
    private List<WebElement> navigationList;

    /**
     * метод для получения 1-го из 5-х навигационных элементов в левой секции по названию
     */
    public WebElement getNavigationElement(String navigationName) {
        return navigationList
                .stream()
                .filter(webElement -> webElement.getText().contains(navigationName))
                .findFirst()
                .get();
    }

    /**
     * метод для получения всех элементов из выпадающего списка элементов навигационного элемента
     */
    public List<WebElement> takeElementsFromList(WebElement navigationElement) {
        return navigationElement.findElements(By.tagName("li"));
    }

    public void checkSelf() {
        self.isEnabled();
    }

    @FindBy(xpath = ".")
    private WebElement self;
}