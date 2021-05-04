package sk.fiit.basicdtd;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public final class BasicDtdAnalyzer implements LanguageAnalyzer {
    @Override
    public List<Token> lexicalAnalysis(final Stream<String> lines) {
        return Collections.emptyList();
    }

    @Override
    public SyntacticAnalysisResult syntacticAnalysis(final List<Token> tokens) {
        return new SyntacticAnalysisResult(false);
    }
}
