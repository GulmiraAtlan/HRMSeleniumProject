package com.end2endTests;
import com.data.TestConstants;
import com.base.BaseClass;
import com.pages.LoginPage;
import com.pages.AdminPage;
import com.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;


public class SearchUserTest extends BaseClass {
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
        Thread.sleep(2000);
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

        // Search for the user
        adminPage.searchUser(TestConstants.NEWUSERNAME);

        // Verify that the user is found
        boolean isUserFound = adminPage.isUserFound(TestConstants.NEWUSERNAME);

        Assert.assertTrue(isUserFound, "User was not found after adding.");
        adminPage.deleteUser();
    }

    @AfterMethod
    public void tearDownTest() {
        // Close the browser
        tearDown();
    }
}
