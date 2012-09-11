package functional.com.thoughtworks.twu;

import functional.com.thoughtworks.twu.utils.BaseFunctionalTest;
import functional.com.thoughtworks.twu.utils.Talk;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class TalksHomePage extends BaseFunctionalTest {
    Talk talk;
    String testTitle;

    @BeforeMethod
    public void setUp() {
        talk = new Talk(webDriver);
        testTitle = UUID.randomUUID().toString();
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


}
