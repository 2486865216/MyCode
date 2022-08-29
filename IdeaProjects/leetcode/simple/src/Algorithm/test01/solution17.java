package Algorithm.test01;

import java.util.*;

public class solution17 {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length()==0){
            return combinations;
        }
        Map<Character, String> map = new HashMap<Character, String>(){
            {
                put('2',"abc");
                put('3',"def");
                put('4',"ghi");
                put('5',"jkl");
                put('6',"mno");
                put('7',"pqrs");
                put('8',"tuv");
                put('9',"wxyz");
            }
        };
        backTrack(combinations, map, digits, 0, new StringBuffer());
        return combinations;
    }

    private void backTrack(List<String> combinations, Map<Character, String> map, String digits, int index, StringBuffer combination) {
        if (index==digits.length()){
            combinations.add(combination.toString());
        }else{
            char digit = digits.charAt(index);
            String letters = map.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backTrack(combinations, map, digits, index+1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
}
