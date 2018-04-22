import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

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
        String expr = "46-61";
        // Llista per emmagatzemar es valors finals
        LinkedList<Token> calc = new LinkedList();
        // Pila per emmagatzemar operadors
        LinkedList<Token> stack = new LinkedList();

        // ********************************************* //
        // Convertim l'string d'entrada en una llista de tokens
        Token[] tokens = Token.getTokens(expr);

        System.out.println(Arrays.toString(tokens));
        // ********************************************* //
        // Efectua el procediment per convertir la llista de tokens en notació RPN

        // Recorrem l'array de tokens
        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i].getTtype() == Token.Toktype.NUMBER) {
                calc.push(tokens[i]);
            }

            if (tokens[i].getTtype() == Token.Toktype.OP) {

                if (stack.isEmpty()) {
                    stack.push(tokens[i]);
                    continue;
                }

                if (definePriority(stack.peek().getTk()) < definePriority(tokens[i].getTk())) {
                    stack.push(tokens[i]);
                    continue;
                }

                while (!stack.isEmpty() && definePriority(stack.peek().getTk()) >= definePriority(tokens[i].getTk())) {
                    calc.add(stack.pop());
                    stack.push(tokens[i]);
                    break;
                }
            }

            if (tokens[i].getTtype() == Token.Toktype.PAREN) {
                if (tokens[i].getTk() == '(') {
                    stack.push(tokens[i]);
                } else {
                    while (stack.peek().getTk() != '(') {
                        calc.add(stack.pop());
                    }

                    stack.pop();
                }
            }

        }

        while (!stack.isEmpty()) {
            calc.add(stack.pop());
        }


        Token[] arrayTokens = new Token[calc.size()];

        for (int i = 0; i < calc.size(); i++) {
            arrayTokens[i] = calc.get(i);
        }

        System.out.println(Arrays.toString(arrayTokens));

        // Finalment, crida a calcRPN amb la nova llista de tokens i torna el resultat
        int result = calcRPN(arrayTokens);

        System.out.println(result);
    }


    public static int calcRPN(Token[] list) {
        LinkedList<Integer> stack = new LinkedList();

        for (Token t : list) {
            if (t.getTtype() == Token.Toktype.NUMBER) {
                stack.push(t.getValue());
            } else if (t.getTtype() == Token.Toktype.OP) {

                int n2 = stack.pop();
                int n1 = stack.pollFirst();
                char tk = t.getTk();

                switch (tk) {
                    case '+':
                        stack.push(n1 + n2);
                        break;
                    case '-':
                        stack.push(n1 - n2);
                        break;
                    case '*':
                        stack.push(n1 * n2);
                        break;
                    case '/':
                        if (n1 == 0 || n2 == 0)
                            stack.push(0);
                        else
                            stack.push(n1 / n2);
                        break;

                }
            }
        }
        // Calcula el valor resultant d'avaluar la llista de tokens
        return stack.pop();
    }


    private static int definePriority(char operador) {
        switch (operador) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
        }
        return 0;
    }
}
