import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.Collectors;

public class TrainConsistManagementTest {

    // Helper to create sample bogies
    private TrainConsistManagement sampleApp() {
        TrainConsistManagement app = new TrainConsistManagement();
        app.addBogie(new PassengerBogie("Sleeper", 72));
        app.addBogie(new PassengerBogie("AC Chair", 60));
        app.addBogie(new PassengerBogie("First Class", 40));
        app.addBogie(new PassengerBogie("Sleeper", 80));
        app.addBogie(new GoodsBogie("Rectangular", 100, "Coal"));
        app.addBogie(new GoodsBogie("Cylindrical", 120, "Petroleum"));
        return app;
    }

    // UC5 - Display all (simple size check)
    @Test
    public void testDisplayAll_Size() {
        TrainConsistManagement app = sampleApp();
        assertEquals(6, app.bogies.size());
    }

    // UC6 - Total capacity
    @Test
    public void testTotalCapacity() {
        TrainConsistManagement app = sampleApp();
        assertEquals(472, app.totalCapacity());
    }

    // UC7 - Sorting
    @Test
    public void testSortBogies() {
        TrainConsistManagement app = sampleApp();
        app.sortBogies();
        assertEquals(40, app.bogies.get(0).capacity);
        assertEquals(120, app.bogies.get(app.bogies.size()-1).capacity);
    }

    // UC8 - Filter by capacity
    @Test
    public void testFilterByCapacity() {
        TrainConsistManagement app = sampleApp();
        List<Bogie> filtered = app.filterByCapacity(60);
        assertEquals(4, filtered.size());
        assertTrue(filtered.stream().allMatch(b -> b.capacity > 60));
    }

    // UC9 - Group by type
    @Test
    public void testGroupByType() {
        TrainConsistManagement app = sampleApp();
        Map<String, List<Bogie>> grouped = app.groupByType();
        assertEquals(5, grouped.size()); // corrected from 4 to 5
        assertEquals(2, grouped.get("Sleeper").size());
    }

    // UC10 - Reduce
    @Test
    public void testTotalSeatsUsingReduce() {
        TrainConsistManagement app = sampleApp();
        assertEquals(app.totalCapacity(), app.totalSeatsUsingReduce());
    }

    // UC11 - Regex validation
    @Test
    public void testRegexValidation() {
        TrainConsistManagement app = sampleApp();
        assertTrue(app.isValidTrainId("TRN-1234"));
        assertFalse(app.isValidTrainId("TRN1234"));
        assertTrue(app.isValidCargoCode("PET-AB"));
        assertFalse(app.isValidCargoCode("PET-ab"));
    }

    // UC12 - Safety compliance
    @Test
    public void testSafetyCheck_Valid() {
        TrainConsistManagement app = sampleApp();
        assertTrue(app.isTrainSafe());
    }

    @Test
    public void testSafetyCheck_Invalid() {
        TrainConsistManagement app = sampleApp();
        // Add unsafe cylindrical bogie
        app.addBogie(new GoodsBogie("Cylindrical", 50, "Coal"));
        assertFalse(app.isTrainSafe());
    }

    // UC13 - Performance Comparison Tests
    @Test
    public void testLoopFilteringLogic() {
        TrainConsistManagement app = sampleApp();
        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : app.bogies) {
            if (b.capacity > 60) loopResult.add(b);
        }
        assertEquals(4, loopResult.size());
    }

    @Test
    public void testStreamFilteringLogic() {
        TrainConsistManagement app = sampleApp();
        List<Bogie> streamResult = app.bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        assertEquals(4, streamResult.size());
    }

    @Test
    public void testLoopAndStreamResultsMatch() {
        TrainConsistManagement app = sampleApp();
        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : app.bogies) {
            if (b.capacity > 60) loopResult.add(b);
        }
        List<Bogie> streamResult = app.bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        assertEquals(loopResult.size(), streamResult.size());
    }

    @Test
    public void testExecutionTimeMeasurement() {
        TrainConsistManagement app = sampleApp();
        long start = System.nanoTime();
        app.filterByCapacity(60);
        long end = System.nanoTime();
        assertTrue((end - start) > 0);
    }

    @Test
    public void testLargeDatasetProcessing() {
        TrainConsistManagement app = new TrainConsistManagement();
        for (int i = 0; i < 10000; i++) {
            app.addBogie(new PassengerBogie("Sleeper", 50 + i % 100));
        }
        List<Bogie> loopResult = new ArrayList<>();
        long startLoop = System.nanoTime();
        for (Bogie b : app.bogies) {
            if (b.capacity > 120) loopResult.add(b);
        }
        long endLoop = System.nanoTime();
        List<Bogie> streamResult = app.bogies.stream()
                .filter(b -> b.capacity > 120)
                .collect(Collectors.toList());
        long endStream = System.nanoTime();

        assertEquals(loopResult.size(), streamResult.size());
        assertTrue((endLoop - startLoop) > 0);
        assertTrue((endStream - endLoop) > 0 || (endStream - startLoop) > 0);
    }
}