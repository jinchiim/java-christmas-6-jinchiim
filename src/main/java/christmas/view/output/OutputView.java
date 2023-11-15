package christmas.view.output;

public class OutputView {

    private static final String EVENT_START_SYMBOL = "<";
    private static final String EVENT_END_SYMBOL = ">";
    private static final String NEW_LINE = "\n";
    private static final String MONEY_COUNT = "원";

    public static void printStringEventMessage(String value) {
        System.out.println(value);
    }

    public static void printMoneyMessage(String value) {
        System.out.println(value + MONEY_COUNT);
    }

    public static void printMessage(Output outputMessage) {
        System.out.println(outputMessage.message);
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void printEventSymbolMessage(Output outputMessage) {
        System.out.println(NEW_LINE + EVENT_START_SYMBOL + outputMessage.message + EVENT_END_SYMBOL);
    }
}
