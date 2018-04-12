public class Token {

    enum Toktype {
        NUMBER, OP, PAREN
    }

    private Toktype ttype;
    private int value;
    private char tk;

    // Constructor privat. Evita que es puguin construir objectes Token externament
    // S'han de crear el objectes desde la mateixa classe
    // S'han de crear el mètodes per comparar-ho
    private Token() {
    }

    // Torna un token de tipus "NUMBER"
    static Token tokNumber(int value) {
        Token tkNumber = new Token();
        return null;
    }

    // Torna un token de tipus "OP"
    static Token tokOp(char c) {
        Token tkOperador = new Token();
        return null;
    }

    // Torna un token de tipus "PAREN"
    static Token tokParen(char c) {
        Token tkParen = new Token();
        return null;
    }

    // Mostra un token (conversió a String)
    public String toString() {
        return "";
    }

    // Mètode equals. Comprova si dos objectes Token són iguals
    public boolean equals(Object o) {
        return false;
    }

    // A partir d'un String, torna una llista de tokens
    public static Token[] getTokens(String expr) {
        return null;
    }


    // ***************************** //
    //            GETTERS            //
    // ***************************** //

    public Toktype getTtype() {
        return ttype;
    }

    public int getValue() {
        return value;
    }

    public char getTk() {
        return tk;
    }
}
