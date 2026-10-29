package org.example.campsiteca1;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class campsiteController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button areasPlotsButton;
    @FXML
    private Button saveCampsiteButton;
    @FXML
    private Button clearButton;

    @FXML
    public TextField nameField;
    @FXML
    public TextField addressField;
    @FXML
    public Spinner<Integer> starsSpinner;
    @FXML
    public TextField phoneField;

    @FXML
    public CheckBox restaurantCheck;
    @FXML
    public CheckBox poolCheck;
    @FXML
    public CheckBox shopsCheck;
    @FXML
    public CheckBox spaCheck;
    @FXML
    public CheckBox gymCheck;
    @FXML
    public CheckBox wifiCheck;
    @FXML
    public CheckBox laundryCheck;
    @FXML
    public CheckBox medicalCheck;

    @FXML
    private TableView<Campsite> campsiteTable;
    @FXML
    private TableColumn<Campsite, String> nameColumn;
    @FXML
    private TableColumn<Campsite, String> addressColumn;
    @FXML
    private TableColumn<Campsite, String> phoneColumn;
    @FXML
    private TableColumn<Campsite, Integer> starsColumn;
    @FXML
    private TableColumn<Campsite, String> featuresColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName())); //fuck oracle documentation https://stackoverflow.com/questions/13381067/simplestringproperty-and-simpleintegerproperty-tableview-javafx
        addressColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAddress()));
        phoneColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPhone()));
        starsColumn.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getStars()));//SimpleObjectProperty can hold a property that is mutable and complex. https://bugs.openjdk.org/browse/JDK-8117412
        featuresColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFacilitiesText()));

        campsiteTable.setItems(DataStore.get().getCampsiteItems());

        starsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1));
    }
    public void backToMain(ActionEvent event) throws IOException {
        switchScene(event, "main-menu.fxml");
    }

    public void gotoCampingArea(ActionEvent event) throws IOException {
        switchScene(event, "areas-plots-view.fxml");
    }

    private void switchScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void saveCampsite(ActionEvent event) {
        String name = nameField.getText().trim();//removes whitespace
        String address = addressField.getText().trim();
        String phone = phoneField.getText().trim();
        Integer stars = starsSpinner.getValue();


        if (name.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter a campsite name.");
            return;
        }
        if (address.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter an address.");
            return;
        }
        if (!phone.matches("\\d{10}")) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Phone number must be exactly 10 digits.");
            return;
        }
        if (stars == null) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please provide a rating (stars).");
            return;
        }
        CustomLinkedList<String> facilities = new CustomLinkedList<>();
        if (restaurantCheck.isSelected()) facilities.add(restaurantCheck.getText());
        if (poolCheck.isSelected()) facilities.add(poolCheck.getText());
        if (shopsCheck.isSelected()) facilities.add(shopsCheck.getText());
        if (spaCheck.isSelected()) facilities.add(spaCheck.getText());
        if (gymCheck.isSelected()) facilities.add(gymCheck.getText());
        if (wifiCheck.isSelected()) facilities.add(wifiCheck.getText());
        if (laundryCheck.isSelected()) facilities.add(laundryCheck.getText());
        if (medicalCheck.isSelected()) facilities.add(medicalCheck.getText());

        // Build the object and hand it to the shared store (custom list + table list)
        DataStore.get().addCampsite(new Campsite(name, address, phone, stars, facilities));
    }

    public void clearEverything(ActionEvent event) {
        nameField.clear();
        addressField.clear();
        phoneField.clear();
        starsSpinner.getValueFactory().setValue(0);
        restaurantCheck.setSelected(false);
        poolCheck.setSelected(false);
        shopsCheck.setSelected(false);
        spaCheck.setSelected(false);
        gymCheck.setSelected(false);
        wifiCheck.setSelected(false);
        laundryCheck.setSelected(false);
        medicalCheck.setSelected(false);
    }
    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}