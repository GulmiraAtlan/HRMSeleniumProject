package com.end2endTests;

import com.data.TestConstants;
import com.pages.LoginPage;
import com.pages.AdminPage;
import com.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewUserTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private AdminPage adminPage;
    private HomePage homePage;

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage(TestConstants.LOGIN_URL);
    }
    @Test
    public void testAddNewUser() throws InterruptedException {

        // Log in to the application
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);


        // Navigate to the Add User page
        homePage.clickAdminTab();
        // Click on the Add button
        adminPage.clickAddUser();

        // Fill in the user details
        adminPage.addUserRole("Admin");
        adminPage.enterAddEmployeeName();
        adminPage.addStatus("Enabled");
        adminPage.enterAddUsername();
        adminPage.enterPassword("BernaSmith123");
        adminPage.enterConfirmPassword("BernaSmith123");
        // Save the new user
        adminPage.saveUser();
        Thread.sleep(2000);
        boolean userAddedSuccessfully = adminPage.isUserAdded();
        assert userAddedSuccessfully == true;
        adminPage.deleteUser();

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}