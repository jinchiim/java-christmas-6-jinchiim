package christmas.domain;

import christmas.view.input.error.InputError;
import christmas.view.input.error.InputIllegalException;

public class PlanDate {

    private static final int EVENT_START_DATE = 1;
    private static final int EVENT_END_DATE = 31;

    private final int visitDate;

    private PlanDate(final int visitDate) {
        this.visitDate = visitDate;
    }

    public static PlanDate setPlan(final int visitDate) {
        validateDate(visitDate);
        return new PlanDate(visitDate);
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
