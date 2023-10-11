package ui;

import dao.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Employee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesController implements Initializable {

    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private Pane detailsPane;
    @FXML
    private Label nameLabel;
    @FXML
    private Label salaryLabel;
    @FXML
    private Label managerLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label idLabel;


    public void goBack(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewCompaniesWindow.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
        employeesTable.setItems(DAO.getListOfEmployees(ViewCompaniesController.getCurrentName()));

        employeesTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> displayInfo(newValue));
    }

    /**
     * Method used to display the information of the selected employee in the JavaFX scene.
     */
    public void displayInfo(Employee employee) {
        detailsPane.setVisible(true);
        nameLabel.setText(employee.getFullName());
        salaryLabel.setText(String.valueOf(employee.getSalary()));
        statusLabel.setText(employee.getStatusAsString());
        idLabel.setText(String.valueOf(employee.getEmployeeID()));
    }

    public void deleteEmployee() {
        DAO.deleteEmployee(Integer.parseInt(idLabel.getText()));
        System.out.println("pressed!");
    }
}
