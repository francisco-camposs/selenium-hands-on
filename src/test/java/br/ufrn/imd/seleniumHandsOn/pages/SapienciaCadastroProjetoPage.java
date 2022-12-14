package br.ufrn.imd.seleniumHandsOn.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SapienciaCadastroProjetoPage {

    private final WebDriver driver;

    private final By newProjectPage = By.xpath("/html/body/div[1]/div/header/div/div/div[4]/a");
    private final By newProjectButton = By.xpath("//*[@id=\"app\"]/div/main/div/div[1]/div/div/div[1]/div/div/div/div/div[3]/button");


    private final By tituloField = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div[1]/div/div[2]/div/div/div[1]/div/input");
    private final By codigoField = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div[2]/div/div[2]/div/div/div[1]/div/input");
    private final By demandaField = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div[3]/div/div[2]/div/div/div[1]/div/input");
    private final By convenioField = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div[4]/div/div[2]/div/div/div[1]/div/input");
    private final By referenciaField = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div[5]/div/div[2]/div/div/div[1]/div/input");
    private final By sigFuncadaoField = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div[6]/div/div[2]/div/div/div[1]/div/input");
    private final By valorTotalProjetoField = By.xpath("/html/body/div[1]/div[1]/main/div/div[1]/div/div/div[2]/div[2]/div[8]/div/div[2]/div/div/div[1]/div/input");

    private final By submitNewProjetoButton = By.xpath("/html/body/div[1]/div/main/div/div[1]/div/div/div[2]/div[2]/div[9]/button[2]");

    private final By allertError = By.xpath("/html/body/div[1]/div/main/div/div[2]/span/div");

    public SapienciaCadastroProjetoPage(WebDriver driver) {
        this.driver = driver;
    }


    public void openNewProjectPage() {
        WebElement newProjectPage = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(this.newProjectPage));
        newProjectPage.click();
    }

    public void startNewProject() {
        WebElement newProjectButton = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(this.newProjectButton));
        newProjectButton.click();
    }

    public String alertText() {
        WebElement allert = new WebDriverWait(this.driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(this.allertError));
        String text = allert.getText();
        allert.click();
        allert.click();
        return text;
    }

    public WebElement checkTituloField() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(this.tituloField));
    }

    public WebElement checkCodigoField() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(this.codigoField));
    }

    public WebElement checkDemandaField() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(this.demandaField));
    }

    public WebElement checkConvenioField() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(this.convenioField));
    }

    public WebElement checkReferenciaField() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(this.referenciaField));
    }

    public WebElement checkSigFundacaoField() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(this.sigFuncadaoField));
    }

    public WebElement checkValorTotalProjetoField() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(this.valorTotalProjetoField));
    }


    public void fillTituloField(String titulo) {
        checkTituloField().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        checkTituloField().sendKeys(titulo);
    }

    public void fillCodigoField(String codigo) {
        checkCodigoField().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        checkCodigoField().sendKeys(codigo);
    }

    public void fillDemandaField(String demanda) {
        checkDemandaField().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        checkDemandaField().sendKeys(demanda);
    }

    public void fillConvenioField(String convenio) {
        checkConvenioField().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        checkConvenioField().sendKeys(convenio);
    }

    public void fillReferenciaField(String referencia) {
        checkReferenciaField().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        checkReferenciaField().sendKeys(referencia);
    }

    public void fillSigFundacaoField(String sigFundacao) {
        checkSigFundacaoField().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        checkSigFundacaoField().sendKeys(sigFundacao);
    }

    public void fillValorTotalProjetoField(String valorTotalProjeto) {
        checkValorTotalProjetoField().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        checkValorTotalProjetoField().sendKeys(valorTotalProjeto);
    }

    public void submitNewProjeto() {
        WebElement submitNewProjetoButton = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(this.submitNewProjetoButton));
        submitNewProjetoButton.click();
    }

    public void checkSuccess() {
        new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success")));
    }

}