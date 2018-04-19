import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        /*String proves = "1 2 20 5 - * +";
        List<Token> listToken = new ArrayList();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < proves.length(); i++) {
            if(proves.charAt(i) == ' '){
                if(!sb.toString().equals("")){
                    listToken.add(Token.tokNumber(Integer.parseInt(sb.toString())));
                    sb.setLength(0);
                }
                continue;
            }
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
        System.out.println(Arrays.toString(arrayToken));*/

        /*Token[] list;

        list = Token.getTokens("1 2 20 5 - * +");;

        LinkedList<Integer> stack = new LinkedList();

        for(Token t : list) {
            if (t.getTtype() == Token.Toktype.NUMBER) {
                stack.push(t.getValue());
            } else if (t.getTtype() == Token.Toktype.OP){
                int n1 = stack.pop();
                int n2 = stack.pop();
                char tk = t.getTk();
                if (tk == '+') {
                    stack.push(n1 + n2);
                } else if (tk == '-') {
                    if (n1 < n2){
                        stack.push(n2 - n1);
                    } else {
                        stack.push(n1 - n2);
                    }
                } else if (tk == '*') {
                    stack.push(n1 * n2);
                } else if (tk == '/') {
                    stack.push(n1 / n2);
                }
            }
        }

        System.out.println(stack.pop());*/

        /* ****************** */

        // últim mètode.
        String expr = "100/2/6";
        // Llista per emmagatzemar es valors finals
        List<Token> calc = new ArrayList();
        // Pila per emmagatzemar operadors
        LinkedList<Token> stack = new LinkedList();

        // ********************************************* //
        // Convertim l'string d'entrada en una llista de tokens
        Token[] tokens = Token.getTokens(expr);
        // ********************************************* //
        // Efectua el procediment per convertir la llista de tokens en notació RPN

        // Recorrem l'array de tokens
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].getTtype() == Token.Toktype.OP) {
                // TODO: Treure si fa falta els operadors amb manco o igual prioritat
                if(stack.isEmpty()){
                    stack.push(tokens[i]);
                } else {
                    calc.add(tokens[i]);
                }
            } else {
                calc.add(tokens[i]);
            }
        }

        while (!stack.isEmpty()) {
            calc.add(stack.pop());
        }

        System.out.println(calc);
        Token[] arrayTokens = new Token[calc.size()];

        for (int i = 0; i < calc.size(); i++) {
            arrayTokens[i] = calc.get(i);
        }

        // Finalment, crida a calcRPN amb la nova llista de tokens i torna el resultat
        int result = Evaluator.calcRPN(arrayTokens);

        System.out.println(result);

    }

}
