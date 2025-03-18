package Test;

import at.fhtw.tourplanner.model.Tour;
import at.fhtw.tourplanner.model.TourLog;
import at.fhtw.tourplanner.viewModel.TourLogViewModel;
import at.fhtw.tourplanner.viewModel.TourViewModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UniTest {
    private TourViewModel tourViewModel;
    private TourLogViewModel tourLogViewModel;
    private Tour tour;

    @Before
    public void setUp() {
        // Initialisiere die ViewModels und eine Test-Tour
        tourViewModel = new TourViewModel();
        tourLogViewModel = new TourLogViewModel();
        tour = new Tour();
        tour.setName("Test Tour");
        tourLogViewModel.setSelectedTour(tour);
    }

    // Test 1: Hinzufügen einer Tour
    @Test
    public void testAddTour() {
        // Arrange
        Tour tour = new Tour();
        tour.setName("Test Tour");
        tourViewModel.addTour(tour);
        assertEquals(1, tourViewModel.getTours().size());
        assertEquals("Test Tour", tourViewModel.getTours().get(0).getName());
    }

    // Test 2: Bearbeiten einer Tour
    @Test
    public void testEditTour() {
        // Arrange
        Tour tour = new Tour();
        tour.setName("Old Name");
        tourViewModel.addTour(tour);
        tourViewModel.setSelectedTour(tour);
        Tour updatedTour = new Tour();
        updatedTour.setName("New Name");
        tourViewModel.editTour(updatedTour);
        assertEquals("New Name", tourViewModel.getSelectedTour().getName());
    }

    // Test 3: Löschen einer Tour
    @Test
    public void testDeleteTour() {
        // Arrange
        Tour tour = new Tour();
        tour.setName("Test Tour");
        tourViewModel.addTour(tour);
        tourViewModel.setSelectedTour(tour);
        tourViewModel.deleteTour();
        assertEquals(0, tourViewModel.getTours().size());
        assertNull(tourViewModel.getSelectedTour());
    }

    // Test 4: Hinzufügen eines Tour-Logs
    @Test
    public void testAddTourLog() {
        TourLog tourLog = new TourLog(tour);
        tourLog.setComment("Test Log");
        tourLogViewModel.addTourLog(tourLog);
        assertEquals(1, tourLogViewModel.getTourLogs().size());
        assertEquals("Test Log", tourLogViewModel.getTourLogs().get(0).getComment());
    }

    // Test 5: Löschen eines Tour-Logs
    @Test
    public void testDeleteTourLog() {
        TourLog tourLog = new TourLog(tour);
        tourLog.setComment("Test Log");
        tourLogViewModel.addTourLog(tourLog);
        tourLogViewModel.setSelectedTourLog(tourLog);
        tourLogViewModel.deleteTourLog(tourLog);
        assertEquals(0, tourLogViewModel.getTourLogs().size()); // Überprüft, ob das Log aus der Liste entfernt wurde
        assertNull(tourLogViewModel.getSelectedTourLog()); // Überprüft, ob selectedTourLog auf null gesetzt wurde
    }
}