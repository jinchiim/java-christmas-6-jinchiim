package christmas.domain;

import christmas.domain.dto.DateEventDto;
import christmas.view.input.dto.InputMenuDto;
import christmas.view.input.error.InputError;
import christmas.view.input.error.InputIllegalException;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

public class PlanMenu {

    private static final String WEEKDAY_DISCOUNT = "평일 할인: ";
    private static final String WEEKEND_DISCOUNT = "주말 할인: ";
    private static final int MAXIMUM_MENU_ORDER = 20;
    private final EnumMap<MenuBoard, Integer> menus;

    private PlanMenu(final EnumMap<MenuBoard, Integer> menus) {
        validateTotalMenuNotExceed(menus.values());
        validateAllMenuIsNotBeverage(menus.keySet());
        this.menus = menus;
    }

    public static PlanMenu createPlanMenu(final List<InputMenuDto> menus) {

        EnumMap<MenuBoard, Integer> orderedMenu = new EnumMap<>(MenuBoard.class);

        menus.forEach(dto -> putMenu(dto, orderedMenu));

        return new PlanMenu(orderedMenu);
    }

    public int calculateTotalPrice() {
        return menus.entrySet().stream()
                .mapToInt(menu -> MenuBoard.calculateTotalPrice(menu.getKey(), menu.getValue()))
                .sum();
    }

    public DateEventDto calculateTotalWeekDayDiscount() {

        return new DateEventDto(WEEKDAY_DISCOUNT, MenuBoard.calculateTotalDessertCount(menus));
    }

    public DateEventDto calculateTotalWeekendDiscount() {
        return new DateEventDto(WEEKEND_DISCOUNT, MenuBoard.calculateTotalMainCount(menus));
    }


    private static void putMenu(final InputMenuDto menuDto, final EnumMap<MenuBoard, Integer> orderedMenu) {
        menuDto.validateNotDuplicateMenu(orderedMenu);

        orderedMenu.put(menuDto.menu(), menuDto.menuCount());
    }

    private void validateTotalMenuNotExceed(Collection<Integer> menuCount) {
        int sumCount = calculateTotalCount(menuCount);

        if (sumCount > MAXIMUM_MENU_ORDER) {
            throw new InputIllegalException(InputError.NOT_POSSIBLE_ORDER);
        }
    }

    private int calculateTotalCount(Collection<Integer> menuCount) {
        return menuCount.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void validateAllMenuIsNotBeverage(Set<MenuBoard> menuBoards) {
        if (MenuBoard.isAllMenuTypesDrink(menuBoards)) {
            throw new InputIllegalException(InputError.NOT_POSSIBLE_ORDER);
        }
    }

    @Override
    public String toString() {
        return MenuBoard.toMenuEventString(menus);
    }
}
