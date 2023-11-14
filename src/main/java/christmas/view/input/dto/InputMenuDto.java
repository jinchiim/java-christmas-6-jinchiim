package christmas.view.input.dto;

import christmas.domain.MenuBoard;
import christmas.view.input.error.InputError;
import christmas.view.input.error.InputIllegalException;
import java.util.EnumMap;

public record InputMenuDto(
        MenuBoard menu, int menuCount) {


    public void validateNotDuplicateMenu(EnumMap<MenuBoard, Integer> menus) {
        if (menus.containsKey(this.menu)) {
            throw new InputIllegalException(InputError.NOT_POSSIBLE_ORDER);
        }
    }
}
