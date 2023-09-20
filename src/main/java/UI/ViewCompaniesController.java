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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewCompaniesController implements Initializable {

    @FXML
    ChoiceBox<String> companiesChoiceBox;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            companiesChoiceBox.getItems().addAll(DBFunctions.viewCompanyNames());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
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
        Company company = null;
        try {
            company = DBFunctions.getCompanyByName(companiesChoiceBox.getValue());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        dateLabel.setText(company.getFoundingDate());
        addressLabel.setText(company.getAddress());
        industryLabel.setText(company.getIndustry());
    }

}