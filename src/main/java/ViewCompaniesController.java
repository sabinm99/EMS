import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class ViewCompaniesController implements Initializable {

    @FXML ChoiceBox<String> companiesChoiceBox;


    public void loadCompanies(List<String> companiesNamesList){
        companiesChoiceBox.getItems().addAll(companiesNamesList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            companiesChoiceBox.getItems().addAll(DBFunctions.viewCompanyNames());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}