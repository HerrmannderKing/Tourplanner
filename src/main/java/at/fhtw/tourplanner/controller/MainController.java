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
        // Bindet die TourListe an die ListView
        tourList.setItems(tourViewModel.getTours());

        // Bindet die TourLogListe an die ListView
        tourLogList.setItems(tourLogViewModel.getTourLogs());

        tourList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            tourViewModel.setSelectedTour(newVal);
            if (newVal != null) {
                // Füllt die Felder mit den Daten der Tour
                nameField.setText(newVal.getName());
                descriptionField.setText(newVal.getDescription());
                fromField.setText(newVal.getFrom());
                toField.setText(newVal.getTo());
                transportTypeField.setText(newVal.getTransportType());
                distanceField.setText(String.valueOf(newVal.getDistance()));
                estimatedTimeField.setText(newVal.getEstimatedTime());
                routeImageField.setText(newVal.getRouteImage());

                tourLogViewModel.setSelectedTour(newVal);
            }
        });

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
            // Ensure the selected tour is not null
            Tour selectedTour = tourViewModel.getSelectedTour();
            if (selectedTour == null) {
                showErrorAlert("Kein Tour ausgewählt", "Bitte wählen Sie eine Tour zum Bearbeiten aus.");
                return;
            }

            // Modify the selected tour directly with the data from the input fields
            selectedTour.setName(nameField.getText());
            selectedTour.setDescription(descriptionField.getText());
            selectedTour.setFrom(fromField.getText());
            selectedTour.setTo(toField.getText());
            selectedTour.setTransportType(transportTypeField.getText());

            // Validate and set the distance
            try {
                double distance = Double.parseDouble(distanceField.getText());
                selectedTour.setDistance(distance);
            } catch (NumberFormatException e) {
                showErrorAlert("Ungültige Eingabe", "Die Distanz muss eine Zahl sein.");
                return; // If invalid, exit the method.
            }

            // Set the estimated time
            selectedTour.setEstimatedTime(estimatedTimeField.getText());

            // Set the route image if it's not empty
            selectedTour.setRouteImage(routeImageField.getText());

            // Update the list of tours by calling the ViewModel's updateTour method
            tourViewModel.updateTour(selectedTour);  // This updates the tour in the list

            // Optionally, clear the input fields
            clearTourInputFields();

        } catch (Exception e) {
            showErrorAlert("Fehler", "Es gab einen Fehler beim Bearbeiten der Tour.");
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
        Tour selectedTour = tourViewModel.getSelectedTour();
        if (selectedTour == null) {
            showErrorAlert("Fehlende Tour", "Bitte wählen Sie zuerst eine Tour aus.");
            return;
        }

        try {
            // Retrieve data from the input fields
            String comment = logCommentField.getText();
            int difficulty = Integer.parseInt(logDifficultyField.getText());
            double totalDistance = Double.parseDouble(logDistanceField.getText());
            double totalTime = Double.parseDouble(logTimeField.getText());
            int rating = Integer.parseInt(logRatingField.getText());

            // Create the new TourLog with the user input
            TourLog newTourLog = new TourLog(selectedTour);
            newTourLog.setDateTime(LocalDateTime.now());
            newTourLog.setComment(comment);
            newTourLog.setDifficulty(difficulty);
            newTourLog.setTotalDistance(totalDistance);
            newTourLog.setTotalTime(totalTime);
            newTourLog.setRating(rating);

            // Add the TourLog to the selected tour
            selectedTour.addTourLog(newTourLog);

            // Update the TourLogViewModel to reflect the added log
            tourLogViewModel.updateTourLogs();

            // Clear the input fields
            clearTourLogInputFields();
        } catch (NumberFormatException e) {
            showErrorAlert("Ungültige Eingabe", "Schwierigkeit, Distanz, Zeit und Bewertung müssen Zahlen sein.");
        }
    }

    @FXML
    private void handleEditTourLog() {
        try {
            // Ensure the selected tour log is not null
            TourLog selectedTourLog = tourLogViewModel.getSelectedTourLog();
            if (selectedTourLog == null) {
                showErrorAlert("Kein Tour-Log ausgewählt", "Bitte wählen Sie ein Tour-Log zum Bearbeiten aus.");
                return;
            }

            // Modify the selected tour log directly with the data from the input fields
            selectedTourLog.setComment(logCommentField.getText());

            // Validate and set the difficulty
            try {
                int difficulty = Integer.parseInt(logDifficultyField.getText());
                selectedTourLog.setDifficulty(difficulty);
            } catch (NumberFormatException e) {
                showErrorAlert("Ungültige Eingabe", "Die Schwierigkeit muss eine Zahl sein.");
                return; // If invalid, exit the method.
            }

            // Validate and set the total distance
            try {
                double totalDistance = Double.parseDouble(logDistanceField.getText());
                selectedTourLog.setTotalDistance(totalDistance);
            } catch (NumberFormatException e) {
                showErrorAlert("Ungültige Eingabe", "Die Distanz muss eine Zahl sein.");
                return; // If invalid, exit the method.
            }

            // Validate and set the total time
            try {
                double totalTime = Double.parseDouble(logTimeField.getText());
                selectedTourLog.setTotalTime(totalTime);
            } catch (NumberFormatException e) {
                showErrorAlert("Ungültige Eingabe", "Die Zeit muss eine Zahl sein.");
                return; // If invalid, exit the method.
            }

            // Validate and set the rating
            try {
                int rating = Integer.parseInt(logRatingField.getText());
                selectedTourLog.setRating(rating);
            } catch (NumberFormatException e) {
                showErrorAlert("Ungültige Eingabe", "Die Bewertung muss eine Zahl sein.");
                return; // If invalid, exit the method.
            }

            // Update the list of tour logs by calling the ViewModel's updateTourLogs method
            tourLogViewModel.updateTourLogs();  // This updates the tour logs list

            // Optionally, clear the input fields
            clearTourLogInputFields();

        } catch (Exception e) {
            showErrorAlert("Fehler", "Es gab einen Fehler beim Bearbeiten des Tour-Logs.");
        }
    }

    @FXML
    private void handleDeleteTourLog() {
        // Löscht das ausgewählte Tour-Log
        TourLog selectedTourLog = tourLogViewModel.getSelectedTourLog();  // Use the method to get selectedTourLog
        if (selectedTourLog != null) {
            tourLogViewModel.deleteTourLog(selectedTourLog);
            clearTourLogInputFields();
        } else {
            showErrorAlert("Kein Log ausgewählt", "Bitte wählen Sie ein Tour-Log zum Löschen aus.");
        }
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
        // schaut ob alle Felder ausgefüllt sind
        if (logCommentField.getText().isEmpty() || logDifficultyField.getText().isEmpty() ||
                logDistanceField.getText().isEmpty() || logTimeField.getText().isEmpty() ||
                logRatingField.getText().isEmpty()) {
            showErrorAlert("Fehlende Eingabe", "Bitte füllen Sie alle Felder aus.");
            return null;
        }

        //probiert die Eingaben in Zahlen umzuwandeln
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

        Tour selectedTour = tourViewModel.getSelectedTour();

        //macht ein neues TourLog aus den Eingabefeldern
        TourLog tourLog = new TourLog(selectedTour);
        tourLog.setDateTime(LocalDateTime.now());
        tourLog.setComment(logCommentField.getText());
        tourLog.setDifficulty(difficulty);
        tourLog.setTotalDistance(totalDistance);
        tourLog.setTotalTime(totalTime);
        tourLog.setRating(rating);

        return tourLog;
    }

    private void clearTourInputFields() {
        // macht die Eingabefelder für Touren leer
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
        logCommentField.clear();
        logDifficultyField.clear();
        logDistanceField.clear();
        logTimeField.clear();
        logRatingField.clear();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}