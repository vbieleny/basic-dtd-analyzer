package sk.fiit.basicdtd;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public final class SyntacticAnalysis {
    private final Stack<Token> stack = new Stack<>();
    private final ParserTable table = new DtdParserTable();
    private final List<Token> tokens;
    private final Map<Integer, Rule> rules;
    private int position;
    private SyntacticAnalysisResult result;

    public SyntacticAnalysis(final List<Token> tokens, final Map<Integer, Rule> rules) {
        this.tokens = tokens;
        this.rules = rules;

        stack.push(Token.T_EOS);
        stack.push(Token.N_DTDDOCUMENT);
    }

    public void step() {
        if (result != null) {
            throw new IllegalStateException("Syntactic analysis already completed. Get result by calling getResult() method.");
        }

        if (position >= tokens.size()) {
            System.out.println("Semantic analysis finished");
            System.out.println("Stack: " + stack);
            result = new SyntacticAnalysisResult(stack.isEmpty());
            return;
        }

        final Token token = tokens.get(position);

        System.out.println("Position: " + position);
        System.out.println("Token: " + token);
        System.out.println("Stack: " + stack);

        if (token == stack.peek()) {
            System.out.println("Removing token from stack " + token);
            stack.pop();
            position++;
        } else {
            final int ruleNumber = table.get(stack.peek(), token);
            final Rule rule = rules.get(ruleNumber);
            if (rule == null) {
                System.out.println("Cannot find rule for this step!");
                result = new SyntacticAnalysisResult(false);
                return;
            }
            System.out.println("Using rule " + rule);
            rule.applyToStack(stack);
        }
    }

    public final void all() {
        while (isNotFinished()) {
            step();
        }
    }

    public final boolean isNotFinished() {
        return result == null;
    }

    public final SyntacticAnalysisResult getResult() {
        return result;
    }
}
