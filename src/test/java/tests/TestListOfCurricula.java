package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.WZ.config.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.pages.ListOfCurriculaPage;
import tests.pages.ListOfCurriculaPage.ElementContextMenu;
import tests.pages.ListOfCurriculaPage.PageView;
import tests.pages.CurriculaCreateOrEditPage;
import tests.pages.ListOfCurriculaPage.PlanOption;
import tests.pages.CurriculaCreateOrEditPage.FormEducationValue;
import tests.pages.CurriculaCreateOrEditPage.FormEducationValue;
import tests.pages.CurriculaCreateOrEditPage.LevelEducationValue;

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

    @AfterEach
    public void clear() {
        Selenide.refresh();
    }

    @Test
    void test() {
        testPage.clickAddPP();
        testPage.selectPlanOption(PlanOption.PARALLEL);
        Selenide.sleep(3000);
        CurriculaCreateOrEditPage testPageCreatePlan = new CurriculaCreateOrEditPage();

        testPageCreatePlan.setTitle("тима").setShortTitle("небо").clickFormEducation().selectFormEducationValue(FormEducationValue.OZ).clickLevelEducation().selectLevelEducationValue(LevelEducationValue.OOO);
        testPageCreatePlan.clickParallel().selectParallel("8").clickFgos().setFgos("ФГОС 30.0").clickSchedule().setSchedule("asdasd").clickWeek().setWeek("5").clickGenerate();

//        testPageCreatePlan.setTitle("тима").setShortTitle("небо").clickFormEducation().selectFormEducationValue(FormEducationValue.OZ).clickLevelEducation().selectLevelEducationValue(CurriculaCreateOrEditPage.LevelEducationValue.OOO).clickParallel().selectParallel("8");
//        testPageCreatePlan.clickFgos();
        Selenide.sleep(3000);


    }
}
