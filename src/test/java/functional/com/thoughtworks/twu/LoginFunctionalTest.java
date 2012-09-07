package functional.com.thoughtworks.twu;

import com.thoughtworks.twu.utils.Cas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class LoginFunctionalTest {
    public static final int HTTP_PORT = 9191;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/";
    private WebDriver webDriver;


    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get(HTTP_BASE_URL);
    }

    @Test
    public void ShouldLoginTestUser() throws Exception {
        Cas.login(webDriver);
        assertTrue(webDriver.getPageSource().contains("Welcome test.twu!"));
    }

    @After
    public void tearDown() {
        Cas.logout(this.webDriver);
        webDriver.close();
    }

}
