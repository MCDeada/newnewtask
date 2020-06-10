package ru.ozon;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecondTest {
    // Переменные содержащие пути до нужных элементов
    private final By CITY_BTN = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[1]/div/button");
    private final By INPUT_CITY = By.xpath("//*[@id=\"__ozon\"]/div/div[2]/div/div/div/div/div/label/div/input");
    private final By CITY_NAME = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[1]/div/button/span");
    private final By MAIN_PAGE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[4]/div/div[1]/div[3]/section[1]/ul/li[1]/a");
    private final By OPEN_MAP = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[4]/div/div[2]/div[5]/div[2]/div/div[2]/div[1]/a/div/div");
    private final By ADDRESS = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[3]/div/div[2]/div[2]/div/div[2]/div/span/span");
    // Функция второго теста
    public void secondTest(ChromeDriver driver, WebDriverWait driverWait) throws InterruptedException {
        // Находим место смены города
        driver.findElement(CITY_BTN).click();
        // Ждем пока появится поле для ввода города
        driverWait.until(ExpectedConditions.presenceOfElementLocated(INPUT_CITY));
        // Вводим город
        driver.findElement(INPUT_CITY).sendKeys("Вольск");
        // Ждем пока отобразятся вохзможные результаты
        Thread.sleep(3000);
        driver.findElement(By.partialLinkText("Вольск")).click();
        // Ждем пока город изменится
        Thread.sleep(1000);
        String city = driver.findElement(CITY_NAME).getText();
        // Проверям, что смена произошла успешно
        if ("Вольск".equals(city))
            System.out.println("Город сменен на Вольск");
        // Вызываем вторизацию
        FirstTest ft = new FirstTest();
        ft.authorization(driver, driverWait);
        // Ожидаем пока появится переход в личный кабинет
        driverWait.until(ExpectedConditions.presenceOfElementLocated(MAIN_PAGE));
        driver.findElement(MAIN_PAGE).click();
        // Ожидаем пока откроется элемент "Перейти к карте"
        driverWait.until(ExpectedConditions.presenceOfElementLocated(OPEN_MAP));
        driver.findElement(OPEN_MAP).click();
        // Так как открывается сайт в новой ссылке, то открываем еще одно окно
        ChromeDriver driverNew = new ChromeDriver();
        driverNew.get("https://www.ozon.ru/geo/volsk/");

        Thread.sleep(1000);
        // Получаем адрес доставки
        String city_delivery = driverNew.findElement(ADDRESS).getText();
        // Закрываем открытое окно
        driverNew.quit();
        // Проверяем
        if (city.equals(city_delivery))
            System.out.println("Test 2 passed");
    }
}
