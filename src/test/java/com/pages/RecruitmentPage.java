package com.pages;

import com.data.TestConstants;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class RecruitmentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Locators
    private By addCandidateButton = By.xpath("//div[@class='orangehrm-header-container']/button");
    private By firstNameField = By.xpath("//input[@name='firstName']");
    private By lastNameField = By.xpath("//input[@name='lastName']");
    private By vacancyDropdown = By.xpath("//div[@class='oxd-select-text--after']/i");
    private By seniorQAOption = By.xpath("//div[@class='oxd-select-wrapper']/div[2]/div[5]");
    private By emailField = By.xpath("//div[@class='oxd-form-row'][3]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input");
    private By contactNumberField = By.xpath("//div[@class='oxd-form-row'][3]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//input");
    private By resumeField = By.xpath("//input[@class='oxd-file-input']");
    private By keyWordField = By.xpath("//div[@class='oxd-form-row'][5]/div/div[1]//input");
    private By dateOfApplicationField = By.xpath("//div[@class='oxd-form-row'][5]/div/div[2]//input");

    private By notesField = By.xpath("//div[@class='oxd-form-row'][6]//textarea");
    private By saveButton = By.xpath("//button[@type='submit']");
    private By tableBody = By.xpath("//div[@class='oxd-table']");
    private By tableRecords = By.xpath("//div[@class='oxd-table-card']");
    private By deleteButton = By.xpath("//div[@role='document']//div[@class='orangehrm-modal-footer']/button[2]");

    private By shortList = By.xpath("//div[@class='orangehrm-recruitment-actions']//button[2]");
    private By saveShortListButton = By.xpath("//div[@class='oxd-form-actions']/button[2]");
    private By candidatesTab = By.xpath("//nav[@class='oxd-topbar-body-nav']//li[1]");
    public void clickAddCandidate() {
        driver.findElement(addCandidateButton).click();
    }

    public void clickOnVacancyDropdown() {
        driver.findElement(vacancyDropdown).click();
    }

    public void selectSeniorQAOption() {
        driver.findElement(seniorQAOption).click();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterContactNumber(String contactNumber) {
        driver.findElement(contactNumberField).sendKeys(contactNumber);
    }

    public void uploadResume(String filePath) {
        WebElement uploadElement = driver.findElement(resumeField);
        uploadElement.sendKeys(filePath);
    }

    public void enterKeyWord(String keyWord) {
        driver.findElement(keyWordField).sendKeys(keyWord);
    }

    public void enterDateOfApplication(String date) {
        driver.findElement(dateOfApplicationField).sendKeys(date);
    }

    public void enterNotes(String notes) {
        driver.findElement(notesField).sendKeys(notes);
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public void scrollToTable() {
        WebElement table = driver.findElement(tableBody);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", table);
    }

    public boolean isCandidateAdded() throws InterruptedException {

        String script = "return document.getElementsByClassName('oxd-table-card') !== null;";
        Boolean exists = (Boolean) (((JavascriptExecutor) driver).executeScript(script));
        System.out.println("Element exists: " + exists);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");


        List<WebElement> records = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRecords));
        System.out.println("Number of records found: " + records.size());
        for (WebElement record : records) {
            System.out.println("Record text: " + record.getText());
            if (record.getText().contains(TestConstants.FIRSTNAME)) {
                return true;
            }
        }
        return false;
    }

    public void confirmDelete() {
        driver.findElement(deleteButton).click();
    }

    public void deleteUser() {
        List<WebElement> records = driver.findElements(tableRecords);
        for (WebElement record : records) {
            if (record.getText().contains(TestConstants.FIRSTNAME+" "+TestConstants.LASTNAME)) {
                record.findElement(By.xpath(".//button[2]")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
                confirmDelete();
                break;
            }
        }

    }
    public void clickOnShortListCandidate() {
        driver.findElement(shortList).click();
    }
    public void clickOnSaveShortListButton() {
        driver.findElement(saveShortListButton).click();
    }
    public void clickOnCandidatesTab() {
        driver.findElement(candidatesTab).click();
    }
}
