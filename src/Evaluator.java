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
        LinkedList<Token> linkedToken = new LinkedList();

        for (int i = 0; i < list.length; i++) {
            linkedToken.push(list[i]);
        }

        Iterator it = linkedToken.iterator();
        int sum = 0;
        int rest = 0;
        while (it.hasNext()){
            if(linkedToken.pop().equals(Token.tokOp('+'))) {
                sum += linkedToken.poll().getValue();
                sum += linkedToken.poll().getValue();
            }else if (linkedToken.pop().equals(Token.tokOp('-'))){
                rest -= linkedToken.poll().getValue();
                rest -= linkedToken.poll().getValue();
            }
        }
        // Calcula el valor resultant d'avaluar la llista de tokens
        return sum;
        
    }


}
