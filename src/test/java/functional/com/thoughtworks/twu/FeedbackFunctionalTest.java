package functional.com.thoughtworks.twu;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.persistence.PresentationMapper;
import com.thoughtworks.twu.persistence.TalkMapper;
import com.thoughtworks.twu.service.TalkService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.mockito.Mockito.mock;

public class FeedbackFunctionalTest {
    public static final int HTTP_PORT = 9091;
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

    @After
    public void tearDown() {
        webDriver.close();
    }

}
