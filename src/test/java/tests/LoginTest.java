package tests;

import base.BaseTest;
import config.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;

import java.util.Objects;

public class LoginTest extends BaseTest {
    //Pages
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeEach
    public void setUp(){
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Verify user not able to sign in with invalid username")
    public void loginWithInvalidUsername(){
        //Entering user data
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword(ConfigReader.get("password"));
        loginPage.clickLoginButton();

        //Verification
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.getErrorMessage());
    }

    @Test
    @DisplayName("Verify user not able to sign in with invalid password")
    public void loginWithInvalidPassword(){
        //Entering user data
        loginPage.enterUsername(ConfigReader.get("username"));
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        //Verification
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.getErrorMessage());
    }

    @Test
    @DisplayName("Verify user not able to sign in with both invalid username and password")
    public void loginWithInvalidCredentials(){
        //Entering user data
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        //Verification
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.getErrorMessage());
    }

    @Test
    @DisplayName("Verify user able to sign in with valid credentials")
    public void loginWithValidCredentials(){
        //Pages (Since dashboard used only in this test)
        dashboardPage = new DashboardPage(driver);

        //Entering user data
        loginPage.login(ConfigReader.get("username"),ConfigReader.get("password"));

        //Verification
        dashboardPage.verifyVisibilityOfHeader();
        Assertions.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).endsWith("/inventorys.html"));
    }
}