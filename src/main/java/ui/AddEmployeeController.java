package ui;

import dao.CompanyDAO;
import dao.EmployeeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Employee;

import java.io.IOException;
import java.sql.SQLException;


public class AddEmployeeController {
    @FXML
    private TextField fnAdd;
    @FXML
    private TextField lnAdd;
    @FXML
    private TextField dateAdd;
    @FXML
    private TextField salaryAdd;
    private Parent root;
    private Scene scene;
    private Stage stage;

    public void submitEmployee() throws SQLException, ClassNotFoundException {
        EmployeeDAO employeeDAO = new EmployeeDAO(new Employee(fnAdd.getText(),lnAdd.getText(),dateAdd.getText(),Float.parseFloat(salaryAdd.getText())));
        employeeDAO.add();
        fnAdd.clear();
        lnAdd.clear();
        dateAdd.clear();
        salaryAdd.clear();
        System.out.println(CompanyDAO.getIdByCompanyName(ViewCompaniesController.getCurrentName()));

    }

    public void goBack(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/employees.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
