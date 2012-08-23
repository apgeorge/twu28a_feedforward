package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AjaxTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
    }

    @Test
    public void shouldShowTryMeLink() {

        webDriver.get("http://hackorama.com/ajax");
        WebElement link = webDriver.findElement(By.tagName("button"));
        link.click();

        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("msg_display"), "Server got the message"));
        WebElement element = webDriver.findElement(By.id("msg_display"));
        assertThat(element.getText().contains("Server got the message"), is(true));

    }

    @After
    public void tearDown() {
        webDriver.close();
    }


}