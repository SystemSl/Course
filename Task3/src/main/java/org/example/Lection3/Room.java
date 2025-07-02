package org.example.Lection3;

import java.util.Random;

public abstract class Room {
    private int roomNumber;
    private int maxOccupancy;
    private Prices price;
    private boolean booked;
    private Random random;

    public Room(int roomNumber, Prices price) {
        this.random = new Random();
        this.roomNumber = roomNumber;
        this.price = price;
        this.maxOccupancy = random.nextInt(4) + 1;
        this.booked = false;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public boolean isBooked() {
        return this.booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
