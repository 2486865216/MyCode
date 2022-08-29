package com.qiqi.InterpreterPattern;

import java.util.HashMap;

/**
 * author ye
 * createDate 2022/2/9  12:43
 */
public class SubExpression extends SymbolExpression{
    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var) - super.right.interpreter(var);
    }
}
