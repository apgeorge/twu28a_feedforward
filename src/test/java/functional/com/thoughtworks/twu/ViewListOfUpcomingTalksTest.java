package functional.com.thoughtworks.twu;

import functional.com.thoughtworks.twu.utils.BaseFunctionalTest;
import functional.com.thoughtworks.twu.utils.Talk;
import org.joda.time.DateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.thoughtworks.twu.utils.WaitHelper.waitForElement;
import static org.junit.Assert.assertTrue;

public class ViewListOfUpcomingTalksTest extends BaseFunctionalTest {

    Talk talk;

    @BeforeMethod
    public void setUp() {
        talk = new Talk(webDriver);
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void shouldNotDisplayFeedbackTextboxForUpcomingTalks() throws InterruptedException {
        DateTime dateTime = DateTime.now().plusDays(3);
        Talk talk = new Talk(webDriver);
        talk.newTalk(UUID.randomUUID().toString(), "Learn Ruby", "Ajanta-Ellora", dateTime.toString("dd/MM/YYYY"), "11:42 AM");
        talk.assertCreationSuccess();
        WebElement upcomingTalksButton = webDriver.findElement(By.id("upcoming_talks_button"));
        upcomingTalksButton.click();
        waitForElement(webDriver, "talks_container");
        List<WebElement> listOfTalks = webDriver.findElements(By.className("ui-link-inherit"));
        listOfTalks.get(0).click();
        assertTrue(webDriver.findElements(By.id("feedback_text")).isEmpty());
    }


}
