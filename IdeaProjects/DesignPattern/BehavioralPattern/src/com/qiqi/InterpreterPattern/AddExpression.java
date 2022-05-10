package com.qiqi.InterpreterPattern;

import java.util.HashMap;

/**
 * author ye
 * createDate 2022/2/9  12:41
 * 加法解析器
 */
public class AddExpression extends SymbolExpression{

    public AddExpression(Expression left, Expression right) {
        super(left,right);
    }

    //处理相加
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var) + super.right.interpreter(var);
    }
}
