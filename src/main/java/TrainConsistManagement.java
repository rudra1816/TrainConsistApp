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

// UC4–UC7 - Manage Bogies
public class TrainConsistManagement {

    List<Bogie> bogies = new ArrayList<>();

    // Add bogie
    void addBogie(Bogie b) {
        bogies.add(b);
    }

    // UC7 - Sort by capacity
    void sortBogies() {
        bogies.sort(Comparator.comparingInt(b -> b.capacity));
    }

    // UC8 - Filter passenger bogies using Streams
    List<Bogie> filterByCapacity(int threshold) {
        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());
    }

    // Display
    void display(List<Bogie> list) {
        list.forEach(System.out::println);
    }

    // Main method
    public static void main(String[] args) {
        TrainConsistManagement app = new TrainConsistManagement();

        // Sample data
        app.addBogie(new PassengerBogie("Sleeper", 72));
        app.addBogie(new PassengerBogie("AC Chair", 60));
        app.addBogie(new PassengerBogie("First Class", 40));
        app.addBogie(new GoodsBogie("Rectangular", 100, "Coal"));

        System.out.println("Filtered Bogies (>60):");
        List<Bogie> filtered = app.filterByCapacity(60);
        app.display(filtered);
    }
}