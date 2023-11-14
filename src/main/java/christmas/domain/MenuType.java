package christmas.domain;

public enum MenuType {
    APPETIZER("에피타이저"),
    DESSERT("디저트"),
    DRINK("음료"),
    MAIN("메인");

    final String name;

    MenuType(String name) {
        this.name = name;
    }
}
