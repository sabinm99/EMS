import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {

    private Scene scene;
    private Stage stage;
    private Parent root;
    private ArrayList<Company> companies = new ArrayList<>();
    @FXML
    private TextField companyName;
    @FXML
    private TextField companyAddress;
    @FXML
    private TextField industry;
    @FXML
    private TextField foundingDate;


    public void submitCompany(ActionEvent e) {
        Company company = new Company(companyName.getText(), companyAddress.getText(), industry.getText(), foundingDate.getText());
        companyName.clear();
        companyAddress.clear();
        industry.clear();
        foundingDate.clear();
        companies.add(company);
        System.out.println(companies);
    }


    @FXML

    public void addCompany(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addCompanyWindow.fxml")));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void listCompanies(){

    }

    public void backToMainScreen(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ui.fxml")));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    public void showErrorPopup (ActionEvent e, String errorMessage){

    }



}

