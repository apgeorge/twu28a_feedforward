package functional.com.thoughtworks.twu;

import functional.com.thoughtworks.twu.utils.BaseFunctionalTest;
import functional.com.thoughtworks.twu.utils.EnterFeedback;
import functional.com.thoughtworks.twu.utils.Talk;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static com.thoughtworks.twu.utils.WaitHelper.waitForElement;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class FeedbackFunctionalTest extends BaseFunctionalTest {
    private EnterFeedback enterFeedback;
    private Talk talk;
    String title;

    @BeforeMethod
    public void setUp() {
        talk = new Talk(webDriver);
        title = UUID.randomUUID().toString();
        talk.newTalk(title, "Seven wise men", "Ajanta Ellora", "28/07/2012", "11:42 AM");
        talk.assertCreationSuccess();
        enterFeedback = new EnterFeedback();
    }

    @Test
    public void shouldNotBeAbleToSubmitBlankFeedbackOnTalk() throws Exception {
        WebElement talkLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(title)));
        talkLink.click();
        waitForElement(webDriver, "feedback_container");
        assertTrue(webDriver.getPageSource().contains("Past Feedback"));
        int countInitial = countNoOfFeedbacks();
        WebElement feedbackTextBox = waitForElement(webDriver, "feedback_text");
        feedbackTextBox.sendKeys("");
        WebElement feedbackSubmitButton = waitForElement(webDriver, "add_feedback_submit");
        feedbackSubmitButton.click();
        int countNewFeedbacks = countNoOfFeedbacks() - countInitial;
        assertThat(countNewFeedbacks, is(0));
    }

    @Test
    public void shouldBeAbleToEnterFeedbackOnTalk() throws InterruptedException {

        WebElement talkLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(title)));
        talkLink.click();
        waitForElement(webDriver, "feedback_container");
        assertTrue(webDriver.getPageSource().contains("Past Feedback"));
        int countInitial = countNoOfFeedbacks();
        enterFeedback.giveFeedback(webDriver);
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Past Feedback")));
        int countNewFeedbacks = countNoOfFeedbacks() - countInitial;
        assertThat(countNewFeedbacks, is(1));
        assertTrue(webDriver.getPageSource().contains(enterFeedback.getFeedbackCreationTime()));
    }


    private int countNoOfFeedbacks() {
        List<WebElement> feedbackList = webDriver.findElements(By.className("feedback-item"));
        return feedbackList.size();
    }
}
