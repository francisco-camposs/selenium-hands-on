package br.ufrn.imd.seleniumHandsOn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SapienciaCadastrarSubprojetoPage {

    private final WebDriver driver;
    private final By subProjetoBtn = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[1]/div[2]/div[3]/button/span/span");
    private final String addSubProjetoBtn = "/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div[%d]/div/a";
    private final String subProjetoSiglaBase = "/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div[%d]/div[2]/div[1]/div[2]/div/div/div[1]/div/input";
    private final String subProjetoTituloBase ="/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div[%d]/div[2]/div[2]/div[2]/div/div/div[1]/div/input";
    private final String removeSubProjectBase= "/html/body/div[1]/div[1]/main/div/div[1]/div/div/div[2]/div[2]/div[%d]/div[2]/div[2]/div[3]/button/span/i";

    private int subProjetoCount = 2;

    public SapienciaCadastrarSubprojetoPage(WebDriver driver) {
        this.driver = driver;
    }


    public void checkSuccess() {
        new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success")));
    }

    public void goToSubProjetoForm() {
        new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(this.subProjetoBtn)).click();
    }

    public void fillSiglaSubProjeto(int i, String valor) {
        final String concretePath = String.format(this.subProjetoSiglaBase, i);
        WebElement sigla = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(concretePath)));
        sigla.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        sigla.sendKeys(valor);
    }

    public void fillTituloSubProjeto(int i, String valor) {
        final String concretePath = String.format(this.subProjetoTituloBase, i);
        WebElement titulo = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(concretePath)));
        titulo.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        titulo.sendKeys(valor);
    }

    public void addSubProjeto() {
        String concretePath = String.format(this.addSubProjetoBtn, subProjetoCount++);
        new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(concretePath))).click();
    }

    public void removeSubProject(int i) {
        final String concretePath = String.format(removeSubProjectBase, i);

        WebElement deleteButton = driver.findElement(By.xpath(concretePath));
        deleteButton.click();
        subProjetoCount--;
    }

    public String getTituloSubProjeto(int i) {
        final String concretePath = String.format(this.subProjetoTituloBase, i);
        WebElement titulo = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(concretePath)));

        final String tituloS =  titulo.getText();
        return tituloS;
    }


}
