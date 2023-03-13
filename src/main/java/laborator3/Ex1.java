package main.java.laborator3;

public class Ex1 {

    public static void main(String[] args){
        Integer i1 = 4;
        Integer i2 = Integer.valueOf(4);
        Integer i3 = Integer.valueOf("4");

        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);

        System.out.println(i1 == i3);

        Integer i4 = Integer.valueOf(128);
        Integer i5 = Integer.valueOf("128");

        System.out.println(i4 == i5);

        System.out.println("instanceOf: " + (i5 instanceof Number));

        boolean b1 = Boolean.parseBoolean("tre");
        System.out.println(b1);

        Integer badint = Integer.parseInt("5");;
        System.out.println(badint);
    }

}
