package com.qiqi.InterpreterPattern;

import java.util.HashMap;

/**
 * author ye
 * createDate 2022/2/9  12:35
 *
 * 抽象运算符号解析器，每个运算符都只和自己左右的数字有关系
 * 但左右的数字有可能也是一个解析的结果，无论何种类型，都是Expression类的实现类
 */
public class SymbolExpression extends Expression{
    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    //由子类实现
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return 0;
    }
}
