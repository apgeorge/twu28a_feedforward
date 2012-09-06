package functional.com.thoughtworks.twu;

import com.thoughtworks.twu.utils.CasLoginLogout;
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
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/";
    private WebDriver webDriver;
    private Talk talk;
    private Feedback feedback;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        talk = new Talk();
        feedback = new Feedback();
        webDriver.get(HTTP_BASE_URL);
        CasLoginLogout.login(webDriver);
        talk.newTalk(webDriver);
    }

    @Test
    public void shouldNotBeAbleToSubmitBlankFeedbackOnTalk() throws Exception {
        WebElement myTalksLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("my_talks_button")));
        myTalksLink.click();
        WaitForAjax.WaitForAjax(webDriver);
        WebElement talkLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(talk.getTalkTitle())));
        talkLink.click();
        WaitForAjax.WaitForAjax(webDriver);
        assertTrue(webDriver.getPageSource().contains("Past Feedback"));
        int countInitial = countNoOfFeedbacks();
        WebElement feedbackTextBox = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("feedback_text")));
        feedbackTextBox.sendKeys("");
        WebElement feedbackSubmitButton = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("add_feedback_submit")));
        feedbackSubmitButton.click();
        WaitForAjax.WaitForAjax(webDriver);
        int countNewFeedbacks = countNoOfFeedbacks() - countInitial;
        assertThat(countNewFeedbacks, is(0));
    }

    @Test
    public void shouldBeAbleToEnterFeedbackOnTalk() throws InterruptedException {
        WebElement myTalksLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("my_talks_button")));
        myTalksLink.click();
        WaitForAjax.WaitForAjax(webDriver);
        WebElement talkLink = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(talk.getTalkTitle())));
        talkLink.click();
        WaitForAjax.WaitForAjax(webDriver);
        assertTrue(webDriver.getPageSource().contains("Past Feedback"));
        int countInitial = countNoOfFeedbacks();
        feedback.giveFeedback(webDriver);
        WaitForAjax.WaitForAjax(webDriver);
        int countNewFeedbacks = countNoOfFeedbacks() - countInitial;
        assertThat(countNewFeedbacks, is(1));
        assertTrue(webDriver.getPageSource().contains(feedback.getNowTime()));
    }

    private int countNoOfFeedbacks() {
        List<WebElement> feedbackList = webDriver.findElements(By.className("feedback-item"));
        return feedbackList.size();
    }

    @After
    public void tearDown() {
        CasLoginLogout.logout(webDriver);
        webDriver.close();
    }

}
