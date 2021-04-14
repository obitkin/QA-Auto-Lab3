package hw3.inner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@FindBy(xpath = "//section[@class=\"uui-info-panel-horizontal\"]/..")
public class RightSection extends HtmlElement {

    @FindBy(css = ".info-panel-body-log li")
    private List<WebElement> log;

    @FindBy(css = ".info-panel-body-result li")
    private List<WebElement> result;

    public List<WebElement> getLog() {
        return log;
    }

    public List<WebElement> getResult() {
        return result;
    }

    public void checkSelf() {
        self.isEnabled();
    }

    @FindBy(xpath = ".")
    private WebElement self;
}