package br.ufrn.imd.seleniumHandsOn;

import br.ufrn.imd.seleniumHandsOn.pages.SapienciaCadastrarParcelaPage;
import br.ufrn.imd.seleniumHandsOn.pages.SapienciaCadastroProjetoPage;
import br.ufrn.imd.seleniumHandsOn.pages.SapienciaLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadastrarParcelaTest {

    private static WebDriver driver;
    private static SapienciaCadastrarParcelaPage sapienciaCadastrarParcelaPage;
    private static SapienciaCadastroProjetoPage sapienciaCadastroProjetoPage;
    private static SapienciaLoginPage sapienciaLoginPage;
    private static final String SAPIENCIA_URL = "http://app-sapiencia-testes.info.ufrn.br";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getOptions());
        sapienciaLoginPage = new SapienciaLoginPage(driver);
        sapienciaCadastroProjetoPage = new SapienciaCadastroProjetoPage(driver);
        sapienciaCadastrarParcelaPage = new SapienciaCadastrarParcelaPage(driver);
        driver.get(SAPIENCIA_URL);
        openNewProjectPage();
    }

    static private ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        return options;
    }

    static private void openNewProjectPage() {
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
        } catch (Exception ignored) {

        }
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @Test
    @Order(1)
    void insertParcelasNegativeValue() {
        sapienciaCadastrarParcelaPage.goToParcelaForm();
        sapienciaCadastrarParcelaPage.fillParcela1("1234-");
        sapienciaCadastrarParcelaPage.fillParcela2("30000");
        sapienciaCadastrarParcelaPage.fillParcela3("30000");
        sapienciaCadastrarParcelaPage.advanceFormProjeto();
        String text = sapienciaCadastrarParcelaPage.alertText();
        assertEquals("erro ao cadastrar parcelas do projeto\n" +
                "o valor autorizado da parcela deve ser positivo", text.toLowerCase());
    }

    @Test
    @Order(2)
    void parcelasSunDonMatchValue() throws InterruptedException {
        sapienciaCadastrarParcelaPage.goToParcelaForm();
        sapienciaCadastrarParcelaPage.fillParcela1("6000");
        sapienciaCadastrarParcelaPage.fillParcela2("3000");
        sapienciaCadastrarParcelaPage.fillParcela3("4000");
        sapienciaCadastrarParcelaPage.advanceFormProjeto();
        String text = sapienciaCadastrarParcelaPage.alertText();
        assertEquals("erro ao cadastrar parcelas do projeto\n"
                +"o valor autorizado das parcelas não pode exceder o valor autorizado fndct", text.toLowerCase());
    }

    @Test
    @Order(3)
    void insertParcelas() throws InterruptedException {
        sapienciaCadastrarParcelaPage.goToParcelaForm();
        sapienciaCadastrarParcelaPage.fillParcela1("3000");
        sapienciaCadastrarParcelaPage.fillParcela2("3000");
        sapienciaCadastrarParcelaPage.fillParcela3("4000");
        sapienciaCadastrarParcelaPage.advanceFormProjeto();
        String text = sapienciaCadastrarParcelaPage.alertText();
        assertEquals("parcela cadastrada com sucesso!\n" +
                "parcela cadastrada com sucesso!", text.toLowerCase());
    }

}
