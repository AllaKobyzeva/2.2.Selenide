import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class OrderCardTest {

    public String deliveryDate(int days) {
        LocalDate date = LocalDate.now();
        LocalDate newDate = date.plusDays(days);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateNewFormat = dateFormat.format(newDate);
        return dateNewFormat;
    }

    @Test
    void shouldOrderCardWithFullData() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");;
        $("[data-test-id=date] input").clear();
        $("[data-test-id=date] input").setValue(deliveryDate(5));
        $("[name='name']").setValue("Василий Иванов");
        $("[name='phone']").setValue("+79200000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
