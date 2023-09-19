import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewCompaniesController implements Initializable {

    @FXML ChoiceBox<String> companiesChoiceBox;
    private List<String> companiesNamesList = new ArrayList<>();




    public void loadCompanies(List<String> companiesNamesList){
        companiesChoiceBox.getItems().addAll(companiesNamesList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        companiesChoiceBox.getItems().addAll(companiesNamesList);
    }
}