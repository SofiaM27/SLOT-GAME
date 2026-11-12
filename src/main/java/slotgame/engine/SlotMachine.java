package slotgame.engine;

import slotgame.model.GameConfig;
import slotgame.model.SpinResult;
import slotgame.model.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlotMachine {

    private final Random random;

    public SlotMachine() {
        this.random = new Random();
    }

    // Генерація одного випадкового символу
    private Symbol generateRandomSymbol() {
        Symbol[] symbols = Symbol.values();

        int randomIndex = random.nextInt(symbols.length);

        return symbols[randomIndex];
    }

    // Обертання трьох барабанів
    public SpinResult spin() {
        List<Symbol> result = new ArrayList<>();

        for (int i = 0; i < GameConfig.REELS_COUNT; i++) {
            result.add(generateRandomSymbol());
        }

        return new SpinResult(result);
    }
}
