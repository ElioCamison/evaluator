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
            // Feim un casting de l'objecte que rebem per paràmetre
            Token t = (Token) o;
            // Comprovam que siguin del mateix tipus
            if (this.ttype==Toktype.NUMBER && this.ttype == t.ttype){
                //Comprovam que tenguin el mateix valor, i retornam verdader.
                if (this.value == t.value) return true;
            }
            if (this.ttype==Toktype.OP && this.ttype == t.ttype){
                if (this.tk == t.tk) return true;
            }
            if (this.ttype==Toktype.PAREN && this.ttype == t.ttype){
                if (this.tk == t.tk) return true;
            }

        }
        // En cas contrari, tornam fals.
        return false;
    }

    // A partir d'un String, torna una llista de tokens
    // Aquí rebem com a paràmetre un string amb anotació inversa; 1+311
    public static Token[] getTokens(String expr) {
        List<Token> listToken = new ArrayList();
        StringBuilder valuesToken = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            // Comprovam que no hagui espais
            if(expr.charAt(i) == ' '){
                if(!valuesToken.toString().equals("")){
                    listToken.add(Token.tokNumber(Integer.parseInt(valuesToken.toString())));
                    // Esborram tot lo que contengui la variable sb
                    valuesToken.setLength(0);
                }
                continue;
            }
            // Comprovam si el caracter que esteim iterant és un operador
            if (expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*' || expr.charAt(i) == '/') {
                // Comprovam que no hagui espais
                if(!valuesToken.toString().equals("")){
                    listToken.add(Token.tokNumber(Integer.parseInt(valuesToken.toString())));
                    valuesToken.setLength(0);
                }
                // Afegim dins es contendidor el objecte Token de tipus operador
                listToken.add(Token.tokOp(expr.charAt(i)));
            } else if (expr.charAt(i) == '(' || expr.charAt(i) == ')') {
                if(!valuesToken.toString().equals("")){
                    listToken.add(Token.tokNumber(Integer.parseInt(valuesToken.toString())));
                    valuesToken.setLength(0);
                }
                // Afegim dins es contendidor el objecte Token de tipus parèntesis
                listToken.add(Token.tokParen(expr.charAt(i)));
            } else if (expr.charAt(i) != '(' || expr.charAt(i) != ')' ||
                    expr.charAt(i) != '+' || expr.charAt(i) != '-' || expr.charAt(i) != '*'
                    || expr.charAt(i) != '/' && i != expr.length() ) {
                valuesToken.append(String.valueOf(expr.charAt(i)));
            }
        }
        if(!valuesToken.toString().equals("")){
            listToken.add(Token.tokNumber(Integer.parseInt(valuesToken.toString())));
        }

        // Cream un array de tokens, amb la mida del contenidor que hem emprat
        Token[] arrayToken = new Token[listToken.size()];

        // Omplim l'array de tokens
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
