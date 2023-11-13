package christmas.view.input.error;

public enum InputError {

    NOT_POSSIBLE_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_POSSIBLE_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    final String message;

    InputError(String message) {
        this.message = message;
    }
}
