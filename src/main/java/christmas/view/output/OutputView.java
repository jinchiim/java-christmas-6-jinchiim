package christmas.view.output;

public class OutputView {

    public static void printMessage(Output outputMessage) {
        System.out.println(outputMessage.message);
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

}
