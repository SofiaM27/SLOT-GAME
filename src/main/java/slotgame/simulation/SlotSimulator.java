package slotgame.simulation;

import slotgame.model.Symbol;
import slotgame.payout.PayoutCalculator;
import slotgame.analysis.GameStatistics;

import java.util.Arrays;
import java.util.Random;

public class SlotSimulator {

    private PayoutCalculator calculator;
    private Random random;

    public SlotSimulator(PayoutCalculator calculator) {
        if (calculator == null) {
            throw new IllegalArgumentException("Калькулятор виплат не може бути порожнім");
        }

        this.calculator = calculator;
        this.random = new Random();
    }

    public GameStatistics runSimulation(int numberOfSpins, int bet) {

        if (numberOfSpins <= 0 || bet <= 0) {
            throw new IllegalArgumentException("Кількість обертань і ставка повинні бути додатними");
        }

        GameStatistics statistics = new GameStatistics();

        Symbol[] symbols = Symbol.values();

        for (int i = 0; i < numberOfSpins; i++) {

            Symbol first = symbols[random.nextInt(symbols.length)];
            Symbol second = symbols[random.nextInt(symbols.length)];
            Symbol third = symbols[random.nextInt(symbols.length)];

            int payout = calculator.calculatePayout(
                    Arrays.asList(first, second, third), bet
            );

            statistics.addResult(bet, payout);
        }

        return statistics;
    }
}
