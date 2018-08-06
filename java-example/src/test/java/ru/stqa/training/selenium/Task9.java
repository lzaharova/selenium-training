package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class Task9 {


    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void task9() {

        //1а. Проверить, что страны расположены в алфавитном порядке

        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> country  = driver.findElements(By.cssSelector("td:nth-child(5)"));
        Collections.sort(country , new Comparator<WebElement>(){
            public int compare(WebElement e1, WebElement e2){
                return e1.getAttribute("innerText").compareTo(e2.getAttribute("innerText"));
            }
        });

        List<WebElement> country_sort = new ArrayList<>(country );
        assertEquals(country_sort, country );

        // 1б. Для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны
        //и там проверить, что зоны расположены в алфавитном порядке


        List<WebElement> country_zones = driver.findElements(By.cssSelector("tr.row"));
        List<String> zones = new ArrayList<>();
        for (WebElement i : country_zones)
            if (!(i.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("innerText")).equals("0")){
                zones.add(i.findElement(By.cssSelector("td:nth-child(5)")).getAttribute("innerText"));
            }

        for (String k : zones){
            driver.findElement(By.linkText(k)).click();
            List<WebElement> country_edit = driver.findElements(By.cssSelector("#table-zone td:nth-child(3)"));
            List<String> country_edit_new = new ArrayList<>();
            for (WebElement j : country_edit){
                String attribute = j.getAttribute("innerText");
                if(!attribute.isEmpty()){
                    country_edit_new.add(j.getAttribute("innerText"));
                }
            }
            List<WebElement> zones_new = new ArrayList(country_edit_new);
            Collections.sort(country_edit, new Comparator<WebElement>(){
                public int compare(WebElement e1, WebElement e2){
                    return e1.getAttribute("innerText").compareTo(e2.getAttribute("innerText"));
                }
            });
            assertEquals(zones_new,country_edit_new);
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        }
    }

    // 2. Зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке

    @Test
    public void geo_zones(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> zones = driver.findElements(By.cssSelector("tr.row td:nth-child(3)"));
        ArrayList<String> countries = new ArrayList<>();

        for (WebElement e1 : zones){
            countries.add(e1.getAttribute("innerText"));
        }

        for (String e2 : countries) {
            driver.findElement(By.linkText(e2)).click();
            zones = driver.findElements(By.cssSelector(".dataTable td:nth-child(3) [selected=selected]"));
            List<String> geozones = new ArrayList<>();
            for (WebElement e3 : zones) {
                geozones.add(e3.getAttribute("innerText"));
            }

            List<String> geozones_sort = new ArrayList<>(geozones);
            Collections.sort(geozones_sort);
            assertEquals(geozones_sort,geozones);

            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;

    }
}








