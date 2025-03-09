package at.tourplanner.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.tourplanner.model.Tour;

public class TourViewModel {
    private final ObservableList<Tour> tours = FXCollections.observableArrayList();
    private final ObjectProperty<Tour> selectedTour = new SimpleObjectProperty<>();

    // Getter für die Tour-Liste
    public ObservableList<Tour> getTours() {
        return tours;
    }

    // Getter für die ausgewählte Tour
    public ObjectProperty<Tour> selectedTourProperty() {
        return selectedTour;
    }

    // Methode zum Hinzufügen einer Tour
    public void addTour(Tour tour) {
        tours.add(tour);
    }

    // Methode zum Entfernen einer Tour
    public void removeTour(Tour tour) {
        tours.remove(tour);
    }
}