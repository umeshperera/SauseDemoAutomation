package tests;

import base.BaseTest;
import config.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LogoutTest extends BaseTest {
    @Test
    @DisplayName("Verify user allow to log out from the page")
    public void logoutTest(){
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        //Entering user data
        loginPage.login(ConfigReader.get("username"),ConfigReader.get("password"));
        //Verification
        dashboardPage.verifyVisibilityOfHeader();
        //LogOut
        dashboardPage.openSideBar();
        dashboardPage.clickLogout();
        Assertions.assertEquals(ConfigReader.get("baseURL"),driver.getCurrentUrl());
    }
}
