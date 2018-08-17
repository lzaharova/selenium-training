package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task13 {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @Test
    public void basket() {
        driver.get("http://localhost/litecart");
        for (int i = 1; i < 4; i++) {
            driver.findElement(By.cssSelector("#box-most-popular .product:first-child")).click();
            driver.findElement(By.cssSelector(".quantity [name=add_cart_product]")).click();
            String b = String.valueOf(i);
            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), b ));
            driver.get("http://localhost/litecart");
        }


        driver.findElement(By.cssSelector("#cart a.link")).click();
        List<WebElement> cart = driver.findElements(By.cssSelector(".viewport [name=remove_cart_item]"));

        for (WebElement element : cart) {
            List<WebElement> table = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr"));
            int size = table.size();
            driver.findElement(By.cssSelector(".viewport li [name=remove_cart_item]")).click();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#order_confirmation-wrapper tr"),size));
            driver.get("http://localhost/litecart/en/checkout");
        }


    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}