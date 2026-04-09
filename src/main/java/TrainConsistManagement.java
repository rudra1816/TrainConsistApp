import java.util.*;
import java.util.stream.*;
import java.util.regex.Pattern;


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

    public String getCargoType() {
        return cargoType;
    }

    public String toString() {
        return type + " | Cargo: " + cargoType + " (Capacity: " + capacity + ")";
    }
}

// MAIN CLASS
public class TrainConsistManagement {

    List<Bogie> bogies = new ArrayList<>();

    // UC4 - Add Bogie
    void addBogie(Bogie b) {
        bogies.add(b);
    }

    // UC5 - Display
    void displayAll() {
        bogies.forEach(System.out::println);
    }

    // UC6 - Total Capacity
    int totalCapacity() {
        return bogies.stream().mapToInt(b -> b.capacity).sum();
    }

    // UC7 - Sort
    void sortBogies() {
        bogies.sort(Comparator.comparingInt(b -> b.capacity));
    }

    // UC8 - Filter
    List<Bogie> filterByCapacity(int threshold) {
        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());
    }

    // UC9 - Group
    Map<String, List<Bogie>> groupByType() {
        return bogies.stream()
                .collect(Collectors.groupingBy(b -> b.type));
    }

    void displayGrouped(Map<String, List<Bogie>> map) {
        map.forEach((type, list) -> {
            System.out.println(type + ":");
            list.forEach(System.out::println);
        });
    }

    // UC10 - Reduce
    int totalSeatsUsingReduce() {
        return bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);
    }

    // UC11 - Regex Validation
    boolean isValidTrainId(String trainId) {
        return Pattern.matches("TRN-\\d{4}", trainId);
    }

    boolean isValidCargoCode(String cargoCode) {
        return Pattern.matches("PET-[A-Z]{2}", cargoCode);
    }

    // UC12 - Safety Check
    boolean isTrainSafe() {
        return bogies.stream()
                .filter(b -> b instanceof GoodsBogie)
                .map(b -> (GoodsBogie) b)
                .allMatch(g ->
                        !g.type.equalsIgnoreCase("Cylindrical") ||
                                g.getCargoType().equalsIgnoreCase("Petroleum")
                );
    }

    // MAIN METHOD
    public static void main(String[] args) {

        TrainConsistManagement app = new TrainConsistManagement();

        // Sample Data
        app.addBogie(new PassengerBogie("Sleeper", 72));
        app.addBogie(new PassengerBogie("AC Chair", 60));
        app.addBogie(new PassengerBogie("First Class", 40));
        app.addBogie(new PassengerBogie("Sleeper", 80));

        app.addBogie(new GoodsBogie("Rectangular", 100, "Coal"));
        app.addBogie(new GoodsBogie("Cylindrical", 120, "Petroleum")); // valid
        // app.addBogie(new GoodsBogie("Cylindrical", 90, "Coal")); // try this → unsafe

        // UC5
        System.out.println("All Bogies:");
        app.displayAll();

        // UC6
        System.out.println("\nTotal Capacity: " + app.totalCapacity());

        // UC7
        app.sortBogies();
        System.out.println("\nSorted Bogies:");
        app.displayAll();

        // UC8
        System.out.println("\nFiltered (>60):");
        app.filterByCapacity(60).forEach(System.out::println);

        // UC9
        System.out.println("\nGrouped:");
        app.displayGrouped(app.groupByType());

        // UC10
        System.out.println("\nTotal Seats (Reduce): " + app.totalSeatsUsingReduce());

        // UC11
        System.out.println("\nRegex Validation:");
        System.out.println("TRN-1234 -> " + (app.isValidTrainId("TRN-1234") ? "Valid" : "Invalid"));
        System.out.println("PET-AB -> " + (app.isValidCargoCode("PET-AB") ? "Valid" : "Invalid"));

        // UC12
        System.out.println("\nSafety Check:");
        System.out.println(app.isTrainSafe() ? "Train is SAFE" : "Train is UNSAFE");
    }
}