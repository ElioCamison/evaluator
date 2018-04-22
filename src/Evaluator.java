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

        // Convertim l'string d'entrada en una llista de tokens
        Token[] tokens = Token.getTokens(expr);

        // Efectua el procediment per convertir la llista de tokens en notació RPN
        // Recorrem l'array de tokens
        for (int i = 0; i < tokens.length; i++) {

            //Si és un número directament entra dins el contenidor
            if (tokens[i].getTtype() == Token.Toktype.NUMBER) {
                calc.push(tokens[i]);
            }

            if (tokens[i].getTtype() == Token.Toktype.OP) {
                // Si la pila está buida, l'operador entra.
                if (stack.isEmpty()) {
                    stack.push(tokens[i]);
                    continue;
                }

                // Si la pila no está buida, hem de revisar quina es la prioritat dels operadors
                if (definePriority(stack.peek().getTk()) < definePriority(tokens[i].getTk())) {
                    stack.push(tokens[i]);
                    continue;
                }

                // En cas de que la prioritat sigui més gran s'ha de treure el que tenim dins la pila i afegir el que entra
                while (!stack.isEmpty() && definePriority(stack.peek().getTk()) >= definePriority(tokens[i].getTk())) {
                    calc.add(stack.pop());
                    continue;
                }
                stack.push(tokens[i]);

            }

            if (tokens[i].getTtype() == Token.Toktype.PAREN) {
                // Si és un parèntesis que obri, l'afegim tot d'una
                if (tokens[i].getTk() == '(') {
                    stack.push(tokens[i]);
                } else {
                    // Es treu lo que  hi ha dins es parèntesis quan trobam el que tanca
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

        // Finalment, crida a calcRPN amb la nova llista de tokens i torna el resultat
        int result = Evaluator.calcRPN(arrayTokens);

        return result;
    }

    public static int calcRPN(Token[] list) {
        LinkedList<Integer> stack = new LinkedList();

        // Recorrem la llista
        for (Token t : list) {
            if (t.getTtype() == Token.Toktype.NUMBER) {
                stack.push(t.getValue());
            } else if (t.getTtype() == Token.Toktype.OP) {
                // Quan arriba un operador es treuen el valor numèrics
                // Emmagatzemam els valors
                int valor2 = stack.pop();
                int valor1 = stack.pop();
                char tk = t.getTk();

                // Realitza els càlculs en base a l'operador
                switch (tk) {
                    case '+':
                        stack.push(valor1 + valor2);
                        break;
                    case '-':
                        stack.push(valor1 - valor2);
                        break;
                    case '*':
                        stack.push(valor1 * valor2);
                        break;
                    case '/':
                        stack.push(valor1 / valor2);
                        break;
                }
            }
        }
        // Calcula el valor resultant d'avaluar la llista de tokens
        return stack.pop();
    }

    // Defineix la prioritat d'un operador
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
