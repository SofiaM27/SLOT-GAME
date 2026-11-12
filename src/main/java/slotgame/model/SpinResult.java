package slotgame.model;

import java.util.List;
import java.util.Objects;

public class SpinResult {

    private final List<Symbol> symbols;

    public SpinResult(List<Symbol> symbols) {

        Objects.requireNonNull(symbols,
                "Список символів не може бути null");

        if (symbols.size() != GameConfig.REELS_COUNT) {
            throw new IllegalArgumentException(
                    "Кількість символів повинна дорівнювати "
                            + GameConfig.REELS_COUNT
            );
        }

        for (Symbol symbol : symbols) {
            if (symbol == null) {
                throw new IllegalArgumentException(
                        "Символи не можуть бути null"
                );
            }
        }

        this.symbols = List.copyOf(symbols);
    }

    // Отримання всіх символів
    public List<Symbol> getSymbols() {
        return symbols;
    }

    // Отримання символу за номером барабана
    public Symbol getSymbol(int reelIndex) {
        return symbols.get(reelIndex);
    }

    // Відображення результату обертання
    @Override
    public String toString() {
        return symbols.toString();
    }
}
