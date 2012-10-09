package functional.com.thoughtworks.twu.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class BaseFunctionalTest {
    public static final int HTTP_PORT = 9191;
    public static final String HTTP_BASE_URL = "http://localhost:" + HTTP_PORT ;
    protected static WebDriver webDriver;

    @BeforeSuite
    public static void casLogin(){
        webDriver = new FirefoxDriver();
        webDriver.get(HTTP_BASE_URL);
        Cas.login(webDriver);
    }

    @AfterSuite
    public static void casLogout(){
        Cas.logout(webDriver);
        webDriver.close();
    }
}
