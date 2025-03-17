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
            // Update the properties of the selectedTour object directly
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
    public void updateTour(Tour updatedTour) {
        if (updatedTour != null) {
            // Find the tour in the list and update it
            int index = tours.indexOf(updatedTour);
            if (index >= 0) {
                tours.set(index, updatedTour);  // Update the list with the modified tour
            }
        }
    }


    public void deleteTour() {
        if (selectedTour != null) {
            tours.remove(selectedTour);
            selectedTour = null;
        }
    }
}