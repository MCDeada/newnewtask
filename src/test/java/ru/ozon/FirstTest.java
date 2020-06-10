package ru.ozon;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {
    // Переменные содержащие пути до нужных элементов
    String login = "//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[4]/div[2]/a";
    String enter = "//*[@id=\"__ozon\"]/div/div[1]/div[3]/div/div[2]/a";
    String phoneInput = "/html/body/div[3]/div/div/div/div/div/div/div/div/div[2]/label/div/input";
    String getCode = "/html/body/div[3]/div/div/div/div/div/div/div/div/div[3]/button";
    String buttonLogIn = "/html/body/div[1]/div/div[1]/header/div[1]/div[4]/div[1]/a";

    // Функционал с авторизацией вынесен в отдельную функцию, чтобы использовать во втором заданнии
    public void authorization(ChromeDriver driver, WebDriverWait driverWait) {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(login)));
        driver.findElement(By.xpath(login)).click();

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(enter)));
        driver.findElement(By.xpath(enter)).click();

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(phoneInput)));
        driver.findElement(By.xpath(phoneInput)).sendKeys("9379777153");
        driver.findElement(By.xpath(getCode)).click();
    }
    // Функция теста целиком
    public void firstTest(ChromeDriver driver, WebDriverWait driverWait) throws InterruptedException {

        authorization(driver, driverWait);

        Thread.sleep(12000);
        String isPassed = driver.findElement(By.xpath(buttonLogIn)).getAttribute("textContent");
        if ("0 Кабинет".equals(isPassed))
            System.out.println("Test 1 passed");
    }
}
