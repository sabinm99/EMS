package ui;

import dao.DBFunctions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {



    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private TextField companyName;
    @FXML
    private TextField companyAddress;
    @FXML
    private TextField industry;
    @FXML
    private TextField foundingDate;

    /**
     *
     * Used to display a message to the user. (I.E. if there are no companies in the database.)
     */

    public static void showErrorPopup(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorMessage);
        alert.show();
    }

    public void submitCompany() {
        DBFunctions.addCompany(companyName.getText(), companyAddress.getText(), industry.getText(), foundingDate.getText());
        companyName.clear();
        companyAddress.clear();
        industry.clear();
        foundingDate.clear();

    }

    public void addCompany(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addCompanyWindow.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewCompanies(ActionEvent e) throws IOException {
        if (DBFunctions.databaseHasRecords()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewCompaniesWindow.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            showErrorPopup("Database has no records. Please add some before viewing!");
        }
    }

    public void backToMainScreen(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

