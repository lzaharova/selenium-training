package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class Task8 {

    private WebDriver driver;


    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void task8() {
        driver.get("http://localhost/litecart/en/");

        List<WebElement> elements = driver.findElements(By.cssSelector("li.product"));
        assertTrue(areElementPresent(By.cssSelector("div.sticker")));
        boolean arePresent = (elements.size() == 1);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private boolean areElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;


    }
}


