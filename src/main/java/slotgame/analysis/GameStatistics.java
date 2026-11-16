package slotgame.analysis;

public class GameStatistics {

    private int totalSpins;
    private int wins;
    private int losses;

    private long totalBets;
    private long totalPayouts;

    public GameStatistics() {
        totalSpins = 0;
        wins = 0;
        losses = 0;
        totalBets = 0;
        totalPayouts = 0;
    }

    // Додаємо результат одного обертання
    public void addResult(int bet, int payout) {

        totalSpins++;
        totalBets += bet;
        totalPayouts += payout;

        if (payout > 0) {
            wins++;
        } else {
            losses++;
        }
    }

    // Кількість виграшів у відсотках
    public double getWinRate() {
        if (totalSpins == 0) {
            return 0;
        }

        return (double) wins / totalSpins * 100;
    }

    // Розрахунок RTP
    public double getRTP() {
        if (totalBets == 0) {
            return 0;
        }

        return (double) totalPayouts / totalBets * 100;
    }

    public int getTotalSpins() {
        return totalSpins;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public long getTotalBets() {
        return totalBets;
    }

    public long getTotalPayouts() {
        return totalPayouts;
    }

    // Виведення статистики
    public void printStatistics() {

        System.out.println("\n===== РЕЗУЛЬТАТИ СИМУЛЯЦІЇ =====");

        System.out.println("Кількість обертань: " + totalSpins);
        System.out.println("Кількість виграшів: " + wins);
        System.out.println("Кількість програшів: " + losses);

        System.out.println("Загальна сума ставок: " + totalBets);
        System.out.println("Загальна сума виплат: " + totalPayouts);

        System.out.printf("Відсоток виграшів: %.2f%%%n", getWinRate());
        System.out.printf("RTP: %.2f%%%n", getRTP());
    }
}

