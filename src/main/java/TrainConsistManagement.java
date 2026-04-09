import java.util.*;
import java.util.stream.Collectors;

// UC1 - Base Bogie class
class Bogie {
    String type;
    int capacity;

    Bogie(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public String toString() {
        return type + " (Capacity: " + capacity + ")";
    }
}

// UC2 - Passenger Bogie
class PassengerBogie extends Bogie {
    PassengerBogie(String type, int capacity) {
        super(type, capacity);
    }
}

// UC3 - Goods Bogie
class GoodsBogie extends Bogie {
    String cargoType;

    GoodsBogie(String type, int capacity, String cargoType) {
        super(type, capacity);
        this.cargoType = cargoType;
    }
}

// UC4–UC9 - Main Management Class
public class TrainConsistManagement {

    List<Bogie> bogies = new ArrayList<>();

    // UC4 - Add Bogie
    void addBogie(Bogie b) {
        bogies.add(b);
    }

    // UC5 - Display all bogies
    void displayAll() {
        bogies.forEach(System.out::println);
    }

    // UC6 - Total capacity
    int totalCapacity() {
        return bogies.stream().mapToInt(b -> b.capacity).sum();
    }

    // UC7 - Sort bogies by capacity
    void sortBogies() {
        bogies.sort(Comparator.comparingInt(b -> b.capacity));
    }

    // UC8 - Filter bogies using Streams
    List<Bogie> filterByCapacity(int threshold) {
        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());
    }

    // UC9 - Group bogies by type
    Map<String, List<Bogie>> groupByType() {
        return bogies.stream()
                .collect(Collectors.groupingBy(b -> b.type));
    }

    // Display grouped data
    void displayGrouped(Map<String, List<Bogie>> map) {
        map.forEach((type, list) -> {
            System.out.println(type + ":");
            list.forEach(System.out::println);
        });
    }

    // MAIN METHOD (Demo)
    public static void main(String[] args) {
        TrainConsistManagement app = new TrainConsistManagement();

        // UC1–UC3 sample data
        app.addBogie(new PassengerBogie("Sleeper", 72));
        app.addBogie(new PassengerBogie("AC Chair", 60));
        app.addBogie(new PassengerBogie("First Class", 40));
        app.addBogie(new PassengerBogie("Sleeper", 80));
        app.addBogie(new GoodsBogie("Rectangular", 100, "Coal"));

        // UC5 - Display
        System.out.println("All Bogies:");
        app.displayAll();

        // UC6 - Total Capacity
        System.out.println("\nTotal Capacity: " + app.totalCapacity());

        // UC7 - Sort
        app.sortBogies();
        System.out.println("\nSorted Bogies:");
        app.displayAll();

        // UC8 - Filter
        System.out.println("\nFiltered Bogies (>60):");
        List<Bogie> filtered = app.filterByCapacity(60);
        filtered.forEach(System.out::println);

        // UC9 - Group
        System.out.println("\nGrouped Bogies:");
        Map<String, List<Bogie>> grouped = app.groupByType();
        app.displayGrouped(grouped);
    }
}