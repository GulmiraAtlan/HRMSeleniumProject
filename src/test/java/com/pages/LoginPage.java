package com.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginPage {
    private WebDriver driver;

    // Constructor to initialize the WebDriver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    // Locators for login page elements
    private By usernameField = By.xpath("//input[@name='username']"); // Replace with the actual ID
    private By passwordField = By.xpath("//input[@name='password']"); // Replace with the actual ID
    private By loginButton = By.xpath("//button[@type='submit']"); // Replace with the actual ID
    private By loginForgot = By.xpath("//[@div[@class='orangehrm-login-forgot']");

    private By errorMessage = By.xpath("//div[@role='alert']");
    // Actions on the login page
    private static final Logger logger = LogManager.getLogger(AdminPage.class);

    // Open the login page
    public void openLoginPage(String url) {
        driver.get(url);
    }

    // Enter username
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Click the login button
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Get error message text (if login fails)
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    // Perform login with a single method
    public void login(String username, String password) {
        logger.info("User is logging in with username: " + username);
        try {
            enterUsername(username);
            enterPassword(password);
            clickLoginButton();
        } catch (Exception e) {
            logger.error("Error during login: " + e.getMessage());
        }
    }
}

