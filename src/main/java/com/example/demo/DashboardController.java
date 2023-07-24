package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class DashboardController {

    @FXML
    private TableColumn<Products, String> pdCategoryColumn;

    @FXML
    private TableColumn<Products, Integer> pdIDColumn;

    @FXML
    private TableColumn<Products, String> pdNameColumn;

    @FXML
    private TableColumn<Products, Integer> pdPriceColumn;

    @FXML
    private TableColumn<Products, Integer> pdQuantityColumn;

    @FXML
    private Button products;

    @FXML
    private AnchorPane products_view;

    @FXML
    private Button remove_btn;

    @FXML
    private TextField search;

    @FXML
    private Button search_btn;

    @FXML
    private TableView<Products> products_table;


    public void switchForm(ActionEvent event){
        if (event.getSource() == dashboard){
            dashboard_view.setVisible(true);
            products_view.setVisible(false);
            add_item_view.setVisible(false);
        } else if (event.getSource() == products) {
            dashboard_view.setVisible(false);
            products_view.setVisible(true);
            add_item_view.setVisible(false);
        } else if (event.getSource() == add_btn) {
            dashboard_view.setVisible(false);
            products_view.setVisible(false);
            add_item_view.setVisible(true);
        }
    }

    //Search an employee
    @FXML
    private void searchProdcut (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Employee information
            Products product = ProductsDAO.searchProduct(search.getText());
            //Populate Employee on TableView and Display on TextArea
            populateAndShowProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }




    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {

        pdIDColumn.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        pdNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        pdCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        pdQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        pdPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
    }

    //Populate Employee
    @FXML
    private void populateProduct (Products product) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Products> productData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        productData.add(product);
        //Set items to the employeeTable
        products_table.setItems(productData);
    }

    //Set Employee information to Text Area


    //Populate Employee for TableView and Display Employee on TextArea
    @FXML
    private void populateAndShowProduct(Products product) throws ClassNotFoundException {
        if (product != null) {
            populateProduct(product);
        }
    }

    //Populate Employees for TableView
    @FXML
    private void populateEmployees (ObservableList<Products> products) throws ClassNotFoundException {
        //Set items to the employeeTable
        products_table.setItems(products);
    }





    //Delete an employee with a given employee Id from DB
    @FXML
    private void removeProduct (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ProductsDAO.removeProduct(search.getText());

        } catch (SQLException e) {
            throw e;
        }
    }
}
