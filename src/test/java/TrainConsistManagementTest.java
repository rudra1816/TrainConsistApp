import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class TrainConsistManagementTest {

    // Test 1: Valid passenger bogie creation
    @Test
    void testException_ValidCapacityCreation() {
        assertDoesNotThrow(() -> {
            PassengerBogie b = new PassengerBogie("Sleeper", 50);
            assertEquals(50, b.capacity);
            assertEquals("Sleeper", b.type);
        });
    }

    // Test 2: Negative capacity throws exception
    @Test
    void testException_NegativeCapacityThrowsException() {
        InvalidCapacityException thrown = assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie("AC Chair", -10);
        });
        assertEquals("Capacity must be greater than zero", thrown.getMessage());
    }

    // Test 3: Zero capacity throws exception
    @Test
    void testException_ZeroCapacityThrowsException() {
        InvalidCapacityException thrown = assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie("First Class", 0);
        });
        assertEquals("Capacity must be greater than zero", thrown.getMessage());
    }

    // Test 4: Multiple valid bogies creation
    @Test
    void testException_MultipleValidBogiesCreation() {
        assertDoesNotThrow(() -> {
            List<PassengerBogie> bogies = Arrays.asList(
                    new PassengerBogie("Sleeper", 60),
                    new PassengerBogie("AC Chair", 70),
                    new PassengerBogie("First Class", 40)
            );
            assertEquals(3, bogies.size());
        });
    }

    // Test 5: Object integrity after creation
    @Test
    void testException_ObjectIntegrityAfterCreation() throws InvalidCapacityException {
        PassengerBogie b = new PassengerBogie("AC Chair", 80);
        assertEquals("AC Chair", b.type);
        assertEquals(80, b.capacity);
    }
}