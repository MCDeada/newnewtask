package ru.ozon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FourthTest {
    // Переменные содержащие пути до нужных элементов
    private final By POWER_INPUT_MIN = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/div[1]/div/aside/div[8]/div[2]/div[2]/div[1]/input");
    private final By SORT_BY_BUTTON = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div/div/input");
    private final By ADD_JUICER = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div/div/div[1]/div/div/div[3]/div[3]/div/div/button/div");
    private final By ONE_MORE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[4]/div/div[1]/div/div[1]/div/div/input");
    private final By CHECK_BASKET = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[4]/a[2]");
    private final By IS_IN_BASKET = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[3]/div/div/div/div/div[1]");
    private final By PRICE_FOR_ONE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[3]/div/div/span");
    private final By FULL_PRICE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[4]/div[2]/div/section/div[2]/div[4]/span[2]");
    private final By AMOUNT = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[4]/div/div[1]/div/div[1]/div");

    // Функция четвертого теста
    public void fourthTest(ChromeDriver driver, WebDriverWait driverWait) throws InterruptedException {
        // Находим все соковыжималки в ценовом диапазоне от 3000 др 4000
        ThirdTest tt = new ThirdTest();
        tt.findJuicersWithPrice(driver, driverWait);
        // Добавляем критерий мощности
        driver.findElement(POWER_INPUT_MIN).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        driver.findElement(POWER_INPUT_MIN).sendKeys("1000");
        driver.findElement(POWER_INPUT_MIN).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        // Выбираем сортировка от самого дешевого
        driver.findElement(SORT_BY_BUTTON).click();
        Thread.sleep(2000);
        driver.findElement(SORT_BY_BUTTON).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        Thread.sleep(2000);
        // Переносим один в корзину
        driver.findElement(ADD_JUICER).click();
        Thread.sleep(600);
        // Переходим в корсзину, проверяем, что мы в нее перешли
        driver.findElement(CHECK_BASKET).click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(IS_IN_BASKET));
        // Вычисляем цену одного товара
        String priceStr = driver.findElement(PRICE_FOR_ONE).getText();
        priceStr = priceStr.substring(0, priceStr.length() - 2);
        priceStr = priceStr.replace(" ", "");
        double priceDouble = Double.parseDouble(priceStr);

        for (int i = 1; i < 5; i++) {
            driver.findElement(ONE_MORE).sendKeys(Keys.ARROW_DOWN);
        }
        driver.findElement(ONE_MORE).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        // Находим общую цену по версии сайта
        String totalPriceStr = driver.findElement(FULL_PRICE).getText();
        totalPriceStr = totalPriceStr.substring(0, totalPriceStr.length() - 2);
        totalPriceStr = totalPriceStr.replace(" ", "");
        double totalPriceDouble = Double.parseDouble(totalPriceStr);
        // Находим число элементов для покупки
        String amountStr = driver.findElement(AMOUNT).getText();
        double amountDouble = Double.parseDouble(amountStr);
        // Сравниваем два значения
        if (totalPriceDouble == amountDouble * priceDouble)
            System.out.println("Test 4 passed");
    }
}
