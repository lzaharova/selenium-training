package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Task12 {

    private WebDriver driver;
    private WebDriverWait wait;
    public Actions actions;

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void addProduct(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        String name = "Test";

        driver.findElement(By.cssSelector("#box-apps-menu-wrapper li:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#content .button:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#tab-general [name='name[en]'")).sendKeys(name);
        driver.findElement(By.cssSelector("#tab-general [name=code]")).sendKeys("001");
        driver.findElement(By.cssSelector("#tab-general [name='product_groups[]'][value='1-1']")).click();
        driver.findElement(By.cssSelector("#tab-general td [name=quantity]")).clear();
        driver.findElement(By.cssSelector("#tab-general td [name=quantity]")).sendKeys("10");
        File image = new File("src/test/java/ru/stqa/training/selenium/OrangeDuck.jpg");
        String path = image.getAbsolutePath();
        driver.findElement(By.cssSelector("#tab-general [name='new_images[]']")).sendKeys(path);
        driver.findElement(By.cssSelector("#tab-general td [name=date_valid_from]")).sendKeys("10.10.2000");
        driver.findElement(By.cssSelector("#tab-general td [name=date_valid_to]")).sendKeys("01.01.2020");


        driver.findElement(By.cssSelector(".tabs li:nth-child(2)")).click();

        actions = new Actions(driver);
        WebElement manufacturer = driver.findElement(By.cssSelector("[name=manufacturer_id]"));
        actions.click(manufacturer).sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).release().perform();

        driver.findElement(By.cssSelector("[name='keywords']")).sendKeys("Test");
        driver.findElement(By.cssSelector("[name='short_description[en]']")).sendKeys("Short Description");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("Full Description");
        driver.findElement(By.cssSelector("[name='head_title[en]'")).sendKeys("Head Title");
        driver.findElement(By.cssSelector("[name='meta_description[en]'")).sendKeys("Meta Description");

        driver.findElement(By.cssSelector(".tabs li:nth-child(4)")).click();
        driver.findElement(By.cssSelector("[name=purchase_price]")).clear();
        driver.findElement(By.cssSelector("[name=purchase_price]")).sendKeys("5");

        WebElement price = driver.findElement(By.cssSelector("[name=purchase_price_currency_code]"));
        actions.click(price).sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).release().perform();

        driver.findElement(By.cssSelector("[name='prices[USD]'")).sendKeys("20");
        driver.findElement(By.cssSelector("[name='gross_prices[USD]'")).clear();
        driver.findElement(By.cssSelector("[name='gross_prices[USD]'")).sendKeys("1");
        driver.findElement(By.cssSelector("[name='prices[EUR]'")).sendKeys("30");
        driver.findElement(By.cssSelector("[name='gross_prices[EUR]'")).clear();
        driver.findElement(By.cssSelector("[name='gross_prices[EUR]'")).sendKeys("2");

        driver.findElement(By.cssSelector(".button-set [name=save]")).click();

        Assert.assertTrue(isElementPresent(By.linkText(name)));


    }

    private boolean isElementPresent(By element) {
        return driver.findElements(element).size() > 0;
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}