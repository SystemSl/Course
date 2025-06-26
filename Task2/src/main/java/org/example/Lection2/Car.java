package org.example.Lection2;

import java.util.Objects;

public class Car implements Comparable<Car>{
    private String vin;
    private String model;
    private String manufacturer;
    private int yearOfProduction;
    private int mileage;
    private int price;
    private CarType type;

    public Car(String vin, String model, String manufacturer, int yearOfProduction, int mileage, int price, CarType type) {
        this.vin = vin;
        this.model = model;
        this.manufacturer = manufacturer;
        this.yearOfProduction = yearOfProduction;
        this.mileage = mileage;
        this.price = price;
        this.type = type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public enum CarType {
        SEDAN,
        SUV,
        HATCHBACK,
        COUPE,
        CABRIOLET,
        TRUCK,
        ELECTRIC,
        HYBRID
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        else {
            Car otherAccount = (Car) obj;
            return this.vin.equals(otherAccount.vin);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.vin);
    }

    @Override
    public String toString() {
        return this.manufacturer + " " + this.model + " " + this.yearOfProduction + " " + this.mileage + " km " + this.price + " rub";
    }

    @Override
    public int compareTo(Car other) {
        return Integer.compare(other.yearOfProduction, this.yearOfProduction);
    }
}
