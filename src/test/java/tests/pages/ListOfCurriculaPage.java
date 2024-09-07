package tests.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class ListOfCurriculaPage {

    public final SelenideElement divContent = $x("//div[@class = ' BJP5OcbYZ8EUnGHnVxYx']");
    private final SelenideElement plansUrl = $("a[href = '/educationmanagement/study-plan/list']");
    private final SelenideElement patternsUrl = $("a[href= '/educationmanagement/study-plan/list/templates']");
    public final SelenideElement searchField = $(".DSXOGdoSiFGKohRuaDDx.ZmmVltuRq_1DuwAx9seL._ELGiVRWaoZZRQLlT7eO.E8taxZlPjqlq_tc1djmu.avaXh0D3pb8_pcyI71B_.UNkcdyvhSJUhg4kFmJnP.GA_rwCv4hCUipejDFuCY.H_mW6hIYJA7vrf2lHQpu");
    private final SelenideElement paralelsFieldDiv = $(".mdlBeTDjk4eJspBtgT8g.hETISWSYE0TZ8F37t9IZ.bbCgRxiSnkQWLDVyZfK0.cuYjfO3VmFg5zAtKnPYK._Ws1lFPY9Cv3NMEJqZQL");
    private final SelenideElement tooltipParallels = $(".\\ B7rf41i9S7P2vAxV8jKd");
    private final SelenideElement loader = $x("//img[@alt = 'loader']");



//    public Dimension getDivContentSize(){
//        return this.divContent.getSize();
//    }

    public ListOfCurriculaPage selectPlansUrl(){
        this.plansUrl.click();
        return this;
    }
    public ListOfCurriculaPage selectPatternsUrl(){
        this.patternsUrl.click();
        return this;
    }

    public ListOfCurriculaPage waitClosingLoader(int duration){
        long endTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(duration);

        while (System.currentTimeMillis() < endTime){
            if (!this.loader.exists()){
                return  this;
            }
            sleep(500);
        }
        throw new RuntimeException("Прелоадер не исчез за %d секунд".formatted(duration));
    }

    public ListOfCurriculaPage setSearchValue(String value){
        this.searchField.setValue(value);
        return this;
    }

    public ListOfCurriculaPage openParallelsField(){
        this.paralelsFieldDiv.click();
        return this;
    }

    public ListOfCurriculaPage checkParallelsOpen(){
        if (this.tooltipParallels.exists()){
            return this;
        }
        throw new RuntimeException("Поле с параллелями не открыто");
    }
}
