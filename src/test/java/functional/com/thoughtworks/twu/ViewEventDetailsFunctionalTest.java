package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.joda.time.DateTime.now;

public class ViewEventDetailsFunctionalTest {

    public static final int HTTP_PORT = 9091;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT + "/twu/home.html";

    private FirefoxDriver webDriver;
    private WebDriverWait wait;

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }

    @Before
    public void setUp() {

        webDriver = new FirefoxDriver();
        wait = new WebDriverWait(webDriver, 20);
        webDriver.get(HTTP_BASE_URL);
    }




}
