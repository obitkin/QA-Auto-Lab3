package hw3.inner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Radio;

import java.util.List;
import java.util.stream.Collectors;

@FindBy(className = "main-content-hg")
public class Main extends HtmlElement {

    @FindBy(className = "label-checkbox")
    private List<WebElement> checkBoxes;

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

    public CheckBox getCheckBoxes(String label) {
        return new CheckBox(
                checkBoxes
                .stream()
                .filter(x -> x.getText().contains(label))
                .findFirst()
                .get()
                .findElement(By.tagName("input")));
    }

    public Radio getRadio(String label) {
        return new Radio(
                radio
                .stream()
                .filter(x -> x.getText().contains(label))
                .findFirst()
                .get()
                .findElement(By.tagName("input")));
    }

    public static WebElement getInput(WebElement label) {
        return label.findElement(By.tagName("input"));
    }

    public void clear() {
        getRadio()
                .stream()
                .filter(webElement -> getInput(webElement).isSelected())
                .forEach(WebElement::click);
        getCheckBoxes()
                .stream()
                .filter(webElement -> getInput(webElement).isSelected())
                .forEach(WebElement::click);
    }

    public void checkSelf() {
        self.isEnabled();
    }

    @FindBy(xpath = ".")
    private WebElement self;
}
