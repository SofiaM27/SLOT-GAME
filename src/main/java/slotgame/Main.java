package slotgame;

import slotgame.engine.GameSession;
import slotgame.engine.SlotMachine;
import slotgame.payout.PayoutCalculator;
import slotgame.payout.PayoutTable;
import slotgame.ui.GameController;
import slotgame.ui.GameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PayoutTable payoutTable = new PayoutTable();
            PayoutCalculator payoutCalculator = new PayoutCalculator(payoutTable);
            SlotMachine slotMachine = new SlotMachine();

            // 2. Ініціалізуємо ігрову сесію
            GameSession gameSession = new GameSession(
                    slotMachine,
                    (result, bet) -> (double) payoutCalculator.calculatePayout(
                            result.getSymbols(), bet.intValue()
                    )
            );

            GameFrame gameFrame = new GameFrame();

            new GameController(gameFrame, gameSession);

            gameFrame.setVisible(true);
        });
    }
}