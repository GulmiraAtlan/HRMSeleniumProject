package com.end2endTests;

import com.data.TestConstants;
import com.base.BaseClass;
import com.pages.LoginPage;
import com.pages.RecruitmentPage;
import com.pages.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;


public class AddCandidateTest extends BaseClass{
    private LoginPage loginPage;
    private RecruitmentPage recruitmentPage;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {

        // Initialize WebDriver from BaseClass
        setUp("chrome");

        // Initialize the LoginPage object
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        recruitmentPage = new RecruitmentPage(getDriver());
    }
    @Test
    public void testAddCandidates() throws InterruptedException {

        loginPage.openLoginPage(TestConstants.LOGIN_URL);
        // Log in to the application
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);


        // Navigate to the Add User page
        homePage.clickRecruitmentTab();
        // Click on the Add button
        recruitmentPage.clickAddCandidate();
        recruitmentPage.enterFirstName(TestConstants.FIRSTNAME);
        recruitmentPage.enterLastName(TestConstants.LASTNAME);
        recruitmentPage.clickOnVacancyDropdown();
        recruitmentPage.selectSeniorQAOption();
        recruitmentPage.enterEmail(TestConstants.EMAIL);
        recruitmentPage.enterContactNumber(TestConstants.CONTACTNUMBER);
        Thread.sleep(2000);
        recruitmentPage.enterKeyWord("Test");
        //recruitmentPage.enterDateOfApplication("2025-21-04");
        recruitmentPage.uploadResume("/Users/gulmire/IdeaProjects/HRMSeleniumProject/src/MyTestUploadDoc.docx");
        Thread.sleep(3000);
        recruitmentPage.enterNotes("Test Notes");
        Thread.sleep(2000);
        recruitmentPage.clickSaveButton();
        Thread.sleep(2000);
        boolean isCandidateAdded = recruitmentPage.isCandidateAdded();
        Assert.assertEquals(isCandidateAdded, true);
        recruitmentPage.clickOnCandidatesTab();
        Thread.sleep(2000);
        recruitmentPage.deleteUser();
    }
    @AfterMethod
    public void tearDownMethod() {
        // Close the browser
        tearDown();
    }
}

