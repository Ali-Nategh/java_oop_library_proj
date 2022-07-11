package com.company;

class Incription {
    private String name;
    private String category;
    private int id;
    private int stockCount;

    public Incription(String name, String category, int id, int stock) {
        this.name = name;
        this.category = category;
        this.id = id;
        this.stockCount = stock;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return "(Inscription)";
    }

    public int getStock() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

}
