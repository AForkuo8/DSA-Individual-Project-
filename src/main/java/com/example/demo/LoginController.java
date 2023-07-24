package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.sql.*;
import java.util.Objects;

public class LoginController {
    @FXML
    private AnchorPane ad_login;

    @FXML
    private AnchorPane ad_signup;

    @FXML
    private Button create_account;

    @FXML
    private Label emailError;

    @FXML
    private AnchorPane lg_left_side;

    @FXML
    private Button login_btn;

    @FXML
    private TextField login_email;

    @FXML
    private Text login_label;

    @FXML
    private PasswordField login_pass;

    @FXML
    private Label nameError;

    @FXML
    private Label passError;

    @FXML
    private PasswordField sign_up_conPass;

    @FXML
    private TextField sign_up_email;

    @FXML
    private TextField sign_up_name;

    @FXML
    private PasswordField sign_up_pass;

    @FXML
    private Label storeNameError;

    @FXML
    private TextField store_name;

    @FXML
    private Button to_login;

    @FXML
    private Button to_signup;


    @FXML
    private Button add_btn;

    @FXML
    private AnchorPane add_item_view;

    @FXML
    private Button dashboard;

    @FXML
    private AnchorPane dashboard_view;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane nav_bar;

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
    private TableView<?> table_view;

    @FXML
    private AnchorPane title_bar;

    @FXML
    private Label us;




    public void switchScreen(ActionEvent event){
        if (event.getSource() == to_login){
            ad_login.setVisible(true);
            ad_signup.setVisible(false);
        } else if (event.getSource() == to_signup) {
            ad_login.setVisible(false);
            ad_signup.setVisible(true);
        }
    }

    public void signUpBtnClick(ActionEvent event) throws SQLException, ClassNotFoundException {
        String username = sign_up_name.getText();
        String email = sign_up_email.getText();
        String storeName = store_name.getText();
        String password = sign_up_pass.getText();
        String confirmPassword = sign_up_conPass.getText();

        checkRegistrationRequirements();

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setStoreName(storeName);
        user.setPassword(password);

        DBUtil.dbConnect();
        String selectStmt = "SELECT * FROM users";

        ResultSet rsUser = DBUtil.dbExecuteQuery(selectStmt);

        Boolean userExist = false;

        while (rsUser.next()) {

        }

        String insertStmt = "INSERT INTO users (FULL_NAME, EMAIL, STORE_NAME, PASSWORD) VALUES ()";

    }




    public void createAccount() throws ClassNotFoundException {


        // checks to see if none of the fields in the sign up page is empty
        if (username.isBlank())
        {
            nameError.setText("Enter your full name!");
        } else if (email.equals("")) {
            emailError.setText("Email cannot be empty!");
        } else if (storeName.equals("")) {
            storeNameError.setText("Enter name of store");
        }else{
            if (!Objects.equals(password, confirmPassword) || password.equals("") || confirmPassword.equals("")){
                passError.setText("Password cannot be empty!");
            }
        }
        String query = "INSERT INTO user ('Full_name', 'Email', 'Store_name', 'Password' VALUES (?,?,?,?)";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            String DBurl = "jdbc:mysql://localhost:3306";
            String DBuser = "root";
            String DBpassword = "";

            Connection connection = DriverManager.getConnection(DBurl, DBuser, DBpassword);
        }catch (ClassNotFoundException e){e.printStackTrace();} catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void checkRegistrationRequirements()
    {
        if (sign_up_name.getText().equals(""))
        {
            nameError.setText("Your full name is required!");
        }
        if (sign_up_email.getText().equals(""))
        {
            emailError.setText("This field is required!");
        }
        if (store_name.getText().equals(""))
        {
            storeNameError.setText("This field is required!");
        }
        if (!Objects.equals(sign_up_pass.getText(), sign_up_conPass.getText()))
        {
            passError.setText("Password does not match!");
        }else if (sign_up_pass.getText().equals(""))
        {
            passError.setText("Password field is required!");
        }else if (sign_up_pass.getText().equals(""))
        {
            passError.setText("Password field is required!");
        }

    }

    public void close()
    {
        System.exit(0);
    }

    public void validateLogin() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306";
            String user = "root";
            String password = "";

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        DatabaseConnection connection = new DatabaseConnection();
        Connection conectDB = connection.getConnection();

        String verifyLogin = "SELECT COUNT(1) FROM users WHERE email = " + login_email.getText() + " AND password = " + login_pass.getText();

        try
        {
            Statement statement = conectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next())
            {
                if(queryResult.getInt(1) == 1)
                {
//                    warningMessage.setText("Welcome");
                }else
                {
//                    warningMessage.setText("Invalid Login. Please try again.");
                }
            }
        }catch (Exception ignored)
        {
            ignored.printStackTrace();
        }
    }


}