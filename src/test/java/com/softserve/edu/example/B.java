package com.softserve.edu.example;

public class B extends A {

    public B(String text) {
        super(text);
    }

    public void print() {
        System.out.printf("B: text = " + text);
    }

    public void print2() {
        System.out.printf("B2: text = " + text);
    }
}
