package christmas.domain;

import christmas.domain.dto.DateEventDto;

public class EventCalculator {
    private static final int CHRISTMAS_EVENT_START_DAY = 1;
    private static final int CHRISTMAS_EVENT_START_MONEY = 1000;
    private static final int CHRISTMAS_EVENT_INCREASE_AMOUNT = 100;
    private static final int DATE_DISCOUNT_PRICE = 2023;
    private static final int MIN_STAR_BADGE_AMOUNT = 5000;
    private static final int MIN_TREE_BADGE_AMOUNT = 10000;
    private static final int MIN_SANTA_BADGE_AMOUNT = 15000;
    private final static int MIN_CHAMPAGNE_EVENT_AMOUNT = 120000;
    private static final String STAR_BADGE = "별";
    private static final String TREE_BADGE = "트리";
    private static final String SANTA_BADGE = "산타";
    private static final String NONE = "없음";

    private int realDiscount;

    public int christMasEventCalculator(int day) {

        return (day - CHRISTMAS_EVENT_START_DAY) * CHRISTMAS_EVENT_INCREASE_AMOUNT + CHRISTMAS_EVENT_START_MONEY;
    }

    public int dateEventCalculator(int amount) {
        return amount * DATE_DISCOUNT_PRICE;
    }

    public int totalDiscountCalculator(int christMasDiscount, int champagneDiscount, int dateDiscount,
                                       int starDiscount) {
        realDiscount = christMasDiscount + dateDiscount + starDiscount;
        return christMasDiscount + champagneDiscount + dateDiscount + starDiscount;
    }

    public int totalPayMoneyCalculator(int totalMoney) {
        return totalMoney - realDiscount;
    }

    public String badgeCalculator(int totalPrice) {
        if (totalPrice >= MIN_SANTA_BADGE_AMOUNT) {
            return SANTA_BADGE;
        }
        if (totalPrice >= MIN_TREE_BADGE_AMOUNT) {
            return TREE_BADGE;
        }
        if (totalPrice >= MIN_STAR_BADGE_AMOUNT) {
            return STAR_BADGE;
        }
        return NONE;
    }

    public DateEventDto calculateDateDiscountAmount(PlanDate planDate, PlanMenu planMenu) {
        boolean isWeekDay = planDate.getDateIsWeekDay();

        if (isWeekDay) {
            return planMenu.calculateTotalWeekDayDiscount();
        }

        return planMenu.calculateTotalWeekendDiscount();
    }

    public int calculateChampagneAmount(int totalPrice) {
        return (int) Math.floor((double) totalPrice / MIN_CHAMPAGNE_EVENT_AMOUNT);
    }
}
