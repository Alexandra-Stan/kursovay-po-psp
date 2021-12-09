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

public class ControllerUserWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button searchButton;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private Button resultButton;

    @FXML
    private TextField sumField;

    @FXML
    private TextField discField;

    @FXML
    private TextField resultField;

    @FXML
    private Button outButton;

    @FXML
    private Button updateData;

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
    private Button addProductInImportButton;


    private final ObservableList<Discount> listDiscounts = FXCollections.observableArrayList();


    @FXML
    void initialize() {

        try {
            initUsers(Client.interactionsWithServer.allDiscounts());
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultButton.setOnAction(event -> {
            double sum = Double.parseDouble(sumField.getText());
            double disc = Double.parseDouble(discField.getText());
            double result =sum - (sum*(disc/100));
            resultField.setText(String.valueOf(result));
        });

        updateData.setOnAction(event -> {
            try {
                initUsers(Client.interactionsWithServer.allDiscounts());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addProductInImportButton.setOnAction(event -> {
            int count = discountsTable.getSelectionModel().getSelectedCells().get(0).getRow();
            try {
                FileAddData.fileAddData.addInFile(listDiscounts.get(count));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        searchButton.setOnAction(event -> {
            initUsers(searchUser());

        });


        outButton.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(outButton,"../views/login.fxml");

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

    private LinkedList<Discount> searchUser(){
        String search = textFieldSearch.getText();
        LinkedList<Discount> discountSearches = new LinkedList<>();
        for(Discount discount : listDiscounts){
            if(search.equals(discount.getName())){
                discountSearches.add(discount);
            }

        }
        return discountSearches;
    }
}
