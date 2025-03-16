package at.fhtw.tourplanner.service;

import at.fhtw.tourplanner.model.Tour;

import at.fhtw.tourplanner.viewModel.TourViewModel;

public class TourService {
    private final TourViewModel viewModel;

    public TourService(TourViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void addTour() {
        // Erstellt eine neue Tour
        Tour newTour = createDefaultTour();

        // Fügt die Tour dem ViewModel hinzu
        viewModel.addTour(newTour);
    }

    private Tour createDefaultTour() {
        // Erstellt eine neue Tour mit Standardwerten
        Tour newTour = new Tour();
        newTour.setName("New Tour");
        newTour.setDescription("Description");
        newTour.setFrom("Start");
        newTour.setTo("Destination");
        newTour.setTransportType("Bike");
        newTour.setDistance(10.5);
        newTour.setEstimatedTime("2 hours");
        newTour.setRouteImage("path/to/image.png");

        return newTour;
    }
}