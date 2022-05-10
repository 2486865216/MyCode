package com.qiqi.InterpreterPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * author ye
 * createDate 2022/2/9  13:00
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String expString = getExpString();
        HashMap<String, Integer> var = getValue(expString);
        Calculator calculator = new Calculator(expString);
        System.out.println("运算结果" + expString + "=" + calculator.run(var));
    }

    //获得表达式
    public static String getExpString() throws IOException {
        System.out.println("请输入表达式:");
        return (new BufferedReader(new InputStreamReader(System.in))).readLine();
    }

    //获得值映射
    public static HashMap<String, Integer> getValue(String expStr) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        for (char ch : expStr.toCharArray()) {
            if ( ch != '+' && ch != '-'){
                if (!map.containsKey(String.valueOf(ch))) {
                        System.out.print("请输入" + String.valueOf(ch) + "的值：");
                    String in = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                    map.put(String.valueOf(ch), Integer.valueOf(in));
                }
            }
        }

        return map;
    }
}
