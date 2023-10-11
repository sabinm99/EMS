package ui;

import dao.DAO;
import models.Company;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField industryField;
    @FXML
    private Label companyNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        companyNameLabel.setText(ViewCompaniesController.getCurrentName());

        try {
            Company company = DAO.getCompanyByName(companyNameLabel.getText());
            nameField.setText(company.getName());
            addressField.setText(company.getAddress());
            dateField.setText(company.getFoundingDate());
            industryField.setText(company.getIndustry());
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void submitUpdate(){
        DAO.updateCompanyDetails(companyNameLabel.getText(),nameField.getText(),addressField.getText(),
                industryField.getText(),dateField.getText());
        nameField.clear();
        addressField.clear();
        industryField.clear();
        dateField.clear();
    }

    public void goBack(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewCompaniesWindow.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
