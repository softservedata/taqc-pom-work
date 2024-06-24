package com.softserve.edu.example;

public class A {
    protected String text;

    public A(String text) {
        this.text = text;
    }

    public void print() {
        System.out.printf("A: text = " + text);
    }
}
