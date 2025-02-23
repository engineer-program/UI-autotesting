package ru.yandex.praktikum.pageorder;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class OrderPage {
    private WebDriver driver;
    // локатор поля "Имя"
    private final By firstName = By.xpath(".//input[@placeholder='* Имя']");
    // локатор поля "Фамилия"
    private final By secondName = By.xpath(".//input[@placeholder='* Фамилия']");
    // локатор поля "Адрес: куда привезти заказ"
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // локатор поля "Станция метро"
    private final By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    // локатор выпадающего списка со станциями метро
    private final By metroList = By.className("select-search__row");
    // локатор поля "Телефон: на него позвонит курьер"
    private final By numberTelephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // локатор кнопки "Далее"
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // ввод имени
    public void sendFirstName(String name) {
        driver.findElement(firstName).sendKeys(name);
    }

    // ввод фамилии
    public void sendSecondName(String sName) {
        driver.findElement(secondName).sendKeys(sName);
    }

    // ввод адреса доставки
    public void adressDeliverly(String adressName) {
        driver.findElement(address).sendKeys(adressName);
    }

    // выбор станции метро
    public void choiseMetro(int index) {
        driver.findElement(metroStation).click();
        WebElement station = driver.findElements(metroList).get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", station);
        station.click();
    }

    // ввод номера телефона
    public void sendNumberTelephone(String number) {
        driver.findElement(numberTelephone).sendKeys(number);
    }

    // клик по кнопке "Далее"
    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }
}
