package functional.com.thoughtworks.twu.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class Feedback {

    public void giveFeedback(WebDriver webDriver) {
        WebElement feedbackTextBox = webDriver.findElement(By.id("feedback_text"));
        feedbackTextBox.sendKeys("New Feedback \n next line");
        WebElement feedbackSubmitButton= webDriver.findElement(By.id("add_feedback_submit"));
        feedbackSubmitButton.click();
    }
}