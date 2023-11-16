package christmas.controller;

import static christmas.view.input.InputView.inputDate;
import static christmas.view.input.InputView.inputMenu;
import static christmas.view.output.OutputView.printErrorMessage;
import static christmas.view.output.OutputView.printMessage;

import christmas.domain.EventOutput;
import christmas.domain.PlanDate;
import christmas.domain.PlanMenu;
import christmas.view.input.dto.InputMenuDto;
import christmas.view.output.Output;
import java.util.List;

public class WoowaPlanner {

    public void run() {
        printMessage(Output.INTRODUCE_WOOWA_PLANNER);

        planStart();
    }

    private void planStart() {
        PlanDate planDate = userPlanDate();
        PlanMenu planMenu = userPlanMenu();

        userEvents(planMenu, planDate);
    }

    private PlanDate userPlanDate() {
        return planDateGet();
    }

    private PlanMenu userPlanMenu() {
        return planMenuGet();
    }

    private void userEvents(PlanMenu planMenu, PlanDate planDate) {
        EventOutput eventOutput = EventOutput.show(planMenu, planDate);
        eventOutput.showAllEvents();
    }

    private PlanDate planDateGet() {
        try {
            printMessage(Output.INPUT_EXPECTED_VISIT_DATE);
            int date = inputDate();
            return visitDateCheck(date);
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            return userPlanDate();
        }
    }

    private PlanDate visitDateCheck(int date) {
        try {
            return PlanDate.createPlan(date);
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            return userPlanDate();
        }
    }

    private PlanMenu planMenuGet() {
        try {
            printMessage(Output.ORDER_MENU_AND_AMOUNT);
            List<InputMenuDto> menus = inputMenu();
            return menuCheck(menus);
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            return userPlanMenu();
        }
    }

    private PlanMenu menuCheck(List<InputMenuDto> menus) {
        return PlanMenu.createPlanMenu(menus);
    }
}
