package main.java.laborator3;

import java.util.Scanner;

public class Ex2 {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Numele: ");
        String nume = scanner.nextLine();

        System.out.print("Varsta: ");
        Integer varsta = scanner.nextInt();

        for(Integer i = varsta % 2; i <= varsta; i += 2){
            System.out.print(i + " ");
        }

        scanner.close();
    }

}
