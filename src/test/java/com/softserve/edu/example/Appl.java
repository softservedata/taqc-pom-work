package com.softserve.edu.example;

import java.lang.reflect.Constructor;

public class Appl {

    public <T> T getInstance(Par<T> par) {
        Class<?> clazz = par.getClazz();
        Constructor<?> ctor = null;
        Object object = null;
        try {
            ctor = clazz.getConstructor(String.class);
            object = ctor.newInstance(new Object[]{"A"});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (T) object;
    }

    public <T> T getInstance(Aenum aenum) {
        Class<?> clazz = aenum.getClazz();
        Constructor<?> ctor = null;
        Object object = null;
        try {
            ctor = clazz.getConstructor(String.class);
            object = ctor.newInstance(new Object[]{"A"});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (T) object;
    }

    public <T> T getInstance(Class<T> clazz) {
        Constructor<?> ctor = null;
        Object object = null;
        try {
            ctor = clazz.getConstructor(String.class);
            object = ctor.newInstance(new Object[]{"A"});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (T) object;
    }

    public <T> T getInstance(String className) {
        Class<?> clazz = null;
        Constructor<?> ctor = null;
        Object object = null;
        try {
            clazz = Class.forName(className);
            ctor = clazz.getConstructor(String.class);
            object = ctor.newInstance(new Object[]{"A"});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (T) object;
    }

    public static void main(String[] args) {
        Appl appl = new Appl();
        //A a = appl.getInstance("A");
        //A a = appl.getInstance(B.class);
        //
        //A a = appl.getInstance(Aenum.DEFAULT);
        //a.print();
        //
        //appl.getInstance(B.class).print2(); // OK
        //
        Par<B> par = new Par<>("tt", B.class);
        appl.getInstance(par).print2();
    }
}
