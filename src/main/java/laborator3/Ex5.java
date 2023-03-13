package main.java.laborator3;

import java.util.Arrays;

public class Ex5 {

    public static void main(String[] args){

        int[][] arrayOfArrays = new int[3][];

        arrayOfArrays[0] = new int[1];
        arrayOfArrays[0][0] = 0;

        arrayOfArrays[1] = new int[2];
        arrayOfArrays[1][0] = 10;
        arrayOfArrays[1][1] = 11;

        arrayOfArrays[2] = new int[3];
        arrayOfArrays[2][0] = 20;
        arrayOfArrays[2][1] = 21;
        arrayOfArrays[2][2] = 22;

        System.out.println(Arrays.toString(arrayOfArrays));
        System.out.println(Arrays.deepToString(arrayOfArrays));

        for(int[] v : arrayOfArrays){
            for(int x : v)
                System.out.print(x + " ");
            System.out.println();
        }
    }

}
