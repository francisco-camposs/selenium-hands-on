package br.ufrn.imd.seleniumHandsOn;

import br.ufrn.imd.seleniumHandsOn.pages.SapienciaLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    private static WebDriver driver;
    private static SapienciaLoginPage sapienciaLoginPage;
    private static final String SAPIENCIA_URL = "http://app-sapiencia-testes.info.ufrn.br";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getOptions());
        sapienciaLoginPage = new SapienciaLoginPage(driver);
    }

    static private ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
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
    @Order(1)
    public void checkEmptyUser() {
        sapienciaLoginPage.setPasswordInput("12345678");
        sapienciaLoginPage.sendLogin();
        assertEquals(driver.getCurrentUrl(), SAPIENCIA_URL+"/login?redirect=%2Fprojetos-vigentes");
    }

    @Test
    @Order(2)
    public void checkEmptyPassword() {
        sapienciaLoginPage.setUsernameInput("12345678");
        sapienciaLoginPage.sendLogin();
        assertEquals(driver.getCurrentUrl(), SAPIENCIA_URL+"/login?redirect=%2Fprojetos-vigentes");
    }

    @Test
    @Order(3)
    public void checkInvalidData() {
        sapienciaLoginPage.setUsernameInput("12345678");
        sapienciaLoginPage.setPasswordInput("12345678");
        sapienciaLoginPage.sendLogin();
        assertEquals(driver.getCurrentUrl(), SAPIENCIA_URL+"/login?redirect=%2Fprojetos-vigentes");
    }

    @Test
    @Order(4)
    public void checkCorrectLoginUser() throws InterruptedException {
        sapienciaLoginPage.setUsernameInput("junior");
        sapienciaLoginPage.setPasswordInput("password");
        sapienciaLoginPage.sendLogin();
        sleep(2000);
        assertEquals(SAPIENCIA_URL+"/projetos-vigentes", driver.getCurrentUrl());
    }



}
