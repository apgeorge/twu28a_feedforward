package functional.com.thoughtworks.twu;

import functional.com.thoughtworks.twu.utils.Cas;
import functional.com.thoughtworks.twu.utils.Talk;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.UUID;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ViewTalkDetailsFunctionalTest {
    public static final int HTTP_PORT = 9191;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";
    private WebDriver webDriver;
    Talk talk;


    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get(HTTP_BASE_URL);
        Cas.login(webDriver);
        talk=new Talk(webDriver);
    }

    @Test
    public void shouldDisplayTalkDetailsOfCreatedTalk() throws Exception {
        String testTitle = "title_" + UUID.randomUUID().toString();
        talk.newTalk(testTitle, "test description", "test venue", "06/09/2012", "10:00 AM");
        talk.assertCreationSuccess();
        talk.clickTitle(testTitle);

        talk.assertHeaderMatch(testTitle, "test.twu");

        talk.expandDetails();

        talk.assertDetailsMatch("test description", "test venue", "06/09/2012", "10:00 AM", "test.twu@thoughtworks.com");
    }

    @After
    public void tearDown() {
        Cas.logout(webDriver);
        webDriver.close();
    }


}
