package sk.fiit.basicdtd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public final class LexicalAnalyzer {
    public final List<Token> analyze(final List<String> lines) {
        final List<Token> tokens = new ArrayList<>();
        int lineNumber = 0;
        for (final String line : lines) {
            lineNumber++;
            for (int i = 0; i < line.length(); ) {
                final Token token = parseToken(line, i);
                if (token == Token.T_INVALID) {
                    System.err.printf("Found invalid input at line %d at index %d (%c)%n", lineNumber, i, line.charAt(i));
                    return Collections.emptyList();
                }
                tokens.add(token);
                i += token.getShift();
            }
        }
        tokens.add(Token.T_EOS);
        return tokens;
    }

    private Token parseToken(final String line, final int i) {
        if (line.startsWith(Token.T_ELEMENT.getPrefix(), i)) {
            return Token.T_ELEMENT;
        } else if (line.startsWith(Token.T_EMPTY.getPrefix(), i)) {
            return Token.T_EMPTY;
        } else if (line.startsWith(Token.T_ANY.getPrefix(), i)) {
            return Token.T_ANY;
        } else if (line.startsWith(Token.T_PCDATA.getPrefix(), i)) {
            return Token.T_PCDATA;
        } else if (line.startsWith(Token.T_SL_PARENS.getPrefix(), i)) {
            return Token.T_SL_PARENS;
        } else if (line.startsWith(Token.T_ATTLIST.getPrefix(), i)) {
            return Token.T_ATTLIST;
        } else if (line.startsWith(Token.T_CDATA.getPrefix(), i)) {
            return Token.T_CDATA;
        } else if (line.startsWith(Token.T_NMTOKEN.getPrefix(), i)) {
            return Token.T_NMTOKEN;
        } else if (line.startsWith(Token.T_IDREF.getPrefix(), i)) {
            return Token.T_IDREF;
        } else if (line.startsWith(Token.T_REQUIRED.getPrefix(), i)) {
            return Token.T_REQUIRED;
        } else if (line.startsWith(Token.T_IMPLIED.getPrefix(), i)) {
            return Token.T_IMPLIED;
        } else if (line.startsWith(Token.T_FIXED.getPrefix(), i)) {
            return Token.T_FIXED;
        } else if (line.startsWith(Token.T_S_QUOTE.getPrefix(), i)) {
            return Token.T_S_QUOTE;
        } else {
            char c = line.charAt(i);
            if (Character.isLetter(c)) {
                return Token.T_LETTER;
            } else if (Character.isDigit(c)) {
                return Token.T_DIGIT;
            }
            switch (c) {
                case '>':
                    return Token.T_R_ANGLE;
                case '?':
                    return Token.T_QUESTION;
                case '*':
                    return Token.T_STAR;
                case '+':
                    return Token.T_PLUS;
                case '(':
                    return Token.T_L_PARENS;
                case ')':
                    return Token.T_R_PARENS;
                case ',':
                    return Token.T_COMMA;
                case '|':
                    return Token.T_PIPE;
                case '"':
                    return Token.T_QUOTE;
                case ' ':
                    return Token.T_SPACE;
                case '_':
                    return Token.T_UNDERSCORE;
                case ':':
                    return Token.T_COLON;
                case '.':
                    return Token.T_DOT;
                case '-':
                    return Token.T_DASH;
                case '\0':
                    return Token.T_EOS;
                default:
                    return Token.T_INVALID;
            }
        }
    }
}
