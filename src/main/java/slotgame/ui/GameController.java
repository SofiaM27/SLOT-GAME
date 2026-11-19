package slotgame.ui;

import slotgame.engine.GameSession;
import slotgame.model.SpinResult;

import javax.swing.*;

public class GameController {

    private final GameFrame gameFrame;
    private final GameSession gameSession;

    public GameController(GameFrame gameFrame, GameSession gameSession) {
        if (gameFrame == null || gameSession == null) {
            throw new IllegalArgumentException("Об'єкти не можуть бути null!");
        }

        this.gameFrame = gameFrame;
        this.gameSession = gameSession;

        updateView();

        gameFrame.getControlPanel().addSpinListener(e -> handleSpin());
    }

    private void handleSpin() {
        ControlPanel controlPanel = gameFrame.getControlPanel();

        double bet = controlPanel.getBetAmount();

        controlPanel.setSpinEnabled(false);
        gameFrame.setMessage("Крутимо барабани...");

        SwingWorker<SpinResult, Void> worker = new SwingWorker<>() {

            @Override
            protected SpinResult doInBackground() throws Exception {
                Thread.sleep(300);
                return gameSession.play(bet);
            }

            @Override
            protected void done() {
                try {
                    SpinResult result = get();

                    gameFrame.getSlotPanel().setSymbols(result.getSymbols());

                    double payout = gameSession.getLastPayout();
                    updateView();

                    if (payout > 0) {
                        gameFrame.setMessage(
                                String.format("ВИГРАШ! Отримано: %.2f ₴", payout)
                        );
                    } else {
                        gameFrame.setMessage("Програш. Спробуйте ще раз!");
                    }

                } catch (Exception ex) {
                    String error = ex.getCause() != null
                            ? ex.getCause().getMessage()
                            : ex.getMessage();

                    JOptionPane.showMessageDialog(
                            gameFrame,
                            error,
                            "Помилка",
                            JOptionPane.WARNING_MESSAGE
                    );

                    gameFrame.setMessage("Зробіть ставку та натисніть SPIN!");
                } finally {
                    controlPanel.setSpinEnabled(true);
                }
            }
        };

        worker.execute();
    }

    private void updateView() {
        gameFrame.updateBalance(gameSession.getBalance());

        double bet = gameFrame.getControlPanel().getBetAmount();
        gameFrame.getInfoPanel().updateBet(bet);
        gameFrame.getInfoPanel().updatePayout(gameSession.getLastPayout());
    }
}