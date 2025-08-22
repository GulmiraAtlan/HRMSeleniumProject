package com.end2endTests;

import com.base.BaseClass;
import com.data.TestConstants;
import com.data.TestConstantsLoader;
import org.testng.annotations.*;
import com.pages.HomePage;
import com.pages.LoginPage;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseClass {
        private LoginPage loginPage;
        private HomePage homePage;
        @BeforeMethod
        public void setUpTest() {
            // Initialize WebDriver from BaseClass
            setUp("chrome");

            // Initialize the LoginPage object
            loginPage = new LoginPage(getDriver());
            homePage = new HomePage(getDriver());
        }

        @Test
        public void testSuccessfulLogin() {
            String loginUrl = TestConstantsLoader.get("LOGIN_URL");
            System.out.println("Login URL: " + loginUrl);
            // Open the login page
            loginPage.openLoginPage(TestConstants.LOGIN_URL);

            // Perform login
            loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);

            //confirmation alert
            //Alert alert = driver.switchTo().alert();
            //alert.accept();

            // Add assertion to verify successful login (e.g., check page title)
            String dashBoardTitle = homePage.getDashboardTitleText();
            System.out.println("Page title after login: " + dashBoardTitle);
            assertEquals(dashBoardTitle, "Dashboard");
        }

        @Test
        public void testInvalidLogin() {
            // Open the login page
            loginPage.openLoginPage(TestConstants.LOGIN_URL);

            // Perform login with invalid credentials
            loginPage.login(TestConstants.USERNAME, TestConstants.INVALIDPASSWORD);

            // Verify the error message
            String errorMessage = loginPage.getErrorMessage();
            System.out.println("Error message: " + errorMessage);
            assertEquals(errorMessage, "Invalid credentials");
        }

        @AfterMethod
        public void tearDownTest() {
            // Close the browser
            tearDown();
        }
}


