import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageorder.MainPage;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
    public class ListOfQuestionsTest {
        private final String url = "https://qa-scooter.praktikum-services.ru/";

        private WebDriver driver;
        private MainPage objMainPage;

        private final String questionLocator;
        private final String answerLocator;
        private final String answerText;

        // создаем конструктор класса
        public ListOfQuestionsTest(String questionLocator, String answerLocator, String answerText) {
            this.questionLocator = questionLocator;
            this.answerLocator = answerLocator;
            this.answerText = answerText;
    }

    //пишем тестовые данные
    @Parameterized.Parameters
    public static Object[][] expectedAnswersText() {
        return new Object[][]{
                {MainPage.arrayListOfQuestions[0], MainPage.arrayAnswerFromTheList[0], "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {MainPage.arrayListOfQuestions[1], MainPage.arrayAnswerFromTheList[1], "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {MainPage.arrayListOfQuestions[2], MainPage.arrayAnswerFromTheList[2], "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {MainPage.arrayListOfQuestions[3], MainPage.arrayAnswerFromTheList[3], "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {MainPage.arrayListOfQuestions[4], MainPage.arrayAnswerFromTheList[4], "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {MainPage.arrayListOfQuestions[5], MainPage.arrayAnswerFromTheList[5], "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {MainPage.arrayListOfQuestions[6], MainPage.arrayAnswerFromTheList[6], "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {MainPage.arrayListOfQuestions[7], MainPage.arrayAnswerFromTheList[7], "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
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

        // жмём кнопку куки "мы все уже поняли"
        objMainPage.clickCoockieButton();
    }

    @After
    public void teardown() {

        // Закрой браузер
        driver.quit();
    }

    @Test
    public void comparingTextWithAnswer() {

        // листаем до списка с вопросами
        objMainPage.scrollMainPageToTheListOfQuestions();

        // жмём строку с вопросом
        objMainPage.clickButtonQuestions(questionLocator);

        // сравниваем ответ
        String actualAnswerText = driver.findElement(By.id(answerLocator)).getText();
        assertEquals("Полученный текст не соответствует тексту отображаемому на странице", answerText, actualAnswerText);
    }
}
