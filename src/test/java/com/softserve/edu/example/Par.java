package com.softserve.edu.example;

public class Par<T> {
    private String text;
    private Class<T> clazz;

    public Par(String text, Class<T> clazz) {
        this.text = text;
        this.clazz = clazz;
    }

    public String getText() {
        return text;
    }

    public Class<T> getClazz() {
        return clazz;
    }
}
