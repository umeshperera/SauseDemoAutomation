package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderCompletePage {
    private final WebDriver driver;

    //Elements
    private final By title = By.xpath("//*[@data-test='complete-header']");
    private final By description = By.xpath("//*[@data-test='complete-text']");
    private final By backHomeButton = By.xpath("//button[@data-test='back-to-products']");

    public OrderCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public String  getTitleText() {
        return driver.findElement(title).getText();
    }

    public String getDescriptionText(){
        return driver.findElement(description).getText();
    }

    public void clickBackButton(){
        driver.findElement(backHomeButton).click();
    }
}
