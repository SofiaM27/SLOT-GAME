package slotgame.ui;

import slotgame.engine.GameSession;
import slotgame.engine.SlotMachine;
import slotgame.model.SpinResult;
import slotgame.payout.PayoutCalculator;
import slotgame.payout.PayoutTable;
import slotgame.simulation.SlotSimulator;
import slotgame.analysis.GameStatistics;
import slotgame.model.GameConfig;

import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner;

    private final GameSession gameSession;
    private final SlotSimulator simulator;

    private GameStatistics lastStatistics;

    public ConsoleMenu() {
        scanner = new Scanner(System.in);

        // Створення необхідних об'єктів гри
        PayoutTable payoutTable = new PayoutTable();
        PayoutCalculator payoutCalculator =
                new PayoutCalculator(payoutTable);

        SlotMachine slotMachine = new SlotMachine();

        // Підключення калькулятора виплат до ігрової сесії
        gameSession = new GameSession(
                slotMachine,
                (result, bet) -> (double) payoutCalculator.calculatePayout(
                        result.getSymbols(), bet.intValue()
                )
        );

        // Створення симулятора
        simulator = new SlotSimulator(payoutCalculator);

        lastStatistics = null;
    }

    // Головне меню
    public void showMenu() {

        boolean running = true;

        while (running) {

            System.out.println("\n========== SLOT GAME ==========");
            System.out.println("1. Грати");
            System.out.println("2. Симуляція");
            System.out.println("3. Статистика");
            System.out.println("4. Вихід");
            System.out.println("===============================");

            int choice = readInt("Оберіть пункт меню: ");

            switch (choice) {

                case 1:
                    playGame();
                    break;

                case 2:
                    runSimulation();
                    break;

                case 3:
                    showStatistics();
                    break;

                case 4:
                    running = false;
                    System.out.println("Дякуємо за гру!");
                    break;

                default:
                    System.out.println("Невірний пункт меню!");
            }
        }
    }

    // Режим гри
    private void playGame() {

        boolean playing = true;

        System.out.println("\n===== РЕЖИМ ГРИ =====");

        while (playing) {

            System.out.printf(
                    "Ваш баланс: %.2f%n",
                    gameSession.getBalance()
            );

            if (gameSession.getBalance() < GameConfig.MIN_BET) {
                System.out.println("Недостатньо коштів для продовження гри.");
                break;
            }

            int bet = readInt("Введіть ставку: ");

            try {
                SpinResult result = gameSession.play(bet);

                System.out.println("\nРезультат обертання:");
                System.out.println(result);

                System.out.printf(
                        "Ваша виплата: %.2f%n",
                        gameSession.getLastPayout()
                );

                System.out.printf(
                        "Поточний баланс: %.2f%n",
                        gameSession.getBalance()
                );

            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage());
                continue;
            }

            System.out.print("\nБажаєте зіграти ще раз? (1 - так, 0 - ні): ");

            int answer = readInt("");

            if (answer != 1) {
                playing = false;
            }
        }
    }

    // Режим симуляції
    private void runSimulation() {

        System.out.println("\n===== РЕЖИМ СИМУЛЯЦІЇ =====");

        int numberOfSpins = readInt("Введіть кількість обертань: ");
        int bet = readInt("Введіть ставку: ");

        try {
            lastStatistics = simulator.runSimulation(
                    numberOfSpins, bet
            );

            System.out.println("\nСимуляцію завершено!");
            lastStatistics.printStatistics();

        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    // Відображення статистики
    private void showStatistics() {

        System.out.println("\n===== СТАТИСТИКА =====");

        if (lastStatistics == null) {
            System.out.println("Статистика ще відсутня.");
            System.out.println("Спочатку проведіть симуляцію.");
            return;
        }

        lastStatistics.printStatistics();
    }

    // Зчитування цілого числа
    private int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Помилка! Введіть ціле число.");
            }
        }
    }
}
