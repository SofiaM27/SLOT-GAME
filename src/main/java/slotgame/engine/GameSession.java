package slotgame.engine;

import slotgame.engine.SlotMachine;
import slotgame.model.GameConfig;
import slotgame.model.SpinResult;

import java.util.Objects;
import java.util.function.BiFunction;

public class GameSession {

    private final SlotMachine slotMachine;

    // Функція для розрахунку виплати
    private final BiFunction<SpinResult, Double, Double> payoutCalculator;

    private double balance;
    private double lastPayout;

    public GameSession(
            SlotMachine slotMachine,
            BiFunction<SpinResult, Double, Double> payoutCalculator) {

        this.slotMachine = Objects.requireNonNull(slotMachine);
        this.payoutCalculator = Objects.requireNonNull(payoutCalculator);

        this.balance = GameConfig.INITIAL_BALANCE;
        this.lastPayout = 0.0;
    }

    // Основний ігровий процес
    public SpinResult play(double bet) {

        // Перевірка коректності ставки
        if (!Double.isFinite(bet) || bet < GameConfig.MIN_BET) {
            throw new IllegalArgumentException(
                    "Ставка повинна бути не меншою за " + GameConfig.MIN_BET
            );
        }

        // Перевірка достатності балансу
        if (bet > balance) {
            throw new IllegalArgumentException(
                    "Недостатньо коштів на балансі!"
            );
        }

        SpinResult result = slotMachine.spin();

        // Розрахунок виплати
        double payout = payoutCalculator.apply(result, bet);

        if (!Double.isFinite(payout) || payout < 0) {
            throw new IllegalStateException("Некоректна сума виплати!");
        }

        // Оновлення балансу
        balance = balance - bet + payout;

        lastPayout = payout;

        return result;
    }

    // Отримання поточного балансу
    public double getBalance() {
        return balance;
    }

    // Отримання останньої виплати
    public double getLastPayout() {
        return lastPayout;
    }
}
