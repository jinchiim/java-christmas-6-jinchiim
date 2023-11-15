package christmas.domain;

import christmas.view.input.error.InputError;
import christmas.view.input.error.InputIllegalException;

public class PlanDate {

    private static final int EVENT_START_DATE = 1;
    private static final int EVENT_END_DATE = 31;
    private static final int CHRISTMAS_EVENT_END_DATE = 25;

    private final int visitDate;

    private PlanDate(final int visitDate) {
        this.visitDate = visitDate;
    }

    public static PlanDate createPlan(final int visitDate) {
        validateDate(visitDate);
        return new PlanDate(visitDate);
    }

    public boolean isWeekDate() {
        return WoowaCalender.isWeekDate(visitDate);
    }

    public boolean isStarDate() {
        return WoowaCalender.isStarDate(visitDate);
    }

    public int getChristmasEventDiscountMoney() {
        if (visitDate > CHRISTMAS_EVENT_END_DATE) {
            return 0;
        }
        return new EventCalculator().christMasEventCalculator(visitDate);
    }

    private static void validateDate(int visitDate) {
        if (isNotEventDate(visitDate)) {
            throw new InputIllegalException(InputError.NOT_POSSIBLE_DATE);
        }

    }

    private static boolean isNotEventDate(int visitDate) {
        return visitDate < EVENT_START_DATE || visitDate > EVENT_END_DATE;
    }
}
