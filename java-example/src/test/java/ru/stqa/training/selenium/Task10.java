package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Task10 {

    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    // а) на главной странице и на странице товара совпадает текст названия товара
    // б) на главной странице и на странице товара совпадают цены (обычная и акционная)
    @Test
    public void task1() {

        List<String> main = new ArrayList<>();
        List<String> card = new ArrayList<>();

        driver.get("http://localhost/litecart/en/");

        main.add(driver.findElement(By.cssSelector("#box-campaigns .name")).getAttribute("innerText"));
        main.add(driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getAttribute("innerText"));
        main.add(driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getAttribute("innerText"));

        driver.findElement(By.cssSelector("#box-campaigns .product")).click();

        card.add(driver.findElement(By.cssSelector("#box-product .title")).getAttribute("innerText"));
        card.add(driver.findElement(By.cssSelector("#box-product .regular-price")).getAttribute("innerText"));
        card.add(driver.findElement(By.cssSelector("#box-product .campaign-price")).getAttribute("innerText"));
        assertEquals(main,card);
    }

    // в) обычная цена зачёркнутая и серая(цвета надо проверить на каждой странице независимо)
    @Test
    public void task2 (){

        driver.get("http://localhost/litecart");

        String line = "line-through";
        String decoration1 = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("text-decoration");
        String line1 = decoration1.substring(0,12);
        assertEquals(line, line1);

        String color1 = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("color");
        String replace=color1.replace(" ","");
        String[] arr = replace.substring(replace.indexOf("(")+1,replace.lastIndexOf(")")).split("\\,");
        for (int i=0; i < 2 ;i++) {
            String k = arr[i];
            String j= arr[i + 1];
            assertEquals(k,j);
        }

        driver.findElement(By.cssSelector("#box-campaigns .product")).click();

        String decoration2 = driver.findElement(By.cssSelector("#box-product .regular-price")).getCssValue("text-decoration");
        String line2 = decoration2.substring(0,12);
        assertEquals(line, line2);

        String color2 = driver.findElement(By.cssSelector("#box-product .regular-price")).getCssValue("color");
        String replacecard=color2.replace(" ","");
        String[] arrcard = replacecard.substring(replacecard.indexOf("(")+1,replacecard.lastIndexOf(")")).split("\\,");
        for (int i=0; i < 2 ;i++) {
            String k= arrcard[i];
            String j = arrcard[i + 1];
            assertEquals(k,j);
        }
    }
    // г) акционная жирная и красная(цвета надо проверить на каждой странице независимо)
    // д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
    @Test
    public void task3(){

        driver.get("http://localhost/litecart");

        String bold = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("font-weight");
        assertTrue( "bold".equals(bold) || "bolder".equals(bold) || Integer.parseInt(bold) >= 700);

        String salecolor = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("color");
        String replace=salecolor.replace(" ","");
        String[] arr = replace.substring(replace.indexOf("(")+1,replace.lastIndexOf(")")).split("\\,");
        String k = arr[1];
        String j = arr[2];
        assertEquals(k, "0");
        assertEquals(j, "0");

        Double font = Double.parseDouble(driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("font-size").replace("px",""));
        Double salefont = Double.parseDouble(driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("font-size").replace("px",""));

        driver.findElement(By.cssSelector("#box-campaigns .product")).click();

        String boldcard = driver.findElement(By.cssSelector("#box-product .campaign-price")).getCssValue("font-weight");
        assertTrue( "bold".equals(boldcard) || "bolder".equals(boldcard) || Integer.parseInt(boldcard) >= 700);

        String salecolorcard = driver.findElement(By.cssSelector("#box-product .campaign-price")).getCssValue("color");
        String replacecard=salecolor.replace(" ","");
        String[] arrcard = replacecard.substring(replacecard.indexOf("(")+1,replacecard.lastIndexOf(")")).split("\\,");
        String m = arrcard[1];
        String n = arrcard[2];
        assertEquals(m, "0");
        assertEquals(n, "0");
        Double cardfont = Double.parseDouble(driver.findElement(By.cssSelector("#box-product .regular-price")).getCssValue("font-size").replace("px",""));
        Double cardsalefont = Double.parseDouble(driver.findElement(By.cssSelector("#box-product .campaign-price")).getCssValue("font-size").replace("px",""));

        assertTrue(font < salefont);
        assertTrue(cardfont < cardsalefont);
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;

    }

}