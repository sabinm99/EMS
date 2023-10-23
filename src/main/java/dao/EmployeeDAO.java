package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import models.Company;
import models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.ViewCompaniesController;

import java.sql.*;

@AllArgsConstructor
public class EmployeeDAO implements DAO {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);
    private Employee employee;

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/EMS_DB", "postgres", "1234");
    }

    /**
     * @return ObservableList object, for populating the JavaFX TableView that displays the employees of each company.
     */

    public static ObservableList<Employee> getListOfEmployees(String companyName) {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = connect().prepareStatement("SELECT * from Employees INNER JOIN Companies on companies.company_id = employees.company_id WHERE company_name = ?");
            statement.setString(1, companyName);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Employee employee = new Employee(results.getInt("employee_id"), results.getString("first_name"), results.getString("last_name"),
                        results.getString("hiring_date"), results.getFloat("salary"));
                list.add(employee);
            }

        } catch (SQLException | ClassNotFoundException e) {
            logger.debug(e.getMessage());
        }
        return list;
    }

    @Override
    public void add() {
        try (PreparedStatement statement = connect().prepareStatement("INSERT into employees (company_id, first_name, last_name, hiring_date, salary) VALUES (?,?,?,?,?)")) {
            statement.setInt(1, CompanyDAO.getIdByCompanyName(ViewCompaniesController.getCurrentName()));
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getHiringDate());
            statement.setFloat(5, employee.getSalary());

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            logger.debug(e.getMessage());
        }
    }

    @Override
    public void update(String name) {

    }

    ;

    @Override
    public void remove() {
        try (PreparedStatement statement = connect().prepareStatement("DELETE from employees WHERE employee_id = ?")) {
            statement.setInt(1, employee.getEmployeeID());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            logger.debug(e.getMessage());
        }
    }

}
