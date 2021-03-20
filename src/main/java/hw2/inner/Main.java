package hw2.inner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@FindBy(className = "main-content-hg")
public class Main extends HtmlElement {

    @FindBy(css = "input[type=\"checkbox\"]")
    private List<WebElement> checkBoxes;

    @FindBy(css = "input[type=\"radio\"]")
    private List<WebElement> radio;

    @FindBy(tagName = "select")
    private List<WebElement> dropDown;

    @FindBy(xpath = "(.//button)|(.//input[@type=\"button\"])")
    private List<WebElement> buttons;

    public List<WebElement> getCheckBoxes() {
        return checkBoxes;
    }

    public List<WebElement> getRadio() {
        return radio;
    }

    public List<WebElement> getDropDown() {
        return dropDown;
    }

    public List<WebElement> getButtons() {
        return buttons;
    }
}
