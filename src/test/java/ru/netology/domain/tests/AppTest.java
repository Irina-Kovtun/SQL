package ru.netology.domain.tests;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.data.SqlRequest;
import ru.netology.domain.pages.LoginPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;


public class AppTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clear() {
        SqlRequest.clearBD();
    }

   @Test
    void shouldRunAppWithValidData() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = SqlRequest.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
       $("[data-test-id='dashboard']").shouldBe(visible)
               .shouldHave(exactText("Личный кабинет"));
    }

    @Test
    void shouldWarnIfRunAppWithInvalidLogin() {
        val loginPage = new LoginPage();
        val invalidAuthInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidLogin(invalidAuthInfo);
        SelenideElement errorBox  = $("[data-test-id=error-notification]");
        errorBox.shouldBe(visible, Duration.ofSeconds(5));
        $("[data-test-id=error-notification]>.notification__title")
                .shouldHave(text("Ошибка"));
        $("[data-test-id=error-notification]>.notification__content")
                .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldWarnIfRunWithInvalidVerCode() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val invalidVerificationCode = DataHelper.getInvalidVerificationCode();
        verificationPage.invalidVerify(invalidVerificationCode);
        SelenideElement errorBox = $("[data-test-id=error-notification]");
        errorBox.shouldBe(visible, ofSeconds(15));
        $("[data-test-id=error-notification]>.notification__title")
                .shouldHave(text("Ошибка"));
        $("[data-test-id=error-notification]>.notification__content")
                .shouldHave(text("Ошибка! \nНеверно указан код! Попробуйте ещё раз."));
    }
    @Test
    void checkVerCode() {
        DataHelper.AuthInfo newinfo = DataHelper.getAuthInfo();
        String str1 = SqlRequest.getVerificationCode(newinfo);
        System.out.println(str1);
    }
}
