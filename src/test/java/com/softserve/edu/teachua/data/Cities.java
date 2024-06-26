package com.softserve.edu.teachua.data;

public enum Cities {
    DEFAULT_CITY("Київ"),
    KYIV_CITY("Київ"),
    HARKIV_CITY("Харків");

    private String city;

    private Cities(String city) {
        // TODO
        this.city = city;
    }

    public String getCity() {
        // TODO
        return city;
    }

        @Override
    public String toString() {
        // TODO
        return city;
    }
}
