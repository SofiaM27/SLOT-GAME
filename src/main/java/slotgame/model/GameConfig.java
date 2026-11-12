package slotgame.model;


public final class GameConfig {

    // Кількість барабанів
    public static final int REELS_COUNT = 3;

    // Початковий баланс гравця
    public static final double INITIAL_BALANCE = 1000.0;

    // Мінімальна ставка
    public static final double MIN_BET = 10.0;

    // Кількість різних символів
    public static final int SYMBOLS_COUNT = Symbol.values().length;

    // Приватний конструктор
    private GameConfig() {
    }
}
