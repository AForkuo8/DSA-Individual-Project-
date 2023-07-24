package com.example.demo;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductsDAO {

    public static Products searchProduct(String productName)throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM products WHERE product_name="+productName;

        try {
            ResultSet resProduct = DBUtil.dbExecuteQuery(selectStmt);

            Products product = getProductFromResultSet(resProduct);

            return product;
        }catch (SQLException e) {
            System.out.println("An error occurred while searching for the product: " + e);
            throw e;
        }
    }

    private static Products getProductFromResultSet(ResultSet rs) throws SQLException {
        Products product = null;
        if (rs.next()) {
            product = new Products();
            product.setProductName(rs.getString("PRODUCT_NAME"));
            product.setCategory(rs.getString("CATEGORY"));
            product.setQuantity(rs.getInt("QUANTITY"));
            product.setPrice(rs.getInt("PRICE"));
        }

        return product;
    }


    public static ObservableList<Products> searchProducts() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM products";

        try {
            ResultSet resProducts = DBUtil.dbExecuteQuery(selectStmt);

            ObservableList<Products> productList = getProductList(resProducts);

            return productList;
        }catch (SQLException e) {
            System.out.println("SQL operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList getProductList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Products> productList = FXCollections.observableArrayList();

        while (rs.next()) {
            Products products = new Products();
            products.setProductName(rs.getString("PRODUCT_NAME"));
            products.setCategory(rs.getString("CATEGORY"));
            products.setQuantity(rs.getInt("QUANTITY"));
            products.setPrice(rs.getInt("PRICE"));

            productList.add(products);
        }
        return productList;
    }

    public static void removeProduct (String productName) throws SQLException, ClassNotFoundException {
        String removeStmt =
                "BEGIN\n" +
                        " DELETE FROM products\n" +
                        " WHERE PRODCUT_NAME ="+ productName +";\n" +
                        " COMMIT;\n" +
                        "END;";

        try {
            DBUtil.dbExecuteUpdate(removeStmt);
        }catch (SQLException e) {
            System.out.println("Error occurred while Deleting the product: " + e);
            throw e;
        }
    }


}
