package tests.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class CurriculaCreateOrEditPage {

    private final SelenideElement title = $(".Z5eYm_jkqZySVYXnxWEL.IhWORM0RCfM_znxFDK4e.ErrgUCgBVpOGk2uD7y4R.IfzJEdB1pkbERSIASybF");
    private final SelenideElement shortTitle = $$(".Z5eYm_jkqZySVYXnxWEL.IhWORM0RCfM_znxFDK4e.ErrgUCgBVpOGk2uD7y4R.IfzJEdB1pkbERSIASybF").get(1);
    private final SelenideElement formEducation = $$(".Z5eYm_jkqZySVYXnxWEL.jDrnuuOrYGEtWVtT_jwL.JN9rAf0zV1reNm6noHyf").get(0);
    private final SelenideElement levelEducation = $$(".NQp5PvHbsg3zw0dqR7BG.U9gZqh4HBcPhDx8E2kxw.Kwq17kqGR1gd2i3e8T4j.t_nfk5KQShwEOZOwRQAr").get(1);
    private final SelenideElement parallelEducation = $$(".NQp5PvHbsg3zw0dqR7BG.U9gZqh4HBcPhDx8E2kxw.Kwq17kqGR1gd2i3e8T4j.t_nfk5KQShwEOZOwRQAr").get(1);
    private final SelenideElement fgosEducation = $$(".Z5eYm_jkqZySVYXnxWEL.jDrnuuOrYGEtWVtT_jwL.JN9rAf0zV1reNm6noHyf").get(4);
    private final SelenideElement scheduleEducation = $$(".Z5eYm_jkqZySVYXnxWEL.jDrnuuOrYGEtWVtT_jwL.JN9rAf0zV1reNm6noHyf").get(5);
    private final SelenideElement weekEducation = $$(".Z5eYm_jkqZySVYXnxWEL.jDrnuuOrYGEtWVtT_jwL.JN9rAf0zV1reNm6noHyf").get(6);

    private final SelenideElement adaptiveCheckbox = $(".NQp5PvHbsg3zw0dqR7BG.Kwq17kqGR1gd2i3e8T4j.VPTO55M4zBKFXTfcgLbE");

    private final SelenideElement btnCancel = $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.XmjWECPjgnwA1tkkgKBr.XSReFeIOyNCXZG6tRWvh");
    private final SelenideElement btnGenerate = $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.Mv0ARbCy0YnB7r_2NLvE.lyW5jmphtuQeL51jZoCw");

    public CurriculaCreateOrEditPage setTitle(String value) {
        this.title.setValue(value);
        return this;
    }

    public String getTitle() {
        return this.title.getValue();
    }

    public CurriculaCreateOrEditPage setShortTitle(String value) {
        this.shortTitle.setValue(value);
        return this;
    }

    public String getShortTitle() {
        return this.shortTitle.getValue();
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

    public CurriculaCreateOrEditPage clickCancel() {
        this.btnCancel.click();
        return this;
    }

    public CurriculaCreateOrEditPage clickGenerate() {
        this.btnGenerate.click();
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
}
