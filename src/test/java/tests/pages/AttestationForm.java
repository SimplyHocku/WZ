package tests.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.checkerframework.checker.units.qual.A;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class AttestationForm {

    public AttestationForm setParallel(String value) {
        $x("//div[@class = ' uRZO5BUKZP9uacRiPpPg']//*[local-name() = 'svg']").click();
        $x("//li[text() = '%s']".formatted(value)).click();
        return this;
    }

    public AttestationForm clickAllInThemes() {
        $x("//button[.//div[text() = 'Все по темам']]").click();
        return this;
    }

    public AttestationForm clickSelectedInThemes() {
        $x("//button[.//div[text() = 'Выбранные по темам']]").click();
        return this;
    }

    public AttestationForm clickAllInStudyPeriods() {
        $x("//button[.//div[text() = 'Все по уч. периодам']]").click();
        return this;
    }

    public AttestationForm clickSelectedInStudyPeriods() {
        $x("//button[.//div[text() = 'Выбранные по уч. периодам']]").click();
        return this;
    }

    public AttestationForm clickTableLeft() {
        $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.zERMumgK_3Tnhk2gagUe.Eymi_gK4kx7SXLBx4dQC").click();
        return this;
    }

    public AttestationForm clickTableRight() {
        $(".wU9TSyFRFc3CpOTQBupH._XYP0roNx135HLimkPnt.w7ECsNGH4Gnnb39mQZw9.zERMumgK_3Tnhk2gagUe.KvrVIQ8xKG9T_eDvcc_I").click();
        return this;
    }

    public enum FormValue {
        THEME,
        PERIOD,
        SUCH
    }

    public AttestationForm selectForm(String _class, String subject, FormValue option) {
        SelenideElement curClass = $x("//td[.//span[text() = '%s']]".formatted(_class));
        SelenideElement curSubject = $x("//td[.//span[text() = '%s']]".formatted(subject));

        Map<String, Long> classCoordinate = Selenide.executeJavaScript("return arguments[0].getBoundingClientRect();",
                curClass);
        Map<String, Long> subjectCoordinate = Selenide.executeJavaScript("return arguments[0].getBoundingClientRect();",
                curSubject);

        curClass.hover();
        assert subjectCoordinate != null;
        assert classCoordinate != null;

        long subjectY = ((Number) subjectCoordinate.get("y")).longValue();
        long classY = ((Number) classCoordinate.get("y")).longValue();
        int heightDiff = (int) (subjectY - classY);
        actions().moveByOffset(0, heightDiff).perform();

        switch (option) {
            case THEME -> $x("//div[@class = ' HkVWsTOlW7Sg4XIe5md9']/div[.//span[text() = 'Тема']]").click();
            case PERIOD -> $x("//div[@class = ' HkVWsTOlW7Sg4XIe5md9']/div[.//span[text() = 'Уч. период']]").click();
        }

        return this;
    }

    public AttestationForm selectForm(String _class, String subject, FormValue option, String anyPeriod) {
        SelenideElement curClass = $x("//td[.//span[text() = '%s']]".formatted(_class));
        SelenideElement curSubject = $x("//td[.//span[text() = '%s']]".formatted(subject));

        Map<String, Long> classCoordinate = Selenide.executeJavaScript("return arguments[0].getBoundingClientRect();",
                curClass);
        Map<String, Long> subjectCoordinate = Selenide.executeJavaScript("return arguments[0].getBoundingClientRect();",
                curSubject);

        curClass.hover();
        assert subjectCoordinate != null;
        assert classCoordinate != null;

        long subjectY = ((Number) subjectCoordinate.get("y")).longValue();
        long classY = ((Number) classCoordinate.get("y")).longValue();
        int heightDiff = (int) (subjectY - classY);
        actions().moveByOffset(0, heightDiff).perform();

        switch (option) {
            case THEME -> $x("//div[@class = ' HkVWsTOlW7Sg4XIe5md9']/div[.//span[text() = 'Тема']]").click();
            case PERIOD -> $x("//div[@class = ' HkVWsTOlW7Sg4XIe5md9']/div[.//span[text() = 'Уч. период']]").click();
            case SUCH -> {
                $x("//div[@class = ' HkVWsTOlW7Sg4XIe5md9']/div[.//span[text() = 'Произвольный']]").hover();
                $x("//div[@class = ' HkVWsTOlW7Sg4XIe5md9']//div[.//span[@title = '%s']]".formatted(anyPeriod)).click();
            }
        }

        return this;
    }

    public AttestationForm formAttestationCancel() {
        $x("//button[.//span[text() = 'Отмена']]").click();
        return this;
    }

    public AttestationForm formAttestationSave() {
        $x("//button[.//span[text() = 'Сохранить']]").click();
        return this;
    }

}
