package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import camp.nextstep.edu.missionutils.test.Assertions;
import christmas.view.input.dto.InputMenuDto;
import christmas.view.input.error.InputIllegalException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlanMenuTest {

    @DisplayName("메뉴가 중복되는 경우 Exception 발생")
    @Test
    void duplicateMenuPlanTest() {
        Assertions.assertSimpleTest(() -> {
            List<InputMenuDto> menus = List.of(
                    new InputMenuDto(MenuBoard.CHRISTMAS_PASTA, 3),
                    new InputMenuDto(MenuBoard.CHAMPAGNE, 1),
                    new InputMenuDto(MenuBoard.CHAMPAGNE, 2)
            );

            assertThrows(InputIllegalException.class, () -> PlanMenu.createPlanMenu(menus));
        });
    }

    @DisplayName("메뉴 개수가 20개가 넘는 경우 Exception 발생")
    @Test
    void exceedMaximumMenuAmount() {
        Assertions.assertSimpleTest(() -> {
            List<InputMenuDto> menus = List.of(
                    new InputMenuDto(MenuBoard.CHRISTMAS_PASTA, 15),
                    new InputMenuDto(MenuBoard.CHAMPAGNE, 6)
            );

            assertThrows(InputIllegalException.class, () -> PlanMenu.createPlanMenu(menus));
        });
    }

    @DisplayName("음료만 주문했을 경우 Exception 발생")
    @Test
    void onlyOrderedDrinks() {
        Assertions.assertSimpleTest(() -> {
            List<InputMenuDto> menus = List.of(
                    new InputMenuDto(MenuBoard.CHAMPAGNE, 1),
                    new InputMenuDto(MenuBoard.RED_WINE, 3)
            );

            assertThrows(InputIllegalException.class, () -> PlanMenu.createPlanMenu(menus));
        });
    }
}
