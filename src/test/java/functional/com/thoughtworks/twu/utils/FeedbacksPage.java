package functional.com.thoughtworks.twu.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.thoughtworks.twu.utils.WaitHelper.waitForElement;
import static org.joda.time.DateTime.now;

public class FeedbacksPage {

    private String feedbackCreationTime;
    private WebDriver webDriver;

    public FeedbacksPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getFeedbackCreationTime() {
        return feedbackCreationTime;
    }

    public void submitFeedback(String feedbackComment) {
        WebElement feedbackTextBox = webDriver.findElement(By.id("feedback_text"));
        feedbackTextBox.sendKeys(feedbackComment);
        WebElement feedbackSubmitButton= webDriver.findElement(By.id("add_feedback_submit"));
        feedbackSubmitButton.click();
        waitForElement(webDriver,"start_of_feedback_list");
    }

    public int countNoOfFeedbacks()
    {
        waitForElement(webDriver,"list_of_feedbacks");
        List<WebElement> feedbackList= webDriver.findElements(By.className("feedback-item"));
        return feedbackList.size();
    }
}
