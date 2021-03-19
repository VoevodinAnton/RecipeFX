module com.netcracker.recipeproject.client.view {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.netcracker.recipeproject.client.view to javafx.fxml;
    exports com.netcracker.recipeproject.client.view;
}