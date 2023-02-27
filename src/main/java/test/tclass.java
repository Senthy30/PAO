package main.java.test;

import main.java.laborator1.lab1;

public class tclass {

    public static void main(String[] args) {
        lab1.main(null);

        lab1 b = new lab1();
        b.setNumberLab(50);
        b.getNumberLab();
        b.main(null);

        System.out.println(b.getNumberLab());
    }

}
