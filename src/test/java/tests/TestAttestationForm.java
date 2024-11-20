package tests;


import com.codeborne.selenide.Selenide;
import org.WZ.config.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.pages.AttestationForm;


public class TestAttestationForm extends Config {
    static AttestationForm testPage;

    @BeforeAll
    public static void createTestPage() {
        testPage = new AttestationForm();
    }

    @BeforeEach
    public void setUp() {
        Selenide.open("handbook/final-attestation-form");
    }


    @Test
    void test(){
        testPage.clickToPeriods().clickAddSchedulePeriods().setNewPeriodTitleMain("Тестовое").increaseCountPeriods(1).setNewPeriodTitle(2, "Test").setDataForPeriod(1, "20.11.2024", "23.11.2024");
    }
}
