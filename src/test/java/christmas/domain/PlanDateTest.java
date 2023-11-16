package christmas.domain;


import static org.junit.jupiter.api.Assertions.assertThrows;

import camp.nextstep.edu.missionutils.test.Assertions;
import christmas.view.input.error.InputIllegalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlanDateTest {

    @DisplayName("1~31 사이의 날짜가 아닌 경우 에러를 출력하는지 확인")
    @ParameterizedTest
    @ValueSource(ints = {0, -5, 32})
    void createPlanWithNotPossibleDateTest(int date) {
        Assertions.assertSimpleTest(() -> {

            assertThrows(InputIllegalException.class, () -> PlanDate.createPlan(date));
        });
    }
}
