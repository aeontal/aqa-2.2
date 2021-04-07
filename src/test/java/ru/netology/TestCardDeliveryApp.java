package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestCardDeliveryApp {

    public String setDate(int date) {
        return LocalDate.now().plusDays(date).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void shouldOpenApplication() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestOverallCorrect() {
        $("[data-test-id=city] input").setValue("Воронеж");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(setDate(5));
        $("[data-test-id=name] input").setValue("Амиржан Мамедов");
        $("[data-test-id=phone] input").setValue("+79274495630");
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $("[data-test-id=notification]").shouldBe((visible), Duration.ofSeconds(15));
    }

}

