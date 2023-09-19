import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Controller {

    private Scene scene;
    private Stage stage;
    private Parent root;
    private Scene previousScene;

    @FXML
    private TextField companyName;
    @FXML
    private TextField companyAddress;
    @FXML
    private TextField industry;
    @FXML
    private TextField foundingDate;

    @FXML
    private Label dateLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label employeesLabel;
    @FXML
    private Label industryLabel;


    public void submitCompany(ActionEvent e) {
        DBFunctions.addCompany(companyName.getText(), companyAddress.getText(), industry.getText(), foundingDate.getText());
        companyName.clear();
        companyAddress.clear();
        industry.clear();
        foundingDate.clear();

    }

    public void addCompany(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addCompanyWindow.fxml")));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        previousScene = scene;
        stage.setScene(scene);
        stage.show();
    }

    public void viewCompanies(ActionEvent e) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewCompaniesWindow.fxml"));
        root = loader.load();
        ViewCompaniesController viewController = loader.getController();

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void backToMainScreen(ActionEvent e) throws IOException {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(previousScene);
        stage.show();
        System.out.println("pressed");
    }


    public void showErrorPopup(ActionEvent e, String errorMessage) {

    }


}

