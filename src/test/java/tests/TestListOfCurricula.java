package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.WZ.config.Config;
import org.junit.jupiter.api.*;
import tests.pages.ListOfCurriculaPage;
import tests.pages.ListOfCurriculaPage.ElementContextMenu;
import tests.pages.ListOfCurriculaPage.PageView;
import tests.pages.CurriculaCreateOrEditPage;
import tests.pages.ListOfCurriculaPage.PlanOption;
import tests.pages.CurriculaCreateOrEditPage.FormEducationValue;
import tests.pages.CurriculaCreateOrEditPage.FormEducationValue;
import tests.pages.CurriculaCreateOrEditPage.LevelEducationValue;
import tests.pages.CurriculaCreateOrEditPage.AdaptiveValue;
import tests.pages.CurriculaCreateOrEditPage.WeekCopyValue;


import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestListOfCurricula extends Config {
    static ListOfCurriculaPage testPage;

    static String plans = "study-plan/list";
    static String patterns = "study-plan/list/templates";

    @BeforeAll
    public static void createTestPage() {
        testPage = new ListOfCurriculaPage();
    }

    @BeforeEach
    public void setUp() {
        Selenide.open(plans);
        testPage.divContent.shouldBe(Condition.visible);
    }

    @Test
    @Order(1)
    @DisplayName("WEBHTCHR-1044 - Создание учебного плана")
    void test1044() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        testPage.clickAddPP().selectPlanOption(PlanOption.PARALLEL);

        curriculaTestPage.fillPlan(curriculaTestPage).clickGenerate().expandSubjectArea("Учебные курсы").clickAddSubject("Учебные курсы").
                clickSubjectList().selectSubject("Астрономия").clickSubmitSubject().setHours("Астрономия", 1, "1").copyHours("Астрономия", 1, WeekCopyValue.EVERY);
        curriculaTestPage.setSubjectChoice("3D-арт").savePlan();

    }

    @Test
    @Order(2)
    @DisplayName("Проверка на соответствие тестового плана")
    void testCheckCreatedPlan(){
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        List<Object> data = curriculaTestPage.getDataForPlan();
        String planName  = (String) curriculaTestPage.getDataForPlan().get(0);
        SelenideElement rowElement = $x("//tr[@class = 'znu3DrCGSbeQf74VU_sQ' and contains(.//text(), '%s')]".formatted(planName));

        testPage.setSearchValue(planName).waitClosingLoader(30);
        rowElement.shouldBe(Condition.exist);
        testPage.openContextAndSelect(planName, ElementContextMenu.EDIT);
        $(".TPZSWYDuKpGEBOskP6qJ").shouldBe(Condition.exist);
        assertEquals(data.get(0), curriculaTestPage.getTitle(), "Заголовок не совпадает");
        assertEquals(data.get(1), curriculaTestPage.getShortTitle(), "Краткий заголовок не совпадает");
        assertEquals("Очно-заочная", curriculaTestPage.getFormEducation(), "Форма обучения");
        assertEquals("ООО", curriculaTestPage.getLevel(), "Уровень образования не совпадает");
        assertEquals((String) data.get(4), curriculaTestPage.getParallel(), "Параллель не совпадает");
        assertEquals((String) data.get(5), curriculaTestPage.getFgos(), "ФГОС не совпадает");
        assertEquals((String) data.get(7), curriculaTestPage.getWeek(), "Неделя не совпадает");
        curriculaTestPage.scheduleElem.shouldHave(Condition.text((String) data.get(6)));
        assertEquals((String) data.get(6), curriculaTestPage.getSchedule(), "График не совпадет");

        curriculaTestPage.expandSubjectArea("Учебные курсы").checkAllCellsExistsValue("Астрономия", "1");
    }

    @Test
    @Order(3)
    @DisplayName("WEBHTCHR-1131 - Редактирование учебного плана")
    void test1131() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        String planName  = (String) curriculaTestPage.getDataForPlan().get(0);
        testPage.setSearchValue(planName).waitClosingLoader(30).openContextAndSelect(planName, ElementContextMenu.EDIT);
        curriculaTestPage.editLabel.shouldBe(Condition.exist);
        curriculaTestPage.setTitle("AutomatedTitle_EDITED");
        curriculaTestPage.expandSubjectArea("Учебные курсы").clickAddSubject("Учебные курсы");
        curriculaTestPage.clickSubjectList().selectSubject("Алгоритмика").clickSubmitSubject();
        curriculaTestPage.setHours("Алгоритмика", 1, "2");
        curriculaTestPage.copyHours("Алгоритмика", 1, WeekCopyValue.INTWOWEEKS);
        curriculaTestPage.savePlan();
    }

    @Test
    @Order(4)
    @DisplayName("Проверка на соответствие тестового плана после редактирования")
    void testCheckEditedPlan(){
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        List<Object> data = curriculaTestPage.getDataForPlan();
        String planName  = (String) curriculaTestPage.getDataForPlan().get(0);
        SelenideElement rowElement = $x("//tr[@class = 'znu3DrCGSbeQf74VU_sQ' and contains(.//text(), '%s')]".formatted(planName));

        testPage.setSearchValue(planName).waitClosingLoader(30);
        rowElement.shouldBe(Condition.exist);
        testPage.openContextAndSelect(planName, ElementContextMenu.EDIT);
        $(".TPZSWYDuKpGEBOskP6qJ").shouldBe(Condition.exist);
        assertEquals("AutomatedTitle_EDITED", curriculaTestPage.getTitle(), "Заголовок не совпадает");
        assertEquals(data.get(1), curriculaTestPage.getShortTitle(), "Краткий заголовок не совпадает");
        assertEquals("Очно-заочная", curriculaTestPage.getFormEducation(), "Форма обучения");
        assertEquals("ООО", curriculaTestPage.getLevel(), "Уровень образования не совпадает");
        assertEquals((String) data.get(4), curriculaTestPage.getParallel(), "Параллель не совпадает");
        assertEquals((String) data.get(5), curriculaTestPage.getFgos(), "ФГОС не совпадает");
        assertEquals((String) data.get(7), curriculaTestPage.getWeek(), "Неделя не совпадает");
        curriculaTestPage.scheduleElem.shouldHave(Condition.text((String) data.get(6)));
        assertEquals((String) data.get(6), curriculaTestPage.getSchedule(), "График не совпадет");

        curriculaTestPage.expandSubjectArea("Учебные курсы").checkAllCellsExistsValue("Астрономия", "1");
        curriculaTestPage.expandSubjectArea("Учебные курсы").checkCellsExpectedOrContinueValue("Алгоритмика", "2");
    }

    @Test
    @Disabled
    void test(){
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        testPage.clickAddPP().selectPlanOption(PlanOption.PARALLEL);

        curriculaTestPage.fillPlan(curriculaTestPage).clickGenerate().expandSubjectArea("Учебные курсы").clickAddSubject("Учебные курсы").
                clickSubjectList().selectSubject("Астрономия").clickSubmitSubject();
        curriculaTestPage.setSubjectChoice("3D-арт");

        curriculaTestPage.expandSubjectArea("Учебные курсы").setHours("Астрономия", 1, "3").copyHours("Астрономия", 1, WeekCopyValue.EVERY);
        Selenide.sleep(5000);


    }
}
