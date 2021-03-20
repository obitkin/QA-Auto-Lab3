package hw2.inner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;
import java.util.stream.Collectors;

@FindBy(className = "main-content-hg")
public class Main extends HtmlElement {

    //@FindBy(css = "input[type=\"checkbox\"]")
    @FindBy(className = "label-checkbox")
    private List<WebElement> checkBoxes;

    //@FindBy(css = "input[type=\"radio\"]")
    @FindBy(className = "label-radio")
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

    public List<Select> getDropDown() {
        return dropDown.stream().map(Select::new).collect(Collectors.toList());
    }

    public List<WebElement> getButtons() {
        return buttons;
    }

    public WebElement getCheckBoxes(String label) {
        return checkBoxes
                .stream()
                .filter(x -> x.getText().contains(label))
                .findFirst()
                .get()
                .findElement(By.tagName("input"));
    }

    public WebElement getRadio(String label) {
        return radio
                .stream()
                .filter(x -> x.getText().contains(label))
                .findFirst()
                .get()
                .findElement(By.tagName("input"));
    }

    public static WebElement getInputFromLabel(WebElement label) {
        return label.findElement(By.tagName("input"));
    }

    public void clear() {
        getRadio()
                .stream()
                .filter(webElement -> getInputFromLabel(webElement).isSelected())
                .forEach(WebElement::click);
        getCheckBoxes()
                .stream()
                .filter(webElement -> getInputFromLabel(webElement).isSelected())
                .forEach(WebElement::click);
    }
}
