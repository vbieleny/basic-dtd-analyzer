package sk.fiit.basicdtd;

public final class DtdParserTable extends ParserTable {
    public DtdParserTable() {
        set(Token.N_DTDDOCUMENT, Token.T_ELEMENT, 100);
        set(Token.N_DTDDOCUMENT, Token.T_ATTLIST, 100);

        set(Token.N_DECLARATIONS, Token.T_ELEMENT, 101);
        set(Token.N_DECLARATIONS, Token.T_ATTLIST, 101);
        set(Token.N_DECLARATIONS, Token.T_EOS, 102);

        set(Token.N_DECLARATION, Token.T_ELEMENT, 104);
        set(Token.N_DECLARATION, Token.T_ATTLIST, 103);

        set(Token.N_ELEMDECL, Token.T_ELEMENT, 200);

        set(Token.N_ELEMVAL, Token.T_SL_PARENS, 204);
        set(Token.N_ELEMVAL, Token.T_PCDATA, 203);
        set(Token.N_ELEMVAL, Token.T_ANY, 202);
        set(Token.N_ELEMVAL, Token.T_EMPTY, 201);

        set(Token.N_QUANTIFIERS, Token.T_PIPE, 208);
        set(Token.N_QUANTIFIERS, Token.T_R_PARENS, 208);
        set(Token.N_QUANTIFIERS, Token.T_COMMA, 208);
        set(Token.N_QUANTIFIERS, Token.T_PLUS, 207);
        set(Token.N_QUANTIFIERS, Token.T_STAR, 206);
        set(Token.N_QUANTIFIERS, Token.T_QUESTION, 205);

        set(Token.N_ELEMCHILD, Token.T_SL_PARENS, 300);

        set(Token.N_ELEMSUB, Token.T_L_PARENS, 301);

        set(Token.N_ELEMNEXT, Token.T_COMMA, 302);
        set(Token.N_ELEMNEXT, Token.T_PIPE, 303);
        set(Token.N_ELEMNEXT, Token.T_R_PARENS, 304);

        set(Token.N_ELEMC, Token.T_R_PARENS, 306);
        set(Token.N_ELEMC, Token.T_COMMA, 305);

        set(Token.N_ELEMP, Token.T_PIPE, 307);
        set(Token.N_ELEMP, Token.T_R_PARENS, 308);

        set(Token.N_CP, Token.T_UNDERSCORE, 309);
        set(Token.N_CP, Token.T_LETTER, 309);
        set(Token.N_CP, Token.T_COLON, 309);
        set(Token.N_CP, Token.T_L_PARENS, 310);

        set(Token.N_ATTRDECL, Token.T_ATTLIST, 400);

        set(Token.N_ATTRVAL, Token.T_SPACE, 401);
        set(Token.N_ATTRVAL, Token.T_R_ANGLE, 402);

        set(Token.N_ATTRTYPE, Token.T_SL_PARENS, 406);
        set(Token.N_ATTRTYPE, Token.T_IDREF, 405);
        set(Token.N_ATTRTYPE, Token.T_NMTOKEN, 404);
        set(Token.N_ATTRTYPE, Token.T_CDATA, 403);

        set(Token.N_WORDS, Token.T_DASH, 407);
        set(Token.N_WORDS, Token.T_UNDERSCORE, 407);
        set(Token.N_WORDS, Token.T_DIGIT, 407);
        set(Token.N_WORDS, Token.T_LETTER, 407);
        set(Token.N_WORDS, Token.T_PIPE, 407);
        set(Token.N_WORDS, Token.T_R_PARENS, 407);

        set(Token.N_WORDSREP, Token.T_PIPE, 408);
        set(Token.N_WORDSREP, Token.T_R_PARENS, 409);

        set(Token.N_DEFAULTDECL, Token.T_FIXED, 412);
        set(Token.N_DEFAULTDECL, Token.T_S_QUOTE, 412);
        set(Token.N_DEFAULTDECL, Token.T_IMPLIED, 411);
        set(Token.N_DEFAULTDECL, Token.T_REQUIRED, 410);

        set(Token.N_FIXEDDECL, Token.T_FIXED, 413);
        set(Token.N_FIXEDDECL, Token.T_S_QUOTE, 414);

        set(Token.N_DEFWORDS, Token.T_DASH, 415);
        set(Token.N_DEFWORDS, Token.T_UNDERSCORE, 415);
        set(Token.N_DEFWORDS, Token.T_DIGIT, 415);
        set(Token.N_DEFWORDS, Token.T_LETTER, 415);
        set(Token.N_DEFWORDS, Token.T_SPACE, 415);
        set(Token.N_DEFWORDS, Token.T_QUOTE, 415);

        set(Token.N_SDEFWORDS, Token.T_SPACE, 416);
        set(Token.N_SDEFWORDS, Token.T_QUOTE, 417);

        set(Token.N_WNAME, Token.T_SPACE, 500);

        set(Token.N_NAME, Token.T_UNDERSCORE, 501);
        set(Token.N_NAME, Token.T_LETTER, 501);
        set(Token.N_NAME, Token.T_COLON, 501);

        set(Token.N_NAMEREP, Token.T_UNDERSCORE, 503);
        set(Token.N_NAMEREP, Token.T_LETTER, 502);
        set(Token.N_NAMEREP, Token.T_COLON, 504);

        set(Token.N_NAMECHARS, Token.T_DASH, 505);
        set(Token.N_NAMECHARS, Token.T_UNDERSCORE, 505);
        set(Token.N_NAMECHARS, Token.T_DIGIT, 505);
        set(Token.N_NAMECHARS, Token.T_LETTER, 505);
        set(Token.N_NAMECHARS, Token.T_COLON, 505);
        set(Token.N_NAMECHARS, Token.T_DOT, 505);
        set(Token.N_NAMECHARS, Token.T_SPACE, 506);
        set(Token.N_NAMECHARS, Token.T_PIPE, 506);
        set(Token.N_NAMECHARS, Token.T_R_PARENS, 506);
        set(Token.N_NAMECHARS, Token.T_SL_PARENS, 506);
        set(Token.N_NAMECHARS, Token.T_IDREF, 506);
        set(Token.N_NAMECHARS, Token.T_NMTOKEN, 506);
        set(Token.N_NAMECHARS, Token.T_CDATA, 506);
        set(Token.N_NAMECHARS, Token.T_R_ANGLE, 506);
        set(Token.N_NAMECHARS, Token.T_COMMA, 506);
        set(Token.N_NAMECHARS, Token.T_PLUS, 506);
        set(Token.N_NAMECHARS, Token.T_STAR, 506);
        set(Token.N_NAMECHARS, Token.T_QUESTION, 506);
        set(Token.N_NAMECHARS, Token.T_PCDATA, 506);
        set(Token.N_NAMECHARS, Token.T_ANY, 506);
        set(Token.N_NAMECHARS, Token.T_EMPTY, 506);

        set(Token.N_NAMECHAR, Token.T_DASH, 510);
        set(Token.N_NAMECHAR, Token.T_UNDERSCORE, 511);
        set(Token.N_NAMECHAR, Token.T_DIGIT, 508);
        set(Token.N_NAMECHAR, Token.T_LETTER, 507);
        set(Token.N_NAMECHAR, Token.T_COLON, 512);
        set(Token.N_NAMECHAR, Token.T_DOT, 509);

        set(Token.N_WORD, Token.T_DASH, 600);
        set(Token.N_WORD, Token.T_UNDERSCORE, 600);
        set(Token.N_WORD, Token.T_DIGIT, 600);
        set(Token.N_WORD, Token.T_LETTER, 600);
        set(Token.N_WORD, Token.T_SPACE, 601);
        set(Token.N_WORD, Token.T_QUOTE, 601);
        set(Token.N_WORD, Token.T_PIPE, 601);
        set(Token.N_WORD, Token.T_R_PARENS, 601);

        set(Token.N_CHAR, Token.T_DASH, 605);
        set(Token.N_CHAR, Token.T_UNDERSCORE, 604);
        set(Token.N_CHAR, Token.T_DIGIT, 603);
        set(Token.N_CHAR, Token.T_LETTER, 602);
    }
}
