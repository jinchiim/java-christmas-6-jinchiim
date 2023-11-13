package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.input.error.InputError;
import christmas.view.input.error.InputIllegalException;

public class InputView {

    public static int inputDate() {
        return validateDateInteger(Console.readLine());
    }


    private static int validateDateInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InputIllegalException(InputError.NOT_POSSIBLE_DATE);
        }
    }
}
