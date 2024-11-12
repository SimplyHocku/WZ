package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.WZ.config.Config;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import tests.pages.CurriculaAndStudents;
import tests.pages.ListOfCurriculaPage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Retention(RetentionPolicy.RUNTIME)
@interface Bug {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface BugNumber {
    String value();
}

public class TestCurriculaAndStudents extends Config {
    private static String FIO;
    private static CurriculaAndStudents testPage;

    @BeforeAll
    public static void createTestPage() {
        testPage = new CurriculaAndStudents();
    }

    @BeforeEach
    void setUp() {
        Selenide.open("study-plan/add-student-to-plan");
    }

    @Test
    @DisplayName("WEBHTCHR-1325 - Добавление нового УП")
    void test1325() {
        testPage.selectInParallel().clickAddCurricula();
        $x("//li[text() = 'Новый учебный план']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("WEBHTCHR-1327 - Удаление УП")
    @Bug("Не исчезают УП")
    @BugNumber("WEBHTCHR-4012")
    void test1327() {
        String plan = "рпапрпр";
        testPage.selectInParallel().selectParalel("12").clickDeleteCurricula(plan, 1);
        $x("//span[text() = '%s']".formatted(plan)).shouldNotBe(Condition.exist);
    }

    @Test
    @DisplayName("WEBHTCHR-1329 - Редактирование УП")
    void test1329() {
        String plan = "СТАС ТЕСТ";
        testPage.selectInParallel().clickEditCurricula(plan);
        $x("//h4[text() = 'Редактирование  учебного плана %s']".formatted(plan)).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("WEBHTCHR-1336 - вкладка По классу. Наличие элементов на странице по умолчанию")
    void test1336() {
        $x("//h3[text() = 'Выберите параллель и класс']").shouldBe(Condition.visible);
        $x("//span[text() = 'Параллель']").shouldBe(Condition.visible);
        $x("//span[text() = 'Класс']").shouldBe(Condition.visible);
        $x("//span[text() = 'Сбросить']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("WEBHTCHR-1337 - По классу. Фильтрация и сброс")
    void test1337() {
        String selectedClass = "2-В";
        testPage.selectParalel("2").selectClass(selectedClass);
        Assertions.assertEquals(2, $$x("//span[text() = '%s']".formatted(selectedClass)).size(), "Неверная фильтрация");
        testPage.clickReset();
        $x("//h3[text() = 'Выберите параллель и класс']").shouldBe(Condition.exist);
    }

    @Test
    @DisplayName("WEBHTCHR-1338 - По классу. Добавление новой привязки")
    void test1338() {
        String student = "Я А К";
        String planName = "тест Катя";
        String startAt = "29.10.2024";
        String finishAt = "30.10.2024";

        testPage.selectParalel("4").selectClass("4-Э");
        testPage.clickAddBind(student).clickPeriodForStudent(student).setPeriod(startAt, finishAt).
                selectCurriculaForStudent(student, planName).clickCancelSubmit();

        testPage.selectParalel("4").selectClass("4-Э");
        testPage.clickAddBind(student).clickPeriodForStudent(student).setPeriod(startAt, finishAt).
                selectCurriculaForStudent(student, planName).clickSubmitBind();

        $x("//tr[.//a[text() = '%s'] and .//span[text() = '%s'] and .//span[text() = '%s - %s']]".formatted(student, planName, startAt, finishAt)).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("WEBHTCHR-1341 - По классу. Удаление привязки")
    void test1341(){
        String parallel = "1";
        String _class = "1-И";
        String studentName = "А И В";
        String periodForDelete = "01.10.2024 - 31.10.2024";
        int beforeDelete =  testPage.selectParalel(parallel).selectClass(_class).getCountAssignment(studentName);

        testPage.clickContextRow(studentName, periodForDelete, CurriculaAndStudents.ContextOptionRow.DELETE).
                contextWindowCancel();
        testPage.clickContextRow(studentName, periodForDelete, CurriculaAndStudents.ContextOptionRow.DELETE).
                contextWindowDelete();

        int afterDelete =  testPage.getCountAssignment(studentName);
        Assertions.assertNotEquals(afterDelete, beforeDelete, "Количество привязок не изменилось");

    }

}
