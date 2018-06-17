import javafx.util.Pair;
import java.util.Stack;

/**
 * Created by jzhen on 6/15/2018.
 */

// this method allows you to properly process nested items ex: 4[abc2[de5[v]5[w]]]

public class Two {
    private Stack<Integer> frequencies = new Stack<>();
    private Stack<Pair<String, Integer>> strings = new Stack<>();

    private int stackLevel = 0;
    private int priorStack = 0;

    private StringBuilder result = new StringBuilder();
    private String temp = "";

    public String decodeString(String s) {
        for(int i = 0; i < s.length(); i++) {
            String currentChar = Character.toString(s.charAt(i));

            if(currentChar.equals("]")) {
                processStacks(frequencies.pop());
                stackLevel--;
                temp = result.toString();
                continue;
            }

            if(currentChar.equals("[")){
                stackLevel++;
                continue;
            }

            if(isInteger(currentChar)){
                frequencies.push(Integer.parseInt(currentChar));
            }else{
                strings.push(new Pair<>(currentChar, stackLevel));
            }
        }
        return result.toString();
    }

    private void processStacks(int freq){
        StringBuilder characters = new StringBuilder();
        Pair<String, Integer> last = null;

        do {
            last = strings.pop();
            characters.insert(0,last.getKey());
        } while(!strings.isEmpty() && strings.peek().getValue() == stackLevel);

        if(priorStack > stackLevel) result = new StringBuilder();

        for(int i = 0; i < freq; i++){
            result.append(characters);
            if(priorStack > stackLevel) result.append(temp);
        };

        priorStack = stackLevel;
    }

    private boolean isInteger (String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

}
