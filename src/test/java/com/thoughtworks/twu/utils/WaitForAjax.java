package com.thoughtworks.twu.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public  class WaitForAjax {
    public static void  WaitForAjax(WebDriver webDriver) throws InterruptedException {

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        while (true)
        {
            Boolean ajaxIsComplete =(Boolean) javascriptExecutor.executeScript("return jQuery.active == 0");
            if (ajaxIsComplete)
                break;

            Thread.currentThread().sleep(1000);
        }
    }

    public static WebElement waitForElement(WebDriver webDriver, String elementId) {
        return (new WebDriverWait(webDriver, 7)).until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
    }
}
