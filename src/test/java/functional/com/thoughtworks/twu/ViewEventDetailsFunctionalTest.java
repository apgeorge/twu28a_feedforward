package functional.com.thoughtworks.twu;

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

    public static final int HTTP_PORT = 9191;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";
    private FirefoxDriver driver;

    @Before
    public void setUp() {

        driver = new FirefoxDriver();
    }

    @Test
    public void shouldDisplayMessageIfNoEventIsPresent() {
        //Given
        String s = "http://10.10.15.134:8080/bacon/home.html";
        driver.get(HTTP_BASE_URL);
        //When
        WebElement myTalksButton = driver.findElement(By.id("my_talks_button"));
        myTalksButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("1")));
        WebElement talksButton = driver.findElement(By.id("1"));
        talksButton.click();
        WebElement text = driver.findElement(By.className("ui-btn-text"));
        //Then
        assertThat(text.getText(), is("There are no talks present at this moment"));
    }


}
