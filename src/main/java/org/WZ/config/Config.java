package org.WZ.config;

import com.codeborne.selenide.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public abstract class Config {
    protected static String mainPath = "https://school-test.mos.ru";
    protected static File tokenFile = new File("src/main/java/org/WZ/cookies/cookie.json");

    String currentSection;

    static {
        Configuration.baseUrl = "https://school-test.mos.ru/educationmanagement/";
        Configuration.timeout = 30000;
        if (!tokenFile.exists()) {
            try {
                tokenFile.createNewFile();
                login();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                loadToken();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void login() throws IOException {
        Selenide.open(mainPath);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Selenide.sleep(2000);

        String btnLoginSelector = ".style_btn__3lIWs";
        String loginInputSelector = "#login";
        String passwordInputSelector = "#password";
        String btnForLoginSelector = "#bind";
        String neededBlockSelector = ".style_service_item_wrapper__1Q4mF";
        String nestedBlockSelector = ".style_block-selector-roles__H8ivz";
        String hTSelector = ".style_block-line__33NoM";

        SelenideElement btnLogin = $(btnLoginSelector);
        SelenideElement loginInput = $(loginInputSelector);
        SelenideElement passwordInput = $(passwordInputSelector);
        SelenideElement btnForLogin = $(btnForLoginSelector);

        btnLogin.click();
        loginInput.setValue("63437117988");
        passwordInput.setValue("Nsdue23!");
        btnForLogin.click();
        saveToken();

        Selenide.sleep(1000);

        SelenideElement neededBlock = $$(neededBlockSelector).get(4);
        SelenideElement nestedBlock = neededBlock.$(nestedBlockSelector);
        SelenideElement hT = nestedBlock.$$(hTSelector).get(0);

        nestedBlock.hover();
        hT.shouldBe(Condition.visible).click();

        Selenide.sleep(8000);
    }

    private static void saveToken() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<Cookie> cookie = WebDriverRunner.getWebDriver().manage().getCookies();
        Token token = new Token();
        for (Cookie cook : cookie) {
            if (cook.getName().equals("aupd_token")) {
                token.setToken(cook.getValue());
            }
        }
        objectMapper.writeValue(tokenFile, token);
    }

    private static void loadToken() throws IOException {
        Selenide.open(mainPath);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        ObjectMapper objectMapper = new ObjectMapper();
        Token token = objectMapper.readValue(tokenFile, Token.class);

        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().addCookie(new Cookie("aupd_token", token.getToken()));
        Selenide.refresh();

    }
}

class Token {
    private String aupd_token;

    public String getToken() {
        return aupd_token;
    }

    public void setToken(String aupd_token) {
        this.aupd_token = aupd_token;
    }
}

