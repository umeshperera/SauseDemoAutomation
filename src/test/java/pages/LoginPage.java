package pages;

import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    //Elements
    private final By usernameField = By.xpath("//input[@data-test='username']");
    private final By passwordField = By.xpath("//input[@data-test='password']");
    private final By loginButton = By.xpath("//input[@data-test='login-button']");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        //Wait until error message displayed on UI and return
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("defaultTimeout")));
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(errorMessage))
                .getText();
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
