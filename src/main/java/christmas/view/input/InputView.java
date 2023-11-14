package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.MenuBoard;
import christmas.view.input.dto.InputMenuDto;
import christmas.view.input.error.InputError;
import christmas.view.input.error.InputIllegalException;
import java.util.List;
import java.util.regex.Pattern;

public class InputView {

    private static final String SPLIT_SYMBOL = "-";
    private static final Pattern COMPILE_REGEX = Pattern.compile(",");


    public static int inputDate() {
        return validateDateInteger(Console.readLine());
    }

    public static List<InputMenuDto> inputMenu() {
        return menuToMap(Console.readLine());
    }

    private static List<InputMenuDto> menuToMap(final String menus) {
        validateNotEmpty(menus);

        return COMPILE_REGEX.splitAsStream(menus)
                .map(InputView::insertToDto).toList();
    }

    public static InputMenuDto insertToDto(final String menu) {
        List<String> menus = splitMenus(menu);
        validateMenuFormat(menus);

        String menuName = menus.get(0).trim();
        int count = validateMenuAmountInteger(menus.get(1).trim());

        validateInputMenus(menuName, count);

        return new InputMenuDto(MenuBoard.getMenuBoardByName(menuName), count);
    }

    private static List<String> splitMenus(final String menu) {
        return List.of(menu.split(SPLIT_SYMBOL));
    }

    private static int validateMenuAmountInteger(final String value) {
        validateNotEmpty(value);

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InputIllegalException(InputError.NOT_POSSIBLE_ORDER);
        }
    }

    private static void validateInputMenus(final String menu, final int count) {
        validateNotEmpty(menu);
        validateMenuAmountPositive(count);
    }

    private static void validateMenuFormat(final List<String> menus) {
        if (menus.size() != 2) {
            throw new InputIllegalException(InputError.NOT_POSSIBLE_ORDER);
        }
    }

    private static void validateNotEmpty(final String menu) {
        if (menu.isBlank()) {
            throw new InputIllegalException(InputError.NOT_POSSIBLE_ORDER);
        }
    }

    private static void validateMenuAmountPositive(final int value) {
        if (isNotPositive(value)) {
            throw new InputIllegalException(InputError.NOT_POSSIBLE_ORDER);
        }
    }

    private static int validateDateInteger(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InputIllegalException(InputError.NOT_POSSIBLE_DATE);
        }
    }

    private static boolean isNotPositive(final int value) {
        return value < 0;
    }
}