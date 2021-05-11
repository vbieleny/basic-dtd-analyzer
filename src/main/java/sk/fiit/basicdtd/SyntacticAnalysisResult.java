package sk.fiit.basicdtd;

public final class SyntacticAnalysisResult {
    private final boolean isValid;

    public SyntacticAnalysisResult(final boolean isValid) {
        this.isValid = isValid;
    }

    public final boolean isValid() {
        return isValid;
    }

    @Override
    public final String toString() {
        return isValid ? "Valid" : "Invalid";
    }
}
