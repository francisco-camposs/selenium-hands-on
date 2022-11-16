package br.ufrn.imd.seleniumHandsOn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SapienciaCadastrarParcelaPage {

    private final WebDriver driver;

    private final By parcela1 = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[1]/div/div/div[1]/div/input");
    private final By parcela2 = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div/div/div[1]/div/input");
    private final By parcela3 = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div/div[1]/div[3]/div[1]/div/div/div[1]/div/input");
    private final By parcelaFormButtom = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[1]/div[2]/div[2]/button/span/span");
    private final By advanceButtom = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div/div[2]/button[2]/span");


    public SapienciaCadastrarParcelaPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By allertError = By.xpath("/html/body/div[1]/div/main/div/div[2]/span/div");

    public void fillParcela1(String valor) {
        WebElement parcela1E = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(this.parcela1));
        parcela1E.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        parcela1E.sendKeys(valor);
    }

    public void fillParcela2(String valor) {
        WebElement parcela2E = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(this.parcela2));

        parcela2E.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        parcela2E.sendKeys(valor);
    }

    public void fillParcela3(String valor) {
        WebElement parcela3E = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(this.parcela3));
        parcela3E.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        parcela3E.sendKeys(valor);
    }

    public void goToParcelaForm() {
        WebElement parcelaButtom = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(this.parcelaFormButtom));
        parcelaButtom.click();
    }

    public void advanceFormProjeto() {
        WebElement advanceButtome = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(this.advanceButtom));
        advanceButtome.click();
    }

    public String alertText() {
        WebElement allert = new WebDriverWait(this.driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(this.allertError));
        String text = allert.getText();
        allert.click();
        allert.click();
        return text;
    }

}
