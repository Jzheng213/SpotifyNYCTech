import java.util.*;

/**
 * Created by jzhen on 6/15/2018.
 */
public class One{

    String sortByStrings(String s, String t){
        List<String> sList = new ArrayList<>(Arrays.asList(s.split("")));
        Collections.sort(sList, new LetterPosComparator(t));
        return String.join("", sList);
    }
}

class LetterPosComparator implements Comparator<String> {
    public Map<String, Integer> letterPositions = new HashMap<>();

    LetterPosComparator(String customString){
        for (int i = 0; i < customString.length(); i++) {
            String currentLetter = Character.toString(customString.charAt(i));
            letterPositions.put(currentLetter, i);
        }
    }

    @Override
    public int compare(String letter1, String letter2) {
        int comparePos = letterPositions.get(letter1).compareTo(letterPositions.get(letter2));
        return comparePos;
    }
}