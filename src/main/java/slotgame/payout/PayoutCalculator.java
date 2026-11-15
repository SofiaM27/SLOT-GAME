package slotgame.payout;

import slotgame.model.Symbol;
import java.util.List;

public class PayoutCalculator {

    private PayoutTable payoutTable;

    public PayoutCalculator(PayoutTable payoutTable) {
        if (payoutTable == null) {
            throw new IllegalArgumentException("Таблиця виплат не може бути порожньою");
        }

        this.payoutTable = payoutTable;
    }

    public int calculatePayout(List<Symbol> symbols, int bet) {

        if (symbols == null || symbols.size() != 3) {
            throw new IllegalArgumentException("Комбінація повинна містити рівно 3 символи");
        }

        if (bet <= 0) {
            throw new IllegalArgumentException("Ставка повинна бути додатною");
        }

        Symbol firstSymbol = symbols.get(0);

        if (firstSymbol == null || symbols.get(1) == null || symbols.get(2) == null) {
            throw new IllegalArgumentException("Символ не може бути порожнім");
        }

        // Перевіряємо, чи всі символи однакові
        if (firstSymbol == symbols.get(1) && firstSymbol == symbols.get(2)) {
            // Обчислюємо виграш
            return bet * payoutTable.getMultiplier(firstSymbol);
        }

        return 0;
    }
}