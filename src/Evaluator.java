import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Evaluator {


    public static int calculate(String expr) {
        // Llista per emmagatzemar es valors finals
        LinkedList<Token> calc = new LinkedList();
        // Pila per emmagatzemar operadors
        LinkedList<Token> stack = new LinkedList();

        // ********************************************* //
        // Convertim l'string d'entrada en una llista de tokens
        Token[] tokens = Token.getTokens(expr);
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

                while (!stack.isEmpty() && definePriority(stack.peek().getTk()) >= definePriority(tokens[i].getTk()))calc.add(stack.pop());
                stack.push(tokens[i]);
                continue;

            }

            if (tokens[i].getTtype() == Token.Toktype.PAREN) {
                continue;
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

        return result;
    }

    public static int calcRPN(Token[] list) {
        LinkedList<Integer> stack = new LinkedList();

        for (Token t : list) {
            if (t.getTtype() == Token.Toktype.NUMBER) {
                stack.push(t.getValue());
            } else if (t.getTtype() == Token.Toktype.OP) {

                int n1 = stack.pop();
                int n2 = stack.pop();
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
