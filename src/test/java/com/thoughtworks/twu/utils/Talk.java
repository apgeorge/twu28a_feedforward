package com.thoughtworks.twu.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.UUID;

import static com.thoughtworks.twu.utils.WaitForAjax.WaitForAjax;
import static org.hamcrest.CoreMatchers.is;
import static org.joda.time.DateTime.now;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

public class Talk {

    public String getTalkTitle() {
        return talkTitle;
    }

    private String talkTitle;

    public void newTalk(WebDriver webDriver){
        WebElement myTalksButton = webDriver.findElement(By.id("my_talks_button"));
        myTalksButton.click();
        try {
            WaitForAjax(webDriver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement new_talk = webDriver.findElement(By.id("new_talk"));
        assertTrue(new_talk.isDisplayed());
        WebElement newTalkButton = new_talk;
        newTalkButton.click();
        try {
            WaitForAjax(webDriver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement title = webDriver.findElement(By.id("title"));
        assertTrue(title.isDisplayed());
        talkTitle = UUID.randomUUID().toString();
        title.sendKeys(talkTitle);
        WebElement description = webDriver.findElement(By.id("description"));
        description.sendKeys("Seven wise men");
        WebElement venue = webDriver.findElement(By.id("venue"));
        venue.sendKeys("Ajanta Ellora");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("$('#datepicker').val('28/09/2012')");
        javascriptExecutor.executeScript("$('#timepicker').val('11:42 AM')");
        javascriptExecutor.executeScript("$('#new_talk_submit').click()");


    }
}
