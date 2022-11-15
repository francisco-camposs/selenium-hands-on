package br.ufrn.imd.seleniumHandsOn.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SapienciaLoginPage {

    private final WebDriver driver;

    private final By usernameInput = By.xpath("/html/body/div[1]/div/main/div/div[1]/div[1]/div/div/div[1]/div[2]/div[2]/div[1]/div/input");
    private final By passwordInput = By.xpath("/html/body/div[1]/div/main/div/div[1]/div[1]/div/div/div[1]/div[3]/div[2]/div[1]/div[1]/input");
    private final By signupButton = By.xpath("/html/body/div[1]/div/main/div/div[1]/div[1]/div/div/div[2]/button[2]");

    public SapienciaLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement checkUsernameInput() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(this.usernameInput));
    }

    public void setUsernameInput(String emailAddress) {
        WebElement usernameInput =checkUsernameInput();
        usernameInput.sendKeys(emailAddress);
    }

    public WebElement checkPasswordField() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(this.passwordInput));
    }

    public void setPasswordInput(String password) {
        WebElement passwordInput =checkPasswordField();
        passwordInput.sendKeys(password);
    }

    public void sendLogin() {
        WebElement signupButton = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(this.signupButton));
        signupButton.click();
    }

    public void alertaIsPresent() {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.alertIsPresent());
    }

}
