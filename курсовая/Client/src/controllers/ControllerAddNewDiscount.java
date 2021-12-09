package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import clientActions.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import windowsAlert.NewWindowOpen;

public class ControllerAddNewDiscount {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField dnumberField;

    @FXML
    private TextField shopNameField;


    @FXML
    private Button addButton;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            Client.interactionsWithServer.addNewDiscountInDataBase(nameField.getText(), surnameField.getText(),dnumberField.getText(),shopNameField.getText());
            NewWindowOpen.newWindowOpen.openWindow(addButton,"../views/adminWindow.fxml");
        });
    }
}
