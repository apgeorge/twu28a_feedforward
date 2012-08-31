package functional.com.thoughtworks.twu;

import com.thoughtworks.twu.utils.CasLoginLogout;
import com.thoughtworks.twu.utils.WaitForAjax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.joda.time.DateTime.now;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

public class TalksHomePage {
    public static final int HTTP_PORT = 9191;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";
    private WebDriver webDriver;
    private String failMessage;
    private String successMessage;


    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        webDriver.get(HTTP_BASE_URL);
        failMessage = "Please Supply Valid Entries For All Fields";
        successMessage="New Talk Successfully Created";
        CasLoginLogout.login(webDriver);


    }

    @Test
    public void shouldBeAbleToCreateNewTalk() throws Exception {
        WebElement myTalksButton = webDriver.findElement(By.id("my_talks_button"));
        myTalksButton.click();
        assertTrue(webDriver.findElement(By.id("new_talk")).isDisplayed());
        webDriver.findElement(By.id("new_talk")).click();
        assertTrue(webDriver.findElement(By.id("title")).isDisplayed());
        webDriver.findElement(By.id("title")).sendKeys(now().toString());
        webDriver.findElement(By.id("description")).sendKeys("Seven wise men");
        webDriver.findElement(By.id("venue")).sendKeys("Ajanta Ellora");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("$('#datepicker').val('8/28/2012')");
        javascriptExecutor.executeScript("$('#timepicker').val('11:42 AM')");
        javascriptExecutor.executeScript("$('#new_talk_submit').click()");
        WaitForAjax.WaitForAjax(webDriver);
        assertTrue(webDriver.getPageSource().contains(successMessage));
    }

    @Test
    public void shouldNotBeAbleToCreateNewTalkWithoutTitle() throws Exception {
        WebElement myTalksButton = webDriver.findElement(By.id("my_talks_button"));
        myTalksButton.click();
        webDriver.findElement(By.id("new_talk")).click();
        webDriver.findElement(By.id("description")).sendKeys("Seven wise men");
        webDriver.findElement(By.id("venue")).sendKeys("Ajanta Ellora");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("$('#datepicker').val('8/28/2012')");
        javascriptExecutor.executeScript("$('#timepicker').val('11:42 AM')");
        javascriptExecutor.executeScript("$('#new_talk_submit').click()");
        WaitForAjax.WaitForAjax(webDriver);
        WebElement text = webDriver.findElement(By.id("message_box_error"));
        assertThat(text.getText(), is(failMessage));
    }
    @Test
    public void shouldNotBeAbleToCreateNewTalkWithoutVenue() throws Exception {
        WebElement myTalksButton = webDriver.findElement(By.id("my_talks_button"));
        myTalksButton.click();
        webDriver.findElement(By.id("new_talk")).click();
        webDriver.findElement(By.id("title")).sendKeys(now().toString());
        webDriver.findElement(By.id("description")).sendKeys("Seven wise men");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("$('#datepicker').val('8/28/2012')");
        javascriptExecutor.executeScript("$('#timepicker').val('11:42 AM')");
        javascriptExecutor.executeScript("$('#new_talk_submit').click()");
        WaitForAjax.WaitForAjax(webDriver);
        WebElement text = webDriver.findElement(By.id("message_box_error"));
        assertThat(text.getText(), is(failMessage));
    }
    @Test
    public void shouldNotBeAbleToCreateNewTalkWithoutDate() throws Exception {
        WebElement myTalksButton = webDriver.findElement(By.id("my_talks_button"));
        myTalksButton.click();
        webDriver.findElement(By.id("new_talk")).click();
        webDriver.findElement(By.id("title")).sendKeys(now().toString());
        webDriver.findElement(By.id("description")).sendKeys("Seven wise men");
        webDriver.findElement(By.id("venue")).sendKeys("Ajanta Ellora");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("$('#timepicker').val('11:42 AM')");
        javascriptExecutor.executeScript("$('#new_talk_submit').click()");
        WaitForAjax.WaitForAjax(webDriver);
        WebElement text = webDriver.findElement(By.id("message_box_error"));
        assertThat(text.getText(), is(failMessage));
    }
    @Test
    public void shouldNotBeAbleToCreateNewTalkWithoutTime() throws Exception {
        WebElement myTalksButton = webDriver.findElement(By.id("my_talks_button"));
        myTalksButton.click();
        webDriver.findElement(By.id("new_talk")).click();
        webDriver.findElement(By.id("title")).sendKeys(now().toString());
        webDriver.findElement(By.id("description")).sendKeys("Seven wise men");
        webDriver.findElement(By.id("venue")).sendKeys("Ajanta Ellora");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("$('#datepicker').val('8/28/2012')");
        javascriptExecutor.executeScript("$('#new_talk_submit').click()");
        WaitForAjax.WaitForAjax(webDriver);
        WebElement text = webDriver.findElement(By.id("message_box_error"));
        assertThat(text.getText(), is(failMessage));
    }


    @After
    public void tearDown() {
        CasLoginLogout.logout(webDriver);
        webDriver.close();
    }
}
