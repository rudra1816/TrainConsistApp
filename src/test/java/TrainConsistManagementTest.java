import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementTest {

    // Helper method to create sample data
    TrainConsistManagement createApp() {
        TrainConsistManagement app = new TrainConsistManagement();

        app.addBogie(new PassengerBogie("Sleeper", 72));
        app.addBogie(new PassengerBogie("AC Chair", 60));
        app.addBogie(new PassengerBogie("First Class", 40));
        app.addBogie(new PassengerBogie("AC 2 Tier", 80));

        return app;
    }

    // UC8 Tests

    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        TrainConsistManagement app = createApp();

        List<Bogie> result = app.filterByCapacity(70);

        assertEquals(2, result.size());
    }

    @Test
    void testFilter_CapacityEqualToThreshold() {
        TrainConsistManagement app = createApp();

        List<Bogie> result = app.filterByCapacity(60);

        // 60 should NOT be included
        for (Bogie b : result) {
            assertTrue(b.capacity > 60);
        }
    }

    @Test
    void testFilter_CapacityLessThanThreshold() {
        TrainConsistManagement app = createApp();

        List<Bogie> result = app.filterByCapacity(70);

        for (Bogie b : result) {
            assertTrue(b.capacity > 70);
        }
    }

    @Test
    void testFilter_MultipleBogiesMatching() {
        TrainConsistManagement app = createApp();

        List<Bogie> result = app.filterByCapacity(50);

        assertTrue(result.size() > 1);
    }

    @Test
    void testFilter_NoBogiesMatching() {
        TrainConsistManagement app = createApp();

        List<Bogie> result = app.filterByCapacity(100);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_AllBogiesMatching() {
        TrainConsistManagement app = createApp();

        List<Bogie> result = app.filterByCapacity(30);

        assertEquals(4, result.size());
    }

    @Test
    void testFilter_EmptyBogieList() {
        TrainConsistManagement app = new TrainConsistManagement();

        List<Bogie> result = app.filterByCapacity(50);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_OriginalListUnchanged() {
        TrainConsistManagement app = createApp();

        int originalSize = app.bogies.size();

        app.filterByCapacity(60);

        assertEquals(originalSize, app.bogies.size());
    }
}