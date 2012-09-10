package functional.com.thoughtworks.twu;

import functional.com.thoughtworks.twu.utils.Cas;
import functional.com.thoughtworks.twu.utils.Talk;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.thoughtworks.twu.utils.WaitHelper.waitForAjax;
import static org.junit.Assert.assertTrue;

public class ViewListOfUpcomingTalksTest  {

    @Autowired
    public static final int HTTP_PORT = 9191;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";
    private WebDriver webDriver;
    Talk talk;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        talk=new Talk(webDriver);
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        webDriver.get(HTTP_BASE_URL);
        Cas.login(webDriver);
    }

    @Test
    public void shouldNotDisplayFeedbackTextboxForUpcomingTalks() throws InterruptedException {


        DateTime dateTime=DateTime.now().plusDays(3);
        Talk talk=new Talk(webDriver);
        talk.newTalk(UUID.randomUUID().toString(),"Learn Ruby", "Ajanta-Ellora",dateTime.toString("dd/MM/YYYY"),"11:42 AM");
        WebElement upcomingTalksButton =webDriver.findElement(By.id("upcoming_talks_button"));
        upcomingTalksButton.click();
        waitForAjax(webDriver);
        List<WebElement> listOfTalks= webDriver.findElements(By.className("ui-link-inherit"));
        listOfTalks.get(0).click();
        assertTrue(webDriver.findElements(By.id("feedback_text")).isEmpty());
    }

    @After
    public void tearDown() {
        Cas.logout(webDriver);
        webDriver.close();
    }


}
