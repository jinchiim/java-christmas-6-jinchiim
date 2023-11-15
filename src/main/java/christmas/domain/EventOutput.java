package christmas.domain;

import static christmas.view.output.OutputView.printEventSymbolMessage;
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
    private final static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###");
    private final static String MINUS = "-";
    private final static String NO_EVENTS = "없음";

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
        showOrderedMenu();
        showOrderedPriceMessage();
        showChampagneAmount();
        validateEventTarget();
        validateOnlyPayMenuPrice();
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

        showTotalDiscountEvent(totalDiscount);
    }

    private int showChristMasEvent() {
        int christmasDiscount = planDate.getChristmasEventDiscountMoney();

        printMoneyMessage(CHRISTMAS_BENEFIT_MESSAGE + MINUS + formatNumber(christmasDiscount));

        return christmasDiscount;
    }

    private int showDateEvent() {
        DateEventDto eventDto = eventCalculator.calculateDateDiscountAmount(planDate, planMenu);

        int dateDiscount = eventCalculator.dateEventCalculator(eventDto.discount());

        printMoneyMessage(eventDto.dateType() + MINUS + formatNumber(dateDiscount));

        return dateDiscount;
    }

    private int showStarEvent() {
        if (planDate.isStarDate()) {
            printMoneyMessage(STAR_BENEFIT_MESSAGE + MINUS + formatNumber(STAR_BENEFIT_DISCOUNT));
            return STAR_BENEFIT_DISCOUNT;
        }
        return 0;
    }

    private int showChampagneEvent() {
        if (champagneAmount > 0) {
            int champagneDiscount = MenuBoard.calculateTotalChampagnePrice(champagneAmount);
            printMoneyMessage(CHAMPAGNE_BENEFIT_MESSAGE + MINUS + formatNumber(champagneDiscount));

            return champagneDiscount;
        }
        return 0;
    }

    private void showTotalDiscountEvent(int discount) {
        printEventSymbolMessage(Output.TOTAL_BENEFIT_AMOUNT);
        printMoneyMessage(formatNumber(discount));

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
            champagneAmount = eventCalculator.calculateChampagneAmount(totalPrice);
        }
    }

    private void validateEventTarget() {
        printEventSymbolMessage(Output.BENEFIT_DETAILS);

        if (!eventTarget) {
            printStringEventMessage(NO_EVENTS);
            showTotalDiscountEvent(0);
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

    private String formatNumber(int number) {
        return DECIMAL_FORMAT.format(number);
    }

}
