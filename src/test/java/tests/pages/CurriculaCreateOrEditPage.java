package tests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class CurriculaCreateOrEditPage {

    private final SelenideElement title = $(".Z5eYm_jkqZySVYXnxWEL.IhWORM0RCfM_znxFDK4e.ErrgUCgBVpOGk2uD7y4R.IfzJEdB1pkbERSIASybF");
    private final SelenideElement shortTitle = $$(".Z5eYm_jkqZySVYXnxWEL.IhWORM0RCfM_znxFDK4e.ErrgUCgBVpOGk2uD7y4R.IfzJEdB1pkbERSIASybF").get(1);
    private final SelenideElement formEducation = $$(".Z5eYm_jkqZySVYXnxWEL.jDrnuuOrYGEtWVtT_jwL.JN9rAf0zV1reNm6noHyf").get(0);
    private final SelenideElement levelEducation = $$(".NQp5PvHbsg3zw0dqR7BG.U9gZqh4HBcPhDx8E2kxw.Kwq17kqGR1gd2i3e8T4j.t_nfk5KQShwEOZOwRQAr").get(1);
    private final SelenideElement parallelEducation = $$(".NQp5PvHbsg3zw0dqR7BG.U9gZqh4HBcPhDx8E2kxw.Kwq17kqGR1gd2i3e8T4j.t_nfk5KQShwEOZOwRQAr").get(2);
    private final SelenideElement adaptiveAOOPFiled = $$(".NQp5PvHbsg3zw0dqR7BG.U9gZqh4HBcPhDx8E2kxw.Kwq17kqGR1gd2i3e8T4j.t_nfk5KQShwEOZOwRQAr").get(7);
    private final SelenideElement fgosEducation = $$(".uRZO5BUKZP9uacRiPpPg").get(3);
    private final SelenideElement scheduleEducation = $$(".uRZO5BUKZP9uacRiPpPg").get(4);
    public final SelenideElement scheduleElem = $$(".Z5eYm_jkqZySVYXnxWEL.rWtYydTGjRz8vkOQ02JO.JN9rAf0zV1reNm6noHyf").get(4);
    private final SelenideElement weekEducation = $$(".uRZO5BUKZP9uacRiPpPg").get(5);

    private final SelenideElement adaptiveCheckbox = $(".NQp5PvHbsg3zw0dqR7BG.Kwq17kqGR1gd2i3e8T4j.VPTO55M4zBKFXTfcgLbE");

    private final SelenideElement btnCancel = $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.XmjWECPjgnwA1tkkgKBr.XSReFeIOyNCXZG6tRWvh");
    private final SelenideElement btnGenerate = $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.Mv0ARbCy0YnB7r_2NLvE.lyW5jmphtuQeL51jZoCw");
    private final String subjectArea = "hnWCePjI8XGQrc1e50Py xBXooQGT7LBOkVI4h7nA";
    private final SelenideElement searchSubjectField = $(".Z5eYm_jkqZySVYXnxWEL.IhWORM0RCfM_znxFDK4e.ErrgUCgBVpOGk2uD7y4R.sW0pcwdJYKkfIrzs94YW.rzaiEMnocKsMWjls6Bgw");
    private final SelenideElement btnConfirmPattern = $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.z14S3NJYxReEAxFG8wJF.lyW5jmphtuQeL51jZoCw");
    private final SelenideElement btnSaveSuchPattern = $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.Mv0ARbCy0YnB7r_2NLvE.XSReFeIOyNCXZG6tRWvh");
    private final SelenideElement btnSave = $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.Mv0ARbCy0YnB7r_2NLvE.lyW5jmphtuQeL51jZoCw");

    public  final SelenideElement editLabel = $x("//h4[contains(text(), 'Редактирование  учебного плана')]");
    public CurriculaCreateOrEditPage setTitle(String value) {
        boolean isEmpty = Objects.requireNonNull(this.title.getValue()).isEmpty();
        if (!isEmpty){
            this.title.sendKeys(Keys.LEFT_CONTROL, "A");
            this.title.sendKeys(Keys.BACK_SPACE);
        }
        this.title.shouldHave(Condition.empty);
        this.title.setValue(value);
        return this;
    }

    public CurriculaCreateOrEditPage clearTitle() {
        Selenide.executeJavaScript("arguments[0].value = '';", this.title);
        return this;
    }

    public String getTitle() {
        return this.title.getValue();
    }

    public CurriculaCreateOrEditPage setShortTitle(String value) {
        boolean isEmpty = Objects.requireNonNull(this.shortTitle.getValue()).isEmpty();
        if (!isEmpty){
            this.shortTitle.sendKeys(Keys.LEFT_CONTROL, "A");
            this.shortTitle.sendKeys(Keys.BACK_SPACE);
        }
        this.shortTitle.setValue(value);
        return this;
    }

    public String getShortTitle() {
        return this.shortTitle.getValue();
    }
    public String getFormEducation(){
        return $$(".Z5eYm_jkqZySVYXnxWEL.rWtYydTGjRz8vkOQ02JO.JN9rAf0zV1reNm6noHyf").get(0).getText();
    }
    public String getLevel(){
        return $$(".Z5eYm_jkqZySVYXnxWEL.rWtYydTGjRz8vkOQ02JO.JN9rAf0zV1reNm6noHyf").get(1).getText();
    }
    public String getParallel(){
        return $$(".Z5eYm_jkqZySVYXnxWEL.rWtYydTGjRz8vkOQ02JO.JN9rAf0zV1reNm6noHyf").get(2).getText();
    }

    public String getFgos(){
        return $$(".Z5eYm_jkqZySVYXnxWEL.rWtYydTGjRz8vkOQ02JO.JN9rAf0zV1reNm6noHyf").get(3).getText();
    }

    public String getSchedule(){
        return $$(".Z5eYm_jkqZySVYXnxWEL.rWtYydTGjRz8vkOQ02JO.JN9rAf0zV1reNm6noHyf").get(4).getText();
    }

    public String getWeek(){
        return $$(".Z5eYm_jkqZySVYXnxWEL.rWtYydTGjRz8vkOQ02JO.JN9rAf0zV1reNm6noHyf").get(5).getText();
    }

    public CurriculaCreateOrEditPage clickFormEducation() {
        this.formEducation.click();
        return this;
    }

    public CurriculaCreateOrEditPage selectFormEducationValue(FormEducationValue value) {
        switch (value) {
            case O -> $x("//li[text() = 'Очная']").click();
            case OZ -> $x("//li[text() = 'Очно-заочная']").click();
            case Z -> $x("//li[text() = 'Заочная']").click();
        }
        return this;
    }

    public CurriculaCreateOrEditPage clickLevelEducation() {
        this.levelEducation.click();
        return this;
    }

    public CurriculaCreateOrEditPage selectLevelEducationValue(LevelEducationValue value) {
        Selenide.sleep(2000);
        switch (value) {
            case NOO -> $x("//li[text() = 'НОО']").click();
            case OOO -> $x("//li[text() = 'ООО']").click();
            case SOO -> $x("//li[text() = 'СОО']").click();
            case SPO -> $x("//li[text() = 'СПО']").click();
        }
        return this;
    }

    public CurriculaCreateOrEditPage clickParallel() {
        this.parallelEducation.click();
        return this;
    }

    public CurriculaCreateOrEditPage selectParallel(String value) {
        $x("//li[text() = '%s']".formatted(value)).click();
        return this;
    }

    public CurriculaCreateOrEditPage clickFgos() {
        this.fgosEducation.click();
        return this;
    }

    public CurriculaCreateOrEditPage setFgos(String value) {
        $x("//li[text() = '%s']".formatted(value)).click();
        return this;
    }

    public CurriculaCreateOrEditPage clickSchedule() {
        this.scheduleEducation.click();
        return this;
    }

    public CurriculaCreateOrEditPage setSchedule(String value) {
        $x("//li[text() = '%s']".formatted(value)).click();
        return this;
    }


    public CurriculaCreateOrEditPage clickWeek() {
        this.weekEducation.click();
        return this;
    }

    public CurriculaCreateOrEditPage setWeek(String value) {
        $x("//li[text() = '%s']".formatted(value)).click();
        return this;
    }

    public CurriculaCreateOrEditPage clickAdaptiveCheckbox() {
        this.adaptiveCheckbox.click();
        return this;
    }

    public CurriculaCreateOrEditPage clickAdaptiveField() {
        this.adaptiveAOOPFiled.click();
        return this;
    }

    public CurriculaCreateOrEditPage setAdaptiveValue(AdaptiveValue value) {
        switch (value) {
            case INCREASE -> $x("//li[text() = 'Увеличение срока освоения ООП для ОВЗ']").click();
            case WITHOUT -> $x("//li[text() = 'Без увеличения срока освоения ООП для ОВЗ']").click();
            case NOT -> $x("//li[text() = 'Нецензовое образование']").click();
        }
        return this;

    }

    public CurriculaCreateOrEditPage clickCancel() {
        this.btnCancel.click();
        return this;
    }

    public CurriculaCreateOrEditPage clickGenerate() {
        this.btnGenerate.click();
        return this;
    }

    public List<Object> getDataForPlan() {
        return new ArrayList<Object>(Arrays.asList("AutomatedTitle", "AT", FormEducationValue.OZ, LevelEducationValue.OOO, "9", "ФГОС 3.0", "КУГ для 10У класса", "5"));
    }

    public List<Object> getDataForPatternPlan() {
        return new ArrayList<Object>(Arrays.asList("AutomatedTitlePattern", "ATP", FormEducationValue.OZ, LevelEducationValue.OOO, "9", "ФГОС 3.0", "КУГ для 10У класса", "5"));
    }


    public CurriculaCreateOrEditPage fillPlan(CurriculaCreateOrEditPage currentPage, List<Object> data) {
        currentPage.setTitle((String) data.get(0)).setShortTitle((String) data.get(1)).clickFormEducation().selectFormEducationValue((FormEducationValue) data.get(2));
        currentPage.clickLevelEducation().selectLevelEducationValue((LevelEducationValue) data.get(3)).clickParallel().selectParallel((String) data.get(4));
        currentPage.clickFgos().setFgos((String) data.get(5)).clickSchedule().setSchedule((String) data.get(6));
        currentPage.clickWeek().setWeek((String) data.get(7));
        return currentPage;
    }

    public CurriculaCreateOrEditPage setSubjectChoice(String subjectName) {
        SelenideElement divWithChoice = $x("//div[@class = 'D0EWo2EGLwKKKkK9cgQi' and .//span[text() = '%s']]".formatted("Предметы по выбору"));
        SelenideElement plusSubjectBtn = divWithChoice.$(".NQp5PvHbsg3zw0dqR7BG.tcpt9oq21v1w8DS00Gdq.Kwq17kqGR1gd2i3e8T4j.glgOeYZM68NoKNYki096");
        divWithChoice.click();
        plusSubjectBtn.click();
        this.clickSubjectList().selectSubject(subjectName).clickSubmitSubject();
        return this;
    }

    public CurriculaCreateOrEditPage expandSubjectArea(String subjectAreaValue) {
        $x("//div[@class = '%s' and .//span[text() = '%s']]".formatted(this.subjectArea, subjectAreaValue)).click();
        return this;
    }

    public CurriculaCreateOrEditPage clickSubjectList() {
        $x("//div[@class = 'mdlBeTDjk4eJspBtgT8g hETISWSYE0TZ8F37t9IZ' and .//span[text() = 'Предмет']]").click();
        return this;
    }

    public CurriculaCreateOrEditPage clickSubmitSubject() {
        $$(".NQp5PvHbsg3zw0dqR7BG.tcpt9oq21v1w8DS00Gdq.Kwq17kqGR1gd2i3e8T4j.iHQYNPDlyB9iKeoLOX6w").get(1).click();
        return this;
    }

    public CurriculaCreateOrEditPage setSubjectSearchField(String value) {
        boolean isEmpty = Objects.requireNonNull(this.searchSubjectField.getValue()).isEmpty();
        if (!isEmpty){
            this.searchSubjectField.sendKeys(Keys.LEFT_CONTROL, "A");
            this.searchSubjectField.sendKeys(Keys.BACK_SPACE);
        }
        this.searchSubjectField.setValue(value);
        return this;
    }

    public CurriculaCreateOrEditPage selectSubject(String value) {
        $x("//span[text() = '%s']".formatted(value)).click();
        return this;
    }

    public CurriculaCreateOrEditPage clickCancelSubject() {
        $$(".NQp5PvHbsg3zw0dqR7BG.tcpt9oq21v1w8DS00Gdq.Kwq17kqGR1gd2i3e8T4j.iHQYNPDlyB9iKeoLOX6w").get(2).click();
        return this;
    }

    public CurriculaCreateOrEditPage clickAddSubject(String subjectAreaValue) {
        SelenideElement areaDiv = $x("//div[@class = 'D0EWo2EGLwKKKkK9cgQi' and .//span[text() = '%s']]".formatted(subjectAreaValue));
        SelenideElement btnAddSubject = areaDiv.$x(".//span[text() = 'Добавить предмет']");
        btnAddSubject.click();
        return this;
    }


    public CurriculaCreateOrEditPage clickConfirmPattern() {
        this.btnConfirmPattern.click();
        return this;
    }

    public CurriculaCreateOrEditPage clickSavePatternBtn() {
        $(".JHHSSmXrEDClsNtfHZOf.JJ4kl5s04PDNCOaw1ADj").$(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.Mv0ARbCy0YnB7r_2NLvE.lyW5jmphtuQeL51jZoCw").click();
        return this;
    }

    public CurriculaCreateOrEditPage setPattern(String patternName) {
        $x("//label[@class='MuiFormLabel-root MuiInputLabel-root HYUVjQy9TBuYwcyYaWjw OIZywbwrQHHOpr7_sUp6 JbNllXTS6oImUvZYZSgH MuiInputLabel-animated' and .//span[text() = '%s' ]]".formatted(patternName)).click();
        return this;
    }

    public CurriculaCreateOrEditPage saveSuchPattern() {
        this.btnSaveSuchPattern.click();
        return this;
    }

    public CurriculaCreateOrEditPage savePlan() {
        this.btnSave.click();
        return this;
    }

    public CurriculaCreateOrEditPage setHours(String subject, int cell, String value){
        SelenideElement mainRowDiv = $x("//div[@class = ' rXMpdIDvlcwzffjY4sO4' and .//span[text() = '%s']]".formatted(subject));
        SelenideElement subjectNameDiv = mainRowDiv.$x(".//div[span[text() = '%s']]".formatted(subject)).parent().parent();
        SelenideElement divCells = subjectNameDiv.sibling(0);
        ElementsCollection cells = divCells.$$x(".//div");
        cells.get(cell).$x(".//input").setValue(value);
        return this;
    }

    public String getHours(String subject, int cell){
        SelenideElement mainRowDiv = $x("//div[@class = ' rXMpdIDvlcwzffjY4sO4' and .//span[text() = '%s']]".formatted(subject));
        ElementsCollection cells = mainRowDiv.$(".guSkr79ef8UMyfqDq1sZ").$$x(".//div");
        return cells.get(cell).$x(".//input").getValue();
    }



    public CurriculaCreateOrEditPage copyHours(String subject, int cellNum, WeekCopyValue mode){
        SelenideElement mainRowDiv = $x("//div[@class = ' rXMpdIDvlcwzffjY4sO4' and .//span[text() = '%s']]".formatted(subject));
        SelenideElement subjectNameDiv = mainRowDiv.$x(".//div[span[text() = '%s']]".formatted(subject)).parent().parent();
        SelenideElement divCells = subjectNameDiv.sibling(0).$x(".//div[@class = ' guSkr79ef8UMyfqDq1sZ']");
        ElementsCollection cells = divCells.$$x(".//div");
        SelenideElement cell = cells.get(cellNum);
        actions().moveToElement(cell).moveByOffset(5,10 ).click().perform();

        switch (mode){
            case EVERY -> $x("//button[text() = 'На каждую неделю']").click();
            case INWEEK -> $x("//button[text() = 'Через неделю']").click();
            case INTWOWEEKS -> $x("//button[text() = 'Через две недели']").click();
        }

        return this;
    }

    public CurriculaCreateOrEditPage checkAllCellsExistsValue(String subject, String expectedValue){
        SelenideElement rowContent = $x("//div[@class = ' rXMpdIDvlcwzffjY4sO4' and .//span[text() = '%s']]".formatted(subject));
        SelenideElement subjectNameDiv = rowContent.$x(".//div[span[text() = '%s']]".formatted(subject)).parent().parent();
        SelenideElement divCells = subjectNameDiv.sibling(0).$x(".//div[@class = ' guSkr79ef8UMyfqDq1sZ']");
        ElementsCollection inputs = divCells.$$x(".//input");
        Assertions.assertThat(inputs).as("Во всех ячейках правильное значение").allMatch(elem -> Objects.equals(elem.getValue(), expectedValue));
        return this;
    }

    public CurriculaCreateOrEditPage checkCellsExpectedOrContinueValue(String subject, String expectedValue){
        SelenideElement rowContent = $x("//div[@class = ' rXMpdIDvlcwzffjY4sO4' and .//span[text() = '%s']]".formatted(subject));
        SelenideElement subjectNameDiv = rowContent.$x(".//div[span[text() = '%s']]".formatted(subject)).parent().parent();
        SelenideElement divCells = subjectNameDiv.sibling(0).$x(".//div[@class = ' guSkr79ef8UMyfqDq1sZ']");
        ElementsCollection inputs = divCells.$$x(".//input");
        Assertions.assertThat(inputs).as("Во всех ячейках правильное значение").allMatch(elem -> {
            String currentValue = elem.getValue();
            assert currentValue != null;
            return currentValue.equals("0") || currentValue.equals(expectedValue);
        });
        return this;
    }


    public enum FormEducationValue {
        OZ,
        O,
        Z
    }

    public enum LevelEducationValue {
        NOO,
        OOO,
        SOO,
        SPO
    }

    public enum AdaptiveValue {
        INCREASE,
        WITHOUT,
        NOT
    }

    public enum WeekCopyValue{
        EVERY,
        INWEEK,
        INTWOWEEKS
    }
}
