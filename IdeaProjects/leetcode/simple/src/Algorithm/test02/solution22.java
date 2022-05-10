package Algorithm.test02;

import java.util.ArrayList;
import java.util.List;

public class solution22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        getParenthesis(res,"",0, 0, n);
        return res;
    }

    private void getParenthesis(List<String> res,String str, int countLeft, int countRight, int n) {
        if (countLeft > n || countRight > n) return;
        if (countLeft == n && countRight == n) res.add(str);

        if (countLeft >= countRight){
            getParenthesis(res,str+"(",countLeft + 1, countRight, n);
            getParenthesis(res,str+")",countLeft, countRight + 1, n);
        }
    }
}
