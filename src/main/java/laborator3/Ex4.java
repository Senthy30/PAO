package main.java.laborator3;

import java.util.Arrays;

public class Ex4 {

    public static void main(String[] args){
        char[] chars = {'j', 'a', 'l', 'e'};

        for(char c : chars){
            System.out.print(c);
        }
        System.out.println();
        System.out.println("Arrays.toString()");
        System.out.println(Arrays.toString(chars));
    }

}
