package slotgame.payout;

import slotgame.model.Symbol;

import java.util.EnumMap;
import java.util.Map;
import java.util.Collections;

public class PayoutTable {

    private Map<Symbol, Integer> payouts;

    public PayoutTable() {
        payouts = new EnumMap<>(Symbol.class);

        payouts.put(Symbol.CHERRY, 2);
        payouts.put(Symbol.LEMON, 3);
        payouts.put(Symbol.BELL, 5);
        payouts.put(Symbol.BAR, 10);
        payouts.put(Symbol.SEVEN, 20);
    }

    public int getMultiplier(Symbol symbol) {
        if (symbol == null) {
            throw new IllegalArgumentException("Символ не може бути порожнім");
        }

        return payouts.getOrDefault(symbol, 0);
    }

    public Map<Symbol, Integer> getAllPayouts() {
        return Collections.unmodifiableMap(payouts);
    }
}