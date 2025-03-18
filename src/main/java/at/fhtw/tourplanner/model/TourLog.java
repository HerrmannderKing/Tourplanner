package at.fhtw.tourplanner.model;

import java.time.LocalDateTime;

public class TourLog {
    private Tour associatedTour;
    private LocalDateTime dateTime;
    private String comment;
    private int difficulty;
    private double totalDistance;
    private double totalTime;
    private int rating;

    public TourLog(Tour associatedTour) {
        this.associatedTour = associatedTour;
        this.dateTime = LocalDateTime.now();
    }

    public Tour getAssociatedTour() {
        return associatedTour;
    }

    public void setAssociatedTour(Tour associatedTour) {
        this.associatedTour = associatedTour;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | Difficulty: %d | Distance: %.2f km | Time: %.2f h | Rating: %d",
                dateTime, comment, difficulty, totalDistance, totalTime, rating);
    }
}