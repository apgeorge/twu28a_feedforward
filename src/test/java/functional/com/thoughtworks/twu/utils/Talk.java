package functional.com.thoughtworks.twu.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static com.thoughtworks.twu.utils.WaitHelper.waitForElement;

public class Talk {
    private WebDriver webDriver;

    public Talk(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void newTalk(String title, String description, String venue, String date, String time){

        waitForElement(webDriver,"my_talks_button").click();
        waitForElement(webDriver,"new_talk").click();

        waitForElement(webDriver, "title").sendKeys(title);
        waitForElement(webDriver, "description").sendKeys(description);
        waitForElement(webDriver, "venue").sendKeys(venue);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("$('#datepicker').val('"+date+"')");
        javascriptExecutor.executeScript("$('#timepicker').val('"+time+"')");
        javascriptExecutor.executeScript("$('#new_talk_submit').click()");
        waitForElement(webDriver, "message_box_success");
    }

    public void loadTalkDetails(String testTitle) {
        waitForElement(webDriver,"my_talks_button").click();

        waitForElement(webDriver, "my_talks_list");

        webDriver.findElement(By.linkText(testTitle)).click();

        waitForElement(webDriver,"talk_details");
    }

    public String getHeaderDescription() {
        return webDriver.findElement(By.xpath("//div[@id='talk_details']//span[@class='ui-btn-text']")).getText();
    }
}
