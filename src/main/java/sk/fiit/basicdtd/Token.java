package sk.fiit.basicdtd;

public enum Token {

    T_EOS,
    T_ELEMENT("<!ELEMENT "),
    T_ATTLIST("<!ATTLIST "),
    T_EMPTY(" EMPTY"),
    T_ANY(" ANY"),
    T_PCDATA(" (#PCDATA)"),
    T_CDATA(" CDATA"),
    T_NMTOKEN(" NMTOKEN"),
    T_IDREF(" IDREF"),
    T_REQUIRED(" #REQUIRED"),
    T_IMPLIED(" #IMPLIED"),
    T_FIXED(" #FIXED"),
    T_SL_DPARENS(" (("),
    T_SL_PARENS(" ("),
    T_S_QUOTE(" \""),
    T_R_ANGLE(1),
    T_QUESTION(1),
    T_STAR(1),
    T_PLUS(1),
    T_L_PARENS(1),
    T_R_PARENS(1),
    T_COMMA(1),
    T_PIPE(1),
    T_QUOTE(1),
    T_SPACE(1),
    T_UNDERSCORE(1),
    T_COLON(1),
    T_LETTER(1),
    T_DIGIT(1),
    T_DOT(1),
    T_DASH(1),
    T_INVALID,

    N_DTDDOCUMENT,
    N_DECLARATIONS,
    N_DECLARATION,
    N_ELEMDECL,
    N_ELEMVAL,
    N_QUANTIFIERS,
    N_ELEMCHILD,
    N_ELEMCMP,
    N_ELEMC,
    N_ELEMP,
    N_CP,
    N_ATTRDECL,
    N_ATTRVAL,
    N_ATTRVALREP,
    N_ATTRTYPE,
    N_WORDS,
    N_WORDSREP,
    N_DEFAULTDECL,
    N_FIXEDDECL,
    N_DEFWORDS,
    N_SDEFWORDS,
    N_WNAME,
    N_NAME,
    N_NAMEREP,
    N_NAMECHARS,
    N_NAMECHAR,
    N_WORD,
    N_CHAR;

    private final String prefix;
    private final int shift;

    Token(final String prefix) {
        this.prefix = prefix;
        this.shift = prefix.length();
    }

    Token(final int shift) {
        this.prefix = null;
        this.shift = shift;
    }

    Token() {
        this(0);
    }

    public String getPrefix() {
        return prefix;
    }

    public int getShift() {
        return shift;
    }
}
