package sk.fiit.basicdtd;

public final class SyntacticAnalysisResult {
    private final boolean isValid;

    public SyntacticAnalysisResult(final boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public String toString() {
        return isValid ? "Valid" : "Invalid";
    }
}
