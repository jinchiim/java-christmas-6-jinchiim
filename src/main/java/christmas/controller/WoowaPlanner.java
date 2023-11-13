package christmas.controller;

import static christmas.view.input.InputView.inputDate;
import static christmas.view.output.OutputView.printErrorMessage;
import static christmas.view.output.OutputView.printMessage;

import christmas.domain.Plan;
import christmas.view.output.Output;

public class WoowaPlanner {

    public void run() {
        printMessage(Output.INTRODUCE_WOOWA_PLANNER);
        userPlanDate();
    }

    private void userPlanDate() {
        planDateGet();
    }

    private void userPlanMenu() {

    }

    private void planDateGet() {
        try {
            printMessage(Output.INPUT_EXPECTED_VISIT_DATE);
            int date = inputDate();
            visitDateCheck(date);
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            userPlanDate();
        }
    }

    private void visitDateCheck(int date) {
        try {
            Plan.setPlan(date);
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            userPlanDate();
        }
    }

    private void planMenuGet() {
        printMessage(Output.ORDER_MENU_AND_AMOUNT);
        /*
         * - input
         *   1. '-'를 기준으로 나누기
         *   2. 뒤가 숫자인지 확인하기
         * */

        // 1. 중복 확인 하기
        // 2. 20개 초과인지 확인하기
        // 3. 있는 메뉴인지 확인하기
        // 4. 1이상인지 확인하기
        // 5. 음료만 있는지 확인하기
    }
}
