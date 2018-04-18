import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Evaluator {


    public static int calculate(String expr) {
        // Llista per emmagatzemar es valors finals
        List<String> calc = new ArrayList();
        // Pila per emmagatzemar operadors
        LinkedList<String> stack = new LinkedList();
        StringBuilder sb = new StringBuilder();

        // ********************************************* //
        // Convertim l'string d'entrada en una llista de tokens
        Token[] tokens = Token.getTokens(expr);
        // ********************************************* //


        // Efectua el procediment per convertir la llista de tokens en notació RPN

        // Recorrem l'array de tokens
        // Efectua el procediment per convertir la llista de tokens en notació RPN
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i].getTtype() == Token.Toktype.OP){
                stack.push(String.valueOf(tokens[i].getTk()));
            } else if (tokens[i].getTtype() == Token.Toktype.PAREN){
                stack.push(String.valueOf(tokens[i].getTk()));
            } else {
                calc.add(String.valueOf(tokens[i].getValue()));
            }
        }

        if (!stack.isEmpty()){
            calc.add(stack.getFirst());
        }

        Token[] t = Token.getTokens(stack.pop().toString());
        // Finalment, crida a calcRPN amb la nova llista de tokens i torna el resultat
        int result = Evaluator.calcRPN(t);

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
