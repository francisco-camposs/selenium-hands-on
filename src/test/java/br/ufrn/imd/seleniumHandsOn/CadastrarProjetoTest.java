package br.ufrn.imd.seleniumHandsOn;


import br.ufrn.imd.seleniumHandsOn.pages.SapienciaCadastroProjetoPage;
import br.ufrn.imd.seleniumHandsOn.pages.SapienciaLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadastrarProjetoTest {

    private static WebDriver driver;
    private static SapienciaCadastroProjetoPage sapienciaCadastroProjetoPage;
    private static SapienciaLoginPage sapienciaLoginPage;
    private static final String SAPIENCIA_URL = "http://app-sapiencia-testes.info.ufrn.br";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getOptions());
        sapienciaLoginPage = new SapienciaLoginPage(driver);
        sapienciaCadastroProjetoPage = new SapienciaCadastroProjetoPage(driver);
        driver.get(SAPIENCIA_URL);
    }

    static private ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        return options;
    }

    @BeforeEach
    void bef() {
        try {
            sapienciaCadastroProjetoPage.alertText();
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
        sapienciaCadastroProjetoPage.openNewProjectPage();
        sapienciaCadastroProjetoPage.startNewProject();
    }

    @Test
    @Order(2)
    void formNewProjectTestNotAllFields() {
        fillNotAllFields();
        sapienciaCadastroProjetoPage.submitNewProjeto();
        String text = sapienciaCadastroProjetoPage.alertText();
        assertEquals("erro ao cadastrar dados do projeto\n" +
                "a demanda não pode ser somente espaços em branco",text.toLowerCase());

    }

    @Test
    @Order(3)
    void formNewProjectTest() {
        assertDoesNotThrow(() -> {
            sapienciaCadastroProjetoPage.checkTituloField();
            sapienciaCadastroProjetoPage.checkCodigoField();
            sapienciaCadastroProjetoPage.checkDemandaField();
            sapienciaCadastroProjetoPage.checkConvenioField();
            sapienciaCadastroProjetoPage.checkReferenciaField();
            sapienciaCadastroProjetoPage.checkSigFundacaoField();
            sapienciaCadastroProjetoPage.checkValorTotalProjetoField();
        });
    }

    @Test
    @Order(4)
    void newProjectTest() {
        assertDoesNotThrow(() -> {
            this.fillFields();
            sapienciaCadastroProjetoPage.submitNewProjeto();
        });

        assertDoesNotThrow(() -> {
            sapienciaCadastroProjetoPage.checkSuccess();
        });
    }

    private void fillFields() {
        sapienciaCadastroProjetoPage.fillTituloField("Titulo Teste 1");
        sapienciaCadastroProjetoPage.fillCodigoField("Codigo Teste 1");
        sapienciaCadastroProjetoPage.fillDemandaField("123456789");
        sapienciaCadastroProjetoPage.fillConvenioField("123456789");
        sapienciaCadastroProjetoPage.fillReferenciaField("123456789");
        sapienciaCadastroProjetoPage.fillSigFundacaoField("123456789");
        sapienciaCadastroProjetoPage.fillValorTotalProjetoField("10000");
    }

    private void fillNotAllFields() {
        sapienciaCadastroProjetoPage.fillTituloField("Titulo Teste 1");
        sapienciaCadastroProjetoPage.fillCodigoField("Codigo Teste 1");
        sapienciaCadastroProjetoPage.fillConvenioField("123456789");
        sapienciaCadastroProjetoPage.fillReferenciaField("123456789");
        sapienciaCadastroProjetoPage.fillSigFundacaoField("123456789");
        sapienciaCadastroProjetoPage.fillValorTotalProjetoField("10000");
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

}
