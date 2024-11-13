package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
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
    void test1341() {
        String parallel = "1";
        String _class = "1-И";
        String studentName = "А И В";
        String periodForDelete = "01.10.2024 - 31.10.2024";
        int beforeDelete = testPage.selectParalel(parallel).selectClass(_class).getCountAssignment(studentName);

        testPage.clickContextRow(studentName, periodForDelete, CurriculaAndStudents.ContextOptionRow.DELETE).
                contextWindowCancel();
        testPage.clickContextRow(studentName, periodForDelete, CurriculaAndStudents.ContextOptionRow.DELETE).
                contextWindowDelete();

        int afterDelete = testPage.getCountAssignment(studentName);
        Assertions.assertNotEquals(afterDelete, beforeDelete, "Количество привязок не изменилось");

    }

    @Test
    @DisplayName("WEBHTCHR-1342 - По классу. Редактирование существующей привязки")
    void test1342() {
        String parallel = "1";
        String _class = "1-И";
        String studentName = "А И В";
        String periodForEdit = "01.09.2024 — 30.09.2024";
        String periodAfterEdit = "01.09.2024 — 20.09.2024";
        testPage.selectParalel(parallel).selectClass(_class)
                .clickContextRow(studentName, periodForEdit, CurriculaAndStudents.ContextOptionRow.EDIT).
                clickPeriodForStudent(studentName).setPeriod("01.09.2024", "20.09.2024").clickCancelSubmit();
        testPage.clickContextRow(studentName, periodForEdit, CurriculaAndStudents.ContextOptionRow.EDIT).
                clickPeriodForStudent(studentName).setPeriod("01.09.2024", "20.09.2024").clickSubmitBind();

        $x("//span[text() = 'Сохранено успешно']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("WEBHTCHR-1344 - По классу. Ошибка при добавлении привязки с пересечением периодов разных УП")
    void test1344() {
        String parallel = "1";
        String _class = "1-И";
        String studentName = "А И В";
        String planForAdd = "УП22";
        testPage.selectParalel(parallel).selectClass(_class).clickAddBind(studentName).selectCurriculaForStudent(studentName, planForAdd).clickSubmitBind();

        $x("//span[text() = 'Период привязки пересекается с периодом существующих привязок']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("WEBHTCHR-1345 - По классу. Ошибка при добавлении привязки с пересечением периодов одного УП")
    void test1345() {
        String parallel = "1";
        String _class = "1-И";
        String studentName = "А И В";
        String planForAdd = "керпвапрвапр";
        testPage.selectParalel(parallel).selectClass(_class).clickAddBind(studentName).selectCurriculaForStudent(studentName, planForAdd).clickSubmitBind();

        $x("//span[text() = 'Уже есть привязки на указанные даты']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("WEBHTCHR-1346 - По классу. Добавление новой привязки нескольким ученикам одновременно")
    void test1346() {
        String parallel = "1";
        String _class = "1-И";
        testPage.selectParalel(parallel).selectClass(_class).selectMainCurricula("УП22").
                selectStudent("В К А").selectStudent("В Ф М").clickBindSelected();

        $x("//span[text() = 'Привязка сохранена']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("WEBHTCHR-1348 - По классу. Наличие элементов при выбранных значениях параллели и класса")
    void test1348() {
        String studentName = "А И В";
        testPage.selectParalel("1").selectClass("1-И");
        SelenideElement plusBind = $x("//tr[.//a[text() = '%s']]/td[6]//*[local-name() = 'svg']".formatted(studentName));
        plusBind.hover();
        $x("//span[text() = 'Добавить привязку']").shouldBe(Condition.exist);
        testPage.clickContextRow("А И В", "-", CurriculaAndStudents.ContextOptionRow.DEMO);
        $x("//button[text() = 'Редактировать']").shouldBe(Condition.visible);
        $x("//button[text() = 'Удалить']").shouldBe(Condition.visible);

    }

    @Test
    @DisplayName("WEBHTCHR-1357 - Удаление привязки УП к учащимся")
    void test1357() {
        testPage.selectInParallel().clickDeleteAllBind("1-И");
        $x("//div[@class = 'D0Lm7cvJvrpX5urg62YR']//button[.//span[text() = 'Отмена']]").click();
        testPage.clickDeleteAllBind("1-Л").contextWindowDelete();
    }

    @Test
    @DisplayName("WEBHTCHR-1358 - Привязка УП к ученику")
    void test1358() {
        String studentName = "А А А";
        String plan = "УП22";
        String _class = "1-И";
        testPage.selectInParallel().expandClass(_class).bindToCurricula(plan, studentName).contextWindowCancel();
        testPage.bindToCurricula(plan, studentName).contextWindowSave();
    }

    @Test
    @DisplayName("WEBHTCHR-1360 - Фильтрация и сброс, вкладка По параллели")
    void test1360() {
        testPage.selectInParallel().selectParalel("3").clickReset();
        $x("//div[@class = 'hnWCePjI8XGQrc1e50Py lCYsN7syvm2qhN0uISEs' and .//span[text() = '1']]").shouldBe(Condition.exist);
    }

    @Test
    @DisplayName("WEBHTCHR-1361 - Наличие элементов при выбранном значении параллели")
    void test1361(){
        testPage.selectInParallel();
        String namePlan = "УП22";
        SelenideElement plan = $x("//td[.//span[text() = '%s']]".formatted(namePlan));
        plan.hover();
        ElementsCollection buttons = plan.$$x(".//*[local-name() = 'svg']");
        for(SelenideElement button: buttons){
            button.shouldBe(Condition.visible);
        }
    }

}
