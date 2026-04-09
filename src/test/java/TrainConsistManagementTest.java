import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementTest {

    // Helper method
    TrainConsistManagement createApp() {
        TrainConsistManagement app = new TrainConsistManagement();

        app.addBogie(new PassengerBogie("Sleeper", 72));
        app.addBogie(new PassengerBogie("Sleeper", 80));
        app.addBogie(new PassengerBogie("AC Chair", 60));
        app.addBogie(new PassengerBogie("First Class", 40));

        return app;
    }

    // UC9 TESTS

    @Test
    void testGrouping_BogiesGroupedByType() {
        TrainConsistManagement app = createApp();

        Map<String, List<Bogie>> result = app.groupByType();

        assertTrue(result.containsKey("Sleeper"));
        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    void testGrouping_MultipleBogiesInSameGroup() {
        TrainConsistManagement app = createApp();

        Map<String, List<Bogie>> result = app.groupByType();

        assertTrue(result.get("Sleeper").size() > 1);
    }

    @Test
    void testGrouping_DifferentBogieTypes() {
        TrainConsistManagement app = createApp();

        Map<String, List<Bogie>> result = app.groupByType();

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
    }

    @Test
    void testGrouping_EmptyBogieList() {
        TrainConsistManagement app = new TrainConsistManagement();

        Map<String, List<Bogie>> result = app.groupByType();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGrouping_SingleBogieCategory() {
        TrainConsistManagement app = new TrainConsistManagement();

        app.addBogie(new PassengerBogie("Sleeper", 70));

        Map<String, List<Bogie>> result = app.groupByType();

        assertEquals(1, result.size());
        assertTrue(result.containsKey("Sleeper"));
    }

    @Test
    void testGrouping_MapContainsCorrectKeys() {
        TrainConsistManagement app = createApp();

        Map<String, List<Bogie>> result = app.groupByType();

        assertTrue(result.keySet().contains("Sleeper"));
        assertTrue(result.keySet().contains("AC Chair"));
    }

    @Test
    void testGrouping_GroupSizeValidation() {
        TrainConsistManagement app = createApp();

        Map<String, List<Bogie>> result = app.groupByType();

        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    void testGrouping_OriginalListUnchanged() {
        TrainConsistManagement app = createApp();

        int originalSize = app.bogies.size();

        app.groupByType();

        assertEquals(originalSize, app.bogies.size());
    }

@Test
void testRegex_InvalidTrainIDFormat() {
    TrainConsistManagement app = new TrainConsistManagement();

    assertFalse(app.isValidTrainId("TRAIN12"));
    assertFalse(app.isValidTrainId("TRN12A"));
    assertFalse(app.isValidTrainId("1234-TRN"));
}
}