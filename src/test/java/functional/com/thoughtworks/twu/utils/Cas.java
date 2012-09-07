package functional.com.thoughtworks.twu.utils;

import com.thoughtworks.twu.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cas {

    public static void login(WebDriver webDriver) {
        WaitHelper.waitForElement(webDriver, "username").sendKeys("test.twu");
        WaitHelper.waitForElement(webDriver, "password").sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
        WaitHelper.waitForElement(webDriver, "talks_container");
    }

    public static void logout(WebDriver webDriver) {
        webDriver.findElement(By.id("logout")).click();
    }
}
