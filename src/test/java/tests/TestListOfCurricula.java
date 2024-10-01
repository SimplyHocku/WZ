package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.WZ.config.Config;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
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
import org.assertj.core.api.Assertions.*;


import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestListOfCurricula extends Config {
    static ListOfCurriculaPage testPage;

    static String plans = "study-plan/list";
    static String patterns = "study-plan/list/templates";
    private boolean createPlanFlag = false;
    private boolean createPlanPatternFlag = false;

    private boolean isCreatePlanFlag() {
        return !createPlanFlag;
    }

    private boolean isCreatePlanPatternFlag() {
        return !createPlanPatternFlag;
    }

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

        curriculaTestPage.fillPlan(curriculaTestPage, curriculaTestPage.getDataForPlan()).clickGenerate().expandSubjectArea("Учебные курсы").clickAddSubject("Учебные курсы").
                clickSubjectList().selectSubject("Астрономия").clickSubmitSubject().setHours("Астрономия", 1, "1").copyHours("Астрономия", 1, WeekCopyValue.EVERY);
        curriculaTestPage.setSubjectChoice("3D-арт").savePlan();
        createPlanFlag = true;

    }

    @Test
    @Order(2)
    @DisplayName("Проверка на соответствие тестового плана")
    @DisabledIf("isCreatePlanFlag")
    void testCheckCreatedPlan() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        List<Object> data = curriculaTestPage.getDataForPlan();
        String planName = (String) curriculaTestPage.getDataForPlan().get(0);
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
    @DisabledIf("isCreatePlanFlag")
    void test1131() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        String planName = (String) curriculaTestPage.getDataForPlan().get(0);
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
    @DisabledIf("isCreatePlanFlag")
    void testCheckEditedPlan() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        List<Object> data = curriculaTestPage.getDataForPlan();
        String planName = (String) curriculaTestPage.getDataForPlan().get(0);
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
    @Order(5)
    @DisplayName("WEBHTCHR-1133 - удаление УП")
    @DisabledIf("isCreatePlanFlag")
    void test1133() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        SelenideElement rowElement = $x("//tr[@class = 'znu3DrCGSbeQf74VU_sQ' and contains(.//text(), '%s')]".formatted("AutomatedTitle_EDITED"));
        testPage.setSearchValue("AutomatedTitle_EDITED").waitClosingLoader(30);
        rowElement.shouldBe(Condition.exist);
        testPage.openContextAndSelect("AutomatedTitle_EDITED", ElementContextMenu.DELETE);
        testPage.clickCancelDeletePlan().openContextAndSelect("AutomatedTitle_EDITED", ElementContextMenu.DELETE);
        testPage.clickDeletePlan();
    }

    @Test
    @Order(6)
    @DisplayName("Проверка удалился ли УП")
    @DisabledIf("isCreatePlanFlag")
    void testCheckDeletedPlan() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        testPage.setSearchValue("AutomatedTitle_EDITED").waitClosingLoader(30);
        $x("//span[text() = 'Данных для отображения пока нет']").shouldBe(Condition.exist);
    }

    @Test
    @Order(7)
    @DisplayName("WEBHTCHR-1067 - создание шаблона УП")
    void test1067() {
        testPage.listCurriculaTable.shouldBe(Condition.exist);
        testPage.selectPatternsUrl().clickAddPP();
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        curriculaTestPage.fillPlan(curriculaTestPage, curriculaTestPage.getDataForPatternPlan()).clickGenerate().expandSubjectArea("Учебные курсы").
                clickAddSubject("Учебные курсы").clickSubjectList().selectSubject("Инженерная графика").clickSubmitSubject().
                setHours("Инженерная графика", 1, "2").copyHours("Инженерная графика", 1, WeekCopyValue.EVERY).
                setSubjectChoice("3D-арт").setHoursSubChoice(1, "1").copyHoursSubjectChoice(1, WeekCopyValue.EVERY).savePlan();
        Selenide.sleep(1000);
        createPlanPatternFlag = true;
    }

    @Test
    @Order(8)
    @DisplayName("Проверка на соответствие шаблона УП")
    @DisabledIf("isCreatePlanPatternFlag")
    void testCheckCreateCurriculaPattern() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        List<Object> data = curriculaTestPage.getDataForPatternPlan();
        String planName = (String) curriculaTestPage.getDataForPatternPlan().get(0);
        SelenideElement rowElement = $x("//tr[@class = 'znu3DrCGSbeQf74VU_sQ' and contains(.//text(), '%s')]".formatted(planName));

        testPage.selectPatternsUrl().waitClosingLoader(30).setSearchValue(planName).waitClosingLoader(30);
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

        curriculaTestPage.expandSubjectArea("Учебные курсы").checkAllCellsExistsValue("Инженерная графика", "2");
    }

    @Test
    @Order(9)
    @DisplayName("WEBHTCHR-1132 - Редактирование шаблона учебного плана")
    @DisabledIf("isCreatePlanPatternFlag")
    void test1132() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        String planName = (String) curriculaTestPage.getDataForPatternPlan().get(0);
        testPage.selectPatternsUrl().waitClosingLoader(30).setSearchValue(planName).waitClosingLoader(30).openContextAndSelect(planName, ElementContextMenu.EDIT);
        curriculaTestPage.editLabelPattern.shouldBe(Condition.exist);
        curriculaTestPage.setTitle("AutomatedTitlePattern_EDITED");
        curriculaTestPage.expandSubjectArea("Учебные курсы").clickAddSubject("Учебные курсы");
        curriculaTestPage.clickSubjectList().selectSubject("Алгоритмика").clickSubmitSubject();
        curriculaTestPage.setHours("Алгоритмика", 1, "2");
        curriculaTestPage.copyHours("Алгоритмика", 1, WeekCopyValue.INTWOWEEKS);
        curriculaTestPage.savePlan();
    }

    @Test
    @Order(10)
    @DisplayName("Проверка на соответствие шаблона УП - после редактирования")
    @DisabledIf("isCreatePlanPatternFlag")
    void testCheckEditedPlanPattern() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        List<Object> data = curriculaTestPage.getDataForPatternPlan();
        String planName = (String) curriculaTestPage.getDataForPatternPlan().get(0);
        SelenideElement rowElement = $x("//tr[@class = 'znu3DrCGSbeQf74VU_sQ' and contains(.//text(), '%s')]".formatted(planName));

        testPage.selectPatternsUrl().waitClosingLoader(30).setSearchValue(planName).waitClosingLoader(30);
        rowElement.shouldBe(Condition.exist);
        testPage.openContextAndSelect(planName, ElementContextMenu.EDIT);
        $(".TPZSWYDuKpGEBOskP6qJ").shouldBe(Condition.exist);
        assertEquals("AutomatedTitlePattern_EDITED", curriculaTestPage.getTitle(), "Заголовок не совпадает");
        assertEquals(data.get(1), curriculaTestPage.getShortTitle(), "Краткий заголовок не совпадает");
        assertEquals("Очно-заочная", curriculaTestPage.getFormEducation(), "Форма обучения");
        assertEquals("ООО", curriculaTestPage.getLevel(), "Уровень образования не совпадает");
        assertEquals((String) data.get(4), curriculaTestPage.getParallel(), "Параллель не совпадает");
        assertEquals((String) data.get(5), curriculaTestPage.getFgos(), "ФГОС не совпадает");
        assertEquals((String) data.get(7), curriculaTestPage.getWeek(), "Неделя не совпадает");
        curriculaTestPage.scheduleElem.shouldHave(Condition.text((String) data.get(6)));
        assertEquals((String) data.get(6), curriculaTestPage.getSchedule(), "График не совпадет");

        curriculaTestPage.expandSubjectArea("Учебные курсы").checkCellsExpectedOrContinueValue("Алгоритмика", "2");
    }

    @Test
    @Order(11)
    @DisplayName("WEBHTCHR-1134 - удаление шаблона УП")
    @DisabledIf("isCreatePlanPatternFlag")
    void test1134(){
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        SelenideElement rowElement = $x("//tr[@class = 'znu3DrCGSbeQf74VU_sQ' and contains(.//text(), '%s')]".formatted("AutomatedTitlePattern_EDITED"));
        testPage.selectPatternsUrl().waitClosingLoader(30).setSearchValue("AutomatedTitlePattern_EDITED").waitClosingLoader(30);
        rowElement.shouldBe(Condition.exist);
        testPage.openContextAndSelect("AutomatedTitlePattern_EDITED", ElementContextMenu.DELETE);
        testPage.clickCancelDeletePlan().openContextAndSelect("AutomatedTitlePattern_EDITED", ElementContextMenu.DELETE);
        testPage.clickDeletePlan();
    }
    @Test
    @Order(12)
    @DisplayName("Проверка удалился ли шаблон УП")
    @DisabledIf("isCreatePlanPatternFlag")
    void checkDeletedPlanPattern(){
        testPage.selectPatternsUrl().waitClosingLoader(30).setSearchValue("AutomatedTitlePattern_EDITED").waitClosingLoader(30);
        $x("//span[text() = 'Данных для отображения пока нет']").shouldBe(Condition.exist);
    }


    @Test
    @Order(14)
    @DisplayName("WEBHTCHR-1173 - вкладка шаблоны учебных планов")
    void test1173() {
        testPage.selectPatternsUrl().waitClosingLoader(30).listCurriculaTable.shouldBe(Condition.visible);
    }

    @Test
    @Order(15)
    @DisplayName("WEBHTCHR-1174 - кнопка скачать план")
    void test1174() {
        testPage.waitClosingLoader(30).listCurriculaTable.shouldBe(Condition.visible);
        testPage.openParallelsField().setParallel("10").setSearchValue("НЕ УДАЛАЙ!!!").waitClosingLoader(30).setCheckbox("НЕ УДАЛАЙ!!!", true).clickBtnDownload();
        $x("//span[text() = 'Сохранено']").shouldBe(Condition.exist);
    }

    @Test
    @Order(16)
    @DisplayName("WEBHTCHR-1175 - Кнопка Добавить учебный план")
    void test1175() {
        testPage.waitClosingLoader(30).listCurriculaTable.shouldBe(Condition.visible);
        testPage.clickAddPP().selectPlanOption(PlanOption.PARALLEL).listCurriculaTable.shouldBe(Condition.visible);
    }

    @Test
    @Order(17)
    @DisplayName("WEBHTCHR-1176 - параллель учебных планов")
    void test1176() {
        testPage.waitClosingLoader(30).listCurriculaTable.shouldBe(Condition.visible);
        testPage.openParallelsField().setParallel("10").waitClosingLoader(30);
        ElementsCollection values = $(".hXA2Gn8zqZ4xBmiyEWhH").$$x(".//span[translate(text(), '0123456789', '') = '']");
        org.assertj.core.api.Assertions.assertThat(values).as("Отображаются только выбранные параллели").allMatch(elem -> {
            if (elem.text().isEmpty()) {
                return true;
            } else if (elem.text().equals("10")) {
                return true;
            }
            return false;
        });
    }

    @Test
    @Order(18)
    @DisplayName("WEBHTCHR-1182 - вкладка учебные планы")
    void test1182() {
        testPage.waitClosingLoader(30).listCurriculaTable.shouldBe(Condition.visible);
        testPage.selectPatternsUrl().selectPlansUrl().waitClosingLoader(30).listCurriculaTable.shouldBe(Condition.visible);
    }

    @Test
    @Order(19)
    @DisplayName("WEBHTCHR-1224 - кнопка скачать шаблон")
    void test1224() {
        testPage.waitClosingLoader(30).selectPatternsUrl().listCurriculaTable.shouldBe(Condition.visible);
        testPage.openParallelsField().setParallel("9").setSearchValue("НЕ_УДАЛЯЙ_ШАБ!!!").waitClosingLoader(30).setCheckbox("НЕ_УДАЛЯЙ_ШАБ!!!", true).clickBtnDownload();
        $x("//span[text() = 'Сохранено']").shouldBe(Condition.exist);
    }

    @Test
    @Order(20)
    @DisplayName("WEBHTCHR-1183 - кнопка добавить шаблон учебного плана")
    void test1183() {
        testPage.waitClosingLoader(30).selectPatternsUrl().listCurriculaTable.shouldBe(Condition.visible);
        testPage.clickAddPP();
        $x("//li[text() = 'Новый шаблон учебного плана']").shouldBe(Condition.exist);
    }

    @Test
    @Order(21)
    @DisplayName("WEBHTCHR-1185 - параллель шаблонов учебных планов")
    void test1185() {
        testPage.waitClosingLoader(30).listCurriculaTable.shouldBe(Condition.visible);
        testPage.selectPatternsUrl().waitClosingLoader(30).openParallelsField().setParallel("10").waitClosingLoader(30);
        ElementsCollection values = $(".hXA2Gn8zqZ4xBmiyEWhH").$$x(".//span[translate(text(), '0123456789', '') = '']");
        org.assertj.core.api.Assertions.assertThat(values).as("Отображаются только выбранные параллели").allMatch(elem -> {
            if (elem.text().isEmpty()) {
                return true;
            } else if (elem.text().equals("10")) {
                return true;
            }
            return false;
        });
    }

    @Test
    @Order(22)
    @DisplayName("WEBHTCHR-1220 - Чекбокс Адаптированная программа - Выпадающий список Нагрузка АООП УП")
    void test1220() {
        testPage.waitClosingLoader(30).listCurriculaTable.shouldBe(Condition.visible);
        testPage.clickAddPP().selectPlanOption(PlanOption.PARALLEL);
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        curriculaTestPage.clickAdaptiveCheckbox().clickLevelEducation().selectLevelEducationValue(LevelEducationValue.OOO).
                clickAdaptiveField().setAdaptiveValue(AdaptiveValue.INCREASE);
        assertEquals("Увеличение срока освоения ООП для ОВЗ", curriculaTestPage.getAdaptiveFieldValue());
        curriculaTestPage.clickAdaptiveCheckbox();
        $x("//span[text() = 'Нагрузка АООП']").shouldNotBe(Condition.visible);
    }

    @Test
    @Order(23)
    @DisplayName("WEBHTCHR-1223 - Кнопка Сгенерировать пустой план УП")
    void test1223() {
        testPage.waitClosingLoader(30).listCurriculaTable.shouldBe(Condition.visible);
        testPage.clickAddPP().selectPlanOption(PlanOption.PARALLEL);
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        curriculaTestPage.fillPlan(curriculaTestPage, curriculaTestPage.getDataForPlanTest()).clickGenerate();
        $x("//span[text() = 'Наполнение учебного плана']").shouldBe(Condition.visible);
    }

    @Test
    @Order(24)
    @DisplayName("WEBHTCHR-1246 - Кнопка Сгенерировать пустой план УП шаблон")
    void test1246() {
        testPage.waitClosingLoader(30).listCurriculaTable.shouldBe(Condition.visible);
        testPage.selectPatternsUrl().clickAddPP();
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        curriculaTestPage.fillPlan(curriculaTestPage, curriculaTestPage.getDataForPlanTest()).clickGenerate();
        $x("//span[text() = 'Наполнение учебного плана']").shouldBe(Condition.visible);
    }

//    @Test
//    @Order(25)
//    @DisplayName("WEBHTCHR-1248 - Переключатель По предметам УП")
//    void test1248() {
////        TODO
//    }

    @Test
    @Order(26)
    @DisplayName("WEBHTCHR-1249 - Список предметной области УП")
    void test1249() {
        testPage.waitClosingLoader(30).listCurriculaTable.shouldBe(Condition.visible);
        testPage.clickAddPP().selectPlanOption(PlanOption.PARALLEL);
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        curriculaTestPage.fillPlan(curriculaTestPage, curriculaTestPage.getDataForPlanTest()).clickGenerate();
        $x("//span[text() = 'Наполнение учебного плана']").shouldBe(Condition.visible);
        curriculaTestPage.expandSubjectArea("Учебные курсы");
        SelenideElement divContent = $x("//div[@class = 'I4cQQg2x1N0wu85nv4PA' and .//span[text() = 'Предметы по выбору']]");
        SelenideElement btn = $(divContent).$x(".//span[text() = 'Добавить предмет']");
//        btn.hover();
        actions().scrollToElement(btn).perform();
        btn.shouldBe(Condition.visible);
        curriculaTestPage.expandSubjectArea("Учебные курсы");
        $x("//span[text() = 'Добавить предмет']").shouldNotBe(Condition.visible);
    }


    @Test
    @Disabled
    void test() {
        CurriculaCreateOrEditPage curriculaTestPage = new CurriculaCreateOrEditPage();
        testPage.clickAddPP().selectPlanOption(PlanOption.PARALLEL);

        curriculaTestPage.fillPlan(curriculaTestPage, curriculaTestPage.getDataForPlanTest()).clickGenerate().expandSubjectArea("Учебные курсы").clickAddSubject("Учебные курсы").
                clickSubjectList().selectSubject("Астрономия").clickSubmitSubject();
        curriculaTestPage.setSubjectChoice("3D-арт");

        curriculaTestPage.expandSubjectArea("Учебные курсы").setHours("Астрономия", 1, "3").copyHours("Астрономия", 1, WeekCopyValue.EVERY);
        Selenide.sleep(5000);


    }
}
