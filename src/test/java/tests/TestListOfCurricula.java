package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.WZ.config.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.pages.ListOfCurriculaPage;

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
    public void clear(){
        Selenide.refresh();
    }

    @Test
    void test(){
        testPage.waitClosingLoader(60).selectPatternsUrl().waitClosingLoader(60).selectPlansUrl().setSearchValue("test");
        testPage.openParallelsField();
        Selenide.sleep(3000);
        testPage.checkParallelsOpen();
//        testPage.selectPatternsUrl()


    }
}
