package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.WZ.config.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.pages.CurriculaAndStudents;
import tests.pages.ListOfCurriculaPage;

import static com.codeborne.selenide.Selenide.$x;

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
    void test() {
        testPage.selectInParallel().selectParalel("1");
        testPage.clickDeleteCurricula("1 к", 1);

    }
}
