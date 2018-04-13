import java.util.ArrayList;
import java.util.List;

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
        Token t = new Token();
        t.ttype = Toktype.NUMBER;
        t.value = value;
        return t;
    }

    // Torna un token de tipus "OP"
    static Token tokOp(char c) {
        Token t = new Token();
        t.ttype = Toktype.OP;
        t.tk = c;
        return t;
    }

    // Torna un token de tipus "PAREN"
    static Token tokParen(char c) {
        Token t = new Token();
        t.ttype = Toktype.PAREN;
        t.tk = c;
        return t;
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
    // Aquí rebem com a paràmetre un string amb anotació inversa; 1+311
    public static Token[] getTokens(String expr) {
        char[] operador = new char[]{'+','-','*','/'};

        List<String> list = new ArrayList();

        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == 'A') {

            }
        }

        list.add(expr);

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
