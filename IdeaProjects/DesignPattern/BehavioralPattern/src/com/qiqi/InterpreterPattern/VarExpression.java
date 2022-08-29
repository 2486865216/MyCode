package com.qiqi.InterpreterPattern;

import java.util.HashMap;

/**
 * author ye
 * createDate 2022/2/9  12:34
 *
 * 变量的解析器
 */
public class VarExpression extends Expression{
    private String key;

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(key);
    }
}
