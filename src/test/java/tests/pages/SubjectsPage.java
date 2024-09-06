package tests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Dimension;

import java.util.Arrays;
import java.util.concurrent.*;

import static com.codeborne.selenide.Selenide.*;

public class SubjectsPage {

    public final SelenideElement btnCloseFilters = $(".addHoKnJHw3XjzJKFeLg.H9ekBWsZfeFtH47wCXK1.m0LvfGIQCmmSvHEdZHp_");
    public final SelenideElement searchField = $(".Z5eYm_jkqZySVYXnxWEL.IhWORM0RCfM_znxFDK4e.ErrgUCgBVpOGk2uD7y4R.sW0pcwdJYKkfIrzs94YW.li6XPmPhUz6LzxGrMDzr.hyOyJfC6odz66Ul2rFKR");
    public final SelenideElement resetSearchFieldBtn = $(".NQp5PvHbsg3zw0dqR7BG.tcpt9oq21v1w8DS00Gdq.lqW5z34Kw0trzNSMYrXf.v_D5RjlRwrl7RSJIZexy.EFwyT9C9C_JSdeMRFk1A");
    public final ElementsCollection checkboxes = $$(".addHoKnJHw3XjzJKFeLg._HBYy7CkXfkNAsKg5cRp.msUvU2DGhY2ygofwPfax");
    public final SelenideElement subjectsArea = $(".uRZO5BUKZP9uacRiPpPg");
    public final SelenideElement mainBlock = $x("//*[contains(@class, ' IjH7lf37wpYN4r9YiQFJ')]");

    public final ElementsCollection modeRadio = $$(".NiolCP7PD6onEqIbBGCQ");
    public final SelenideElement resetBtn = $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.XmjWECPjgnwA1tkkgKBr.XSReFeIOyNCXZG6tRWvh.MFDsafZC3fdVgWzLWQhW");
    public final ElementsCollection tablesContent = $$(".INrChrmHTwoqhAtOsqk2");
    public final SelenideElement divContent = $x("//div[@class = ' IjH7lf37wpYN4r9YiQFJ']");


    public SubjectsPage pushBtnCloseFilter() {
        btnCloseFilters.click();
        return this;
    }

    public SubjectsPage setSearchFieldValue(String value) {
        searchField.setValue(value);
        return this;
    }

    public String getSearchFieldValue() {
        return searchField.getValue();
    }

    public SubjectsPage pushBtnResetSearchField() {
        if (searchField.getText().isEmpty()) {
            resetSearchFieldBtn.click();
            return this;
        }
        return this;
    }

    public SubjectsPage setCheckbox(checkBoxValue value) {
        switch (value) {
            case DO -> checkboxes.get(0).click();
            case NOO -> checkboxes.get(1).click();
            case OOO -> checkboxes.get(2).click();
            case SOO -> checkboxes.get(3).click();
            case SPO -> checkboxes.get(4).click();
        }
        return this;

    }

    public SubjectsPage pushSubjectArea() {
        subjectsArea.click();
        return this;
    }

    public SubjectsPage selectSubjectAreaElem(String subjectName) {
        SelenideElement subject = findSubjectAreaElem(subjectName);
        subject.click();
        return this;
    }

    public SubjectsPage selectShowMode(radioValue value) {
        switch (value) {
            case ALL -> modeRadio.get(0).click();
            case ADAPTIVE -> modeRadio.get(1).click();
            case DELETE -> modeRadio.get(2).click();
        }
        return this;
    }

    public SubjectsPage pushResetBtn() {
        resetBtn.click();
        return this;
    }


    public enum checkBoxValue {
        DO,
        NOO,
        OOO,
        SOO,
        SPO

    }

    public enum radioValue {
        ALL,
        ADAPTIVE,
        DELETE
    }

    private SelenideElement findSubjectAreaElem(String subjectName) {
        String xpath = String.format(".//li[@class='Z5eYm_jkqZySVYXnxWEL rWtYydTGjRz8vkOQ02JO YaZ_UNjQdRChEsq3MD0O zCkBFe0dc6Kv5X3_9ihe'][text()='%s']", subjectName);
        SelenideElement subject = $x(xpath);
        return subject;
    }

    public boolean checkMainTableExistsValue(String xpath, String... values) {
        boolean[] foundValues = new boolean[values.length];
        Arrays.fill(foundValues, false);

        for (SelenideElement table : this.tablesContent) {
            ElementsCollection elementsOfTable = table.$$x(".//tr[@class='znu3DrCGSbeQf74VU_sQ']");
            for (SelenideElement element : elementsOfTable) {
                String text = element.getText();
                if (text.contains("Основные") || text.contains("Учебные курсы")) {
                    continue;
                }

                for (int i = 0; i < values.length; i++) {
                    String xp = xpath.formatted(values[i]);
                    if (element.$x(xp).exists()) {
                        foundValues[i] = true;
                    }
                }
            }
        }

        for (boolean found : foundValues) {
            if (!found) {
                return false;
            }
        }

        return true;

    }

    public boolean checkTablesContentIsEmpty() {
        org.assertj.core.api.Assertions.assertThat(this.tablesContent).as("Пустые ли таблицы").allMatch(table -> {
            SelenideElement element = table.$x(".//span[text() = 'Данных для отображения пока нет']");
            return element.exists();
        });
        return true;
    }

    public boolean waitForElementSizeChange(SelenideElement element, Dimension initialSize, int duration) {
        long endTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(duration);


        while (System.currentTimeMillis() < endTime) {
            Dimension currentSize = element.getSize();
            if (!initialSize.equals(currentSize)) {
                return true;
            }
            sleep(500);
        }

        throw new RuntimeException("Размер элемента не изменился: %s - %s".formatted(initialSize.toString(), element.getSize().toString()));
    }

    public Dimension getDivContentSize() {
        return this.divContent.getSize();
    }


}
