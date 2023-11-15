package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.view.input.dto.InputMenuDto;
import java.util.EnumMap;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuBoardTest {

    private static final String NOTHING = "없음";
    private static final String COUNT = "개";
    private static final String BLANK = " ";

    @DisplayName("메뉴 이름을 String으로 잘 만드는지 Test")
    @ParameterizedTest
    @CsvSource({
            "ZERO_COLA, 2", "CAESAR_SALAD, 4"
    })
    void toMenuEventStringTest(String menuName, int menuCount) {
        EnumMap<MenuBoard, Integer> menus = new EnumMap<>(MenuBoard.class);
        MenuBoard menuBoard = MenuBoard.valueOf(menuName);

        menus.put(menuBoard, menuCount);

        String result = MenuBoard.toMenuEventString(menus);

        assertEquals(result, menuBoard.menuName + " " + menuCount + "개");
    }

    @DisplayName("dto에 잘 담기는지 Test")
    @ParameterizedTest
    @CsvSource({
            "레드와인, RED_WINE, 3",
            "바비큐립, BBQ_RIBS, 5"
    })
    void toDtoByMenuNameTest(String menuName, String menuBoardName, int menuCount) {
        InputMenuDto inputMenuDto = MenuBoard.getDtoByName(menuName, menuCount);
        MenuBoard menuBoard = MenuBoard.valueOf(menuBoardName);

        assertEquals(inputMenuDto.menuCount(), menuCount);
        assertEquals(inputMenuDto.menu(), menuBoard);
    }

    @DisplayName("샴페인이 있는 경우 잘 가져오는지 Test")
    @ParameterizedTest
    @ValueSource(ints = {1})
    void toChampagneAmountStringTest(int count) {
        String champagneString = MenuBoard.toChampagneAmountString(count);

        MenuBoard champagne = MenuBoard.CHAMPAGNE;

        assertEquals(champagneString, champagne.menuName + BLANK + count + COUNT);
    }

    @DisplayName("샴페인이 없는 경우 없음 return Test")
    @ParameterizedTest
    @ValueSource(ints = {0})
    void toZeroChampagneAmountStringTest(int count) {
        String champagneString = MenuBoard.toChampagneAmountString(count);

        assertEquals(champagneString, NOTHING);
    }

    @DisplayName("주문 Type에 따른 boolean return Test")
    @ParameterizedTest
    @CsvSource({
            "true, RED_WINE, CHAMPAGNE",
            "false, T_BONE_STEAK, CHRISTMAS_PASTA"
    })
    void checkAllMenuTypesDrinkMethodTest(boolean result, String menuName, String menuName2) {
        MenuBoard menuBoard = MenuBoard.valueOf(menuName);
        MenuBoard menuBoard2 = MenuBoard.valueOf(menuName2);

        Set<MenuBoard> menuBoards = Set.of(menuBoard, menuBoard2);

        assertEquals(MenuBoard.isAllMenuTypesDrink(menuBoards), result);
    }

    @DisplayName("샴페인의 측정 가격에 따라 총 가격을 잘 가져오는지 Test")
    @ParameterizedTest
    @ValueSource(ints = {1})
    void getChampagneTotalPrice(int amount) {
        int totalChampagnePrice = MenuBoard.calculateTotalChampagnePrice(amount);

        MenuBoard champagne = MenuBoard.CHAMPAGNE;

        assertEquals(totalChampagnePrice, champagne.menuPrice * amount);
    }
}