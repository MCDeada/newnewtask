package ru.ozon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ThirdTest {
    // Переменные содержащие пути до нужных элементов
    private final By CATALOG = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[2]/div/div[1]/button/div");
    private final By KITCHEN = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[2]/div/div[2]/div/div[1]/div/a[11]");
    private final By KITCHEN_CHECK = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div/div[1]/aside/div[2]/a");
    private final By SHOW_MORE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div/div[1]/aside/div[2]/span");
    private final By JUICERS = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div/div[1]/aside/div[2]/div[6]/a");
    private final By PRICE_INPUT_MIN = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/div[1]/div/aside/div[2]/div[2]/div[2]/div[1]/input");
    private final By PRICE_INPUT_MAX = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/div[1]/div/aside/div[2]/div[2]/div[2]/div[2]/input");
    private final By HOMEMADE_ENTER = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/div[1]/div/aside/div[4]/div[1]");
    private final By CHECK_INTERVAL = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div/div/button/div/span");
    private final By CHECK_TITLE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[1]/div/div[2]/h1");
    private final By SORT_BY_BUTTON = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div/div/input");
    private final By ADD_JUICER = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div/div/div[1]/div/div/div[3]/div[3]/div/div/button/div");
    private final By ONE_MORE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[4]/div/div[1]/div/div[1]/div/div/input");
    private final By CHECK_BASKET = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[4]/a[2]");
    private final By IS_IN_BASKET = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[3]/div/div/div/div/div[1]");
    private final By PRICE_FOR_ONE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[3]/div/div/span");
    private final By FULL_PRICE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[4]/div[2]/div/section/div[2]/div[4]/span[2]");
    // Контрольные переменные и переменные для проверки
    String intervalControlstring;
    String titleControlstring;
    String text;
    String title;
    // Функционал с поиском соковыжималок от 3000 до 4000 вынесен в отдельную функцию, чтобы использовать в четвертом заданнии
    public void findJuicersWithPrice(ChromeDriver driver, WebDriverWait driverWait) throws InterruptedException{
        driver.findElement(CATALOG).click();
        // Бытовая техника
        driverWait.until(ExpectedConditions.presenceOfElementLocated(KITCHEN));
        driver.findElement(KITCHEN).click();
        // Показать еще
        driverWait.until(ExpectedConditions.presenceOfElementLocated(KITCHEN_CHECK));
        driver.findElement(SHOW_MORE).click();
        // Соковыжималки
        driverWait.until(ExpectedConditions.presenceOfElementLocated(JUICERS));
        driver.findElement(JUICERS).click();
        // Установка нижней ценовой границы
        driverWait.until(ExpectedConditions.presenceOfElementLocated(PRICE_INPUT_MIN));
        driver.findElement(PRICE_INPUT_MIN).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        driver.findElement(PRICE_INPUT_MIN).sendKeys("3000");
        driver.findElement(PRICE_INPUT_MIN).sendKeys(Keys.ENTER);
        //driver.findElement(HOMEMADE_ENTER).click();
        Thread.sleep(2000);
        // Установка верхней ценовой границы
        driver.findElement(PRICE_INPUT_MAX).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        driver.findElement(PRICE_INPUT_MAX).sendKeys("4000");
        driver.findElement(PRICE_INPUT_MAX).sendKeys(Keys.ENTER);
        //driver.findElement(HOMEMADE_ENTER).click();
        Thread.sleep(2000);
        // Проверка, что условие выбраны верно
        driverWait.until(ExpectedConditions.presenceOfElementLocated(CHECK_INTERVAL));
        intervalControlstring = "Цена: от 3 000 до 4 000";
        titleControlstring = "Соковыжималки";
        text = driver.findElement(CHECK_INTERVAL).getText();
        title = driver.findElement(CHECK_TITLE).getText();
    }
    // Функция с третьим тестом
    public void thirdTest(ChromeDriver driver, WebDriverWait driverWait) throws InterruptedException{
        findJuicersWithPrice(driver, driverWait);

        if (text.equals(intervalControlstring) && title.equals(titleControlstring))
            System.out.println("Интервал и категория товара заданы корректно");
        // Сортировка элементов
        driver.findElement(SORT_BY_BUTTON).click();
        Thread.sleep(2000);
        driver.findElement(SORT_BY_BUTTON).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(ADD_JUICER).click();
        Thread.sleep(700);
        // Переход в корзину
        driver.findElement(CHECK_BASKET).click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(IS_IN_BASKET));
        // Вычисление цены одного выбранного товара
        String priceStr = driver.findElement(PRICE_FOR_ONE).getText();
        priceStr = priceStr.substring(0, priceStr.length() - 2);
        priceStr = priceStr.replace(" ", "");
        double priceDouble = Double.parseDouble(priceStr);
        // Изменение числа выбранных для покупки товаров
        for (int i = 1; i < 5; i++) {
            driver.findElement(ONE_MORE).sendKeys(Keys.ARROW_DOWN);
        }
        driver.findElement(ONE_MORE).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        // Нахождение общей цены, которую вычислил сайт
        String totalPriceStr = driver.findElement(FULL_PRICE).getText();
        totalPriceStr = totalPriceStr.substring(0, totalPriceStr.length() - 2);
        totalPriceStr = totalPriceStr.replace(" ", "");
        double totalPriceDouble = Double.parseDouble(totalPriceStr);
        // Сравнение цены на сайте, с ориентировочной по вычислениям
        if (totalPriceDouble == 5 * priceDouble)
            System.out.println("Test 3 passed");
    }
}
