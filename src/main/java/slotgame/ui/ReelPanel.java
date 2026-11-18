
package slotgame.ui;

import slotgame.model.Symbol;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class ReelPanel extends JPanel {

    private static final Color REEL_COLOR = new Color(255, 246, 229);
    private static final Color GOLD = new Color(232, 185, 78);
    private static final Color DARK_PURPLE = new Color(38, 25, 58);

    private final JLabel symbolLabel;

    public ReelPanel(int reelNumber) {
        setLayout(new BorderLayout(5, 10));
        setBackground(REEL_COLOR);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GOLD, 3),
                new EmptyBorder(12, 8, 12, 8)
        ));

        JLabel titleLabel = new JLabel(
                "БАРАБАН " + reelNumber,
                SwingConstants.CENTER
        );

        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(DARK_PURPLE);

        symbolLabel = new JLabel("?", SwingConstants.CENTER);
        symbolLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 55));
        symbolLabel.setForeground(DARK_PURPLE);

        add(titleLabel, BorderLayout.NORTH);
        add(symbolLabel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(150, 160));
    }

    // Встановлення символу на барабані
    public void setSymbol(Symbol symbol) {
        if (symbol == null) {
            throw new IllegalArgumentException("Символ не може бути null");
        }

        String imagePath = switch (symbol) {
            case CHERRY -> "/images/cherry.png";
            case LEMON -> "/images/lemon.png";
            case BELL -> "/images/bell.png";
            case BAR -> "/images/bar.png";
            case SEVEN -> "/images/seven.png";
        };

        URL imageUrl = getClass().getResource(imagePath);

        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);

            Image image = icon.getImage().getScaledInstance(
                    85, 85, Image.SCALE_SMOOTH
            );

            symbolLabel.setIcon(new ImageIcon(image));
            symbolLabel.setText("");
        } else {
            symbolLabel.setIcon(null);
            symbolLabel.setText(getSymbolText(symbol));
        }

        symbolLabel.setToolTipText(symbol.getDisplayName());
    }

    // Запасний варіант, якщо зображення ще не додані
    private String getSymbolText(Symbol symbol) {
        return switch (symbol) {
            case CHERRY -> "🍒";
            case LEMON -> "🍋";
            case BELL -> "🔔";
            case BAR -> "BAR";
            case SEVEN -> "7️⃣";
        };
    }
}
