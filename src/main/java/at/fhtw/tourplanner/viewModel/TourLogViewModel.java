package at.fhtw.tourplanner.viewModel;

import at.fhtw.tourplanner.model.Tour;
import at.fhtw.tourplanner.model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.ArrayList;

public class TourLogViewModel {
    private final ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();
    private Tour selectedTour;
    private TourLog selectedTourLog;

    public ObservableList<TourLog> getTourLogs() {
        return tourLogs;
    }

    public Tour getSelectedTour() {
        return selectedTour;
    }

    public void setSelectedTour(Tour selectedTour) {
        this.selectedTour = selectedTour;
        updateTourLogs();
    }

    public TourLog getSelectedTourLog() {
        return selectedTourLog;
    }

    public void setSelectedTourLog(TourLog selectedTourLog) {
        this.selectedTourLog = selectedTourLog;
    }

    public void updateTourLogs() {
        if (selectedTour != null) {
            // Fetch the logs for the selected tour
            tourLogs.setAll(selectedTour.getTourLogs());
        } else {
            tourLogs.clear();
        }
    }

    private List<TourLog> getLogsForTour(Tour tour) {
        // Replace this with your logic to fetch logs for the given tour
        return new ArrayList<>();
    }

    public void addTourLog(TourLog tourLog) {
        if (selectedTour != null) {
            selectedTour.addTourLog(tourLog);
            updateTourLogs();
        }
    }

    public void editTourLog(TourLog updatedTourLog) {
        if (selectedTourLog != null) {
            selectedTourLog.setDateTime(updatedTourLog.getDateTime());
            selectedTourLog.setComment(updatedTourLog.getComment());
            selectedTourLog.setDifficulty(updatedTourLog.getDifficulty());
            selectedTourLog.setTotalDistance(updatedTourLog.getTotalDistance());
            selectedTourLog.setTotalTime(updatedTourLog.getTotalTime());
            selectedTourLog.setRating(updatedTourLog.getRating());
        }
    }

    public void removeTourLog(TourLog tourLog) {
        tourLogs.remove(tourLog);
    }

    public void deleteTourLog(TourLog tourLog) {
        if (tourLog != null && selectedTour != null) {
            selectedTour.removeTourLog(tourLog);
            updateTourLogs();
        }
    }
}