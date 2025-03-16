package at.fhtw.tourplanner.controller;

import at.fhtw.tourplanner.model.TourLog;
import at.fhtw.tourplanner.viewModel.TourLogViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.time.LocalDateTime;

public class TourLogController {
    @FXML
    private ListView<TourLog> tourLogList;

    private final TourLogViewModel viewModel = new TourLogViewModel();

    @FXML
    public void initialize() {
        // Bindet die Tour-Log-Liste an die ListView
        tourLogList.setItems(viewModel.getTourLogs());
    }

    @FXML
    private void handleAddTourLog() {
        // Erstellt ein neues Tour-Log und fügt es hinzu
        TourLog newTourLog = createDefaultTourLog();
        viewModel.addTourLog(newTourLog);
    }

    @FXML
    private void handleRemoveTourLog() {
        // Entfernt das ausgewählte Tour-Log
        TourLog selectedTourLog = tourLogList.getSelectionModel().getSelectedItem();
        if (selectedTourLog != null) {
            viewModel.removeTourLog(selectedTourLog);
        }
    }

    private TourLog createDefaultTourLog() {
        // Erstellt ein neues Tour-Log mit Standardwerten
        TourLog newTourLog = new TourLog();
        newTourLog.setDateTime(LocalDateTime.now());
        newTourLog.setComment("New Log");
        newTourLog.setDifficulty(3);
        newTourLog.setTotalDistance(15.0);
        newTourLog.setTotalTime(2.5);
        newTourLog.setRating(4);

        return newTourLog;
    }
}