package com.softserve.edu.teachua.data;

public enum CommentContents {
    DEFAULT_COMMENT("Проба Проба", "2024-06-22 10:59:59", "Проба Коментар"),
    FIRST_COMMENT("Проба Проба", "2024-06-22 10:59:59", "Проба Коментар");

    private String author;
    private String datetime;
    private String text;

    private CommentContents(String author, String datetime, String text) {
        // TODO
        this.author = author;
        this.datetime = datetime;
        this.text = text;
    }

    public String getAuthor() {
        // TODO
        return author;
    }

    public String getDatetime() {
        // TODO
        return datetime;
    }

    public String getText() {
        // TODO
        return text;
    }

    @Override
    public String toString() {
        // TODO
        return text;
    }

}
