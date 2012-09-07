package com.thoughtworks.twu.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cas {

    public static void login(WebDriver webDriver) {
        WaitForAjax.waitForElement(webDriver,"username").sendKeys("test.twu");
        WaitForAjax.waitForElement(webDriver,"password").sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
        WaitForAjax.waitForElement(webDriver,"talks_container");
    }

    public static void logout(WebDriver webDriver) {
        webDriver.findElement(By.id("logout")).click();
    }
}
