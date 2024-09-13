package tests.pages;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

public class ListOfCurriculaPage {

    public final SelenideElement divContent = $x("//div[@class = ' BJP5OcbYZ8EUnGHnVxYx']");
    private final SelenideElement plansUrl = $("a[href = '/educationmanagement/study-plan/list']");
    private final SelenideElement patternsUrl = $("a[href= '/educationmanagement/study-plan/list/templates']");
    public final SelenideElement searchField = $(".DSXOGdoSiFGKohRuaDDx.ZmmVltuRq_1DuwAx9seL._ELGiVRWaoZZRQLlT7eO.E8taxZlPjqlq_tc1djmu.avaXh0D3pb8_pcyI71B_.UNkcdyvhSJUhg4kFmJnP.GA_rwCv4hCUipejDFuCY.H_mW6hIYJA7vrf2lHQpu");
    private final SelenideElement paralelsFieldDiv = $(".mdlBeTDjk4eJspBtgT8g.hETISWSYE0TZ8F37t9IZ.bbCgRxiSnkQWLDVyZfK0.cuYjfO3VmFg5zAtKnPYK._Ws1lFPY9Cv3NMEJqZQL");
    private final SelenideElement tooltipParallels = $(".B7rf41i9S7P2vAxV8jKd");
    private final SelenideElement btnDownload = $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.KSvMbtTQc_w54dUVY3Ec.XSReFeIOyNCXZG6tRWvh.eyr9ysIBIhzIpRgTFeRS");
    private final SelenideElement addPP = $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.Mv0ARbCy0YnB7r_2NLvE.lyW5jmphtuQeL51jZoCw");
    private final SelenideElement pageModeDiv = $(".HAk0sC05RiMUhsw82i5K");
    private final ElementsCollection btnForChangePage = $$(".NQp5PvHbsg3zw0dqR7BG.LMvmdpuoCw8WIV8URMvV.D1PQTgffcK3m41s8FACQ");
    private final SelenideElement loader = $x("//img[@alt = 'loader']");

    public ListOfCurriculaPage selectPlansUrl() {
        this.plansUrl.click();
        return this;
    }

    public ListOfCurriculaPage selectPatternsUrl() {
        this.patternsUrl.click();
        return this;
    }

    public ListOfCurriculaPage waitClosingLoader(int duration) {
        long endTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(duration);

        while (System.currentTimeMillis() < endTime) {
            if (!this.loader.exists()) {
                return this;
            }
            sleep(500);
        }
        throw new RuntimeException("Прелоадер не исчез за %d секунд".formatted(duration));
    }

    public ListOfCurriculaPage setSearchValue(String value) {
        boolean isEmpty = Objects.requireNonNull(this.searchField.getValue()).isEmpty();
        if (!isEmpty){
            this.searchField.sendKeys(Keys.LEFT_CONTROL, "A");
            this.searchField.sendKeys(Keys.BACK_SPACE);
        }
        this.searchField.setValue(value);
        return this;
    }

    public ListOfCurriculaPage openParallelsField() {
        this.paralelsFieldDiv.click();
        return this;
    }

    public ListOfCurriculaPage checkParallelsOpen() {
        if (this.tooltipParallels.exists()) {
            return this;
        }
        throw new RuntimeException("Поле с параллелями не открыто");
    }

    public ListOfCurriculaPage setParallel(String value) {
        String parallel = "//li[text() = '%s']".formatted(value);
        $x(parallel).click();
        return this;
    }

    public ListOfCurriculaPage clickBtnDownload() {
        this.btnDownload.click();
        return this;
    }

    public ListOfCurriculaPage clickAddPP(){
        this.addPP.click();
        return this;
    }

    public ListOfCurriculaPage selectPlanOption(PlanOption value){
        switch (value){
            case PARALLEL -> $x("//button[text() = 'Для параллели']").click();
            case INDIVIDUAL -> $x("//button[text() = 'Индивидуальный']").click();
        }
        return this;
    }

    public ListOfCurriculaPage setCheckbox(String namePlan, boolean state) {
        SelenideElement rowElement = $x("//tr[@class = 'znu3DrCGSbeQf74VU_sQ' and contains(.//text(), '%s')]".formatted(namePlan));
        SelenideElement checkboxTrue = rowElement.$(".DSXOGdoSiFGKohRuaDDx.ebIBbAN3ZomwnCMWP167._ELGiVRWaoZZRQLlT7eO.E8taxZlPjqlq_tc1djmu.fjD1NzLA4758nA8U8ga0.HgoY_VW_p3O_AqURScMK.n6krPOgz9y4WOh9YVCZL.zr7bxNeUU4GQQ13jY9CI");
        SelenideElement checkbox = rowElement.$(".CZtlm_DGpYLNIT_tqZuO");

        boolean currentState = checkboxTrue.exists();
        if (currentState != state) {
            checkbox.click();
        }
        return this;
    }

    public enum ElementContextMenu {
        EDIT,
        DOWNLOAD,
        DELETE
    }

    public enum PageView {
        PAGE10,
        PAGE15,
        PAGE25,
        PAGE50,
        PAGE90,
        ALL

    }

    public enum PlanOption{
        PARALLEL,
        INDIVIDUAL
    }

    public void openContextAndSelect(String namePlan, ElementContextMenu mode) {
        ElementsCollection svgs = $(".INrChrmHTwoqhAtOsqk2").$$x(".//svg");
        SelenideElement rowElement = $x("//tr[@class = 'znu3DrCGSbeQf74VU_sQ' and contains(.//text(), '%s')]".formatted(namePlan));

        rowElement.$(".NQp5PvHbsg3zw0dqR7BG.tcpt9oq21v1w8DS00Gdq.Kwq17kqGR1gd2i3e8T4j.In5fRE_jL2Nv9RlJmC78").click();
        switch (mode) {
            case EDIT -> $x("//button[text() = 'Редактировать']").click();
            case DOWNLOAD -> $x("//button[text() = 'Скачать']").click();
            case DELETE -> $x("//button[text() = 'Удалить']").click();
        }
    }

    public ListOfCurriculaPage openPageModeView() {
        this.pageModeDiv.click();
        return this;
    }

    public ListOfCurriculaPage setPageView(PageView pages) {
        switch (pages) {
            case PAGE10 -> $x("//li[text() = 'Отображать по 10']").click();
            case PAGE15 ->  $x("//li[text() = 'Отображать по 15']").click();
            case PAGE25 -> $x("//li[text() = 'Отображать по 25']").click();
            case PAGE50 -> $x("//li[text() = 'Отображать по 50']").click();
            case PAGE90 -> $x("//li[text() = 'Отображать по 90']").click();
            case ALL -> $x("//li[text() = 'Отображать все']").click();
        }
        return this;
    }

    public ListOfCurriculaPage clickPreviousPage(){
        this.btnForChangePage.get(0).click();
        return this;
    }
    public ListOfCurriculaPage clickNextPage(){
        this.btnForChangePage.get(1).click();
        return this;
    }

}
