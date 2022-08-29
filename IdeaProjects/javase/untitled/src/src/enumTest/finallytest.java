package src.enumTest;

import src.Interface_Test.Test;

public class finallytest {
    public static void main(String[] args) {
        System.out.println(finallytest1());
    }
    static int finallytest1(){
        int i = 100;
        try {
            return i;
        }finally {
            i++;
        }
    }
}
