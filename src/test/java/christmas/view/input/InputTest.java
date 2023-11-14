package christmas.view.input;

import static org.junit.jupiter.api.Assertions.assertThrows;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.input.error.InputIllegalException;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputTest {

    @DisplayName("날짜 문자 입력 Test")
    @ParameterizedTest
    @ValueSource(strings = {"!", "a", "-", "아"})
    void inputNotPossibleDateTest(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertThrows(InputIllegalException.class, InputView::inputDate);
        Console.close();
    }

    @DisplayName(",로 나누어지지 않는 메뉴와 개수 입력 Test")
    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드-4제로콜라-2,티본스테이크-1", "티본스테이크-3,제로콜라2,샴페인-1"})
    void inputNotSplitMenusTest(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertThrows(InputIllegalException.class, InputView::inputMenu);
        Console.close();
    }

    @DisplayName("구매할 수 없는 매뉴 개수 Test")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-a,티본스테이크-2", "샴페인-1,시저샐러드-3,해산물파스타-o"})
    void inputNotAvailableCount(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertThrows(InputIllegalException.class, InputView::inputMenu);
        Console.close();
    }

    @DisplayName("비어있는 매뉴 개수 Test")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라- ,티본스테이크-2", "제로콜라-,티본스테이크-2", " "})
    void inputBlankCount(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertThrows(InputIllegalException.class, InputView::inputMenu);
        Console.close();
    }
}
