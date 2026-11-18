
package slotgame.ui;

import slotgame.model.GameConfig;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private static final Color DARK_PURPLE = new Color(38, 25, 58);
    private static final Color GOLD = new Color(232, 185, 78);
    private static final Color CREAM = new Color(255, 246, 229);

    private final JSpinner betSpinner;
    private final JButton spinButton;

    public ControlPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        setBackground(DARK_PURPLE);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Напис BET
        JLabel betLabel = new JLabel("YOUR BET:");
        betLabel.setFont(new Font("Arial", Font.BOLD, 15));
        betLabel.setForeground(CREAM);

        // Поле введення ставки
        SpinnerNumberModel betModel = new SpinnerNumberModel(
                100.0,
                (double) GameConfig.MIN_BET,
                10000.0,
                10.0
        );

        betSpinner = new JSpinner(betModel);
        betSpinner.setPreferredSize(new Dimension(110, 40));
        betSpinner.setFont(new Font("Arial", Font.BOLD, 16));

        JComponent editor = betSpinner.getEditor();
        editor.setBackground(CREAM);

        // Кнопка SPIN
        spinButton = new JButton("SPIN");
        spinButton.setFont(new Font("Arial", Font.BOLD, 20));
        spinButton.setForeground(DARK_PURPLE);
        spinButton.setBackground(GOLD);
        spinButton.setFocusPainted(false);
        spinButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CREAM, 2),
                new EmptyBorder(10, 35, 10, 35)
        ));
        spinButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Додаємо елементи на панель
        add(betLabel);
        add(betSpinner);
        add(spinButton);
    }

    // Отримання ставки
    public double getBetAmount() {
        return ((Number) betSpinner.getValue()).doubleValue();
    }

    // Додавання обробника натискання кнопки SPIN
    public void addSpinListener(ActionListener listener) {
        spinButton.addActionListener(listener);
    }

    // Блокування або розблокування кнопки
    public void setSpinEnabled(boolean enabled) {
        spinButton.setEnabled(enabled);
    }
}
