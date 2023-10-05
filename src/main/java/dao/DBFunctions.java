package dao;

import models.Company;
import models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DBFunctions {

    private static final Logger logger = LoggerFactory.getLogger(DBFunctions.class);

    public static void addCompany(String name, String foundingDate, String address, String industry) {
        try {
            PreparedStatement statement = connect().prepareStatement("INSERT INTO Companies (company_name, company_address, founding_date, industry) VALUES (?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, foundingDate);
            statement.setString(4, industry);
            statement.executeUpdate();
        } catch (Exception e) {
            logger.error("Failed to add company with name {}", name, e);
        }

    }

    /**
     *
     * @return A collection of all the companies in the database, for populating the JavaFX choicebox.
     *
     */
    public static Collection<String> viewCompanyNames() throws ClassNotFoundException, SQLException {
        List<String> names = new ArrayList<>();
        Statement statement = connect().createStatement();
        ResultSet results = statement.executeQuery("SELECT company_name FROM companies");
        while (results.next()) {
            String companyName = results.getString("company_name");
            names.add(companyName);
        }
        return names;
    }

    public static Company getCompanyByName(String name) throws SQLException, ClassNotFoundException {
        Statement statement = connect().createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM companies WHERE company_name = '" + name + "'");
        results.next();
        return new Company(name, results.getString("founding_date"), results.getString("company_address"), results.getString("industry"));
    }

    public static void removeCompany(String name) {
        try {
            PreparedStatement statement = connect().prepareStatement("DELETE from Companies WHERE company_name = ?;");
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            logger.debug("Failed to remove company with name {}", name, e);
        }
    }

    /**
     * Verify if the database has any companies before viewing them. If not, the program will display an error message to the user.
     *
     */
    public static boolean databaseHasRecords() {
        boolean hasRecords = false;
        try {
            Statement statement = connect().createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM companies");
            if (results.next()) {
                hasRecords = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.debug("Failed to run query", e);
        }
        return hasRecords;
    }

    public static void updateCompanyDetails(String name, String newName, String newAddress, String newIndustry, String newFoundingDate) {
        try {
            PreparedStatement statement = connect().prepareStatement("UPDATE companies SET company_name = ?, company_address = ?, founding_date = ?, industry = ? WHERE company_name = ?");
            statement.setString(1, newName);
            statement.setString(2, newAddress);
            statement.setString(3, newFoundingDate);
            statement.setString(4, newIndustry);
            statement.setString(5, name);

            statement.executeUpdate();

            System.out.println("record modified");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     *
     * @return List of employees from a certain company in the database, as an ObservableList object, for visualising in JavaFX.
     */
    public static ObservableList<Employee> getListOfEmployees(String companyName) {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = connect().prepareStatement("SELECT * from Employees INNER JOIN Companies on companies.company_id = employees.company_id WHERE company_name = ?");
            statement.setString(1, companyName);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Employee employee = new Employee(results.getString("first_name"), results.getString("last_name"),
                        results.getString("hiring_date"), results.getFloat("salary"));
                list.add(employee);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     *
     * @return Connection to the database.
     *
     */
    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/EMS_DB","postgres", "1234");
    }
}

