module at.fhtw.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.fhtw.tourplanner to javafx.fxml;
    exports at.fhtw.tourplanner;
}