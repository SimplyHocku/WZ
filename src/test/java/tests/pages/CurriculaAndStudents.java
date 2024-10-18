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

    public CurriculaAndStudents selectAllStudents() {
        $x("//tr[@class = 'znu3DrCGSbeQf74VU_sQ pTz7BTrZNhYPlYi1QAqg']/td[1]//*[local-name()='svg']").click();
        return this;
    }

    public CurriculaAndStudents selectStudent(String FIO) {
        $x("//tr[.//a[text() = '%s']]/td[1]//button".formatted(FIO)).click();
        return this;
    }

    public CurriculaAndStudents selectCurricula(String planName) {
        $(".NQp5PvHbsg3zw0dqR7BG.U9gZqh4HBcPhDx8E2kxw.Kwq17kqGR1gd2i3e8T4j.t_nfk5KQShwEOZOwRQAr").click();
        $x("//li[text() = '%s']".formatted(planName)).click();
        return this;
    }

    public CurriculaAndStudents setPeriod(String date, String dateTo) {
        $(".Z5eYm_jkqZySVYXnxWEL.IhWORM0RCfM_znxFDK4e.ErrgUCgBVpOGk2uD7y4R.IfzJEdB1pkbERSIASybF.qLLJbCbx70ffOWhy2sj7.rzaiEMnocKsMWjls6Bgw.hyOyJfC6odz66Ul2rFKR").click();

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
    public CurriculaAndStudents bindToCurricula(String titleCurricula, String studentName) {
        var curriculaTitleElement = $x("//span[text() = '%s']".formatted(titleCurricula));
        LinkedHashMap<String, Double> coordinatesCurricula = Selenide.executeJavaScript("return arguments[0].getBoundingClientRect();", curriculaTitleElement);

        assert coordinatesCurricula != null;
        var radioButton = findElementForBind(coordinatesCurricula, studentName);
        radioButton.click();

        return this;
    }

    public CurriculaAndStudents inParallelCancel(){
        $x("//button[.//span[text() = 'Отмена']]").click();
        return this;
    }

    public CurriculaAndStudents inParallelSave(){
        $x("//button[.//span[text() = 'Сохранить']]").click();
        return this;
    }

}
