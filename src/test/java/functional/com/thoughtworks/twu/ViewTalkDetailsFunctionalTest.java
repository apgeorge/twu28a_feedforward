package functional.com.thoughtworks.twu;

import com.thoughtworks.twu.utils.CasLoginLogout;
import com.thoughtworks.twu.utils.WaitForAjax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class ViewTalkDetailsFunctionalTest {
    public static final int HTTP_PORT = 9191;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get(HTTP_BASE_URL);
        CasLoginLogout.login(webDriver);
    }

    @Test
    public void shouldDisplayTalkDetailsOfCreatedTalk() throws Exception {
        webDriver.findElement(By.id("my_talks_button")).click();
        WaitForAjax.WaitForAjax(webDriver);
        webDriver.findElement(By.id("new_talk")).click();
        WaitForAjax.WaitForAjax(webDriver);
        String testTitle = "title_" + UUID.randomUUID().toString();

        webDriver.findElement(By.id("title")).sendKeys(testTitle);
        webDriver.findElement(By.id("description")).sendKeys("test description");
        webDriver.findElement(By.id("venue")).sendKeys("test venue");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("$('#datepicker').val('06/09/2012')");
        javascriptExecutor.executeScript("$('#timepicker').val('10:00 AM')");
        javascriptExecutor.executeScript("$('#new_talk_submit').click()");
        WaitForAjax.WaitForAjax(webDriver);
        webDriver.findElement(By.linkText(testTitle)).click();
        assertTrue(webDriver.getPageSource().contains(testTitle));
        assertTrue(webDriver.getPageSource().contains("test.twu"));
        assertTrue(webDriver.getPageSource().contains("test description"));
        assertTrue(webDriver.getPageSource().contains("test venue"));
        assertTrue(webDriver.getPageSource().contains("06/09/2012"));
        assertTrue(webDriver.getPageSource().contains("10:00 AM"));
    }

    @After
    public void tearDown() {
        CasLoginLogout.logout(webDriver);
        webDriver.close();
    }


}
