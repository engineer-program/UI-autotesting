import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageorder.MainPage;
import ru.yandex.praktikum.pageorder.OrderPage;
import ru.yandex.praktikum.pageorder.RentPage;

import java.time.LocalDate;

@RunWith(Parameterized.class)
public class OrderScooter {
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    private WebDriver driver;
    private MainPage objMainPage;
    private OrderPage objOrderPage;
    private RentPage objRentPage;

    private String firstName;
    private String secondName;
    private String adressName;
    private int indexMetroStation;
    private String telephoneNumber;

    static LocalDate currentDate = LocalDate.now();
    private int indexRentPeriod;
    private String commentForCourier;

    public OrderScooter(String firstName, String secondName, String adressName, int indexMetroStation, String telephoneNumber, LocalDate currentDate, int indexRentPeriod, String commentForCourier) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.adressName = adressName;
        this.indexMetroStation = indexMetroStation;
        this.telephoneNumber = telephoneNumber;
        this.currentDate = currentDate;
        this.indexRentPeriod = indexRentPeriod;
        this.commentForCourier = commentForCourier;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{{"Тест", "Тест", "Гражданский проспект 24", 9, "79117975846", currentDate, 4, "Полный заряд"},
                {"Мука", "Муки", "Суздальский 40", 7, "89564876234", currentDate, 2, "Нужно вино"}
        };
    }

    public void inputPersonInformationForOrder() {

        //создаем объект класса страницы заказа
        objOrderPage = new OrderPage(driver);

        // ввод имени
        objOrderPage.sendFirstName(firstName);

        // ввод фамилии
        objOrderPage.sendSecondName(secondName);

        // ввод адреса доставки
        objOrderPage.adressDeliverly(adressName);

        // выбор станции метро
        objOrderPage.choiseMetro(indexMetroStation);

        // ввод номера телефона
        objOrderPage.sendNumberTelephone(telephoneNumber);
    }

    public void inputRentInformationForOrder() {

        // создаем объект класса страницы с выбором аренды
        objRentPage = new RentPage(driver);

        // ввод даты аренды
        objRentPage.dateDeliverly(String.valueOf(currentDate));

        // ввод срока на который заказывается аренда
        objRentPage.choiseRentPeriod(indexRentPeriod);

        // выбор чек-бокса с цветом самоката
        objRentPage.choiseColourGreyScooter();

        // ввод комментария для курьера
        objRentPage.sendCommentCourier(commentForCourier);
    }

    @Before
    public void openMainPage() {

        // создаем драйвер для браузера
        driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();

        // запускаем сайт
        driver.get(url);

        // создаем объект класса главной страницы
        objMainPage = new MainPage(driver);

        // жмём кнопку куки
        objMainPage.clickCoockieButton();
    }

    @After
    public void teardown() {

        // Закрой браузер
        driver.quit();
    }

    // Заказ самоката через кнопку заказа в середине сайта
    @Test
    public void orderBlackScooterThroughInMiddleButton() {

        //клик по кнопке "Заказать" в середине сайта
        objMainPage.clickMiddleButtonOrder();

        // заполняем данные человека для заказа
        inputPersonInformationForOrder();

        // жмём по кнопке далее
        objOrderPage.clickButtonNext();

        // заполняем данные выбора аренды
        inputRentInformationForOrder();

        // жмём по кнопке заказа
        objRentPage.clickButtonOrder();

        // клик по кнопке да в модальном окне подтверждения
        objRentPage.clickButtonYes();

        //проверка отображения модального окна с номером заказа
        objRentPage.isDisplayModalOrder();
    }

    @Test
    public void orderScooterColourGreyTroughtButtonInHeader() {

        // жмём по кнопке "Заказать" в хедере сайта
        objMainPage.clickButtonHeaderButtonOrder();

        // заполняем данные человека для заказа
        inputPersonInformationForOrder();

        // клик по кнопке далее
        objOrderPage.clickButtonNext();

        // заполняем данные выбора аренды
        inputRentInformationForOrder();

        // жмём по кнопке заказа
        objRentPage.clickButtonOrder();

        // жмём по кнопке да в модальном окне
        objRentPage.clickButtonYes();

        // проверка отображения модального окна с номером заказа
        objRentPage.isDisplayModalOrder();
    }
}
