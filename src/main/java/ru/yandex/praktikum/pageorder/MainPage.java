package ru.yandex.praktikum.pageorder;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;
    // локатор кнопки "Заказать" в header сайта
    private final By headerButtonOrder = By.className("Button_Button__ra12g");
    // локатор кнопки "Статус заказа"
    private final By buttonStatusOrder = By.className("Header_Link__1TAG7");
    // локатор кнопки "Заказать" в середине сайта
    private final By middleButtonOrder = By.className("Button_Middle__1CSJM");
    // локатор кнопки куки "Да все привыкли"
    private final By cookieButton = By.className("App_CookieButton__3cvqF");
    // локаторы выпадающего списка с вопросами
    public static final String[] arrayListOfQuestions = new String[]{
            "accordion__heading-0",
            "accordion__heading-1",
            "accordion__heading-2",
            "accordion__heading-3",
            "accordion__heading-4",
            "accordion__heading-5",
            "accordion__heading-6",
            "accordion__heading-7"
    };
    // локаторы выпадающих ответов из списка с вопросами
    public static final String[] arrayAnswerFromTheList = new String[]{
            "accordion__panel-0",
            "accordion__panel-1",
            "accordion__panel-2",
            "accordion__panel-3",
            "accordion__panel-4",
            "accordion__panel-5",
            "accordion__panel-6",
            "accordion__panel-7"
    };

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //клик по кнопке заказа в хедере сайта
    public void clickButtonHeaderButtonOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(headerButtonOrder));
        driver.findElement(headerButtonOrder).click();
    }

    // клик по кнопке "Заказать" в середине страницы
    public void clickMiddleButtonOrder() {
        WebElement button = driver.findElement(middleButtonOrder);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        driver.findElement(middleButtonOrder).click();
    }

    // клик по кнопке "да все привыкли"
    public void clickCoockieButton() {
        driver.findElement(cookieButton).click();
    }

    // скролл главной страницы до выпадающего списка с вопросами
    public void scrollMainPageToTheListOfQuestions() {
        WebElement element = driver.findElement(By.id(arrayListOfQuestions[7]));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // клик по кнопке вопроса
    public void clickButtonQuestions(String elementArrayQuestions) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id(elementArrayQuestions)));
        driver.findElement(By.id(elementArrayQuestions)).click();
    }
}

