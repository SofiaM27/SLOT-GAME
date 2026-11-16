package slotgame.analysis;

import slotgame.model.Symbol;
import slotgame.payout.PayoutTable;

public class ProbabilityAnalyzer {

    private PayoutTable payoutTable;

    public ProbabilityAnalyzer(PayoutTable payoutTable) {
        if (payoutTable == null) {
            throw new IllegalArgumentException("Таблиця виплат не може бути порожньою");
        }

        this.payoutTable = payoutTable;
    }

    // Теоретична ймовірність отримати три однакові символи
    public double getWinningProbability() {

        int numberOfSymbols = Symbol.values().length;

        return 1.0 / (numberOfSymbols * numberOfSymbols);
    }

    // Математичне очікування виплати за одне обертання
    public double getExpectedPayout(int bet) {

        if (bet <= 0) {
            throw new IllegalArgumentException("Ставка повинна бути додатною");
        }

        Symbol[] symbols = Symbol.values();

        int sumOfMultipliers = 0;

        for (Symbol symbol : symbols) {
            sumOfMultipliers += payoutTable.getMultiplier(symbol);
        }

        double combinations = Math.pow(symbols.length, 3);

        return bet * sumOfMultipliers / combinations;
    }

    // Теоретичне значення RTP
    public double getTheoreticalRTP(int bet) {

        return getExpectedPayout(bet) / bet * 100;
    }

    // Виведення теоретичних результатів
    public void printTheoreticalResults(int bet) {

        System.out.println("\n===== ТЕОРЕТИЧНІ РЕЗУЛЬТАТИ =====");

        System.out.printf("Ймовірність виграшу: %.2f%%%n",
                getWinningProbability() * 100);

        System.out.printf("Математичне очікування виплати: %.2f%n",
                getExpectedPayout(bet));

        System.out.printf("Теоретичний RTP: %.2f%%%n",
                getTheoreticalRTP(bet));
    }

    // Порівняння теоретичних і практичних результатів
    public void compareResults(GameStatistics statistics, int bet) {

        if (statistics == null) {
            throw new IllegalArgumentException("Статистика не може бути порожньою");
        }

        System.out.println("\n===== ПОРІВНЯННЯ РЕЗУЛЬТАТІВ =====");

        System.out.printf("Теоретична ймовірність виграшу: %.2f%%%n",
                getWinningProbability() * 100);

        System.out.printf("Практична кількість виграшів: %.2f%%%n",
                statistics.getWinRate());

        System.out.printf("Теоретичний RTP: %.2f%%%n",
                getTheoreticalRTP(bet));

        System.out.printf("Практичний RTP: %.2f%%%n",
                statistics.getRTP());
    }
}
