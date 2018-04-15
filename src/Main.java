import java.util.*;

public class Main {

    public static void main(String[] args) {
        String proves = "1+311";
        Map<Character,Integer> mapa = new HashMap();

        for (int i = 0; i < proves.length(); i++) {
            mapa.put(proves.charAt(i),i);
        }
    }

}
