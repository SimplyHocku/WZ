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

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

//    @AfterEach
//    public void clear() {
//        Selenide.refresh();
//    }

    @Test
    @DisplayName("WEBHTCHR-1044 - Создание учебного плана")
    void test1044() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        testPage.clickAddPP().selectPlanOption(PlanOption.PARALLEL);

        curriculaTestPage.fillPlan(curriculaTestPage).clickGenerate().expandSubjectArea("Учебные курсы").clickAddSubject("Учебные курсы").
                clickSubjectList().selectSubject("Астрономия").clickSubmitSubject().setHours("Астрономия", 1, "3");
        curriculaTestPage.setSubjectChoice("3D-арт").savePlan();

    }

    @Test
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
        Selenide.sleep(3000);
        assertEquals((String) data.get(6), curriculaTestPage.getSchedule(), "График не совпадет");

        String currentHours = curriculaTestPage.expandSubjectArea("Учебные курсы").getHours("Астрономия", 1);
        assertEquals("3", currentHours, "Часы нагрузки не сходятся");

    }

    @Test
    @DisplayName("WEBHTCHR-1131 - Редактирование учебного плана")
    @Disabled
    void test1131() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        String planName  = (String) curriculaTestPage.getDataForPlan().get(0);
        testPage.setSearchValue(planName).waitClosingLoader(30).openContextAndSelect(planName, ElementContextMenu.EDIT);
        curriculaTestPage.setTitle("AutomatedTitle_EDITED");
        curriculaTestPage.savePlan();
    }

    @Test
    @Disabled
    void test(){
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        testPage.clickAddPP().selectPlanOption(PlanOption.PARALLEL);

        curriculaTestPage.fillPlan(curriculaTestPage).clickGenerate().expandSubjectArea("Учебные курсы").clickAddSubject("Учебные курсы").
                clickSubjectList().selectSubject("Астрономия").clickSubmitSubject();
        curriculaTestPage.setSubjectChoice("3D-арт");

        curriculaTestPage.expandSubjectArea("Учебные курсы").setHours("Астрономия", 1, "3").copyHours("Астрономия", 1);
        Selenide.sleep(5000);


    }
}
