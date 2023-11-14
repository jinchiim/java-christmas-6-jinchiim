package christmas.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WoowaPlannerTest extends NsTest {

    @DisplayName("잘못된 날짜 입력값을 입력한 경우 재입력 Test")
    @Test
    void inputNotPossibleDateTest() {
        assertSimpleTest(() -> {
            run("a", "1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @DisplayName("잘못된 메뉴 입력값을 입력한 경우 재입력 Test")
    @Test
    void inputNotSplitMenuTest() {
        assertSimpleTest(() -> {
            run("1", "티본스테이크-3,제로콜라-1아이스크림-4", "해산물파스타-2,레드와인-1,초코케이크-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        WoowaPlanner woowaPlanner = new WoowaPlanner();
        woowaPlanner.run();
    }
}
