package christmas.view.input.error;

public class InputIllegalException extends IllegalArgumentException {

    private static final String ERROR_SYMBOL = "[ERROR] ";

    public InputIllegalException(InputError inputError) {
        super(ERROR_SYMBOL + inputError.message);
    }
}
