package org.example.campsiteca1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class guestController {
    @FXML
    private Button backButton;
    @FXML
    private TextField guestNameField;
    @FXML
    private DatePicker guestDobPicker;
    @FXML
    private TextField guestAddressField;
    @FXML
    private TextField guestPhoneField;
    @FXML
    private TextField guestEmailField;
    @FXML
    private TextArea guestNotesArea;
    @FXML
    private Button clearGuestButton;
    @FXML
    private Button saveGuestButton;
    @FXML
    private TextField lookupidField;
    @FXML
    private TableView guestBookingsTable;
    @FXML
    private TextField bookingGuestField;
    @FXML
    private ComboBox<String> bookingAreaCombo;
    @FXML
    private DatePicker bookingStartPicker;
    @FXML
    private Spinner<Integer> bookingGuestsSpinner;
    @FXML
    private TextField bookingMinSizeField;
    @FXML
    private CheckBox bookingPowerCheck;
    @FXML
    private ComboBox<String> bookingCampsiteCombo;
    @FXML
    private ComboBox<String> bookingTypeCombo;
    @FXML
    private DatePicker bookingEndPicker;
    @FXML
    private Spinner<Integer> bookingBedroomsSpinner;
    @FXML
    private TextField bookingBudgetField;
    @FXML
    private Button clearBookingButton;
    @FXML
    private Button saveBookingButton;
    @FXML
    private ComboBox<String> pendingCampsiteCombo;
    @FXML
    private TreeTableView pendingTable;
    @FXML
    private Label pendingTotalLabel;
    @FXML
    private Button cancelBookingButton;
    @FXML
    private DatePicker findStartPicker;
    @FXML
    private DatePicker findEndPicker;
    @FXML
    private Spinner<Integer> findGuestsSpinner;
    @FXML
    private Spinner<Integer> findStarsSpinner;
    @FXML
    private TextField findBudgetField;
    @FXML
    private CheckBox findRestaurantCheck;
    @FXML
    private CheckBox findPoolCheck;
    @FXML
    private CheckBox findShopsCheck;
    @FXML
    private CheckBox findSpaCheck;
    @FXML
    private CheckBox findGymCheck;
    @FXML
    private CheckBox findWifiCheck;
    @FXML
    private CheckBox findLaundryCheck;
    @FXML
    private CheckBox findMedicalCheck;
    @FXML
    private CheckBox findAirConCheck;
    @FXML
    private CheckBox findPowerCheck;
    @FXML
    private Button smartFindButton;
    @FXML
    private Button bookMatchButton;

    private Stage stage3;
    private Scene scene3;

    public static void main(String[] args) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        // Add elements
        list.add(10);
        list.add(20);
        list.addFirst(5);  // Head insertion

        // List now: 5 → 10 → 20

        System.out.println(list.contains(10));  // true
        list.delete(10);

        list.deleteAt(1);  // Removes 5
    }

    public void setBackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-menu.fxml")));
        stage3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene3 = new Scene(root);
        stage3.setScene(scene3);
        stage3.show();
    }


}
