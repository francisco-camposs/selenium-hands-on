package br.ufrn.imd.seleniumHandsOn;

import br.ufrn.imd.seleniumHandsOn.pages.SapienciaCadastroProjeto;
import br.ufrn.imd.seleniumHandsOn.pages.SapienciaLoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private static WebDriver driver;
    private static SapienciaLoginPage sapienciaLoginPage;
    private static final String SAPIENCIA_URL = "http://app-sapiencia-testes.info.ufrn.br";

    @BeforeAll
    static void beforeAll() {
        driver = new ChromeDriver(getOptions());
        sapienciaLoginPage = new SapienciaLoginPage(driver);
    }

    static private ChromeOptions getOptions() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\55849\\Documents\\uni\\testes\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        return options;
    }

    @BeforeEach
    void setUp() {
        driver.get(SAPIENCIA_URL);
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @Test
    public void checkEmptyUser() {
        sapienciaLoginPage.setPasswordInput("12345678");
        sapienciaLoginPage.sendLogin();
        assertEquals(driver.getCurrentUrl(), SAPIENCIA_URL+"/login?redirect=%2Fprojetos-vigentes");
    }

    @Test
    public void checkEmptyPassword() {
        sapienciaLoginPage.setUsernameInput("12345678");
        sapienciaLoginPage.sendLogin();
        assertEquals(driver.getCurrentUrl(), SAPIENCIA_URL+"/login?redirect=%2Fprojetos-vigentes");
    }

    @Test
    public void checkInvalidData() {
        sapienciaLoginPage.setUsernameInput("12345678");
        sapienciaLoginPage.setPasswordInput("12345678");
        sapienciaLoginPage.sendLogin();
        assertDoesNotThrow(() -> {
            sapienciaLoginPage.alertaIsPresent();
        });
        assertEquals(driver.getCurrentUrl(), SAPIENCIA_URL+"/login?redirect=%2Fprojetos-vigentes");
    }

}
