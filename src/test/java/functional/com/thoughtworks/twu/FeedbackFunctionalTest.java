package functional.com.thoughtworks.twu;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.persistence.PresentationMapper;
import com.thoughtworks.twu.persistence.TalkMapper;
import com.thoughtworks.twu.service.TalkService;
import com.thoughtworks.twu.utils.Cas;
import com.thoughtworks.twu.utils.Feedback;
import com.thoughtworks.twu.utils.Talk;
import com.thoughtworks.twu.utils.WaitForAjax;
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
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.testng.AssertJUnit.assertTrue;

public class FeedbackFunctionalTest {
    public static final int HTTP_PORT = 9191;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";
    private WebDriver webDriver;
    private TalkMapper mockTalkMapper;
    private PresentationMapper mockPresentationMapper;
    private TalkService talkService;
    private Feedback feedback;
    private Talk talk;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get(HTTP_BASE_URL);
        Cas.login(webDriver);
        talk = new Talk();
        talk.newTalk(webDriver);
        feedback = new Feedback();


    }

    @Test
    public void shouldNotBeAbleToSubmitBlankFeedbackOnTalk() throws Exception {

        WaitForAjax.WaitForAjax(webDriver);
        WebElement talkLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(talk.getTalkTitle())));
        talkLink.click();
        WaitForAjax.WaitForAjax(webDriver);
        assertTrue(webDriver.getPageSource().contains("Past Feedback"));
        int countInitial = countNoOfFeedbacks();
        WebElement feedbackTextBox = WaitForAjax.waitForElement(webDriver,"feedback_text");
        feedbackTextBox.sendKeys("");
        WebElement feedbackSubmitButton = WaitForAjax.waitForElement(webDriver, "add_feedback_submit");
        feedbackSubmitButton.click();
        WaitForAjax.WaitForAjax(webDriver);
        int countNewFeedbacks = countNoOfFeedbacks() - countInitial;
        assertThat(countNewFeedbacks, is(0));
    }

    @Test
    public void shouldBeAbleToEnterFeedbackOnTalk() throws InterruptedException {

        WaitForAjax.WaitForAjax(webDriver);
        WebElement talkLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(talk.getTalkTitle())));
        talkLink.click();
        WaitForAjax.WaitForAjax(webDriver);
        assertTrue(webDriver.getPageSource().contains("Past Feedback"));
        int countInitial = countNoOfFeedbacks();
        feedback.giveFeedback(webDriver);
        int countNewFeedbacks = countNoOfFeedbacks() - countInitial;
        assertThat(countNewFeedbacks, is(1));
        assertTrue(webDriver.getPageSource().contains(feedback.getNowTime()));
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
