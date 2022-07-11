package com.company;

class Book extends Incription {
    private String writer;
    private int id;

    public Book(String name, String writer, String category, int id, int stockCount) {
        super(name, category, id, stockCount);
        this.writer = writer;
    }

    public String getWriter() {
        return writer;
    }

    public String getType() {
        return "(Book)";
    }

}
