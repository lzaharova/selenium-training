package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class Task7 {

    private WebDriver driver;

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (InvalidSelectorException ex) {
            throw ex;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void task7() {

        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> menu = driver.findElements(By.cssSelector("ul#box-apps-menu li"));
        for (int i = 1; i <= menu.size(); i++) {
            driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ") a")).click();
            assertTrue(areElementPresent(By.cssSelector("#content h1")));

            List<WebElement> submenu = driver.findElements(By.cssSelector("ul.docs li"));
            if (submenu.size() > 0) {
                for (int j = 1; j <= submenu.size(); j++) {
                    driver.findElement(By.cssSelector("ul.docs > li:nth-child(" + j + ") a")).click();
                    assertTrue(areElementPresent(By.cssSelector("#content h1")));
                }
            }
        }
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