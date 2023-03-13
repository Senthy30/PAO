package main.java.laborator3;

import java.util.Arrays;

public class Ex3 {

    public static void main(String[] args){

        int[] ar1, ar5[];
        int ar2[], i3, ar3[][], ar4[];

        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = new int[10];
        int[] array3[] = {{1, 2}, {2, 3}};
        int[] array4 = new int[] {1, 2, 3, 4, 5};
        int[] array5 = array1;

        System.out.println(array3);

        System.out.println(array1 == array4);
        System.out.println(Arrays.compare(array1, array4));
        System.out.println(Arrays.equals(array1, array4));

        for(int i = 0; i < array1.length; i++){
            System.out.print(array1[i]);
        }
    }

}
