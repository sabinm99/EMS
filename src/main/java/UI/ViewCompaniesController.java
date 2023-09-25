package UI;

import JDBC.DBFunctions;

import Models.Company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewCompaniesController implements Initializable {

    @FXML
    ChoiceBox<String> companiesChoiceBox = new ChoiceBox<>();
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private Label dateLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label employeesLabel;
    @FXML
    private Label industryLabel;

    private static String currentName;

    public static void showErrorPopup(ActionEvent e, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorMessage);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            companiesChoiceBox.getItems().addAll(DBFunctions.viewCompanyNames());
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("miaspl " + e.getMessage());
        }

        companiesChoiceBox.setOnAction(this::displayInfo);


    }

    public void backToMainScreen(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayInfo(ActionEvent e) {

        if (DBFunctions.databaseHasRecords()) {
            try {
                Company company = DBFunctions.getCompanyByName(companiesChoiceBox.getValue());
                currentName = companiesChoiceBox.getValue();
                dateLabel.setText(company.getFoundingDate());
                addressLabel.setText(company.getAddress());
                industryLabel.setText(company.getIndustry());
            } catch (SQLException ex) {
                System.out.println("error: " + ex.getMessage());
            }
        }
    }

    public void deleteCompany(ActionEvent e) throws IOException {
        String toRemove = companiesChoiceBox.getValue();
        companiesChoiceBox.getItems().remove(companiesChoiceBox.getValue());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        DBFunctions.removeCompany(toRemove);
    }

    public static String getCurrentName(){
        return currentName;
    }

    public void switchToUpdateScene(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateWindow.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEmployees(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/employees.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}