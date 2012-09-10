package functional.com.thoughtworks.twu;

import com.thoughtworks.twu.utils.WaitHelper;
import functional.com.thoughtworks.twu.utils.Cas;
import functional.com.thoughtworks.twu.utils.EnterFeedback;
import functional.com.thoughtworks.twu.utils.Talk;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class FeedbackFunctionalTest {
    public static final int HTTP_PORT = 9191;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";
    private WebDriver webDriver;
    private EnterFeedback enterFeedback;
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
        enterFeedback = new EnterFeedback();


    }

    @Test
    public void shouldNotBeAbleToSubmitBlankFeedbackOnTalk() throws Exception {

        WaitHelper.waitForAjax(webDriver);
        WebElement talkLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(title)));
        talkLink.click();
        WaitHelper.waitForAjax(webDriver);
        assertTrue(webDriver.getPageSource().contains("Past Feedback"));
        int countInitial = countNoOfFeedbacks();
        WebElement feedbackTextBox = WaitHelper.waitForElement(webDriver,"feedback_text");
        feedbackTextBox.sendKeys("");
        WebElement feedbackSubmitButton = WaitHelper.waitForElement(webDriver, "add_feedback_submit");
        feedbackSubmitButton.click();
        WaitHelper.waitForAjax(webDriver);
        int countNewFeedbacks = countNoOfFeedbacks() - countInitial;
        assertThat(countNewFeedbacks, is(0));
    }

    @Test
    public void shouldBeAbleToEnterFeedbackOnTalk() throws InterruptedException {

        WaitHelper.waitForAjax(webDriver);
        WebElement talkLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(title)));
        talkLink.click();
        WaitHelper.waitForAjax(webDriver);
        assertTrue(webDriver.getPageSource().contains("Past Feedback"));
        int countInitial = countNoOfFeedbacks();
        enterFeedback.giveFeedback(webDriver);
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Past Feedback")));
        int countNewFeedbacks = countNoOfFeedbacks() - countInitial;
        assertThat(countNewFeedbacks, is(1));
        assertTrue(webDriver.getPageSource().contains(enterFeedback.getFeedbackCreationTime()));
    }




    private int countNoOfFeedbacks()
    {
        List<WebElement> feedbackList= webDriver.findElements(By.className("feedback-item"));
        return feedbackList.size();
    }

    @After
    public void tearDown() {
        Cas.logout(webDriver);
        webDriver.close();
    }

}
