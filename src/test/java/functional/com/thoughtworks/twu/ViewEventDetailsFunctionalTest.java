package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ViewEventDetailsFunctionalTest {

    public static final int HTTP_PORT = 9091;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";

    private FirefoxDriver driver;
    private WebDriverWait wait;

    @After
    public void tearDown() throws Exception {
        driver.close();
    }

    @Before
    public void setUp() {

        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void shouldDisplayMessageIfNoEventIsPresent() {
        //Given
        driver.get(HTTP_BASE_URL);
        //When
        WebElement myTalksButton = driver.findElement(By.id("my_talks_button"));
        myTalksButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("1")));
        WebElement talksButton = driver.findElement(By.id("1"));
        talksButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("noeventmessage")));
        WebElement text = driver.findElement(By.id("noeventmessage"));
        //Then
        assertThat(text.getText(), is("There are no talks at this moment."));
    }


}
