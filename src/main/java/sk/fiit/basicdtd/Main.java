package sk.fiit.basicdtd;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public final class Main {
    public static void main(final String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter input file name: ");
                final String input = scanner.nextLine().trim();

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
                    final LanguageAnalyzer analyzer = new BasicDtdAnalyzer();
                    final List<Token> tokens = analyzer.lexicalAnalysis(reader.lines());
                    final SyntacticAnalysisResult result = analyzer.syntacticAnalysis(tokens);
                    System.out.printf("File \"%s\" is %s.%n", path, result.isValid() ? "valid" : "invalid");
                } catch (final NoSuchFileException exception) {
                    System.err.printf("File \"%s\" does not exist.%n", exception.getFile());
                } catch (final IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
