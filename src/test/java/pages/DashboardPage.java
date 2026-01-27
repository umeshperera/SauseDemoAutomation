package pages;

import config.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class DashboardPage {
    private final WebDriver driver;

    //Elements
    private final By headerLogo = By.className("app_logo");
    //Item Card
    private final By inventoryContainer = By.className("inventory_item");
    private final By itemImage = By.xpath("//img[@class='inventory_item_img']");
    private final By itemName = By.xpath("//*[@data-test='inventory-item-name']");
    private final By itemDescription = By.xpath("//*[@data-test='inventory-item-desc']");
    private final By itemPrice = By.xpath("//*[@data-test='inventory-item-price']");
    private final By addToCartButtons = By.xpath("//button[starts-with(@data-test,'add-to-cart')]");
    //Shopping Cart
    private final By shoppingCartIcon = By.xpath("//a[@data-test='shopping-cart-link']");
    //Sidebar
    private final By sidebarIcon = By.id("react-burger-menu-btn");
    private final By logout = By.xpath("//a[@data-test='logout-sidebar-link']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyVisibilityOfHeader(){
        //Wait until headerLogo get displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("defaultTimeout")));
        Assertions.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(headerLogo)).isDisplayed());
    }

    public void verifyVisibilityOfShoppingCartIcon(){
        //Wait until cart icon get displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("defaultTimeout")));
        Assertions.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartIcon)).isDisplayed());
    }

    public void clickShoppingCartIcon(){
        driver.findElement(shoppingCartIcon).click();
    }

    public void openSideBar(){
        driver.findElement(sidebarIcon).click();
    }

    public void clickLogout(){
        //Wait until cart icon get displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("defaultTimeout")));
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }

    public void verifyVisibilityOfGivenNumberOfItems(int numberOfItems) {
        Assertions.assertEquals(numberOfItems, driver.findElements(itemImage).size());
        Assertions.assertEquals(numberOfItems, driver.findElements(itemName).size());
        Assertions.assertEquals(numberOfItems, driver.findElements(itemDescription).size());
        Assertions.assertEquals(numberOfItems, driver.findElements(itemPrice).size());
        Assertions.assertEquals(numberOfItems, driver.findElements(addToCartButtons).size());
    }

    public void clickAddToCartByProductName(String productName) {
        List<WebElement> items = driver.findElements(inventoryContainer);

        for (WebElement item : items) {
            //Retrieve current item name
            String itemName = item.findElement(By.className("inventory_item_name")).getText();

            //Click on add to cart if item name match with given product name
            if(itemName.equals(productName)){
                item.findElement(By.xpath(".//button[starts-with(@data-test,'add-to-cart')]")).click();
                //Exiting loop
                return;
            }
        }
        //Throw exception if given product not found
        throw new RuntimeException("Product not found : " + productName);
    }
}
