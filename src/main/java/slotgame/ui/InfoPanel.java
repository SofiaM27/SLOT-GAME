
package slotgame.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfoPanel extends JPanel {

    private static final Color DARK_PURPLE = new Color(38, 25, 58);
    private static final Color GOLD = new Color(232, 185, 78);
    private static final Color CREAM = new Color(255, 246, 229);

    private final JLabel balanceLabel;
    private final JLabel betLabel;
    private final JLabel payoutLabel;
    private final JLabel resultLabel;

    public InfoPanel() {

        setLayout(new GridLayout(2, 2, 15, 10));
        setOpaque(false);

        setBorder(new EmptyBorder(10, 5, 10, 5));

        balanceLabel = createInfoLabel("BALANCE", "1000.00 ₴");
        betLabel = createInfoLabel("YOUR BET", "0.00 ₴");
        payoutLabel = createInfoLabel("LAST WIN", "0.00 ₴");
        resultLabel = createInfoLabel("GAME RESULT", "Let's play!");

        add(createInfoCard("BALANCE", balanceLabel));
        add(createInfoCard("YOUR BET", betLabel));
        add(createInfoCard("LAST WIN", payoutLabel));
        add(createInfoCard("GAME RESULT", resultLabel));
    }

    // Створення текстового елемента
    private JLabel createInfoLabel(String title, String value) {

        JLabel label = new JLabel(value, SwingConstants.CENTER);

        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(GOLD);

        return label;
    }

    // Створення картки інформації
    private JPanel createInfoCard(String title, JLabel valueLabel) {

        JPanel card = new JPanel(new BorderLayout(5, 8));

        card.setBackground(DARK_PURPLE);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GOLD, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel titleLabel = new JLabel(
                title,
                SwingConstants.CENTER
        );

        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(CREAM);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    // Оновлення балансу
    public void updateBalance(double balance) {
        balanceLabel.setText(String.format("%.2f ₴", balance));
    }

    // Оновлення ставки
    public void updateBet(double bet) {
        betLabel.setText(String.format("%.2f ₴", bet));
    }

    // Оновлення останньої виплати
    public void updatePayout(double payout) {
        payoutLabel.setText(String.format("%.2f ₴", payout));
    }

    // Повідомлення про результат гри
    public void updateResult(String message) {
        resultLabel.setText(message);
    }
}
