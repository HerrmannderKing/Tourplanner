package at.fhtw.tourplanner.viewModel;

import at.fhtw.tourplanner.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourViewModel {
    private final ObservableList<Tour> tours = FXCollections.observableArrayList();
    private Tour selectedTour;

    public ObservableList<Tour> getTours() {
        return tours;
    }

    public Tour getSelectedTour() {
        return selectedTour;
    }

    public void setSelectedTour(Tour selectedTour) {
        this.selectedTour = selectedTour;
    }

    public void addTour(Tour tour) {
        tours.add(tour);
    }

    public void editTour(Tour updatedTour) {
        if (selectedTour != null) {
            selectedTour.setName(updatedTour.getName());
            selectedTour.setDescription(updatedTour.getDescription());
            selectedTour.setFrom(updatedTour.getFrom());
            selectedTour.setTo(updatedTour.getTo());
            selectedTour.setTransportType(updatedTour.getTransportType());
            selectedTour.setDistance(updatedTour.getDistance());
            selectedTour.setEstimatedTime(updatedTour.getEstimatedTime());
            selectedTour.setRouteImage(updatedTour.getRouteImage());
        }
    }

    public void deleteTour() {
        if (selectedTour != null) {
            tours.remove(selectedTour);
            selectedTour = null;
        }
    }
}