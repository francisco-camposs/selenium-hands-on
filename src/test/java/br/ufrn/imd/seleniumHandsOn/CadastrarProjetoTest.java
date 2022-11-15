package br.ufrn.imd.seleniumHandsOn;


import br.ufrn.imd.seleniumHandsOn.pages.SapienciaCadastroProjeto;
import br.ufrn.imd.seleniumHandsOn.pages.SapienciaLoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadastrarProjetoTest {

    private static WebDriver driver;
    private static SapienciaCadastroProjeto sapienciaCadastroProjeto;
    private static SapienciaLoginPage sapienciaLoginPage;
    private static final String SAPIENCIA_URL = "http://app-sapiencia-testes.info.ufrn.br";

    @BeforeAll
    static void beforeAll() {
        driver = new ChromeDriver(getOptions());
        sapienciaLoginPage = new SapienciaLoginPage(driver);
        sapienciaCadastroProjeto = new SapienciaCadastroProjeto(driver);
        driver.get(SAPIENCIA_URL);
    }

    static private ChromeOptions getOptions() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\55849\\Documents\\uni\\testes\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        return options;
    }

    @BeforeEach
    void bef() {
        try {
            sapienciaCadastroProjeto.alertText();
        } catch (Exception e) {

        }
    }
    @Test
    @Order(1)
    void newProjectPageTest() {
        assertDoesNotThrow(() -> {
            this.login();
            this.openNewProjectPage();
        });
        assertEquals(SAPIENCIA_URL+"/cadastro-projetos/novo-cadastro", driver.getCurrentUrl());
    }

    private void login() {
        sapienciaLoginPage.setUsernameInput("junior");
        sapienciaLoginPage.setPasswordInput("password");
        sapienciaLoginPage.sendLogin();
    }

    private void openNewProjectPage() {
        sapienciaCadastroProjeto.openNewProjectPage();
        sapienciaCadastroProjeto.startNewProject();
    }

    @Test
    @Order(2)
    void formNewProjectTest() {
        assertDoesNotThrow(() -> {
            sapienciaCadastroProjeto.checkTituloField();
            sapienciaCadastroProjeto.checkCodigoField();
            sapienciaCadastroProjeto.checkDemandaField();
            sapienciaCadastroProjeto.checkConvenioField();
            sapienciaCadastroProjeto.checkReferenciaField();
            sapienciaCadastroProjeto.checkSigFundacaoField();
            sapienciaCadastroProjeto.checkValorTotalProjetoField();
        });
    }

    @Test
    @Order(3)
    void newProjectTest() {
        assertDoesNotThrow(() -> {
            this.fillFields();
            sapienciaCadastroProjeto.submitNewProjeto();
        });

        assertDoesNotThrow(() -> {
            sapienciaCadastroProjeto.checkSuccess();
        });
    }

    @Test
    @Order(4)
    void inserirParcelasBadPath() {
        sapienciaCadastroProjeto.goToParcelaForm();
        sapienciaCadastroProjeto.fillParcela1("");
        sapienciaCadastroProjeto.fillParcela2("");
        sapienciaCadastroProjeto.fillParcela3("");
        sapienciaCadastroProjeto.fillParcela1("1234-");
        sapienciaCadastroProjeto.fillParcela2("30000");
        sapienciaCadastroProjeto.fillParcela3("30000");
        sapienciaCadastroProjeto.advanceFormProjeto();
        String text = sapienciaCadastroProjeto.alertText();
        assertEquals("erro ao cadastrar parcelas do projeto\n" +
                "o valor autorizado da parcela deve ser positivo", text.toLowerCase());
    }

    @Test
    @Order(5)
    void inserirParcelas() throws InterruptedException {
        sapienciaCadastroProjeto.goToParcelaForm();
        sapienciaCadastroProjeto.fillParcela1("");
        sapienciaCadastroProjeto.fillParcela2("");
        sapienciaCadastroProjeto.fillParcela3("");
        sapienciaCadastroProjeto.fillParcela1("3000");
        sapienciaCadastroProjeto.fillParcela2("3000");
        sapienciaCadastroProjeto.fillParcela3("4000");
        sapienciaCadastroProjeto.advanceFormProjeto();
        String text = sapienciaCadastroProjeto.alertText();
        assertEquals("parcela cadastrada com sucesso!\n" +
                "parcela cadastrada com sucesso!", text.toLowerCase());
    }



    private void fillFields() {
        sapienciaCadastroProjeto.fillTituloField("Titulo Teste 1");
        sapienciaCadastroProjeto.fillCodigoField("Codigo Teste 1");
        sapienciaCadastroProjeto.fillDemandaField("123456789");
        sapienciaCadastroProjeto.fillConvenioField("123456789");
        sapienciaCadastroProjeto.fillReferenciaField("123456789");
        sapienciaCadastroProjeto.fillSigFundacaoField("123456789");
        sapienciaCadastroProjeto.fillValorTotalProjetoField("10000");

    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

}
