package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BillingInfoPage {
    private final WebDriver driver;

    //Elements
    //Form
    private final By firstNameField = By.xpath("//input[@data-test='firstName']");
    private final By lastNameField = By.xpath("//input[@data-test='lastName']");
    private final By postalCodeField = By.xpath("//input[@data-test='postalCode']");
    //Other Elements
    private final By continueButton = By.xpath("//input[@data-test='continue']");
    private final By formValidationMessage = By.xpath("//h3[@data-test = 'error']");

    public BillingInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode){
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public String getValidationMessage() {
        return driver.findElement(formValidationMessage).getText();
    }
}
