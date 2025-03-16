package at.fhtw.tourplanner.controller;

import at.fhtw.tourplanner.model.Tour;
import at.fhtw.tourplanner.model.TourLog;
import at.fhtw.tourplanner.viewModel.TourLogViewModel;
import at.fhtw.tourplanner.viewModel.TourViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDateTime;

public class MainController {
    @FXML
    private ListView<Tour> tourList;
    @FXML
    private ListView<TourLog> tourLogList;

    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;
    @FXML
    private TextField transportTypeField;
    @FXML
    private TextField distanceField;
    @FXML
    private TextField estimatedTimeField;
    @FXML
    private TextField routeImageField;

    @FXML
    private TextField logCommentField;
    @FXML
    private TextField logDifficultyField;
    @FXML
    private TextField logDistanceField;
    @FXML
    private TextField logTimeField;
    @FXML
    private TextField logRatingField;

    @FXML
    private ImageView mapPlaceholder;

    private final TourViewModel tourViewModel = new TourViewModel();
    private final TourLogViewModel tourLogViewModel = new TourLogViewModel();

    @FXML
    public void initialize() {
        // Bindet die Tour-Liste an die ListView
        tourList.setItems(tourViewModel.getTours());

        // Bindet die Tour-Log-Liste an die ListView
        tourLogList.setItems(tourLogViewModel.getTourLogs());

        // Listener für die Auswahl einer Tour
        tourList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            tourViewModel.setSelectedTour(newVal);
            if (newVal != null) {
                // Füllt die Felder mit den Daten der ausgewählten Tour
                nameField.setText(newVal.getName());
                descriptionField.setText(newVal.getDescription());
                fromField.setText(newVal.getFrom());
                toField.setText(newVal.getTo());
                transportTypeField.setText(newVal.getTransportType());
                distanceField.setText(String.valueOf(newVal.getDistance()));
                estimatedTimeField.setText(newVal.getEstimatedTime());
                routeImageField.setText(newVal.getRouteImage());

                // Lädt das Bild für die Karte
                if (newVal.getRouteImage() != null && !newVal.getRouteImage().isEmpty()) {
                    mapPlaceholder.setImage(new Image(newVal.getRouteImage()));
                } else {
                    mapPlaceholder.setImage(null);
                }
            }
        });

        // Listener für die Auswahl eines Tour-Logs
        tourLogList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            tourLogViewModel.setSelectedTourLog(newVal);
            if (newVal != null) {
                // Füllt die Felder mit den Daten des ausgewählten Logs
                logCommentField.setText(newVal.getComment());
                logDifficultyField.setText(String.valueOf(newVal.getDifficulty()));
                logDistanceField.setText(String.valueOf(newVal.getTotalDistance()));
                logTimeField.setText(String.valueOf(newVal.getTotalTime()));
                logRatingField.setText(String.valueOf(newVal.getRating()));
            }
        });
    }

    @FXML
    private void handleAddTour() {
        try {
            // Erstellt eine neue Tour und fügt sie hinzu
            Tour newTour = createTourFromInput();
            if (newTour != null) {
                tourViewModel.addTour(newTour);
                clearTourInputFields();
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Ungültige Eingabe", "Die Distanz muss eine Zahl sein.");
        }
    }

    @FXML
    private void handleEditTour() {
        try {
            // Bearbeitet die ausgewählte Tour
            Tour updatedTour = createTourFromInput();
            if (updatedTour != null) {
                tourViewModel.editTour(updatedTour);
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Ungültige Eingabe", "Die Distanz muss eine Zahl sein.");
        }
    }

    @FXML
    private void handleDeleteTour() {
        // Löscht die ausgewählte Tour
        tourViewModel.deleteTour();
        clearTourInputFields();
    }

    @FXML
    private void handleAddTourLog() {
        try {
            // Erstellt ein neues Tour-Log und fügt es hinzu
            TourLog newTourLog = createTourLogFromInput();
            if (newTourLog != null) {
                tourLogViewModel.addTourLog(newTourLog);
                clearTourLogInputFields();
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Ungültige Eingabe", "Schwierigkeit, Distanz, Zeit und Bewertung müssen Zahlen sein.");
        }
    }

    @FXML
    private void handleEditTourLog() {
        try {
            // Bearbeitet das ausgewählte Tour-Log
            TourLog updatedTourLog = createTourLogFromInput();
            if (updatedTourLog != null) {
                tourLogViewModel.editTourLog(updatedTourLog);
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Ungültige Eingabe", "Schwierigkeit, Distanz, Zeit und Bewertung müssen Zahlen sein.");
        }
    }

    @FXML
    private void handleDeleteTourLog() {
        // Löscht das ausgewählte Tour-Log
        tourLogViewModel.deleteTourLog();
        clearTourLogInputFields();
    }

    private Tour createTourFromInput() throws NumberFormatException {
        // Überprüft, ob alle Felder ausgefüllt sind
        if (nameField.getText().isEmpty() || descriptionField.getText().isEmpty() ||
                fromField.getText().isEmpty() || toField.getText().isEmpty() ||
                transportTypeField.getText().isEmpty() || distanceField.getText().isEmpty() ||
                estimatedTimeField.getText().isEmpty() || routeImageField.getText().isEmpty()) {
            showErrorAlert("Fehlende Eingabe", "Bitte füllen Sie alle Felder aus.");
            return null;
        }

        // Versucht, die Distanz in eine Zahl umzuwandeln
        double distance;
        try {
            distance = Double.parseDouble(distanceField.getText());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ungültige Distanz-Eingabe");
        }

        // Erstellt eine neue Tour aus den Eingabefeldern
        Tour tour = new Tour();
        tour.setName(nameField.getText());
        tour.setDescription(descriptionField.getText());
        tour.setFrom(fromField.getText());
        tour.setTo(toField.getText());
        tour.setTransportType(transportTypeField.getText());
        tour.setDistance(distance);
        tour.setEstimatedTime(estimatedTimeField.getText());
        tour.setRouteImage(routeImageField.getText());

        return tour;
    }

    private TourLog createTourLogFromInput() throws NumberFormatException {
        // Überprüft, ob alle Felder ausgefüllt sind
        if (logCommentField.getText().isEmpty() || logDifficultyField.getText().isEmpty() ||
                logDistanceField.getText().isEmpty() || logTimeField.getText().isEmpty() ||
                logRatingField.getText().isEmpty()) {
            showErrorAlert("Fehlende Eingabe", "Bitte füllen Sie alle Felder aus.");
            return null;
        }

        // Versucht, die Eingaben in Zahlen umzuwandeln
        int difficulty;
        double totalDistance;
        double totalTime;
        int rating;
        try {
            difficulty = Integer.parseInt(logDifficultyField.getText());
            totalDistance = Double.parseDouble(logDistanceField.getText());
            totalTime = Double.parseDouble(logTimeField.getText());
            rating = Integer.parseInt(logRatingField.getText());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ungültige Eingabe");
        }

        // Erstellt ein neues Tour-Log aus den Eingabefeldern
        TourLog tourLog = new TourLog();
        tourLog.setDateTime(LocalDateTime.now());
        tourLog.setComment(logCommentField.getText());
        tourLog.setDifficulty(difficulty);
        tourLog.setTotalDistance(totalDistance);
        tourLog.setTotalTime(totalTime);
        tourLog.setRating(rating);

        return tourLog;
    }

    private void clearTourInputFields() {
        // Leert die Eingabefelder für Touren
        nameField.clear();
        descriptionField.clear();
        fromField.clear();
        toField.clear();
        transportTypeField.clear();
        distanceField.clear();
        estimatedTimeField.clear();
        routeImageField.clear();
    }

    private void clearTourLogInputFields() {
        // Leert die Eingabefelder für Tour-Logs
        logCommentField.clear();
        logDifficultyField.clear();
        logDistanceField.clear();
        logTimeField.clear();
        logRatingField.clear();
    }

    private void showErrorAlert(String title, String message) {
        // Zeigt eine Fehlermeldung an
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}