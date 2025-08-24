package com.end2endTests;

import com.data.TestConstants;
import com.base.BaseClass;
import com.pages.LoginPage;
import com.pages.AdminPage;
import com.pages.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddNewUserTest extends BaseClass{
    private static final Logger logger = LogManager.getLogger(AddNewUserTest.class);
    private LoginPage loginPage;
    private AdminPage adminPage;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        logger.info("Setting up the test environment.");
        // Initialize WebDriver from BaseClass
        setUp("chrome");

        // Initialize the LoginPage object
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        adminPage = new AdminPage(getDriver());
    }

    @Test
    public void testAddNewUser() throws InterruptedException {

        loginPage.openLoginPage(TestConstants.LOGIN_URL);
        // Log in to the application
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);


        // Navigate to the Add User page
        homePage.clickAdminTab();
        // Click on the Add button
        adminPage.clickAddUser();

        // Fill in the user details
        adminPage.addUserRole();
        adminPage.enterAddEmployeeName();
        adminPage.addStatus();
        adminPage.enterAddUsername();
        adminPage.enterPassword("JamesButler123");
        adminPage.enterConfirmPassword("JamesButler123");
        // Save the new user
        adminPage.saveUser();

        boolean userIsAdded = adminPage.isUserAdded();
        Assert.assertEquals(userIsAdded, true);
        logger.info("User added successfully.");

        adminPage.deleteUser();

    }
    @AfterMethod
    public void tearDownMethod() {
        // Close the browser
        tearDown();
    }
}