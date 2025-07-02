package org.example.Lection3;

public enum Prices {
    CHEAP(50),
    MEDIUM(100),
    EXPENSIVE(150);

    private final int price;

    Prices(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}