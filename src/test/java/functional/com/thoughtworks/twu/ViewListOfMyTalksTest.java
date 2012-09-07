package functional.com.thoughtworks.twu;

import functional.com.thoughtworks.twu.utils.Cas;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.matchers.StringContains;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.UUID;

import static com.thoughtworks.twu.utils.WaitHelper.waitForElement;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ViewListOfMyTalksTest {
    public static final int HTTP_PORT = 9091;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get(HTTP_BASE_URL);
        Cas.login(webDriver);
    }

    @Test
    public void shouldDisplayListOfTalksOnlyByLoggedInUser() throws Exception {
        waitForElement(webDriver,"my_talks_button").click();
        waitForElement(webDriver,"new_talk").click();

        String testTitle = "title_" + UUID.randomUUID().toString();
        waitForElement(webDriver, "title").sendKeys(testTitle);
        waitForElement(webDriver, "description").sendKeys("Seven wise men");
        waitForElement(webDriver, "venue").sendKeys("Ajanta Ellora");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("$('#datepicker').val('28/09/2012')");
        javascriptExecutor.executeScript("$('#timepicker').val('11:42 AM')");
        javascriptExecutor.executeScript("$('#new_talk_submit').click()");

        waitForElement(webDriver, "message_box_success");

        waitForElement(webDriver,"new_talk").click();

        String secondTestTitle = "title_" + UUID.randomUUID().toString();
        waitForElement(webDriver, "title").sendKeys(secondTestTitle);
        waitForElement(webDriver, "description").sendKeys("second description");
        waitForElement(webDriver, "venue").sendKeys("second venue");
        javascriptExecutor.executeScript("$('#datepicker').val('28/09/2012')");
        javascriptExecutor.executeScript("$('#timepicker').val('11:45 AM')");
        javascriptExecutor.executeScript("$('#new_talk_submit').click()");

        waitForElement(webDriver, "message_box_success");

        waitForElement(webDriver,"my_talks_list");

        webDriver.findElement(By.linkText(testTitle)).click();

        waitForElement(webDriver,"talk_details");

        assertTrue(webDriver.getPageSource().contains("test.twu"));
        assertThat(webDriver.findElement(By.xpath("//div[@id='talk_details']//span[@class='ui-btn-text']")).getText(), StringContains.containsString("test.twu"));

        webDriver.findElement(By.id("my_talks_button")).click();

        waitForElement(webDriver,"my_talks_list");

        webDriver.findElement(By.linkText(secondTestTitle)).click();

        waitForElement(webDriver,"talk_details");

        assertTrue(webDriver.getPageSource().contains("test.twu"));

    }
}
