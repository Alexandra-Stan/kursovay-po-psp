package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import clientActions.Client;
import fileConnection.FileAddData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Discount;
import windowsAlert.NewWindowOpen;

public class ControllerAdminWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Discount> discountsTable;

    @FXML
    private TableColumn<Discount, Integer> idTab;

    @FXML
    private TableColumn<Discount, String> nameTab;

    @FXML
    private TableColumn<Discount, String> surnameTab;

    @FXML
    private TableColumn<Discount, String > dnumberTab;

    @FXML
    private TableColumn<Discount, String> shopNameTab;

    @FXML
    private Button addDiscountButton;

    @FXML
    private Button deleteDiscountButton;

    @FXML
    private Button addInFileButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchText;


    @FXML
    private Button redactionFieldButton;

    @FXML
    private Button chooseButton;

    @FXML
    private Button updateButton;
    @FXML
    private Button outButton;
    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField dnumberField;

    @FXML
    private TextField shopNameProduct;



    private final ObservableList<Discount> listDiscounts = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        initTextField();

        outButton.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(outButton,"../views/login.fxml");
        });


        updateButton.setOnAction(event -> {
            try {
                initUsers(Client.interactionsWithServer.allDiscounts());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            initUsers(Client.interactionsWithServer.allDiscounts());
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchButton.setOnAction(event -> {
            initUsers(searchUser());

        });
        deleteDiscountButton.setOnAction(event -> {
            int count = discountsTable.getSelectionModel().getSelectedCells().get(0).getRow();

            Client.interactionsWithServer.deleteDiscount(listDiscounts.get(count).getId());
            try {
                initUsers(Client.interactionsWithServer.allDiscounts());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addInFileButton.setOnAction(event -> {
            int count = discountsTable.getSelectionModel().getSelectedCells().get(0).getRow();
            try {
                FileAddData.fileAddData.addInFile(listDiscounts.get(count));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        redactionFieldButton.setOnAction(event -> {
            int count = discountsTable.getSelectionModel().getSelectedCells().get(0).getRow();

            Client.interactionsWithServer.redactionDiscount(listDiscounts.get(count).getId(),nameField.getText(), surnameField.getText(),dnumberField.getText(),shopNameProduct.getText());
            try {
                initUsers(Client.interactionsWithServer.allDiscounts());
            } catch (IOException e) {
                e.printStackTrace();
            }
            initTextField();
        });

        chooseButton.setOnAction(event -> {
            int count = discountsTable.getSelectionModel().getSelectedCells().get(0).getRow();
            System.out.println(count);
            nameField.setText(listDiscounts.get(count).getName());
            surnameField.setText(listDiscounts.get(count).getSurname());
            dnumberField.setText(listDiscounts.get(count).getDnumber());
            shopNameProduct.setText(listDiscounts.get(count).getShopName());


        });
        addDiscountButton.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(addDiscountButton,"../views/addNewDiscount.fxml");
          });


    }

    private void initUsers(LinkedList<Discount> discounts){

        listDiscounts.clear();
        listDiscounts.addAll(discounts);

        idTab.setCellValueFactory(new PropertyValueFactory<Discount, Integer>("id"));
        nameTab.setCellValueFactory(new PropertyValueFactory<Discount, String>("name"));
        surnameTab.setCellValueFactory(new PropertyValueFactory<Discount, String>("surname"));
        dnumberTab.setCellValueFactory(new PropertyValueFactory<Discount, String>("dnumber"));
        shopNameTab.setCellValueFactory(new PropertyValueFactory<Discount, String>("shopName"));

        discountsTable.setItems(listDiscounts);
    }
    private void initTextField(){
        nameField.setText("");
        surnameField.setText("");
        dnumberField.setText("");
        shopNameProduct.setText("");



    }

    private LinkedList<Discount> searchUser(){
        String search = searchText.getText();
        LinkedList<Discount> discountSearches = new LinkedList<>();
        for(Discount discount : listDiscounts){
            if(search.equals(discount.getName())){
                discountSearches.add(discount);
            }

        }
         return discountSearches;
    }



}
