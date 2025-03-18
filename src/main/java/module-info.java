module at.fhtw.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;

    // Öffnet die Pakete für JavaFX
    opens at.fhtw.tourplanner.controller to javafx.fxml;
    opens at.fhtw.tourplanner.model to javafx.base;
    opens at.fhtw.tourplanner.viewModel to javafx.base;
    opens at.fhtw.tourplanner to javafx.fxml;

    // Exportiert die Pakete
    exports at.fhtw.tourplanner;
    exports at.fhtw.tourplanner.controller;
    exports at.fhtw.tourplanner.model;
    exports at.fhtw.tourplanner.viewModel;
}