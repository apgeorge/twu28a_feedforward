package com.thoughtworks.twu.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.thoughtworks.twu.utils.WaitForAjax.WaitForAjax;
import static org.hamcrest.CoreMatchers.is;
import static org.joda.time.DateTime.now;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

public class Talk {
    private String title;
    private String description;
    private String venue;
    private String date;
    private  String time;

    public Talk()
    {
        this.title=now().toString();
        this.description= "Seven wise men";
        this.venue="Ajanta Ellora";
        this.date="$('#datepicker').val('28/09/2012')";
        this.time="$('#timepicker').val('11:42 AM')";
    }

    public Talk(String title, String description, String venue, String date, String time)
    {
        this.title=title;
        this.description= description;
        this.venue=venue;
        this.date="$('#datepicker').val('"+date+"')";
        this.time="$('#timepicker').val('"+time+"')";
    }

    public void newTalk(WebDriver webDriver){
        WebElement myTalksButton = webDriver.findElement(By.id("my_talks_button"));
        myTalksButton.click();
        try {
            WaitForAjax(webDriver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(webDriver.findElement(By.id("new_talk")).isDisplayed());
        webDriver.findElement(By.id("new_talk")).click();
        try {
            WaitForAjax(webDriver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(webDriver.findElement(By.id("title")).isDisplayed());
        webDriver.findElement(By.id("title")).sendKeys(title);
        webDriver.findElement(By.id("description")).sendKeys(description);
        webDriver.findElement(By.id("venue")).sendKeys(venue);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript(date);
        javascriptExecutor.executeScript(time);
        webDriver.findElement(By.id("new_talk_submit")).click();
    }
}
