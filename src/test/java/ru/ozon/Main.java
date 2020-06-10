package ru.ozon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

    public ChromeDriver driver;
    public WebDriverWait driverWait;

    @Before // Инициализация ChromeDriver и WebDriverWait
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","/Testing/WebDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.ozon.ru");
        driver.manage().window().maximize();
        driverWait = new WebDriverWait (driver,20);
    }

    @Test // Авторизация на сайте и проверка, что это произошло корректно
    public void firstTest() throws InterruptedException {
        FirstTest ft = new FirstTest();
        ft.firstTest(driver, driverWait);
    }

    @Test // Изменение города и проверка в кабинете, что измененный город соответствует городу доставки
    public void secondTest() throws InterruptedException {
        SecondTest st = new SecondTest();
        st.secondTest(driver, driverWait);
    }

    @Test // Поиск самой дешевой соковыжималки в ценовом диапазоне от 3000 до 4000, увеличения числа заказов до 5
    public void ThirdTest() throws InterruptedException {
        ThirdTest tt = new ThirdTest();
        tt.thirdTest(driver, driverWait);
    }

    @Test // Поиск самой дешевой соковыжималки в ценовом диапазоне от 3000 до 4000 с мощьностью от 1000 Вт
    public void fourthTest() throws InterruptedException {
        FourthTest ft = new FourthTest();
        ft.fourthTest(driver, driverWait);
    }

    @After // Закрытие ChromeDriver
    public void setDown() {
        driver.quit();
    }
}