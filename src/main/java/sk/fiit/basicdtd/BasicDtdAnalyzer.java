package sk.fiit.basicdtd;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public final class BasicDtdAnalyzer implements LanguageAnalyzer {

    private boolean contains(final String input, final int from, final String content) {
        if (from + content.length() > input.length()) {
            return false;
        }
        return input.startsWith(content, from);
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
        } else if (line.startsWith(Token.T_SL_DPARENS.getPrefix(), i)) {
            return Token.T_SL_DPARENS;
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
                case '>': return Token.T_R_ANGLE;
                case '?': return Token.T_QUESTION;
                case '*': return Token.T_STAR;
                case '+': return Token.T_PLUS;
                case '(': return Token.T_L_PARENS;
                case ')': return Token.T_R_PARENS;
                case ',': return Token.T_COMMA;
                case '|': return Token.T_PIPE;
                case '"': return Token.T_QUOTE;
                case ' ': return Token.T_SPACE;
                case '_': return Token.T_UNDERSCORE;
                case ':': return Token.T_COLON;
                case '.': return Token.T_DOT;
                case '-': return Token.T_DASH;
                case '\0': return Token.T_EOS;
                default: return Token.T_INVALID;
            }
        }
    }

    @Override
    public List<Token> lexicalAnalysis(final Stream<String> lines) {
        final List<Token> tokens = new ArrayList<>();
        lines.forEach(line -> {
            for (int i = 0; i < line.length();) {
                final Token token = parseToken(line, i);
                System.out.println(token);
                tokens.add(token);
                i += token.getShift();
            }
            System.out.println();
        });
        return tokens;
    }

    @Override
    public SyntacticAnalysisResult syntacticAnalysis(final List<Token> tokens) {
        final Stack<Token> stack = new Stack<>();
        final ParserTable table = new ParserTable();

        stack.push(Token.T_EOS);
        stack.push(Token.N_DTDDOCUMENT);

        table.set(Token.N_DTDDOCUMENT, Token.T_ELEMENT, 1);
        table.set(Token.N_DTDDOCUMENT, Token.T_ATTLIST, 1);

        table.set(Token.N_DECLARATIONS, Token.T_ELEMENT, 2);
        table.set(Token.N_DECLARATIONS, Token.T_ATTLIST, 2);
        table.set(Token.N_DECLARATIONS, Token.T_EOS, 3);

        table.set(Token.N_DECLARATION, Token.T_ATTLIST, 4);
        table.set(Token.N_DECLARATION, Token.T_ELEMENT, 5);

        table.set(Token.N_ELEMDECL, Token.T_ELEMENT, 6);

        table.set(Token.N_ELEMVAL, Token.T_SL_DPARENS, 10);
        table.set(Token.N_ELEMVAL, Token.T_PCDATA, 9);
        table.set(Token.N_ELEMVAL, Token.T_ANY, 8);
        table.set(Token.N_ELEMVAL, Token.T_EMPTY, 7);

        table.set(Token.N_QUANTIFIERS, Token.T_PIPE, 14);
        table.set(Token.N_QUANTIFIERS, Token.T_R_PARENS, 14);
        table.set(Token.N_QUANTIFIERS, Token.T_R_ANGLE, 14);
        table.set(Token.N_QUANTIFIERS, Token.T_COMMA, 14);
        table.set(Token.N_QUANTIFIERS, Token.T_PLUS, 13);
        table.set(Token.N_QUANTIFIERS, Token.T_STAR, 12);
        table.set(Token.N_QUANTIFIERS, Token.T_QUESTION, 11);

        table.set(Token.N_ELEMCHILD, Token.T_SL_DPARENS, 15);

        table.set(Token.N_ELEMCMP, Token.T_PIPE, 17);
        table.set(Token.N_ELEMCMP, Token.T_R_PARENS, 18);
        table.set(Token.N_ELEMCMP, Token.T_COMMA, 16);

        table.set(Token.N_ELEMC, Token.T_R_PARENS, 20);
        table.set(Token.N_ELEMC, Token.T_COMMA, 19);
        table.set(Token.N_ELEMC, Token.T_COMMA, 19);

        table.set(Token.N_ELEMP, Token.T_PIPE, 21);
        table.set(Token.N_ELEMP, Token.T_R_PARENS, 22);

        table.set(Token.N_CP, Token.T_UNDERSCORE, 23);
        table.set(Token.N_CP, Token.T_LETTER, 23);
        table.set(Token.N_CP, Token.T_COLON, 23);

        table.set(Token.N_ATTRDECL, Token.T_ATTLIST, 24);

        table.set(Token.N_ATTRVAL, Token.T_SPACE, 25);

        table.set(Token.N_ATTRVALREP, Token.T_SPACE, 26);
        table.set(Token.N_ATTRVALREP, Token.T_R_ANGLE, 27);

        table.set(Token.N_ATTRTYPE, Token.T_SL_PARENS, 31);
        table.set(Token.N_ATTRTYPE, Token.T_IDREF, 30);
        table.set(Token.N_ATTRTYPE, Token.T_NMTOKEN, 29);
        table.set(Token.N_ATTRTYPE, Token.T_CDATA, 28);

        table.set(Token.N_WORDS, Token.T_DASH, 32);
        table.set(Token.N_WORDS, Token.T_UNDERSCORE, 32);
        table.set(Token.N_WORDS, Token.T_LETTER, 32);
        table.set(Token.N_WORDS, Token.T_DIGIT, 32);
        table.set(Token.N_WORDS, Token.T_PIPE, 32);
        table.set(Token.N_WORDS, Token.T_R_PARENS, 32);

        table.set(Token.N_WORDSREP, Token.T_PIPE, 33);
        table.set(Token.N_WORDSREP, Token.T_R_PARENS, 34);

        table.set(Token.N_DEFAULTDECL, Token.T_FIXED, 37);
        table.set(Token.N_DEFAULTDECL, Token.T_S_QUOTE, 37);
        table.set(Token.N_DEFAULTDECL, Token.T_IMPLIED, 36);
        table.set(Token.N_DEFAULTDECL, Token.T_REQUIRED, 35);

        table.set(Token.N_FIXEDDECL, Token.T_FIXED, 38);
        table.set(Token.N_FIXEDDECL, Token.T_S_QUOTE, 39);

        table.set(Token.N_DEFWORDS, Token.T_DASH, 40);
        table.set(Token.N_DEFWORDS, Token.T_UNDERSCORE, 40);
        table.set(Token.N_DEFWORDS, Token.T_LETTER, 40);
        table.set(Token.N_DEFWORDS, Token.T_DIGIT, 40);
        table.set(Token.N_DEFWORDS, Token.T_PIPE, 40);
        table.set(Token.N_DEFWORDS, Token.T_R_PARENS, 40);
        table.set(Token.N_DEFWORDS, Token.T_SPACE, 40);
        table.set(Token.N_DEFWORDS, Token.T_QUOTE, 40);

        table.set(Token.N_SDEFWORDS, Token.T_SPACE, 41);
        table.set(Token.N_SDEFWORDS, Token.T_QUOTE, 42);

        table.set(Token.N_WNAME, Token.T_SPACE, 43);

        table.set(Token.N_NAME, Token.T_UNDERSCORE, 44);
        table.set(Token.N_NAME, Token.T_LETTER, 44);
        table.set(Token.N_NAME, Token.T_COLON, 44);

        table.set(Token.N_NAMEREP, Token.T_UNDERSCORE, 46);
        table.set(Token.N_NAMEREP, Token.T_LETTER, 45);
        table.set(Token.N_NAMEREP, Token.T_COLON, 47);

        table.set(Token.N_NAMECHARS, Token.T_DASH, 48);
        table.set(Token.N_NAMECHARS, Token.T_UNDERSCORE, 48);
        table.set(Token.N_NAMECHARS, Token.T_DIGIT, 48);
        table.set(Token.N_NAMECHARS, Token.T_LETTER, 48);
        table.set(Token.N_NAMECHARS, Token.T_COLON, 48);
        table.set(Token.N_NAMECHARS, Token.T_DOT, 48);
        table.set(Token.N_NAMECHARS, Token.T_SPACE, 49);
        table.set(Token.N_NAMECHARS, Token.T_PIPE, 49);
        table.set(Token.N_NAMECHARS, Token.T_R_PARENS, 49);
        table.set(Token.N_NAMECHARS, Token.T_SL_PARENS, 49);
        table.set(Token.N_NAMECHARS, Token.T_IDREF, 49);
        table.set(Token.N_NAMECHARS, Token.T_NMTOKEN, 49);
        table.set(Token.N_NAMECHARS, Token.T_CDATA, 49);
        table.set(Token.N_NAMECHARS, Token.T_COMMA, 49);
        table.set(Token.N_NAMECHARS, Token.T_SL_DPARENS, 49);
        table.set(Token.N_NAMECHARS, Token.T_PLUS, 49);
        table.set(Token.N_NAMECHARS, Token.T_STAR, 49);
        table.set(Token.N_NAMECHARS, Token.T_QUESTION, 49);
        table.set(Token.N_NAMECHARS, Token.T_PCDATA, 49);
        table.set(Token.N_NAMECHARS, Token.T_ANY, 49);
        table.set(Token.N_NAMECHARS, Token.T_EMPTY, 49);

        table.set(Token.N_NAMECHAR, Token.T_DASH, 53);
        table.set(Token.N_NAMECHAR, Token.T_UNDERSCORE, 54);
        table.set(Token.N_NAMECHAR, Token.T_DIGIT, 51);
        table.set(Token.N_NAMECHAR, Token.T_LETTER, 50);
        table.set(Token.N_NAMECHAR, Token.T_COLON, 55);
        table.set(Token.N_NAMECHAR, Token.T_DOT, 52);

        table.set(Token.N_WORD, Token.T_DASH, 60);
        table.set(Token.N_WORD, Token.T_UNDERSCORE, 60);
        table.set(Token.N_WORD, Token.T_DIGIT, 60);
        table.set(Token.N_WORD, Token.T_LETTER, 60);
        table.set(Token.N_WORD, Token.T_SPACE, 61);
        table.set(Token.N_WORD, Token.T_QUOTE, 61);
        table.set(Token.N_WORD, Token.T_PIPE, 61);
        table.set(Token.N_WORD, Token.T_R_PARENS, 61);

        table.set(Token.N_CHAR, Token.T_DASH, 65);
        table.set(Token.N_CHAR, Token.T_UNDERSCORE, 64);
        table.set(Token.N_CHAR, Token.T_DIGIT, 63);
        table.set(Token.N_CHAR, Token.T_LETTER, 62);

        for (int i = 0; i < tokens.size();) {
            final Token token = tokens.get(i);

            System.out.println("Token: (" + i + ") " + token);
            System.out.println("Stack: " + stack);

            if (token == stack.peek()) {
                System.out.println("Removing token from stack " + token);
                stack.pop();
                i++;
            } else {
                int rule = table.get(stack.peek(), token);
                System.out.println("Using rule " + rule);
                switch (rule) {
                    case 1:
                        stack.pop();
                        stack.push(Token.N_DECLARATIONS);
                        stack.push(Token.N_DECLARATION);
                        break;
                    case 2:
                        stack.pop();
                        stack.push(Token.N_DECLARATIONS);
                        stack.push(Token.N_DECLARATION);
                        break;
                    case 3:
                        stack.pop();
                        break;
                    case 4:
                        stack.pop();
                        stack.push(Token.N_ATTRDECL);
                        break;
                    case 5:
                        stack.pop();
                        stack.push(Token.N_ELEMDECL);
                        break;
                    default:
                        System.out.println("Cannot parse");
                        return new SyntacticAnalysisResult(false);
                }
            }
            System.out.println();
        }

        System.out.println("Finished parsing");
        return new SyntacticAnalysisResult(true);
    }
}
