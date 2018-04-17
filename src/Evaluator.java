import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Evaluator {


    public static int calculate(String expr) {

        List<String> calc = new ArrayList();
        // Convertim l'string d'entrada en una llista de tokens
        Token[] tokens = Token.getTokens(expr);

        // Efectua el procediment per convertir la llista de tokens en notació RPN
        // Finalment, crida a calcRPN amb la nova llista de tokens i torna el resultat
        return 0;
    }

    public static int calcRPN(Token[] list) {
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

        // Calcula el valor resultant d'avaluar la llista de tokens
        return stack.pop();
        
    }


}
