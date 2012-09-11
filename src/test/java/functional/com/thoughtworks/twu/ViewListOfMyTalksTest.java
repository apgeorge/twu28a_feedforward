package functional.com.thoughtworks.twu;

import functional.com.thoughtworks.twu.utils.BaseFunctionalTest;
import functional.com.thoughtworks.twu.utils.Talk;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class ViewListOfMyTalksTest extends BaseFunctionalTest {
    public static final String TEST_USERNAME = "test.twu";
    Talk talk;

    @BeforeMethod
    public void setUp() {
        talk=new Talk(webDriver);
    }

    @Test
    public void shouldDisplayListOfTalksOnlyByLoggedInUser() throws Exception {

        String testTitle = "Title_" + UUID.randomUUID().toString();
        talk.newTalk(testTitle,"Seven wise men","Ajanta Ellora","28/09/2012","11:42 AM");
        talk.assertCreationSuccess();

        String secondTestTitle = "Title_" + UUID.randomUUID().toString();
        talk.newTalk(secondTestTitle,"second description","second venue","28/09/2012","11:45 AM");
        talk.assertCreationSuccess();

        talk.loadTalkDetails(testTitle);

        talk.assertHeaderMatch(testTitle,TEST_USERNAME);

        talk.loadTalkDetails(secondTestTitle);

        talk.assertHeaderMatch(secondTestTitle,TEST_USERNAME);

    }

}
