package ru.yandex.praktikum.pageorder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RentPage {

    private WebDriver driver;
    //  локатор поля "Когда привезти самокат"
    private final By whenToBringScooter = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // локатор поля "Срок доставки"
    private final By timeRent = By.xpath(".//div[@class='Dropdown-placeholder']");

    // локатор поля Срок аренды
    private final By rent = By.className("Dropdown-placeholder");

    // локатор выпадающего списка срока аренды
    private final String[] arrayTimeRent = new String[]{
            ".//div[text()='сутки']",
            ".//div[text()='двое суток']",
            ".//div[text()='трое суток']",
            ".//div[text()='четверо суток']",
            ".//div[text()='пятеро суток']",
            ".//div[text()='шестеро суток']",
            ".//div[text()='семеро суток']"

    };

    //локатор чек-бокса "черный самокат"
    private final By blackScooter = By.xpath(".//input[@id='black']");

    //локатор чек-бокса "серый самокат"
    private final By greyScooter = By.xpath(".//input[@id='grey']");

    //локатор поля "Комментарий для курьера"
    private final By commentCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //локатор кнопки "Заказать" в середине страницы
    private final By buttonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //локатор кнопки "ДА" в модальном окне подтверждения заказа
    private final By buttonYes = By.xpath("//*/div/div[2]/div[5]/div[2]/button[2]");

    //локатор модального окна Успешно оформленного заказа
    private final By modalWindowOrder = By.xpath("//div[text()='Заказ оформлен']");


    // методы
    public RentPage(WebDriver driver) {

        this.driver = driver;
    }

    //метод выбора даты доставки
    public void dateDeliverly(String date) {
        driver.findElement(whenToBringScooter).click();
        driver.findElement(whenToBringScooter).sendKeys(date);
        driver.findElement(whenToBringScooter).sendKeys(Keys.ENTER);
    }

    // метод выбора срока аренды
    public void choiseRentPeriod(int period) {
        driver.findElement(rent).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(arrayTimeRent[period])));
        driver.findElement(By.xpath(arrayTimeRent[period])).click();
    }

    // метод выбора чекбокса черного цвета Самоката
    public void choiseColourBlackScooter() {
        driver.findElement(blackScooter).click();
    }

    // метод выбора чекбокса серого цвета Самоката
    public void choiseColourGreyScooter() {
        driver.findElement(greyScooter).click();
    }

    // метод заполнения поля комментарий для курьера
    public void sendCommentCourier(String comment) {
        driver.findElement(commentCourier).click();
        driver.findElement(commentCourier).sendKeys(comment);
    }

    // метод нажатия кнопки "Заказать"
    public void clickButtonOrder() {
        driver.findElement(buttonOrder).click();
    }

    // метод нажатия кнопки "Да" в подтверждении заказа
    public void clickButtonYes() {
        driver.findElement(buttonYes).click();
    }

    // проверка что модальное окно успешного заказа отобразилось
    public boolean isDisplayModalOrder() {
        return (driver.findElement(modalWindowOrder).isDisplayed());
    }
}