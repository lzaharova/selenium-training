package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;



public class Task11 {

    private WebDriver driver;
    public Actions actions;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


        @Test
        public void signUp(){
            String email = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()) + "@gmail.com";
            String password = "123456";


            driver.get("http://localhost/litecart");
            driver.findElement(By.cssSelector(".content td a")).click();


            driver.findElement(By.cssSelector(".content td [name=tax_id]")).sendKeys("001");
            driver.findElement(By.cssSelector(".content td [name=company]")).sendKeys("MEGASYSTEMS INC");
            driver.findElement(By.cssSelector(".content td [name=firstname]")).sendKeys("JOHN");
            driver.findElement(By.cssSelector(".content td [name=lastname]")).sendKeys("SMITH");
            driver.findElement(By.cssSelector(".content td [name=address1]")).sendKeys("MAIN ST");
            driver.findElement(By.cssSelector(".content td [name=address2]")).sendKeys("100");
            driver.findElement(By.cssSelector(".content td [name=postcode]")).sendKeys("98104");
            driver.findElement(By.cssSelector(".content td [name=city]")).sendKeys(" SEATTLE");

            actions = new Actions(driver);
            WebElement country = driver.findElement(By.cssSelector(".content td .selection"));
            actions.click(country).sendKeys("United States").sendKeys(Keys.ENTER).release().perform();

            driver.findElement(By.cssSelector(".content td [name=email]")).sendKeys(email);
            driver.findElement(By.cssSelector(".content td [name=phone]")).sendKeys(Keys.HOME +  "+1-541-754-3010");
            driver.findElement(By.cssSelector(".content td [name=password]")).sendKeys(password );
            driver.findElement(By.cssSelector(".content td [name=confirmed_password]")).sendKeys(password);

            driver.findElement(By.cssSelector(".content td [name=create_account]")).click();
            driver.findElement(By.cssSelector("#box-account li:nth-child(4) a")).click();

            driver.findElement(By.cssSelector("#box-account-login td [name=email")).sendKeys(email);
            driver.findElement(By.cssSelector("#box-account-login td [name=password]")).sendKeys(password );
            driver.findElement(By.cssSelector("#box-account-login td [name=login]")).click();
            driver.findElement(By.cssSelector("#box-account li:nth-child(4) a")).click();



        }

        @After
        public void stop(){
            driver.quit();
            driver = null;
        }
    }