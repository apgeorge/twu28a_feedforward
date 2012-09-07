package functional.com.thoughtworks.twu;

import functional.com.thoughtworks.twu.utils.Cas;
import functional.com.thoughtworks.twu.utils.Talk;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.thoughtworks.twu.utils.WaitHelper.waitForElement;
import static org.joda.time.DateTime.now;

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
    (expected=NoSuchElementException.class)
    public void shouldNotDisplayFeedbackTextboxForUpcomingTalks(){
//        String date= DateTime.now().plusDays(3).toDate().toString();
//        Talk talk=new Talk("RubyConf","Learn Ruby", "Ajanta-Ellora",date,"11:42 AM");
//        talk.newTalk(webDriver);
//
        String date= DateTime.now().plusDays(3).toString("dd/MM/YYYY");
        talk.newTalk(now().toString(), "Seven wise men", "Ajanta Ellora", date, "11:42 AM");

        WebElement upcomingTalksButton =webDriver.findElement(By.id("upcoming_talks_button"));
        upcomingTalksButton.click();
        waitForElement(webDriver,"");
        List<WebElement> listOfTalks= webDriver.findElements(By.className("ui-link-inherit"));
        listOfTalks.get(0).click();
        webDriver.findElement(By.id("feedback_text"));
    }


    @After
    public void tearDown() {
        Cas.logout(webDriver);
        webDriver.close();
    }


}
