package christmas.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WoowaPlannerTest extends NsTest {

    @DisplayName("에러 후 재입력을 받는지 Test")
    @Test
    void inputNotPossibleDate() {
        assertSimpleTest(() -> {
            run("a", "1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        WoowaPlanner woowaPlanner = new WoowaPlanner();
        woowaPlanner.run();
    }
}
