package functional.com.thoughtworks.twu;

import functional.com.thoughtworks.twu.utils.Cas;
import functional.com.thoughtworks.twu.utils.FeedbacksPage;
import functional.com.thoughtworks.twu.utils.Talk;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.matchers.StringContains;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

import static com.thoughtworks.twu.utils.WaitHelper.waitForElement;
import static org.hamcrest.CoreMatchers.is;
import static org.joda.time.DateTime.now;
import static org.junit.Assert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class FeedbackFunctionalTest {
    public static final int HTTP_PORT = 9191;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";
    private WebDriver webDriver;
    private FeedbacksPage feedbacksPage;
    private Talk talk;

    String title;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get(HTTP_BASE_URL);
        Cas.login(webDriver);
        talk = new Talk(webDriver);
        title = UUID.randomUUID().toString();
        talk.newTalk(title,"Seven wise men","Ajanta Ellora","28/07/2012","11:42 AM");
        talk.assertCreationSuccess();
        feedbacksPage = new FeedbacksPage(webDriver);


    }

    @Test
    public void shouldNotBeAbleToSubmitBlankFeedbackOnTalk() throws Exception {

        WebElement talkLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(title)));
        talkLink.click();
        waitForElement(webDriver,"start_of_feedback_list");
        assertTrue(webDriver.getPageSource().contains("Feedback"));
        int countInitial = feedbacksPage.countNoOfFeedbacks();
        feedbacksPage.submitFeedback("");
        waitForElement(webDriver, "start_of_feedback_list");
        int countNewFeedbacks = feedbacksPage.countNoOfFeedbacks() - countInitial;
        assertThat(countNewFeedbacks, is(0));
    }

    @Test
    public void shouldBeAbleToEnterFeedbackOnTalk() throws InterruptedException {

        WebElement talkLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(title)));
        talkLink.click();
        waitForElement(webDriver,"start_of_feedback_list");
        assertTrue(webDriver.getPageSource().contains("Feedback"));
        int countInitial = feedbacksPage.countNoOfFeedbacks();
        String feedbackCreationTime = now().toString();
        feedbacksPage.submitFeedback(feedbackCreationTime);
        int countNewFeedbacks = feedbacksPage.countNoOfFeedbacks() - countInitial;
        assertThat(countNewFeedbacks, is(1));
        assertThat(webDriver.getPageSource(), StringContains.containsString(feedbackCreationTime));
    }

    @After
    public void tearDown() {
        Cas.logout(webDriver);
        webDriver.close();
    }

}
