package sk.fiit.basicdtd;

import java.util.HashMap;
import java.util.Map;

public final class ParserTable {
    private final Map<Token, Map<Token, Integer>> table = new HashMap<>();

    public final void set(Token nonTerminal, Token terminal, int ruleNumber) {
        final Map<Token, Integer> subTable = table.computeIfAbsent(nonTerminal, k -> new HashMap<>());
        subTable.put(terminal, ruleNumber);
    }

    public final int get(Token nonTerminal, Token terminal) {
        final Map<Token, Integer> subTable = table.get(nonTerminal);
        if (subTable == null) {
            return 0;
        }
        return subTable.getOrDefault(terminal, 0);
    }
}
