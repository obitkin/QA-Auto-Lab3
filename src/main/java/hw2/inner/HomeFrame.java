package hw2.inner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeFrame {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;

    public HomeFrame(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "div.epam-logo")
    private WebElement logo;

    /**
     * метод для нахождения иконки EPAM
     */
    public WebElement getLogo() {
        return logo;
    }
}