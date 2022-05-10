package com.example.StringTable;

/**
 * author ye
 * createDate 2022/2/14  14:11
 */
public class StringExercise {
    String string = new String("good");
    char[] chars = {'t', 'e', 's', 't'};
    public void change(String string, char chars[]){
        string = "test ok";
        chars[0] = 'b';
    }

    public static void main(String[] args) {
        StringExercise stringExercise = new StringExercise();
        stringExercise.change(stringExercise.string, stringExercise.chars);
        System.out.println(stringExercise.string);
        System.out.println(stringExercise.chars);
    }
}
