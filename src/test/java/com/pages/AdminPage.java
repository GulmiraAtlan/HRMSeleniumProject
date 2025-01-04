package com.pages;

import com.data.TestConstants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class AdminPage {
        private WebDriver driver;

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

        private By tableRecords = By.xpath("//div[@class='oxd-table-body oxd-card-table-body']/div");

        public AdminPage(WebDriver driver) {
            this.driver = driver;
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
            driver.findElement(addUserEmployeeNameField).sendKeys(TestConstants.EMPLOYEENAME);
            Thread.sleep(5000);
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
        public boolean isUserAdded() {
            List<WebElement> records = driver.findElements(tableRecords);
            for (WebElement record : records) {
                if (record.getText().contains(TestConstants.EMPLOYEENAME)) {
                    return true;
                }
            }
            return false;
        }
        public void deleteUser() {
            List<WebElement> records = driver.findElements(tableRecords);
            for (WebElement record : records) {
                if (record.getText().contains(TestConstants.EMPLOYEENAME)) {
                    record.findElement(By.xpath("//button[1]")).click();
                    break;
                }
            }
        }
    }

