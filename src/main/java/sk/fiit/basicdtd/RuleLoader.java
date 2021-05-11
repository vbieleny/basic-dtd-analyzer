package sk.fiit.basicdtd;

import java.io.InputStream;
import java.util.*;

public final class RuleLoader {
    public final Map<Integer, Rule> loadRules(String path) {
        final Map<Integer, Rule> rules = new HashMap<>();

        final InputStream inputStream = getClass().getResourceAsStream(path);
        if (inputStream == null) {
            System.err.println("Cannot find file \"" + path + "\"!");
            return rules;
        }

        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }

                final Scanner lineScanner = new Scanner(line);
                final List<Token> tokens = new ArrayList<>();

                final int ruleNumber = lineScanner.nextInt();
                final String from = lineScanner.next();
                lineScanner.next();
                while (lineScanner.hasNext()) {
                    final String rulePart = lineScanner.next();
                    tokens.add(Token.valueOf(rulePart));
                }

                rules.put(ruleNumber, new Rule(Token.valueOf(from), tokens));
            }
            return rules;
        }
    }
}
