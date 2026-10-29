package org.example.campsiteca1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class areaController implements Initializable {

    //Area
    @FXML private ComboBox<Campsite> areaCampsiteCombo;
    @FXML private TextField areaIdField;
    @FXML private TextArea areaDescriptionArea;

    //Plots
    @FXML private TreeView<String> campsiteTree;
    @FXML private ComboBox<String> plotAreaCombo;
    @FXML private ComboBox<String> plotTypeCombo;
    @FXML private Spinner<Integer> bedroomsSpinner;
    @FXML private Spinner<Integer> bedsSpinner;
    @FXML private CheckBox powerCheck;
    @FXML private CheckBox airConCheck;
    @FXML private CheckBox terraceCheck;
    @FXML private CheckBox heatingCheck;
    @FXML private CheckBox kitchenCheck;
    @FXML private CheckBox bathroomCheck;
    @FXML private CheckBox tvCheck;
    @FXML private Button clearAreaButton;
    @FXML private Button saveAreaButton;
    @FXML private Button removePlotButton;
    @FXML private Button backButton;
    @FXML private Button savePlotButton;
    @FXML private TextField photoUrlField;
    @FXML private TextField sizeField;
    @FXML private TextField plotIDField;
    @FXML private TextField costField;

   public void backButtonAction(ActionEvent event) throws IOException {
        switchScene(event, "campsite-view.fxml");
    }

    private void switchScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Campsite c : DataStore.get().getCampsites()) {

            areaCampsiteCombo.getItems().add(c);
        }
        refreshTree(null);
    }

    private void refreshTree(Campsite toExpand) {
        TreeItem<String> root = new TreeItem<>("Campsites");
        root.setExpanded(true);

        for (Campsite c : DataStore.get().getCampsites()) {
            TreeItem<String> campsiteItem = new TreeItem<>(c.getName());
            campsiteItem.setExpanded(c == toExpand);   // keep the one you just edited open

            campsiteItem.getChildren().add(new TreeItem<>("Address: " + c.getAddress()));
            campsiteItem.getChildren().add(new TreeItem<>("Phone: " + c.getPhone()));
            campsiteItem.getChildren().add(new TreeItem<>("Stars: " + c.getStars()));

            TreeItem<String> facilitiesItem = new TreeItem<>("Facilities");
            for (String f : c.getFacilities()) {
                facilitiesItem.getChildren().add(new TreeItem<>(f));
            }
            campsiteItem.getChildren().add(facilitiesItem);

            TreeItem<String> areasItem = new TreeItem<>("Areas");
            areasItem.setExpanded(true);
            for (Area a : c.getAreas()) {
                TreeItem<String> areaItem = new TreeItem<>(a.getIdentifier());
                areaItem.getChildren().add(new TreeItem<>("Description: " + a.getDescription()));
                areasItem.getChildren().add(areaItem);
            }
            campsiteItem.getChildren().add(areasItem);

            root.getChildren().add(campsiteItem);
        }

        campsiteTree.setRoot(root);
    }

    public void saveArea(ActionEvent event) {
        Campsite campsite = areaCampsiteCombo.getValue();
        String identifier = areaIdField.getText().trim();
        String description = areaDescriptionArea.getText().trim();

        if (campsite == null) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please select a campsite.");
            return;
        }
        if (identifier.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter an area id.");
            return;
        }
        if (description.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter a description.");
            return;
        }
        // optional: stop duplicate ids within the same campsite
        if (campsite.getAreas().find(a -> a.getIdentifier().equalsIgnoreCase(identifier)) != null) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "That campsite already has an area with this id.");
            return;
        }

        campsite.addArea(new Area(identifier, description));

        areaIdField.clear();
        areaDescriptionArea.clear();
        refreshTree(campsite);
    }
    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
