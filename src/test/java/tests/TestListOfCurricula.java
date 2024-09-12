package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
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
                clickSubjectList().selectSubject("Астрономия").clickSubmitSubject();
        curriculaTestPage.setSubjectChoice("3D-арт").savePlan();
    }

    @Test
    @DisplayName("WEBHTCHR-1131 - Редактирование учебного плана")
    void test1131() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        String planName  = (String) curriculaTestPage.getDataForPlan().get(0);
        testPage.setSearchValue(planName).waitClosingLoader(30).openContextAndSelect(planName, ElementContextMenu.EDIT);
        curriculaTestPage.setTitle("AutomatedTitle_EDITED").savePlan();
    }
}
