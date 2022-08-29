package com.qiqi.InterpreterPattern;


import java.util.HashMap;
import java.util.Stack;

/**
 * author ye
 * createDate 2022/2/9  12:45
 */
public class Calculator {
    //定义表达式
    private Expression expression;

    //过早函数床传参，并解析

    public Calculator(String exprString) {
        //安排运算先后顺序
        Stack<Expression> stack = new Stack<>();
        //表达式拆分成字符数组
        char[] charArray = exprString.toCharArray();//a + b

        Expression left;
        Expression right;
        //遍历字符串数组
        for (int i = 0; i < charArray.length; i++) {
            switch(charArray[i]){
                case '+':
                    left = stack.pop();//从stack取出left=>a
                    right = new VarExpression(String.valueOf(charArray[++i]));//取出right=>b
                    stack.push(new AddExpression(left, right));//将left和right构建AddExpression加入stack
                    break;
                case '-':
                    left = stack.pop();//从stack取出left=>a
                    right = new VarExpression(String.valueOf(charArray[++i]));//取出right=>b
                    stack.push(new SubExpression(left, right));//将left和right构建SubExpression加入stack
                    break;
                default:
                    //如果是一个var,创建一个VarExpression
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
                    break;
            }
        }
        //遍历完成之后，栈顶元素为结果
        this.expression = stack.pop();
    }

    //将表达式a+b和 var = {a:10, b:20}
    //最后传递给expression的interpreter进行解析执行
    public int run(HashMap<String, Integer> var){
        return this.expression.interpreter(var);
    }
}
