package christmas.view.output;

public enum Output {

    PLANNER_DATE("12"),

    INTRODUCE_WOOWA_PLANNER("안녕하세요! 우테코 식당 " + PLANNER_DATE.message + "월 이벤트 플래너입니다."),
    INPUT_EXPECTED_VISIT_DATE(PLANNER_DATE.message + "월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_MENU_AND_AMOUNT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PREVIEW_EVENT_BENEFIT(PLANNER_DATE.message + "월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERED_MENU("주문 메뉴"),
    TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT("할인 전 총주문 금액"),
    FREEBIE_MENU("증정 메뉴"),
    BENEFIT_DETAILS("혜택 내역"),
    TOTAL_BENEFIT_AMOUNT("총혜택 금액"),
    EXPECTED_PAYMENT_AMOUNT_AFTER_DISCOUNT("할인 후 예상 결제 금액"),
    DECEMBER_EVENT_BADGE(PLANNER_DATE.message + "월 이벤트 배지");

    final String message;

    Output(String message) {
        this.message = message;
    }
}
