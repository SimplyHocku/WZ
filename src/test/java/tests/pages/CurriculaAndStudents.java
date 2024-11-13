package tests.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Coordinates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Locale;
import java.util.Objects;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$x;
public class CurriculaAndStudents {

    private SelenideElement paralelPointClick;
    private SelenideElement classPointClick;

    private boolean existEmptyRow = false;


    public Map<String, String> getStudentWithoutBindOrForDeleteBind() {
        ElementsCollection emptyRows = $$x("//tr/*[3][not(.//span)]");
        if (emptyRows.isEmpty()) {
            return Map.of("FIO", $x("//tr[@class = 'znu3DrCGSbeQf74VU_sQ'][1]/td[2]/div/a").getText());
        }
        existEmptyRow = true;
        return Map.of("FIO", emptyRows.get(0).$x(".//td[2]/div/a").getText());
    }

    public CurriculaAndStudents selectInClass() {
        $x("//a[text() = 'По классу']").click();
        return this;
    }

    public CurriculaAndStudents selectInParallel() {
        $x("//a[text() = 'По параллели']").click();
        return this;
    }


    public CurriculaAndStudents selectParalel(String value) {
        ElementsCollection svgs = $$(".NQp5PvHbsg3zw0dqR7BG.U9gZqh4HBcPhDx8E2kxw.Kwq17kqGR1gd2i3e8T4j.t_nfk5KQShwEOZOwRQAr");
        if (svgs.size() == 3) {
            paralelPointClick = svgs.get(1);
        } else if (svgs.size() < 3) {
            paralelPointClick = svgs.get(0);
        }
        this.paralelPointClick.click();
        $x("//li[.//span[text() = '%s параллель']]".formatted(value)).click();
        return this;
    }

    public CurriculaAndStudents selectClass(String value) {
        ElementsCollection svgs = $$(".NQp5PvHbsg3zw0dqR7BG.U9gZqh4HBcPhDx8E2kxw.Kwq17kqGR1gd2i3e8T4j.t_nfk5KQShwEOZOwRQAr");
        if (svgs.size() == 3) {
            classPointClick = svgs.get(2);
        } else if (svgs.size() < 3) {
            classPointClick = svgs.get(1);
        }
        this.classPointClick.click();
        $x("//li[text() = '%s']".formatted(value)).click();
        return this;
    }

    public CurriculaAndStudents clickReset() {
        $x("//span[text() = 'Сбросить']").click();
        return this;
    }

    public CurriculaAndStudents selectAllStudents() {
        $x("//tr[@class = 'znu3DrCGSbeQf74VU_sQ pTz7BTrZNhYPlYi1QAqg']/td[1]//*[local-name()='svg']").click();
        return this;
    }

    public CurriculaAndStudents selectStudent(String FIO) {
        $x("//tr[.//a[text() = '%s']]/td[1]//button".formatted(FIO)).click();
        return this;
    }

    public CurriculaAndStudents selectMainCurricula(String planName) {
        $x("//span[text() = 'Учебный план']").parent().parent().parent().$x(".//*[local-name() = 'svg']").click();
//        $(".NQp5PvHbsg3zw0dqR7BG.U9gZqh4HBcPhDx8E2kxw.Kwq17kqGR1gd2i3e8T4j.t_nfk5KQShwEOZOwRQAr").click(); //NQp5PvHbsg3zw0dqR7BG U9gZqh4HBcPhDx8E2kxw Kwq17kqGR1gd2i3e8T4j t_nfk5KQShwEOZOwRQAr
        $x("//li[text() = '%s']".formatted(planName)).click();
        return this;
    }

    public CurriculaAndStudents selectCurriculaForStudent(String student, String planName) {
        $x("//tr[.//a[text() = '%s']]//*[local-name() = 'svg' and @class = 'NQp5PvHbsg3zw0dqR7BG U9gZqh4HBcPhDx8E2kxw Kwq17kqGR1gd2i3e8T4j t_nfk5KQShwEOZOwRQAr']".formatted(student)).click();
        $x("//li[text() = '%s']".formatted(planName)).click();
        return this;
    }

    public CurriculaAndStudents clickMainPeriod() {
        $$(".Z5eYm_jkqZySVYXnxWEL.IhWORM0RCfM_znxFDK4e.ErrgUCgBVpOGk2uD7y4R.IfzJEdB1pkbERSIASybF.qLLJbCbx70ffOWhy2sj7.rzaiEMnocKsMWjls6Bgw.hyOyJfC6odz66Ul2rFKR").get(0).click();
        return this;
    }

    public CurriculaAndStudents clickPeriodForStudent(String studentName) {
        $x("//tr[.//a[text() = '%s']]//input[@class = 'Z5eYm_jkqZySVYXnxWEL IhWORM0RCfM_znxFDK4e ErrgUCgBVpOGk2uD7y4R IfzJEdB1pkbERSIASybF qLLJbCbx70ffOWhy2sj7 rzaiEMnocKsMWjls6Bgw hyOyJfC6odz66Ul2rFKR']".formatted(studentName)).click();
        return this;
    }

    public CurriculaAndStudents setPeriod(String date, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(date, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        String monthStart = startDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru")).substring(0, 1).toUpperCase() + startDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru")).substring(1, 3);
        String monthEnd = endDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru")).substring(0, 1).toUpperCase() + endDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru")).substring(1, 3);
        SelenideElement headerWithMonthStart = $x("//header[@class = ' ZUNOrqafshvSeBHXmiOR' and .//span[contains(text(), '%s')] and .//span[contains(text(), '%s')]]".formatted(monthStart, startDate.getYear()));
        SelenideElement headerWithMonthEnd = $x("//header[@class = ' ZUNOrqafshvSeBHXmiOR' and .//span[contains(text(), '%s')] and .//span[contains(text(), '%s')]]".formatted(monthEnd, endDate.getYear()));

        headerWithMonthStart.shouldBe(Condition.exist);
        actions().scrollToElement(headerWithMonthStart).perform();
        headerWithMonthStart.parent().$x(".//span[text() = '%s']".formatted(startDate.getDayOfMonth())).click();

        actions().scrollToElement(headerWithMonthEnd).perform();
        headerWithMonthEnd.parent().$x(".//span[text() = '%s']".formatted(endDate.getDayOfMonth())).click();
        return this;
    }


    public CurriculaAndStudents clickContextRow(String studentName, String period, ContextOptionRow option) {
        SelenideElement rowWithStudent = $x("//tr[.//a[text() = '%s']]".formatted(studentName));
        if (option == ContextOptionRow.DEMO){
            rowWithStudent.$x("./td[5]").$x(".//button").click();
            return this;
        }
        ElementsCollection assignments = rowWithStudent.$$x(".//span[@class = 'wo7Ab8Szrw_1kpfz6YdS IhWORM0RCfM_znxFDK4e']");
        int index = 0;
        $(".iyHhc6P8W_bLXRv6v9KL").shouldBe(Condition.visible);
        for (int count = 0; count < assignments.size(); count++ ){
            assignments.get(count).shouldBe(Condition.visible);
            if (assignments.get(count).getText().equals(period)){
                index = count;
                break;
            }
        }
        rowWithStudent.$x("./td[5]").$$x(".//button").get(index).click();


        switch (option){
            case EDIT -> $x("//button[text() = 'Редактировать']").click();
            case DELETE -> $x("//button[text() = 'Удалить']").click();
        }
        return this;
    }

    public int getCountAssignment(String studentName){
        SelenideElement rowWithAssignment = $x("//tr[.//a[text() = '%s']]/td[4]".formatted(studentName));
        $(".iyHhc6P8W_bLXRv6v9KL").shouldBe(Condition.visible);

        int count = rowWithAssignment.$$x(".//span").size();
        return count;

    }

    public CurriculaAndStudents clickSubmitBind() {
        $(".NQp5PvHbsg3zw0dqR7BG.tcpt9oq21v1w8DS00Gdq.lqW5z34Kw0trzNSMYrXf.iHQYNPDlyB9iKeoLOX6w").click();
        return this;
    }

    public CurriculaAndStudents clickCancelSubmit() {
        $(".NQp5PvHbsg3zw0dqR7BG.lqW5z34Kw0trzNSMYrXf.iHQYNPDlyB9iKeoLOX6w.vVZkz0JBBuxM9PLodGK3").click();
        return this;
    }

    public CurriculaAndStudents clickBindSelected() {
        $(".wU9TSyFRFc3CpOTQBupH.U3KlTnpKD1HK6nSfYQIr.w7ECsNGH4Gnnb39mQZw9.Mv0ARbCy0YnB7r_2NLvE.lyW5jmphtuQeL51jZoCw").click();
        return this;
    }

    public CurriculaAndStudents expandClass(String className) {
        var classForClick = $x("//tr[.//td[.//span[text() = '%s']]]/td[1]//*[local-name()='svg']".formatted(className));
        Selenide.sleep(1000);
        actions().scrollToElement(classForClick).perform();
        classForClick.click();
        return this;
    }

    private SelenideElement findElementForBind(LinkedHashMap<String, Double> curriculaCoordinates, String student) {
        ElementsCollection radioButtons = $$x("//tr[.//span[text() = '%s']]//input".formatted(student)).filterBy(Condition.visible);

        for (SelenideElement el : radioButtons) {
            LinkedHashMap<String, Double> elCoordinates = Selenide.executeJavaScript("return arguments[0].getBoundingClientRect();", el);
            assert elCoordinates != null;

            int elCoordinate = Math.round(Float.parseFloat(String.valueOf(elCoordinates.get("x"))));
            int curCoordinate = Math.round(Float.parseFloat(String.valueOf(curriculaCoordinates.get("x"))));
            if (elCoordinate == curCoordinate) {
                return el;
            }

        }
        throw new NullPointerException("Элемента не найдено");
    }

    public CurriculaAndStudents clickAddCurricula() {
        $x("//span[text() = 'Добавить график']").click();
        return this;
    }

    public CurriculaAndStudents clickEditCurricula(String namePlan) {
        SelenideElement plan = $x("//td[.//span[text() = '%s']]".formatted(namePlan));
        plan.hover();
        SelenideElement btnForDeletePlan = plan.$$x(".//button").get(0);
        btnForDeletePlan.click();
        return this;
    }

    public CurriculaAndStudents clickDeleteCurricula(String namePlan, int option) {
        SelenideElement plan = $x("//td[.//span[text() = '%s']]".formatted(namePlan));
        plan.hover();
        SelenideElement btnForDeletePlan = plan.$$x(".//button").get(1);
        btnForDeletePlan.click();
        if (option == 0) {
            $x("//div[@class = ' JHHSSmXrEDClsNtfHZOf']//button[.//span[text() = 'Отмена']]").click();
        } else if (option == 1) {
            $x("//div[@class = ' JHHSSmXrEDClsNtfHZOf']//button[.//span[text() = 'Удалить']]").click();
        }
        return this;
    }

    public CurriculaAndStudents bindToCurricula(String titleCurricula, String studentName) {
        var curriculaTitleElement = $x("//span[text() = '%s']".formatted(titleCurricula));
        LinkedHashMap<String, Double> coordinatesCurricula = Selenide.executeJavaScript("return arguments[0].getBoundingClientRect();", curriculaTitleElement);

        assert coordinatesCurricula != null;
        var radioButton = findElementForBind(coordinatesCurricula, studentName);
        radioButton.click();

        return this;
    }

    public CurriculaAndStudents clickAddBind(String student) {
        $x("//tr[.//a[text() = '%s']]//td[@class  = ' xF_IFEe0QuJX0ySbgMHT']/button//*[local-name()='svg']".formatted(student)).click();
        return this;
    }

    public CurriculaAndStudents contextWindowCancel() {
        $x("//button[.//span[text() = 'Отмена']]").click();
        return this;
    }

    public CurriculaAndStudents contextWindowSave() {
        $x("//button[.//span[text() = 'Сохранить']]").click();
        return this;
    }
    public CurriculaAndStudents contextWindowDelete() {
        $x("//button[.//span[text() = 'Удалить']]").click();
        return this;
    }

    public enum ContextOptionRow{
        EDIT,
        DELETE,
        DEMO
    }

}
