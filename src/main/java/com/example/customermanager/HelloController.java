package com.example.customermanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> provinceBox;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> provinceColumn;


    private ObservableList<Customer> customers =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // Add provinces to the ComboBox
        provinceBox.getItems().addAll(
                "Central",
                "Copperbelt",
                "Eastern",
                "Luapula",
                "Lusaka",
                "Muchinga",
                "Northern",
                "North-Western",
                "Southern",
                "Western"
        );


        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        provinceColumn.setCellValueFactory(
                new PropertyValueFactory<>("province")
        );


        customerTable.setItems(customers);
    }


    @FXML
    public void addCustomer() {

        String name = nameField.getText().trim();
        String province = provinceBox.getValue();

        // Validate input
        if (name.isEmpty() || province == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Invalid Input");
            alert.setHeaderText("Missing Information");

            alert.setContentText(
                    "Please enter the customer's name "
                            + "and select a province."
            );

            alert.showAndWait();

            return;
        }


        Customer customer = new Customer(name, province);


        customers.add(customer);


        nameField.clear();
        provinceBox.getSelectionModel().clearSelection();
    }


    @FXML
    public void deleteCustomer() {


        Customer selectedCustomer =
                customerTable.getSelectionModel().getSelectedItem();


        if (selectedCustomer == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("No Customer Selected");
            alert.setHeaderText("No Customer Selected");

            alert.setContentText(
                    "Please select a customer to delete."
            );

            alert.showAndWait();

            return;
        }


        Alert confirmation = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        confirmation.setTitle("Delete Customer");
        confirmation.setHeaderText("Delete Customer");

        confirmation.setContentText(
                "Are you sure you want to delete "
                        + selectedCustomer.getName() + "?"
        );

        confirmation.showAndWait().ifPresent(response -> {

            if (response == ButtonType.OK) {


                customers.remove(selectedCustomer);
            }
        });
    }
}