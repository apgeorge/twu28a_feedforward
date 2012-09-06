package functional.com.thoughtworks.twu;

import com.thoughtworks.twu.persistence.PresentationMapper;
import com.thoughtworks.twu.persistence.TalkMapper;
import com.thoughtworks.twu.service.TalkService;
import com.thoughtworks.twu.utils.CasLoginLogout;
import com.thoughtworks.twu.utils.Talk;
import com.thoughtworks.twu.utils.WaitForAjax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.joda.time.DateTime.now;
import static org.junit.Assert.assertTrue;

public class DisplayTalkDetailsFunctionalTest {
    public static final int HTTP_PORT = 9091;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";
    private WebDriver webDriver;
    private TalkMapper mockTalkMapper;
    private PresentationMapper mockPresentationMapper;
    private TalkService talkService;
    private Talk talk;

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
        String testTitle = now().toString();
        webDriver.findElement(By.id("title")).sendKeys(testTitle);
        webDriver.findElement(By.id("description")).sendKeys("test description");
        webDriver.findElement(By.id("venue")).sendKeys("test venue");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("$('#datepicker').val('28/09/2012')");
        javascriptExecutor.executeScript("$('#timepicker').val('11:42 AM')");
        javascriptExecutor.executeScript("$('#new_talk_submit').click()");
        WaitForAjax.WaitForAjax(webDriver);
        webDriver.findElement(By.id("talk_details")).click();
        assertTrue(webDriver.getPageSource().contains(testTitle));
//        assertTrue(webDriver.getPageSource().contains("test description"));
//        assertTrue(webDriver.getPageSource().contains("test venue"));
//        assertTrue(webDriver.getPageSource().contains("28/09/2012"));
//        assertTrue(webDriver.getPageSource().contains("11:42 AM"));


    }

    @After
    public void tearDown() {
        CasLoginLogout.logout(webDriver);
        webDriver.close();
    }


}
