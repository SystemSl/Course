package org.example.Lection2;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class CarDealershipApplication {
    public static void main(String[] args) {
        CarDealership dealership = new CarDealership();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCar(scanner, dealership);
                    break;
                case 2:
                    displayAllCarsByManufacturer(scanner, dealership);
                    break;
                case 3:
                    displayAveragePriceOfType(scanner, dealership);
                    break;
                case 4:
                    displaySortedByYear(dealership);
                    break;
                case 5:
                    displayCountsByType(dealership);
                    break;
                case 6:
                    displayOldestAndNewestCar(dealership);
                    break;
                case 7:
                    System.out.println("Shut down...");
                    return;
                default:
                    System.out.println("Wrong choice. Try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Add car");
        System.out.println("2. Show all cars by manufacturer");
        System.out.println("3. Average price of cars by type");
        System.out.println("4. Show sorted by year");
        System.out.println("5. Show number of cars of each type");
        System.out.println("6. Show oldest and newest car");
        System.out.println("7. Quit");
        System.out.print("Your choice: ");
    }

    private static void addCar(Scanner scanner, CarDealership dealership) {
        System.out.print("VIN: ");
        String vin = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Manufacturer: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Year of production: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Mileage: ");
        int mileage = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Price: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Type (SEDAN/SUV/HATCHBACK/COUPE/CABRIOLET/TRUCK/ELECTRIC/HYBRID):");
        Car.CarType type = Car.CarType.valueOf(scanner.nextLine().toUpperCase());
        Car newCar = new Car(vin, model, manufacturer, year, mileage, price, type);
        if (dealership.addCar(newCar))
            System.out.println("Car has been added");
        else
            System.out.println("ERROR: VIN already in use");
    }

    private static void displayAllCarsByManufacturer(Scanner scanner, CarDealership dealership) {
        System.out.print("Manufacturer: ");
        String manufacturer = scanner.nextLine();
        System.out.println("\n--- Cars by manufacturer ---");
        if (!dealership.findCarsByManufacturer(manufacturer).isEmpty())
            dealership.findCarsByManufacturer(manufacturer).forEach(System.out::println);
        else
            System.out.println("\t\tNo cars.");
    }

    private static void displayAveragePriceOfType(Scanner scanner, CarDealership dealership) {
        System.out.print("Type (SEDAN/SUV/HATCHBACK/COUPE/CABRIOLET/TRUCK/ELECTRIC/HYBRID):");
        Car.CarType type = Car.CarType.valueOf(scanner.nextLine().toUpperCase());
        System.out.println(dealership.getAveragePriceForType(type) + " rub");
    }

    private static void displaySortedByYear(CarDealership dealership) {
        System.out.println("\n--- Cars in dealership ---");
        if (!dealership.getCars().isEmpty())
            dealership.getSortedByYear().forEach(System.out::println);
        else
            System.out.println("\t\tNo cars.");
    }

    private static void displayCountsByType(CarDealership dealership) {
        Map<Car.CarType, Long> countsByType = dealership.getCountByType();
        System.out.println("\nNumber of cars of each type:");
        countsByType.forEach((type, count) -> System.out.println(type + ": " + count));
    }

    private static void displayOldestAndNewestCar(CarDealership dealership) {
        Optional<Car> oldestCar = dealership.getOldestCar();
        Optional<Car> newestCar = dealership.getNewestCar();

        System.out.println("\nOldest car:");
        oldestCar.ifPresentOrElse(
                car -> System.out.println(car),
                () -> System.out.println("No cars")
        );

        System.out.println("\nNewest car:");
        newestCar.ifPresentOrElse(
                car -> System.out.println(car),
                () -> System.out.println("No cars")
        );
    }
}
