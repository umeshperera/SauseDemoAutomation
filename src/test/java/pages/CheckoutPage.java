package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;

    //Elements
    private final By title = By.xpath("//*[@data-test='title']");
    private final By itemTitles = By.xpath("//*[@data-test='inventory-item-name']");
    private final By itemPrices = By.xpath("//*[@data-test='inventory-item-price']");
    private final By totalAmountLabel = By.xpath("//*[@data-test='total-label']");
    private final By finishButton = By.xpath("//button[@data-test='finish']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyTitleIsDisplayed(){
        Assertions.assertTrue(driver.findElement(title).isDisplayed());
    }

    public boolean verifyItemWithGivenNameIdDisplayed(String itemName){
        return driver.findElements(itemTitles)
                .stream()
                .anyMatch(item -> item.getText().equals(itemName));
    }

    public boolean verifyItemPriceWithGivenPriceDisplayed(String itemPrice){
        return driver.findElements(itemPrices)
                .stream()
                .anyMatch(item -> item.getText().equals(itemPrice));
    }

    public boolean verifyOrderTotalWithGivenTotalDisplayed(String totalAmount){
        return driver.findElement(totalAmountLabel).getText().contains(totalAmount);
    }

    public void clickFinishButton(){
        driver.findElement(finishButton).click();
    }
}
