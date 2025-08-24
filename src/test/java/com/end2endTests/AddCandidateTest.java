package com.end2endTests;

import com.data.TestConstants;
import com.base.BaseClass;
import com.pages.LoginPage;
import com.pages.RecruitmentPage;
import com.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class AddCandidateTest extends BaseClass{
    private static final Logger logger = LogManager.getLogger(AddCandidateTest.class);
    private LoginPage loginPage;
    private RecruitmentPage recruitmentPage;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {

        logger.info("Setting up the test environment.");
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
        logger.info("Logged in successfully.");

        // Navigate to the Add User page
        homePage.clickRecruitmentTab();
        logger.info("Navigated to the Recruitment page.");
        // Click on the Add button
        recruitmentPage.clickAddCandidate();
        recruitmentPage.enterFirstName(TestConstants.FIRSTNAME);
        recruitmentPage.enterLastName(TestConstants.LASTNAME);
        recruitmentPage.clickOnVacancyDropdown();
        recruitmentPage.selectSeniorQAOption();
        recruitmentPage.enterEmail(TestConstants.EMAIL);
        recruitmentPage.enterContactNumber(TestConstants.CONTACTNUMBER);
        recruitmentPage.enterKeyWord("Test");
        //recruitmentPage.enterDateOfApplication("2025-21-04");
        recruitmentPage.uploadResume("/Users/gulmire/IdeaProjects/HRMSeleniumProject/src/MyTestUploadDoc.docx");
        Thread.sleep(3000);
        recruitmentPage.enterNotes("Test Notes");
        recruitmentPage.clickSaveButton();
        boolean isCandidateAdded = recruitmentPage.isCandidateAdded();
        Assert.assertEquals(isCandidateAdded, true);
        logger.info("Candidate added successfully.");

        recruitmentPage.clickOnCandidatesTab();
        // Delete the candidate
        recruitmentPage.deleteUser();
        logger.info("Candidate deleted successfully.");

    }
    @AfterMethod
    public void tearDownMethod() {
        // Close the browser
        tearDown();
    }
}

