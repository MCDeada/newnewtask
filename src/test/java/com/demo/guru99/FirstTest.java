package com.demo.guru99;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FirstTest {
    @Test
    public void firstTest() {

        System.setProperty("webdriver.chrome.driver","/Testing/WebDriver/chromedriver.exe");
        //ChromeOptions options = new ChromeOptions();
        //options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

        ChromeDriver driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/");
        WebElement element=driver.findElement(By.xpath("//input[@name='emailid']"));
        element.sendKeys("abc@gmail.com ");

        WebElement button=driver.findElement(By.xpath("//input[@name='btnLogin']"));
        button.click();
    }
}
