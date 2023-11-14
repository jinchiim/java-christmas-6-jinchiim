package christmas.domain;

import christmas.view.input.dto.InputMenuDto;
import christmas.view.input.error.InputError;
import christmas.view.input.error.InputIllegalException;
import java.util.Arrays;
import java.util.Set;

public enum MenuBoard {

    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6000),
    TAPAS(MenuType.APPETIZER, "타파스", 5500),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8000),
    CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15000),
    ICE_CREAM(MenuType.DESSERT, "아이스크림", 5000),
    ZERO_COLA(MenuType.DRINK, "제로콜라", 3000),
    RED_WINE(MenuType.DRINK, "레드와인", 60000),
    CHAMPAGNE(MenuType.DRINK, "샴페인", 25000),
    T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55000),
    BBQ_RIBS(MenuType.MAIN, "바비큐립", 54000),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25000);

    final MenuType menuType;
    final String menuName;
    final int menuPrice;

    MenuBoard(MenuType menuType, String menuName, int menuPrice) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    private String getMenuName() {
        return this.menuName;
    }

    private MenuType getMenuType() {
        return this.menuType;
    }

    private static boolean containsMenuName(MenuBoard menuBoard, String menuName) {
        return menuBoard.getMenuName().equals(menuName);
    }

    private static MenuBoard getMenuBoardByName(String name) {
        return Arrays.stream(values())
                .filter(menu -> containsMenuName(menu, name))
                .findFirst()
                .orElseThrow(() -> new InputIllegalException(InputError.NOT_POSSIBLE_ORDER));
    }

    public static InputMenuDto getDtoByName(String name, int count) {
        return new InputMenuDto(getMenuBoardByName(name), count);
    }

    public static boolean isAllMenuTypesDrink(Set<MenuBoard> menuSet) {
        return menuSet.stream()
                .allMatch(menu -> menu.getMenuType() == MenuType.DRINK);
    }
}
