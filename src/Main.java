import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String proves = "((4/5)-6*(36+7))";
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
        }

        System.out.println(Arrays.toString(arrayToken));
    }

}
