package com.qiqi.InterpreterPattern;

import java.util.HashMap;

/**
 * author ye
 * createDate 2022/2/9  12:30
 * 抽象类表达式，通过HashMap键值对，可以获取到变量的值
 */
public abstract class Expression {
    /**
     * a + b - c
     * 解释公式和值key就是表达式公式，参数[a,b,c],value就是具体值
     * HashMap {a:10, b:20, c:30}
     * @param var
     * @return
     */
    public abstract int interpreter(HashMap<String, Integer> var);
}
