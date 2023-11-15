package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventCalculatorTest {

    private static final int CHRISTMAS_EVENT_START_DAY = 1;
    private static final int CHRISTMAS_EVENT_START_MONEY = 1000;
    private static final int CHRISTMAS_EVENT_INCREASE_AMOUNT = 100;

    @DisplayName("크리스마스 혜택을 잘 계산하는지 Test")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 24})
    void getChristmasBenefit(int day) {

        assertEquals(new EventCalculator().christMasEventCalculator(day),
                (day - CHRISTMAS_EVENT_START_DAY) * CHRISTMAS_EVENT_INCREASE_AMOUNT + CHRISTMAS_EVENT_START_MONEY);
    }
}
