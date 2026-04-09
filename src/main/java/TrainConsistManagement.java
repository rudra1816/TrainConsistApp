import java.util.*;
import java.util.stream.Collectors;

// UC2 - Passenger Bogie with UC14 validation
class PassengerBogie extends Bogie {
    PassengerBogie(String type, int capacity) throws InvalidCapacityException {
        super(type, capacity);
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
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

// UC14 - Custom Exception
class InvalidCapacityException extends Exception {
    InvalidCapacityException(String message) {
        super(message);
    }
}

// Main Management Class (UC4–UC13 logic still here)
public class TrainConsistManagement {

    List<Bogie> bogies = new ArrayList<>();

    // Add Bogie
    void addBogie(Bogie b) {
        bogies.add(b);
    }

    // Display all bogies
    void displayAll() {
        bogies.forEach(System.out::println);
    }

    // MAIN METHOD DEMO
    public static void main(String[] args) {
        TrainConsistManagement app = new TrainConsistManagement();

        try {
            // UC1–UC2 sample data with UC14 validation
            app.addBogie(new PassengerBogie("Sleeper", 72));
            app.addBogie(new PassengerBogie("AC Chair", 60));
            app.addBogie(new PassengerBogie("First Class", 40));

            // UC14 test: Invalid capacity
            // Uncomment to test exception
            // app.addBogie(new PassengerBogie("Sleeper", -10));
            // app.addBogie(new PassengerBogie("AC Chair", 0));

            app.addBogie(new GoodsBogie("Rectangular", 100, "Coal"));

        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("All Bogies:");
        app.displayAll();
    }
}