package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Objects;

public class CartPage {
    private final WebDriver driver;

    //Elements
    private final By header = By.xpath("//*[@data-test='title']");
    private final By cartItemName =  By.xpath("//*[@data-test='inventory-item-name']");
    private final By cartItemPrice = By.xpath("//*[@data-test='inventory-item-price']");
    private final By cartItemDescription = By.xpath("//*[@data-test='inventory-item-desc']");
    private final By cartItemQuantity = By.xpath("//*[@data-test='item-quantity']");
    private final By cartItemRemoveButton = By.xpath("//button[starts-with(@data-test,'remove-')]");
    private final By checkoutButton = By.xpath("//button[@data-test='checkout']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyUserIsOnCartPage() {
        Assertions.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).endsWith("/cart.html"));
    }

    public void verifyHeaderGetDisplayed(){
        Assertions.assertTrue(driver.findElement(header).isDisplayed());
    }

    public boolean verifyCartItemWithGivenNameIdDisplayed(String itemName) {
        return driver.findElements(cartItemName)
                .stream()
                .anyMatch(item -> item.getText().equals(itemName));
    }

    public int getCartItemsCount() {
        return driver.findElements(cartItemName).size();
    }

    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }
}