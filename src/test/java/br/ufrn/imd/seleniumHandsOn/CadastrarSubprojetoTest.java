package br.ufrn.imd.seleniumHandsOn;

import br.ufrn.imd.seleniumHandsOn.pages.SapienciaCadastrarSubprojetoPage;
import br.ufrn.imd.seleniumHandsOn.pages.SapienciaCadastroProjetoPage;
import br.ufrn.imd.seleniumHandsOn.pages.SapienciaLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadastrarSubprojetoTest {

    private static WebDriver driver;
    private static SapienciaCadastrarSubprojetoPage sapienciaCadastrarSubprojetoPage;
    private static SapienciaCadastroProjetoPage sapienciaCadastroProjetoPage;
    private static SapienciaLoginPage sapienciaLoginPage;
    private static final String SAPIENCIA_URL = "http://app-sapiencia-testes.info.ufrn.br";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getOptions());
        sapienciaLoginPage = new SapienciaLoginPage(driver);
        sapienciaCadastroProjetoPage = new SapienciaCadastroProjetoPage(driver);
        sapienciaCadastrarSubprojetoPage = new SapienciaCadastrarSubprojetoPage(driver);
        driver.get(SAPIENCIA_URL);
        createNewProject();
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    static private ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        return options;
    }

    static private void createNewProject() {
        sapienciaLoginPage.setUsernameInput("junior");
        sapienciaLoginPage.setPasswordInput("password");
        sapienciaLoginPage.sendLogin();
        sapienciaCadastroProjetoPage.openNewProjectPage();
        sapienciaCadastroProjetoPage.startNewProject();
        sapienciaCadastroProjetoPage.fillTituloField("Titulo Teste 1");
        sapienciaCadastroProjetoPage.fillCodigoField("Codigo Teste 1");
        sapienciaCadastroProjetoPage.fillDemandaField("123456789");
        sapienciaCadastroProjetoPage.fillConvenioField("123456789");
        sapienciaCadastroProjetoPage.fillReferenciaField("123456789");
        sapienciaCadastroProjetoPage.fillSigFundacaoField("123456789");
        sapienciaCadastroProjetoPage.fillValorTotalProjetoField("10000");
        sapienciaCadastroProjetoPage.submitNewProjeto();
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
    void insertSubProjeto() {
        sapienciaCadastrarSubprojetoPage.goToSubProjetoForm();
        sapienciaCadastrarSubprojetoPage.addSubProjeto();
        sapienciaCadastrarSubprojetoPage.fillSiglaSubProjeto(1, "TESTE");
        sapienciaCadastrarSubprojetoPage.fillTituloSubProjeto(1, "TITULO TESTE");
        sapienciaCadastrarSubprojetoPage.fillSiglaSubProjeto(2, "TESTE");
        sapienciaCadastrarSubprojetoPage.fillTituloSubProjeto(2, "TITULO TESTE");
    }

    @Test
    @Order(2)
    void removeSubProjeto() {
        sapienciaCadastrarSubprojetoPage.addSubProjeto();
        sapienciaCadastrarSubprojetoPage.fillSiglaSubProjeto(1, "TESTE");
        sapienciaCadastrarSubprojetoPage.fillTituloSubProjeto(1, "TITULO TESTE");
        sapienciaCadastrarSubprojetoPage.fillSiglaSubProjeto(2, "TESTE");
        sapienciaCadastrarSubprojetoPage.fillTituloSubProjeto(2, "TITULO TESTE");
        sapienciaCadastrarSubprojetoPage.fillSiglaSubProjeto(3, "TESTE 3");
        sapienciaCadastrarSubprojetoPage.fillTituloSubProjeto(3, "TITULO TESTE 3");
        sapienciaCadastrarSubprojetoPage.removeSubProject(2);
        assertEquals("TITULO TESTE 3", sapienciaCadastrarSubprojetoPage.getTituloSubProjeto(2));
    }

}
