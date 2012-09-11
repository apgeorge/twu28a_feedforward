package functional.com.thoughtworks.twu;

import com.thoughtworks.twu.utils.WaitHelper;
import functional.com.thoughtworks.twu.utils.BaseFunctionalTest;
import functional.com.thoughtworks.twu.utils.Talk;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ViewRecentTalksTest extends BaseFunctionalTest {

    Talk talk;

    @BeforeMethod
    public void setUp() {
        talk = new Talk(webDriver);
    }

    @Test
    public void shouldDisplayPastTalkWithinTwoDays() throws InterruptedException {
        DateTime dateTime = DateTime.now().minusDays(1);
        String title1 = "Title_" + UUID.randomUUID().toString();
        talk.newTalk(title1, "1 day back", "here", dateTime.toString("dd/MM/YYYY"), "11:00 AM");
        talk.assertCreationSuccess();

        dateTime = DateTime.now().minusDays(3);
        String title2 = "Title_" + UUID.randomUUID().toString();
        Talk pastTalkNotWithinTwoDays = new Talk(webDriver);
        pastTalkNotWithinTwoDays.newTalk(title2, "3 days back", "somewhere", dateTime.toString("dd/MM/YYYY"), "01:00 PM");
        talk.assertCreationSuccess();

        dateTime = DateTime.now().plusDays(1);
        String title3 = "Title_" + UUID.randomUUID().toString();
        Talk upcomingTalk = new Talk(webDriver);
        upcomingTalk.newTalk(title3, "1 day later", "here", dateTime.toString("dd/MM/YYYY"), "03:00 PM");
        talk.assertCreationSuccess();

        WebElement recentTalksButton = webDriver.findElement(By.id("talks_button"));
        recentTalksButton.click();
        WaitHelper.waitForElement(webDriver, "talks_container");
        WebElement talkContainer = webDriver.findElement(By.id("talks_container"));
        String talkContainerText = talkContainer.getText();
        assertTrue(talkContainerText.contains(title1));
        assertFalse(talkContainerText.contains(title2));
        assertFalse(talkContainerText.contains(title3));
    }

}

