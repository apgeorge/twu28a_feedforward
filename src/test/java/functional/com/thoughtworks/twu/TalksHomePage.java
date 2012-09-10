package functional.com.thoughtworks.twu;

import functional.com.thoughtworks.twu.utils.Cas;
import functional.com.thoughtworks.twu.utils.Talk;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.UUID;

public class TalksHomePage {
    public static final int HTTP_PORT = 9191;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";
    private WebDriver webDriver;
    Talk talk;
    String testTitle;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        talk = new Talk(webDriver);
        testTitle = UUID.randomUUID().toString();
        webDriver.get(HTTP_BASE_URL);
        Cas.login(webDriver);
    }

    @Test
    public void shouldBeAbleToCreateNewTalk() throws Exception {
        talk.newTalk(testTitle, "description", "venue", "28/09/2012", "11:42 AM");
        talk.assertCreationSuccess();
    }

    @Test
    public void shouldBeAbleToCreateNewTalkWithoutDescription() throws Exception {
        talk.newTalk(testTitle, "", "venue", "28/09/2012", "11:42 AM");
        talk.assertCreationSuccess();
    }

    @Test
    public void shouldNotBeAbleToCreateNewTalkWithoutTitle() throws Exception {
        talk.newTalk("", "description", "venue", "28/09/2012", "11:42 AM");
        talk.assertCreationFailForElement("title");
    }

    @Test
    public void shouldNotBeAbleToCreateNewTalkWithoutVenue() throws Exception {
        talk.newTalk(testTitle, "description", "", "28/09/2012", "11:42 AM");
        talk.assertCreationFailForElement("venue");
    }

    @Test
    public void shouldNotBeAbleToCreateNewTalkWithoutDate() throws Exception {
        talk.newTalk(testTitle, "description", "Ajanta", "", "11:42 AM");
        talk.assertCreationFailForElement("datepicker");
    }

    @Test
    public void shouldNotBeAbleToCreateNewTalkWithoutTime() throws Exception {
        talk.newTalk(testTitle, "description", "ellora", "16/09/2012", "");
        talk.assertCreationFailForElement("timepicker");
    }

    @After
    public void tearDown() {
        Cas.logout(webDriver);
        webDriver.close();
    }

}
