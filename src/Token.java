import java.util.ArrayList;
import java.util.Arrays;
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

    // Mètode equals. Comprova si dos objectes Token són iguals
    public boolean equals(Object o) {
        if(o instanceof Token){
            if (this.ttype==((Token) o).ttype){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // A partir d'un String, torna una llista de tokens
    // Aquí rebem com a paràmetre un string amb anotació inversa; 1+311
    public static Token[] getTokens(String expr) {
        Token[] arrayToken = new Token[expr.length()];

        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*' || expr.charAt(i) == '/') {
                arrayToken[i] = Token.tokOp(expr.charAt(i));
            } else if (expr.charAt(i) == '(' || expr.charAt(i) == ')') {
                arrayToken[i] = Token.tokParen(expr.charAt(i));
            } else {
                arrayToken[i] = Token.tokNumber(Integer.parseInt(String.valueOf(expr.charAt(i))));
                //El correcte seria verificar que els caràcters que rebem siguin només números
                // Revisar que no retorni un error, ja que es un char i no un int
            }
        }
        return arrayToken;
    }

    @Override
    public String toString() {
        return "Token{" + "ttype=" + this.ttype + ", value=" + this.value + ", tk=" + this.tk + '}';
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
