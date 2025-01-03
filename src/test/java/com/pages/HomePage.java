package com.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

    public class HomePage {
        private WebDriver driver;

        // Constructor to initialize the WebDriver
        public HomePage(WebDriver driver) {
            this.driver = driver;
        }

        // Locators for login page elements
        private By dashboardTitle = By.xpath("//div[@class='oxd-topbar-header-title']"); // Replace with the actual ID
        private By adminTab = By.xpath("//ul[@class='oxd-main-menu']/li[1]"); // Replace with the actual ID
        public String getDashboardTitleText() {
            return driver.findElement(dashboardTitle).getText();
        }
        public void clickAdminTab() {
            driver.findElement(adminTab).click();
        }
}
