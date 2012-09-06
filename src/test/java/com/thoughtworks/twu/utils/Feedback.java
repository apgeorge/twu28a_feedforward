package com.thoughtworks.twu.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.joda.time.DateTime.now;

public class Feedback {

    private String nowTime;

    public String getNowTime() {
        return nowTime;
    }

    public void giveFeedback(WebDriver webDriver) {
        WebElement feedbackTextBox = webDriver.findElement(By.id("feedback_text"));
        nowTime = now().toString();
        feedbackTextBox.sendKeys(nowTime);
        WebElement feedbackSubmitButton= webDriver.findElement(By.id("add_feedback_submit"));
        feedbackSubmitButton.click();
    }
}
