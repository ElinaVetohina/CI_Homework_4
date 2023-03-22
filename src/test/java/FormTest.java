import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormTest {

    @Test
    void shouldSendForm() {
        Configuration.headless = true;
        Configuration.holdBrowserOpen = true;
//        LocalDate date = LocalDate.now();
        String date = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
//        $("[data-test-id='date'] input").setValue(date.plusDays(5).format(DateTimeFormatter.ofPattern("dd MM yyyy")));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Анастасия-Россия Витальевна");
        $("[data-test-id='phone'] input").setValue("+12345678910");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
//        $(".notification__content").shouldHave(Condition.ownText(date.plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
        $(".notification__content").shouldHave(Condition.ownText(date));
    }
}