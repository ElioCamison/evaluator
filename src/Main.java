import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        /*String proves = "1+311";
        List<Token> listToken = new ArrayList();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < proves.length(); i++) {
            if (proves.charAt(i) == '+' || proves.charAt(i) == '-' || proves.charAt(i) == '*' || proves.charAt(i) == '/') {
                if(!sb.toString().equals("")){
                    listToken.add(Token.tokNumber(Integer.parseInt(sb.toString())));
                    sb.setLength(0);
                }
                listToken.add(Token.tokOp(proves.charAt(i)));
            } else if (proves.charAt(i) == '(' || proves.charAt(i) == ')') {
                if(!sb.toString().equals("")){
                    listToken.add(Token.tokNumber(Integer.parseInt(sb.toString())));
                    sb.setLength(0);
                }
                listToken.add(Token.tokParen(proves.charAt(i)));
            } else if (proves.charAt(i) != '(' || proves.charAt(i) != ')' ||
                    proves.charAt(i) != '+' || proves.charAt(i) != '-' || proves.charAt(i) != '*'
                    || proves.charAt(i) != '/' && i != proves.length() ) {
                sb.append(String.valueOf(proves.charAt(i)));
            }
        }
        if(!sb.toString().equals("")){
            listToken.add(Token.tokNumber(Integer.parseInt(sb.toString())));
        }

        Token[] arrayToken = new Token[listToken.size()];

        for (int i = 0; i < listToken.size(); i++) {
            arrayToken[i] = listToken.get(i);
        }*/

        Token[] tokens;

        tokens = new Token[]{Token.tokNumber(3), Token.tokNumber(5), Token.tokOp('+')};

        LinkedList<Token> linkedToken = new LinkedList();

        for (int i = 0; i < tokens.length; i++) {
            linkedToken.push(tokens[i]);
        }

        System.out.println(Arrays.toString(linkedToken.toArray()));
    }

}
