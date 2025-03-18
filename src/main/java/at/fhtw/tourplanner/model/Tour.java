package at.fhtw.tourplanner.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tour {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();
    private final DoubleProperty distance = new SimpleDoubleProperty();
    private final StringProperty estimatedTime = new SimpleStringProperty();
    private final StringProperty routeImage = new SimpleStringProperty();
    private final ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();

    // Getter and Setter for Name
    public String getName() { return name.get(); }

    public void setName(String name) {this.name.set(name);}

    public StringProperty nameProperty() {return name;}

    // Getter and Setter for Description
    public String getDescription() {return description.get();}

    public void setDescription(String description) {this.description.set(description);}

    public StringProperty descriptionProperty() {return description;}

    // Getter and Setter for From
    public String getFrom() {return from.get();}

    public void setFrom(String from) {this.from.set(from);}

    public StringProperty fromProperty() {return from;}

    // Getter and Setter for To
    public String getTo() {return to.get();}

    public void setTo(String to) {this.to.set(to);}

    public StringProperty toProperty() {return to;}

    // Getter and Setter for TransportType
    public String getTransportType() {return transportType.get();}

    public void setTransportType(String transportType) {this.transportType.set(transportType);}

    public StringProperty transportTypeProperty() {return transportType;}

    // Getter and Setter for Distance
    public double getDistance() {return distance.get();}

    public void setDistance(double distance) {this.distance.set(distance);}

    public DoubleProperty distanceProperty() {return distance;}

    // Getter and Setter for EstimatedTime
    public String getEstimatedTime() {return estimatedTime.get();}

    public void setEstimatedTime(String estimatedTime) {this.estimatedTime.set(estimatedTime);}

    public StringProperty estimatedTimeProperty() {return estimatedTime;}

    // Getter and Setter for RouteImage
    public String getRouteImage() {return routeImage.get();}

    public void setRouteImage(String routeImage) {this.routeImage.set(routeImage);}

    public StringProperty routeImageProperty() {return routeImage;}

    public ObservableList<TourLog> getTourLogs() {
        return tourLogs;
    }

    // New: Add a tour log
    public void addTourLog(TourLog log) {
        tourLogs.add(log);
    }

    // New: Remove a tour log
    public void removeTourLog(TourLog log) {
        tourLogs.remove(log);
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s | %.2f km | %s | %s",
                name.get(), description.get(), from.get(), to.get(), transportType.get(),
                distance.get(), estimatedTime.get(), routeImage.get());
    }
}