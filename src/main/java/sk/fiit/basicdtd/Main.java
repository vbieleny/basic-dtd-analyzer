package sk.fiit.basicdtd;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class Main {
    public static void main(final String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter input file name: ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("quit")) {
                    break;
                }

                final Path path = Paths.get(input);

                if (Files.isDirectory(path)) {
                    System.err.printf("\"%s\" is a directory.%n", path);
                    continue;
                }

                if (!Files.exists(path)) {
                    System.err.printf("File \"%s\" does not exist.%n", path);
                    continue;
                }

                if (!Files.isReadable(path)) {
                    System.err.printf("File \"%s\" is not readable.%n", path);
                    continue;
                }

                try (final BufferedReader reader = Files.newBufferedReader(path)) {
                    final RuleLoader ruleLoader = new RuleLoader();
                    final Map<Integer, Rule> rules = ruleLoader.loadRules("/rules.txt");

                    final LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
                    List<Token> tokens = lexicalAnalyzer.analyze(reader.lines().collect(Collectors.toList()));
                    if (tokens.isEmpty()) {
                        System.out.println("No valid tokens found!");
                        continue;
                    }
                    System.out.println("Read tokens: " + tokens + "\n");
                    final SyntacticAnalysis semanticAnalysis = new SyntacticAnalysis(tokens, rules);

                    while (semanticAnalysis.isNotFinished()) {
                        System.out.print("Enter number of steps to go (or \"end\" to go all steps): ");
                        input = scanner.nextLine().trim();

                        if (input.equalsIgnoreCase("end")) {
                            semanticAnalysis.all();
                        } else {
                            try {
                                int numberOfSteps = Integer.parseInt(input);
                                while (semanticAnalysis.isNotFinished() && numberOfSteps > 0) {
                                    semanticAnalysis.step();
                                    numberOfSteps--;
                                }
                            } catch (NumberFormatException exception) {
                                System.err.println("Please enter only number or string \"end\"!");
                            }
                        }
                    }

                    SyntacticAnalysisResult result = semanticAnalysis.getResult();
                    System.out.println();
                    System.out.printf("File \"%s\" is %s.%n%n", path, result.isValid() ? "valid" : "invalid");
                } catch (final NoSuchFileException exception) {
                    System.err.printf("File \"%s\" does not exist.%n", exception.getFile());
                } catch (final IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
