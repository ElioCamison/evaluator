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
            Token t = (Token) o;
            if (this.ttype==Toktype.NUMBER && this.ttype == t.ttype){
                if (this.value == t.value) return true;
            }
            if (this.ttype==Toktype.OP && this.ttype == t.ttype){
                if (this.tk == t.tk) return true;
            }
            if (this.ttype==Toktype.PAREN && this.ttype == t.ttype){
                if (this.tk == t.tk) return true;
            }

        }
        return false;
    }

    // A partir d'un String, torna una llista de tokens
    // Aquí rebem com a paràmetre un string amb anotació inversa; 1+311
    public static Token[] getTokens(String expr) {
        List<Token> listToken = new ArrayList();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*' || expr.charAt(i) == '/') {
                if(!sb.toString().equals("")){
                    listToken.add(Token.tokNumber(Integer.parseInt(sb.toString())));
                    sb.setLength(0);
                }
                listToken.add(Token.tokOp(expr.charAt(i)));
            } else if (expr.charAt(i) == '(' || expr.charAt(i) == ')') {
                if(!sb.toString().equals("")){
                    listToken.add(Token.tokNumber(Integer.parseInt(sb.toString())));
                    sb.setLength(0);
                }
                listToken.add(Token.tokParen(expr.charAt(i)));
            } else if (expr.charAt(i) != '(' || expr.charAt(i) != ')' ||
                    expr.charAt(i) != '+' || expr.charAt(i) != '-' || expr.charAt(i) != '*'
                    || expr.charAt(i) != '/' && i != expr.length() ) {
                sb.append(String.valueOf(expr.charAt(i)));
            }
        }
        if(!sb.toString().equals("")){
            listToken.add(Token.tokNumber(Integer.parseInt(sb.toString())));
        }

        Token[] arrayToken = new Token[listToken.size()];

        for (int i = 0; i < listToken.size(); i++) {
            arrayToken[i] = listToken.get(i);
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
