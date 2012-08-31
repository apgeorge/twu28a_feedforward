package functional.com.thoughtworks.twu;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.persistence.PresentationMapper;
import com.thoughtworks.twu.persistence.TalkMapper;
import com.thoughtworks.twu.service.TalkService;
import com.thoughtworks.twu.utils.WaitForAjax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

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

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        mockTalkMapper = mock(TalkMapper.class);
        mockPresentationMapper = mock(PresentationMapper.class);
        talkService = new TalkService(mockTalkMapper, mockPresentationMapper);
        Presentation presentation = new Presentation("test title", "test description", "test presenter");
        talkService.createTalkWithNewPresentation(presentation, "venue", "date", "time");
        webDriver.get(HTTP_BASE_URL);

    }

    @Test
    public void shouldBeAbleToEnterFeedbackOnTalk() throws InterruptedException {
        WebElement talkLink = webDriver.findElement(By.linkText("Test Talk"));
        talkLink.click();
        WaitForAjax.WaitForAjax(webDriver);
        assertTrue(webDriver.getPageSource().contains("Past Feedback"));
        int countInitial= countNoOfFeedbacks();
        WebElement feedbackTextBox = webDriver.findElement(By.id("feedback_text"));
        feedbackTextBox.sendKeys("New Feedback");
        WebElement feedbackSubmitButton= webDriver.findElement(By.id("add_feedback_submit"));
        feedbackSubmitButton.click();
        WaitForAjax.WaitForAjax(webDriver);
        int countNewFeedbacks=countNoOfFeedbacks()-countInitial;
        assertThat(countNewFeedbacks, is(1));
    }

    private int countNoOfFeedbacks()
    {
        List<WebElement> feedbackList= webDriver.findElements(By.className("feedback-item"));
        return feedbackList.size();
    }

    @After
    public void tearDown() {
        webDriver.close();
    }

}
