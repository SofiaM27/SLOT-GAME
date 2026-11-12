package slotgame.model;


public enum Symbol {

    CHERRY("Вишня"),
    LEMON("Лимон"),
    BELL("Дзвін"),
    BAR("BAR"),
    SEVEN("Сімка");

    private final String displayName;

    Symbol(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
