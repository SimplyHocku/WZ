package tests;

import com.codeborne.selenide.*;
import org.WZ.config.Config;
import org.junit.jupiter.api.*;
import tests.pages.SubjectsPage;
import org.openqa.selenium.Dimension;


import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestSubjects extends Config {
    static String currentSection = "handbook/subjects";
    static SubjectsPage testPage;

    @BeforeAll
    public static void createTestPage() {
        Selenide.open(currentSection);
        testPage = new SubjectsPage();
    }

    @BeforeEach
    public void setUp() {
        testPage.mainBlock.shouldBe(Condition.visible);
    }

    @AfterEach
    public void clear() {
        testPage.pushResetBtn();
    }

    @Test
    @DisplayName("WEBHTCHR-896 - Поиск по названию предмета")
    void test896() {
        Dimension sizeBefore = testPage.getDivContentSize();
        testPage.setSearchFieldValue("Астрономия");
        testPage.searchField.shouldHave(Condition.value("Астрономия"));

        assertTrue(testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60));
        assertTrue(testPage.checkMainTableExistsValue(".//span[contains(text(), '%s')]", "строномия"));

        testPage.pushResetBtn();
        testPage.searchField.shouldHave(Condition.empty);

        sizeBefore = testPage.getDivContentSize();
        assertTrue(testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60));
        testPage.setSearchFieldValue("@######");
        testPage.searchField.shouldHave(Condition.value("@######"));

        sizeBefore = testPage.getDivContentSize();
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);
        testPage.checkTablesContentIsEmpty();
    }

    @Test
    @DisplayName("WEBHTCHR-899 - Фильтрация по уровню образования")
    void test899() {
        Dimension sizeBefore = testPage.getDivContentSize();
        testPage.setCheckbox(SubjectsPage.checkBoxValue.NOO);
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);
        assertTrue(testPage.checkMainTableExistsValue(".//span[text() = '%s']", "НОО"));

        sizeBefore = testPage.getDivContentSize();
        testPage.pushResetBtn();
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);

        sizeBefore = testPage.getDivContentSize();
        testPage.setCheckbox(SubjectsPage.checkBoxValue.NOO).setCheckbox(SubjectsPage.checkBoxValue.SOO);
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);
        assertTrue(testPage.checkMainTableExistsValue(".//span[text() = '%s']", "НОО", "СОО"));
    }

    @Test
    @DisplayName("WEBHTCHR-900 - Фильтрация по предметной области")
    void test900() {
        Selenide.sleep(5000);
        testPage.pushSubjectArea();
        $(".E83e3rk6TcOHYz8_bdv3").shouldBe(Condition.visible);
        Dimension sizeBefore = testPage.getDivContentSize();
        testPage.selectSubjectAreaElem("Естествознание");
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);
        assertTrue(testPage.checkMainTableExistsValue(".//span[text() = '%s']", "Естествознание"));
    }

    @Test
    @DisplayName("WEBHTCHR-901 - Фильтрация по режиму отображения")
    void test901() {
        Dimension sizeBefore = testPage.getDivContentSize();
        testPage.selectShowMode(SubjectsPage.radioValue.ADAPTIVE);
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);
        assertTrue(testPage.checkMainTableExistsValue(".//span[text() = '%s']", "АООП"));

        sizeBefore = testPage.getDivContentSize();
        testPage.pushResetBtn();
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);

        sizeBefore = testPage.getDivContentSize();
        testPage.selectShowMode(SubjectsPage.radioValue.DELETE);
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);
        testPage.checkTablesContentIsEmpty();
    }

    @Test
    @DisplayName("WEBHTCHR-922 - Просмотр")
    void test922() {
        Dimension sizeBefore = testPage.getDivContentSize();
        testPage.selectShowMode(SubjectsPage.radioValue.ADAPTIVE);
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);

        SelenideElement elementWithElements = testPage.tablesContent.get(0).$$x(".//tr[@class='znu3DrCGSbeQf74VU_sQ']").get(0).shouldBe(Condition.visible);
        SelenideElement aoopOrOopBtn = $(".addHoKnJHw3XjzJKFeLg.PM3B6Ywdzl_1Hr4KMU18");
        aoopOrOopBtn.hover();
        $x("//span[text() = 'Адаптированная основная общеобразовательная программа']").shouldBe(Condition.exist);
        sizeBefore = testPage.getDivContentSize();
        testPage.pushResetBtn();
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);
        sizeBefore = testPage.getDivContentSize();
        testPage.setSearchFieldValue("Астрономия").searchField.shouldHave(Condition.value("Астрономия"));
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);

        aoopOrOopBtn.shouldBe(Condition.visible).hover();
        elementWithElements.$x(".//span[text() = 'Астрономия']").shouldBe(Condition.exist);
        $x("//span[text() = 'Основная общеобразовательная программа']").shouldBe(Condition.exist);

        sizeBefore = testPage.getDivContentSize();
        testPage.pushResetBtn().searchField.shouldBe(Condition.empty);
        testPage.setSearchFieldValue("Второй иностранный язык");
        testPage.waitForElementSizeChange(testPage.divContent, sizeBefore, 60);
        elementWithElements.$(".hnWCePjI8XGQrc1e50Py.xBXooQGT7LBOkVI4h7nA.T3JvfGx2I776F31TUtcB").shouldBe(Condition.visible).click();
        elementWithElements.$(".hnWCePjI8XGQrc1e50Py.lCYsN7syvm2qhN0uISEs").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("WEBHTCHR-4176 - Кнопка скрытия/раскрытия в панели фильтров")
    void test4176() {
        SelenideElement openFilters = $(".Zma57HfFxWbyKr5Ev5WQ");
        SelenideElement closedFilters = $(".Zma57HfFxWbyKr5Ev5WQ.zwURGUouIFZ1NyGOdaEK");
        testPage.pushBtnCloseFilter();
        closedFilters.shouldBe(Condition.exist);
        testPage.pushBtnCloseFilter();
        openFilters.shouldBe(Condition.exist);
    }
}
