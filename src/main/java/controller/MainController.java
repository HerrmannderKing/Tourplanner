package at.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import at.tourplanner.model.Tour;
import at.tourplanner.viewmodel.TourViewModel;

public class MainController {
    @FXML
    private ListView<Tour> tourList;

    private final TourViewModel viewModel = new TourViewModel();

    @FXML
    public void initialize() {
        // Bindet die Tour-Liste an die ListView
        tourList.setItems(viewModel.getTours());
    }

    @FXML
    private void handleAddTour() {
        // Erstellt eine neue Tour und fügt sie hinzu
        Tour newTour = new Tour();
        newTour.setName("New Tour");
        newTour.setDescription("Description");
        newTour.setFrom("Start");
        newTour.setTo("Destination");
        newTour.setTransportType("Bike");
        newTour.setDistance(10.5);
        newTour.setEstimatedTime("2 hours");
        newTour.setRouteImage("path/to/image.png");

        viewModel.addTour(newTour);
    }

    @FXML
    private void handleRemoveTour() {
        // Entfernt die ausgewählte Tour
        Tour selectedTour = tourList.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            viewModel.removeTour(selectedTour);
        }
    }
}