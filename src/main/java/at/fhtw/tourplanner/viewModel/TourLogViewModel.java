package at.fhtw.tourplanner.viewModel;

import at.fhtw.tourplanner.model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourLogViewModel {
    private final ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();
    private TourLog selectedTourLog;

    public ObservableList<TourLog> getTourLogs() {
        return tourLogs;
    }

    public TourLog getSelectedTourLog() {
        return selectedTourLog;
    }

    public void setSelectedTourLog(TourLog selectedTourLog) {
        this.selectedTourLog = selectedTourLog;
    }

    public void addTourLog(TourLog tourLog) {
        tourLogs.add(tourLog);
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
        if (tourLog != null) {
            tourLogs.remove(tourLog);
            selectedTourLog = null;
        }
    }

    public void deleteTourLog() {
        if (selectedTourLog != null) {
            tourLogs.remove(selectedTourLog);
            selectedTourLog = null;
        }
    }
}