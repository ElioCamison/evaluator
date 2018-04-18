import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Evaluator {


    public static int calculate(String expr) {
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
            if(tokens[i].getTtype() == Token.Toktype.OP){
                // TODO: Treure si fa falta els operadors amb manco o igual prioritat
                stack.push(tokens[i]);
            } else {
                calc.add(tokens[i]);
            }
        }

        while (!stack.isEmpty()){
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
