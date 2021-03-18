module com.netcracker.recipeproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.netcracker.recipeproject to javafx.fxml;
    exports com.netcracker.recipeproject;
}