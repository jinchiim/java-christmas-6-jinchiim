package christmas.controller;

import static christmas.view.input.InputView.inputDate;
import static christmas.view.input.InputView.inputMenu;
import static christmas.view.output.OutputView.printErrorMessage;
import static christmas.view.output.OutputView.printMessage;

import christmas.domain.PlanDate;
import christmas.domain.PlanMenu;
import christmas.view.input.dto.InputMenuDto;
import christmas.view.output.Output;
import java.util.List;

public class WoowaPlanner {

    public void run() {
        printMessage(Output.INTRODUCE_WOOWA_PLANNER);
        userPlanDate();
        userPlanMenu();
    }

    private void userPlanDate() {
        planDateGet();
    }

    private void userPlanMenu() {
        planMenuGet();
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
            PlanDate.setPlan(date);
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            userPlanDate();
        }
    }

    private void planMenuGet() {
        try {
            printMessage(Output.ORDER_MENU_AND_AMOUNT);
            List<InputMenuDto> menus = inputMenu();
            menuCheck(menus);
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            userPlanMenu();
        }
    }

    private void menuCheck(List<InputMenuDto> menus) {
        PlanMenu.createPlanMenu(menus);
    }
}
