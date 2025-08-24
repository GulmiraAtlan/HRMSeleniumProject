package com.end2endTests;
import com.data.TestConstants;
import com.base.BaseClass;
import com.pages.LoginPage;
import com.pages.AdminPage;
import com.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchUserTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(SearchUserTest.class);
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
    public void testSearchUser() throws InterruptedException {
        logger.info("Starting test: testSearchUser");
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
        logger.info("Searching for the newly added user.");
        // Search for the user
        adminPage.searchUser(TestConstants.NEWUSERNAME);
        // Verify that the user is found
        boolean isUserFound = adminPage.isUserFound(TestConstants.NEWUSERNAME);

        Assert.assertTrue(isUserFound, "User was not found after adding.");
        logger.info("User found successfully. Proceeding to delete the user.");
        adminPage.deleteUser();
    }

    @AfterMethod
    public void tearDownTest() {
        // Close the browser
        tearDown();
    }
}
