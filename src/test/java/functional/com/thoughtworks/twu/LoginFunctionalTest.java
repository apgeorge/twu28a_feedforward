package functional.com.thoughtworks.twu;

import functional.com.thoughtworks.twu.utils.BaseFunctionalTest;
import functional.com.thoughtworks.twu.utils.Cas;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginFunctionalTest {
    public static final String HTTP_BASE_URL = "http://localhost:" + BaseFunctionalTest.HTTP_PORT + "/twu/";
    private WebDriver webDriver;
    private String passwordErrorMessage;
    private String usernameErrorMessage;


    @BeforeMethod
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get(HTTP_BASE_URL);
        passwordErrorMessage = "Password is a required field.";
        usernameErrorMessage = "Username is a required field.";
    }

    @Test
    public void ShouldLoginTestUser() throws Exception {
        Cas.login(webDriver);
        assertTrue(webDriver.getPageSource().contains("Welcome test.twu!"));
        Cas.logout(this.webDriver);
    }

   @Test
    public void ShouldNotLoginTestUserWithNoUsername() throws Exception {
        webDriver.findElement(By.id("username")).sendKeys("");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
        WebElement text = webDriver.findElement(By.id("msg"));
        assertThat(text.getText(), is(usernameErrorMessage));
    }
    @Test
    public void ShouldNotLoginTestUserWithNoPassword() throws Exception {
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("");
        webDriver.findElement(By.className("btn-submit")).click();
        WebElement text = webDriver.findElement(By.id("msg"));
        assertThat(text.getText(), is(passwordErrorMessage));
    }

    @AfterMethod
    public void tearDown() {
        webDriver.close();
    }

}
