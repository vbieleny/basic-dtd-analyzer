package sk.fiit.basicdtd;

import java.util.List;
import java.util.stream.Stream;

public interface LanguageAnalyzer {
    List<Token> lexicalAnalysis(final Stream<String> lines);
    SyntacticAnalysisResult syntacticAnalysis(final List<Token> tokens);
}
