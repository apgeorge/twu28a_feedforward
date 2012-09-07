package functional.com.thoughtworks.twu.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.joda.time.DateTime.now;

public class EnterFeedback {

    private String feedbackCreationTime;

    public String getFeedbackCreationTime() {
        return feedbackCreationTime;
    }

    public void giveFeedback(WebDriver webDriver) {
        WebElement feedbackTextBox = webDriver.findElement(By.id("feedback_text"));
        feedbackCreationTime = now().toString();
        feedbackTextBox.sendKeys(feedbackCreationTime);
        WebElement feedbackSubmitButton= webDriver.findElement(By.id("add_feedback_submit"));
        feedbackSubmitButton.click();
    }
}
