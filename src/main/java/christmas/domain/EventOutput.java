package christmas.domain;

import static christmas.view.output.OutputView.printEventSymbolMessage;
import static christmas.view.output.OutputView.printMessage;
import static christmas.view.output.OutputView.printMoneyMessage;
import static christmas.view.output.OutputView.printStringEventMessage;

import christmas.domain.dto.DateEventDto;
import christmas.view.output.Output;
import java.text.DecimalFormat;

public class EventOutput {

    private final static int MIN_EVENT_TARGET_PRICE = 10000;
    private final static int MIN_CHAMPAGNE_EVENT_AMOUNT = 120000;
    private final static String CHRISTMAS_BENEFIT_MESSAGE = "크리스마스 디데이 할인: ";
    private final static String STAR_BENEFIT_MESSAGE = "특별 할인: ";
    private final static String CHAMPAGNE_BENEFIT_MESSAGE = "증정 이벤트: ";
    private final static int STAR_BENEFIT_DISCOUNT = 1000;
    private final static int CHAMPAGNE_EVENT_AMOUNT = 1;
    private final static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###");
    private final static String MINUS = "-";
    private final static String NO_EVENTS = "없음";
    private final static int NO_DISCOUNT = 0;

    private final PlanMenu planMenu;
    private final PlanDate planDate;
    private final EventCalculator eventCalculator;

    private boolean eventTarget = true;
    private int champagneAmount = 0;

    private EventOutput(PlanMenu planMenu, PlanDate planDate, EventCalculator eventCalculator) {
        this.planMenu = planMenu;
        this.planDate = planDate;
        this.eventCalculator = eventCalculator;
    }

    public static EventOutput show(final PlanMenu planMenu, final PlanDate planDate) {

        return new EventOutput(planMenu, planDate, new EventCalculator());
    }

    public void showAllEvents() {
        printMessage(Output.PREVIEW_EVENT_BENEFIT, planDate.toString());
        showOrderedMenu();
        showOrderedPriceMessage();
        showChampagneAmount();
        validateEventTarget();
    }

    private void showOrderedMenu() {
        printEventSymbolMessage(Output.ORDERED_MENU);
        printStringEventMessage(planMenu.toString());
    }

    private void showOrderedPriceMessage() {
        printEventSymbolMessage(Output.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT);
        int totalPrice = planMenu.calculateTotalPrice();

        printMoneyMessage(formatNumber(totalPrice));
        checkIsEventTargetMember(totalPrice);
    }

    private void showChampagneAmount() {
        printEventSymbolMessage(Output.FREEBIE_MENU);

        printStringEventMessage(MenuBoard.toChampagneAmountString(champagneAmount));
    }

    private void showTotalDiscountBenefits() {
        int christmasDiscount = showChristMasEvent();
        int dateDiscount = showDateEvent();
        int starDiscount = showStarEvent();
        int champagneDiscount = showChampagneEvent();

        int totalDiscount = eventCalculator.totalDiscountCalculator(christmasDiscount, champagneDiscount,
                dateDiscount, starDiscount);

        if (totalDiscount == NO_DISCOUNT) {
            printStringEventMessage(NO_EVENTS);
        }

        showTotalDiscountEvent(totalDiscount);
    }

    private int showChristMasEvent() {
        int christmasDiscount = planDate.getChristmasEventDiscountMoney();

        if (christmasDiscount > NO_DISCOUNT) {
            printMoneyMessage(CHRISTMAS_BENEFIT_MESSAGE + checkDiscountAmount(christmasDiscount));
        }

        return christmasDiscount;
    }

    private int showDateEvent() {
        DateEventDto eventDto = eventCalculator.calculateDateDiscountAmount(planDate, planMenu);

        int dateDiscount = eventCalculator.dateEventCalculator(eventDto.discount());

        if (dateDiscount > NO_DISCOUNT) {
            printMoneyMessage(eventDto.dateType() + MINUS + formatNumber(dateDiscount));
        }

        return dateDiscount;
    }

    private int showStarEvent() {
        if (planDate.isStarDate()) {
            printMoneyMessage(STAR_BENEFIT_MESSAGE + MINUS + formatNumber(STAR_BENEFIT_DISCOUNT));
            return STAR_BENEFIT_DISCOUNT;
        }
        return NO_DISCOUNT;
    }

    private int showChampagneEvent() {
        if (champagneAmount > NO_DISCOUNT) {
            int champagneDiscount = MenuBoard.calculateTotalChampagnePrice(champagneAmount);
            printMoneyMessage(CHAMPAGNE_BENEFIT_MESSAGE + MINUS + formatNumber(champagneDiscount));

            return champagneDiscount;
        }
        return NO_DISCOUNT;
    }

    private void showTotalDiscountEvent(int discount) {
        printEventSymbolMessage(Output.TOTAL_BENEFIT_AMOUNT);
        printMoneyMessage(checkDiscountAmount(discount));

        validateOnlyPayMenuPrice();
        showBadgeEvent(discount);
    }

    private void showBadgeEvent(int discount) {
        printEventSymbolMessage(Output.DECEMBER_EVENT_BADGE);
        printStringEventMessage(eventCalculator.badgeCalculator(discount));
    }

    private void checkIsEventTargetMember(int totalPrice) {
        if (totalPrice < MIN_EVENT_TARGET_PRICE) {
            eventTarget = false;
        }
        if (totalPrice >= MIN_CHAMPAGNE_EVENT_AMOUNT) {
            champagneAmount = CHAMPAGNE_EVENT_AMOUNT;
        }
    }

    private void validateEventTarget() {
        printEventSymbolMessage(Output.BENEFIT_DETAILS);

        if (!eventTarget) {
            printStringEventMessage(NO_EVENTS);
            showTotalDiscountEvent(NO_DISCOUNT);
        }

        if (eventTarget) {
            showTotalDiscountBenefits();
        }
    }

    private void validateOnlyPayMenuPrice() {
        printEventSymbolMessage(Output.EXPECTED_PAYMENT_AMOUNT_AFTER_DISCOUNT);
        int totalPrice = planMenu.calculateTotalPrice();

        if (!eventTarget) {
            printMoneyMessage(formatNumber(totalPrice));
        }

        if (eventTarget) {
            int realPayPrice = eventCalculator.totalPayMoneyCalculator(totalPrice);
            printMoneyMessage(formatNumber(realPayPrice));
        }
    }

    private String checkDiscountAmount(int discount) {
        String totalDiscountNumber = formatNumber(discount);

        if (discount > NO_DISCOUNT) {
            totalDiscountNumber = MINUS + totalDiscountNumber;
        }

        return totalDiscountNumber;
    }

    private String formatNumber(int number) {
        return DECIMAL_FORMAT.format(number);
    }

}
