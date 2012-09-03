package com.thoughtworks.twu.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CasLoginLogout {

    public static void login(WebDriver webDriver) {
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
    }

    public static void logout(WebDriver webDriver) {
        webDriver.findElement(By.id("logout")).click();
    }
}
