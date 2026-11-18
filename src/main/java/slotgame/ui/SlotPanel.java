
package slotgame.ui;

import slotgame.model.GameConfig;
import slotgame.model.Symbol;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class SlotPanel extends JPanel {

    private static final Color GOLD = new Color(232, 185, 78);

    private final ReelPanel[] reels =
            new ReelPanel[GameConfig.REELS_COUNT];

    public SlotPanel() {

        setOpaque(false);

        setLayout(new GridLayout(
                1,
                GameConfig.REELS_COUNT,
                15,
                0
        ));

        setBorder(new EmptyBorder(15, 10, 15, 10));

        // Створення трьох барабанів
        for (int i = 0; i < GameConfig.REELS_COUNT; i++) {

            reels[i] = new ReelPanel(i + 1);

            add(reels[i]);
        }

        // Початкові символи
        setSymbols(List.of(
                Symbol.CHERRY,
                Symbol.LEMON,
                Symbol.SEVEN
        ));
    }

    // Встановлення символів на всіх трьох барабанах
    public void setSymbols(List<Symbol> symbols) {

        if (symbols == null ||
                symbols.size() != GameConfig.REELS_COUNT) {

            throw new IllegalArgumentException(
                    "Потрібно передати рівно три символи!"
            );
        }

        for (int i = 0; i < GameConfig.REELS_COUNT; i++) {

            setSymbol(i, symbols.get(i));
        }
    }

    // Зміна символу на конкретному барабані
    public void setSymbol(int reelIndex, Symbol symbol) {

        if (reelIndex < 0 ||
                reelIndex >= GameConfig.REELS_COUNT) {

            throw new IllegalArgumentException(
                    "Номер барабана повинен бути від 0 до 2!"
            );
        }

        if (symbol == null) {
            throw new IllegalArgumentException(
                    "Символ не може бути null!"
            );
        }

        reels[reelIndex].setSymbol(symbol);
    }

    // Отримання конкретного барабана
    public ReelPanel getReel(int reelIndex) {

        if (reelIndex < 0 ||
                reelIndex >= GameConfig.REELS_COUNT) {

            throw new IllegalArgumentException(
                    "Неправильний номер барабана!"
            );
        }

        return reels[reelIndex];
    }
}
