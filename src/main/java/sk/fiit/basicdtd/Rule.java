package sk.fiit.basicdtd;

import java.util.List;
import java.util.Stack;

public class Rule {
    private final Token from;
    private final List<Token> tokens;

    public Rule(final Token from, final List<Token> tokens) {
        this.from = from;
        this.tokens = tokens;
    }

    public final void applyToStack(final Stack<Token> stack) {
        stack.pop();
        if (isEpsilon()) {
            return;
        }
        for (int i = tokens.size() - 1; i >= 0; i--) {
            stack.push(tokens.get(i));
        }
    }

    public final boolean isEpsilon() {
        return tokens.size() == 1 && tokens.get(0) == Token.T_EPSILON;
    }

    @Override
    public final String toString() {
        return String.format("%s -> %s%n", from, tokens);
    }
}
