package com.softserve.edu.example;

public enum Aenum {
    DEFAULT("Text1", B.class);
    //
    private String text;
    private Class<?> clazz;

    private Aenum(String text, Class<?> clazz) {
        this.text = text;
        this.clazz = clazz;
    }

    public String getText() {
        return text;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
