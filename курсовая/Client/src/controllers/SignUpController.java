package controllers;


import java.net.URL;
import java.util.ResourceBundle;

import clientActions.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import windowsAlert.AlertWindow;
import windowsAlert.NewWindowOpen;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField familInput;

    @FXML
    private TextField loginInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private Button signUpButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {

     signUpButton.setOnAction(event ->{
                 Client.interactionsWithServer.addNewUserInDataBase(nameInput.getText(), familInput.getText(),loginInput.getText(),passwordInput.getText());
                 AlertWindow.alertWindow.alertWindow("Пользователь добавлен!");
     }
        );

        exitButton.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(exitButton,"../views/login.fxml");
        });


    }
}