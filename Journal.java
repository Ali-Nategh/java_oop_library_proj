package com.company;

class Journal extends Incription {
    private int jNum;
    private String pDate;

    public Journal(String name, int jNum, String date, String category, int id, int stockCount) {
        super(name, category, id, stockCount);
        this.jNum = jNum;
        this.pDate = date;
    }

    public int getjNum() {
        return jNum;
    }

    public String getpDate() {
        return pDate;
    }

    public String getType() {
        return "(Journal)";
    }

}
