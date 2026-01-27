package tests;

import base.BaseTest;
import config.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

public class CheckoutTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CartPage cartPage;
    BillingInfoPage billingInfoPage;
    CheckoutPage checkoutPage;
    OrderCompletePage orderCompletePage;

    @BeforeEach
    public void setUp() {
        //Pages
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        cartPage = new CartPage(driver);
        billingInfoPage = new BillingInfoPage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderCompletePage = new OrderCompletePage(driver);

        //Entering user data
        loginPage.login(ConfigReader.get("username"),ConfigReader.get("password"));

        //Verification
        dashboardPage.verifyVisibilityOfHeader();
    }

    @Test
    @DisplayName("Verify user able to add specified products to the cart")
    public void addProductToCart() {
        //Add Products to cart
        dashboardPage.clickAddToCartByProductName("Sauce Labs Bike Light");
        dashboardPage.clickAddToCartByProductName("Sauce Labs Bolt T-Shirt");

        //Navigating to cart
        dashboardPage.clickShoppingCartIcon();
        cartPage.verifyUserIsOnCartPage();

        //Verify items in cart
        Assertions.assertAll(
                () -> Assertions.assertTrue(cartPage.verifyCartItemWithGivenNameIdDisplayed("Sauce Labs Bike Light")),
                () -> Assertions.assertTrue(cartPage.verifyCartItemWithGivenNameIdDisplayed("Sauce Labs Bolt T-Shirt"))
        );
        Assertions.assertEquals(2,cartPage.getCartItemsCount());
    }

    @Test
    @DisplayName("Verify user not allow to proceed without providing first name in billing information")
    public void checkoutWithoutFirstName() {
        //Add Products to cart
        dashboardPage.clickAddToCartByProductName("Sauce Labs Bike Light");
        dashboardPage.clickAddToCartByProductName("Sauce Labs Bolt T-Shirt");

        //Navigating to cart
        dashboardPage.clickShoppingCartIcon();
        cartPage.verifyUserIsOnCartPage();

        //Verify items in cart
        Assertions.assertAll(
                () -> Assertions.assertTrue(cartPage.verifyCartItemWithGivenNameIdDisplayed("Sauce Labs Bike Light")),
                () -> Assertions.assertTrue(cartPage.verifyCartItemWithGivenNameIdDisplayed("Sauce Labs Bolt T-Shirt"))
        );

        //Navigating to billing form
        cartPage.clickCheckoutButton();

        //Check validations in billing form
        //Filling form
        billingInfoPage.enterLastName(faker.name().lastName());
        billingInfoPage.enterPostalCode(faker.address().zipCode());
        billingInfoPage.clickContinueButton();
        Assertions.assertEquals("Error: First Name is required",billingInfoPage.getValidationMessage());
    }

    @Test
    @DisplayName("Verify user not allow to proceed without providing last name in billing information")
    public void checkoutWithoutLastName() {
        //Add Products to cart
        dashboardPage.clickAddToCartByProductName("Sauce Labs Bike Light");
        dashboardPage.clickAddToCartByProductName("Sauce Labs Bolt T-Shirt");

        //Navigating to cart
        dashboardPage.clickShoppingCartIcon();
        cartPage.verifyUserIsOnCartPage();

        //Verify items in cart
        Assertions.assertAll(
                () -> Assertions.assertTrue(cartPage.verifyCartItemWithGivenNameIdDisplayed("Sauce Labs Bike Light")),
                () -> Assertions.assertTrue(cartPage.verifyCartItemWithGivenNameIdDisplayed("Sauce Labs Bolt T-Shirt"))
        );

        //Navigating to billing form
        cartPage.clickCheckoutButton();

        //Check validations in billing form
        //Filling form
        billingInfoPage.enterFirstName(faker.name().firstName());
        billingInfoPage.enterPostalCode(faker.address().zipCode());
        billingInfoPage.clickContinueButton();
        Assertions.assertEquals("Error: Last Name is required",billingInfoPage.getValidationMessage());
    }

    @Test
    @DisplayName("Verify user not allow to proceed without providing postal code in billing information")
    public void checkoutWithoutPostalCode() {
        //Add Products to cart
        dashboardPage.clickAddToCartByProductName("Sauce Labs Bike Light");
        dashboardPage.clickAddToCartByProductName("Sauce Labs Bolt T-Shirt");

        //Navigating to cart
        dashboardPage.clickShoppingCartIcon();
        cartPage.verifyUserIsOnCartPage();

        //Verify items in cart
        Assertions.assertAll(
                () -> Assertions.assertTrue(cartPage.verifyCartItemWithGivenNameIdDisplayed("Sauce Labs Bike Light")),
                () -> Assertions.assertTrue(cartPage.verifyCartItemWithGivenNameIdDisplayed("Sauce Labs Bolt T-Shirt"))
        );

        //Navigating to billing form
        cartPage.clickCheckoutButton();

        //Check validations in billing form
        //Filling form
        billingInfoPage.enterFirstName(faker.name().firstName());
        billingInfoPage.enterLastName(faker.name().lastName());
        billingInfoPage.clickContinueButton();
        Assertions.assertEquals("Error: Postal Code is required",billingInfoPage.getValidationMessage());
    }

    @Test
    @DisplayName("Verify user allow to proceed with checkout process")
    public void checkoutProcess() {
        //Add Products to cart
        dashboardPage.clickAddToCartByProductName("Sauce Labs Bike Light");
        dashboardPage.clickAddToCartByProductName("Sauce Labs Bolt T-Shirt");

        //Navigating to cart
        dashboardPage.clickShoppingCartIcon();
        cartPage.verifyUserIsOnCartPage();

        //Verify items in cart
        Assertions.assertAll(
                () -> Assertions.assertTrue(cartPage.verifyCartItemWithGivenNameIdDisplayed("Sauce Labs Bike Light")),
                () -> Assertions.assertTrue(cartPage.verifyCartItemWithGivenNameIdDisplayed("Sauce Labs Bolt T-Shirt"))
        );

        //Navigating to billing form
        cartPage.clickCheckoutButton();

        //Filling form
        billingInfoPage.enterFirstName(faker.name().firstName());
        billingInfoPage.enterLastName(faker.name().lastName());
        billingInfoPage.enterPostalCode(faker.address().zipCode());
        billingInfoPage.clickContinueButton();

        //Checkout
        checkoutPage.verifyTitleIsDisplayed();

        //Verify items in checkout page
        //Title
        Assertions.assertAll(
                () -> Assertions.assertTrue(checkoutPage.verifyItemWithGivenNameIdDisplayed("Sauce Labs Bike Light")),
                () -> Assertions.assertTrue(checkoutPage.verifyItemWithGivenNameIdDisplayed("Sauce Labs Bolt T-Shirt"))
        );
        //Price
        Assertions.assertAll(
                () -> Assertions.assertTrue(checkoutPage.verifyItemPriceWithGivenPriceDisplayed("$9.99")),
                () -> Assertions.assertTrue(checkoutPage.verifyItemPriceWithGivenPriceDisplayed("$15.99"))
        );
        //Total
        Assertions.assertTrue(checkoutPage.verifyOrderTotalWithGivenTotalDisplayed("$28.06"));

        //Finish Order
        checkoutPage.clickFinishButton();

        //Final Screen
        Assertions.assertEquals("Thank you for your order!",orderCompletePage.getTitleText());
        Assertions.assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!",orderCompletePage.getDescriptionText());
        orderCompletePage.clickBackButton();

        //Verify back on dashboard
        dashboardPage.verifyVisibilityOfHeader();
    }
}
