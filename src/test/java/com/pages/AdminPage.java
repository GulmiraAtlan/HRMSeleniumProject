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

public class AdminPage {
        private WebDriver driver;
        private WebDriverWait wait;

        // Locators
        private By addUserButton = By.xpath("//div[@class='orangehrm-header-container']/button");
        private By userRoleDropdownInput = By.xpath("//div[@class='oxd-form-row']/div/div[2]///div[@class='oxd-select-text-input']");
        private By statusDropdownInput = By.xpath("//div[@class='oxd-form-row']/div/div[4]///div[@class='oxd-select-text-input']");;

        private By employeeNameField = By.xpath("//div[@class='oxd-autocomplete-wrapper']//input");


        private By searchButton = By.xpath("//button[@type='submit']");
        private By addUserUserNameField = By.xpath("//div[@class='oxd-form-row']/div/div[4]//input");

        private By addUserEmployeeNameField = By.xpath("//div[@class='oxd-form-row']/div/div[2]//input");

        private By addEmployeeNameOption = By.xpath("//div[@class='oxd-form-row']/div/div[2]//div[@role='listbox']/div");

        private By addUserRoleDropdownInput = By.xpath("//div[@class='oxd-form-row']/div/div[1]//div[@class='oxd-select-text-input']");
        private By addUserRoleOptionAdmin = By.xpath("//div[@class='oxd-form-row']/div/div[1]//div[@role='listbox']/div[2]");
        private By addUserStatusDropdownInput = By.xpath("//div[@class='oxd-form-row']/div/div[3]//div[@class='oxd-select-text-input']");
        private By addUserStatusOptionEnabled = By.xpath("//div[@class='oxd-form-row']/div/div[3]//div[@role='listbox']/div[2]");
        private By addStatusSelect = By.xpath("//div[@class='oxd-form-row']/div/div[3]//div[@class='oxd-select-wrapper']");
        private By passwordField = By.xpath("//div[@class='oxd-form-row user-password-row']/div/div[1]//input[@type='password']");
        private By confirmPasswordField = By.xpath("//div[@class='oxd-form-row user-password-row']/div/div[2]//input[@type='password']");
        private By saveButton = By.xpath("//div[@class='oxd-form-actions']/button[2]");
        private By tableRecords = By.xpath("//div[@class='oxd-table-card']");
        private By tableBody = By.xpath("//div[@class='oxd-table']");
        private By deleteButton = By.xpath("//div[@role='document']//div[@class='orangehrm-modal-footer']/button[2]");
        private By searchUserNameField = By.xpath("//div[@class='oxd-form-row']/div/div[1]//input");
        public AdminPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        }

        // Methods
        public void clickAddUser() {
            driver.findElement(addUserButton).click();
        }

        public void selectUserRole(String role) {
            WebElement dropdown = driver.findElement(userRoleDropdownInput);
            dropdown.sendKeys(role);
        }
        public void selectStatus(String status) {
            WebElement dropdown = driver.findElement(statusDropdownInput);
            dropdown.sendKeys(status);
        }

        public void enterEmployeeName(String employeeName) {
            driver.findElement(employeeNameField).sendKeys(employeeName);
        }
        public void addUserRole()  {
            driver.findElement(addUserRoleDropdownInput).click();

            driver.findElement(addUserRoleOptionAdmin).click();
        }
        public void addStatus() {
            driver.findElement(addUserStatusDropdownInput).click();

            driver.findElement(addUserStatusOptionEnabled).click();
        }
        public void enterAddEmployeeName() throws InterruptedException {
            driver.findElement(addUserEmployeeNameField).sendKeys("James");
            Thread.sleep(2000);
            driver.findElement(addEmployeeNameOption).click();
        }
        public void enterAddUsername() {
            driver.findElement(addUserUserNameField).sendKeys(TestConstants.NEWUSERNAME);
        }

        public void enterPassword(String password) {
            driver.findElement(passwordField).sendKeys(password);
        }

        public void enterConfirmPassword(String confirmPassword) {
            driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
        }

        public void saveUser() {
            driver.findElement(saveButton).click();
        }
        public void clickSearch() {
            driver.findElement(searchButton).click();
        }

        public void scrollToTable() {
            WebElement table = driver.findElement(tableBody);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", table);
        }

        public boolean isUserAdded() throws InterruptedException {
           Thread.sleep(2000);
           scrollToTable();
           String script = "return document.getElementsByClassName('oxd-table-card') !== null;";
           Boolean exists = (Boolean) (((JavascriptExecutor) driver).executeScript(script));
           System.out.println("Element exists: " + exists);
           ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");

           List<WebElement> records = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRecords));
            System.out.println("Number of records found: " + records.size());
            for (WebElement record : records) {
                System.out.println("Record text: " + record.getText());
                if (record.getText().contains(TestConstants.NEWUSERNAME)) {
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
                if (record.getText().contains(TestConstants.NEWUSERNAME)) {
                    record.findElement(By.xpath(".//button[1]")).click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
                    confirmDelete();
                    break;
                }
            }
        }
        public void scrollBy(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
        }

        public void searchUser(String username) {
            driver.findElement(searchUserNameField).sendKeys(username);
            clickSearch();
        }
        public boolean isUserFound(String username) {
            List<WebElement> records = driver.findElements(tableRecords);
            for (WebElement record : records) {
                if (record.getText().contains(username)) {
                    return true;
                }
            }
            return false;
        }
}

