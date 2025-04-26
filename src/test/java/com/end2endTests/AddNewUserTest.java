package com.end2endTests;

import com.data.TestConstants;
import com.base.BaseClass;
import com.pages.LoginPage;
import com.pages.AdminPage;
import com.pages.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;

public class AddNewUserTest extends BaseClass{
    private LoginPage loginPage;
    private AdminPage adminPage;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {

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
        Thread.sleep(5000);
        adminPage.enterAddEmployeeName();
        Thread.sleep(5000);
        adminPage.addStatus();
        Thread.sleep(2000);
        adminPage.enterAddUsername();
        Thread.sleep(2000);
        adminPage.enterPassword("JamesButler123");
        Thread.sleep(2000);
        adminPage.enterConfirmPassword("JamesButler123");
        Thread.sleep(2000);
        // Save the new user
        adminPage.saveUser();
        Thread.sleep(2000);
        boolean userIsAdded = adminPage.isUserAdded();
        Assert.assertEquals(userIsAdded, true);
        adminPage.deleteUser();

    }
    @AfterMethod
    public void tearDownMethod() {
        // Close the browser
        tearDown();
    }
}