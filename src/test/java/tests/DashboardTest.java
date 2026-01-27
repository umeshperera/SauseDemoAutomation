package tests;

import base.BaseTest;
import config.ConfigReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest {
    //Pages
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CartPage cartPage;

    @BeforeEach
    public void setUp() {
        //Pages
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        cartPage = new CartPage(driver);

        //Entering user data
        loginPage.login(ConfigReader.get("username"),ConfigReader.get("password"));

        //Verification
        dashboardPage.verifyVisibilityOfHeader();
    }

    @Test
    @DisplayName("Verify user able to view all items")
    public void checkVisibilityOfItems() {
        dashboardPage.verifyVisibilityOfGivenNumberOfItems(6);
    }

    @Test
    @DisplayName("Verify user able to view the cart icon")
    public void checkVisibilityOfCartIcon() {
        dashboardPage.verifyVisibilityOfShoppingCartIcon();
    }

    @Test
    @DisplayName("Verify user able to click on cart icon")
    public void checkCartIconClickable() {
        dashboardPage.clickShoppingCartIcon();
        //Verification
        cartPage.verifyUserIsOnCartPage();
        cartPage.verifyHeaderGetDisplayed();
    }
}