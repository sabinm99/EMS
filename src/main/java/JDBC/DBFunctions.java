package JDBC;

import Models.Company;
import Models.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DBFunctions {

    public static void addCompany(String name, String foundingDate, String address, String industry) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/EMS_DB",
                            "postgres", "1234");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Companies (company_name, company_address, founding_date, industry) VALUES (?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, foundingDate);
            statement.setString(4, industry);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public static Collection<String> viewCompanyNames() throws ClassNotFoundException, SQLException {
        List<String> names = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/EMS_DB",
                        "postgres", "1234");
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT company_name FROM companies");
        while (results.next()) {
            String companyName = results.getString("company_name");
            names.add(companyName);
        }
        return names;
    }

    public static Company getCompanyByName(String name) throws SQLException {
        Company company = null;
        Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/EMS_DB",
                        "postgres", "1234");
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM companies WHERE company_name = '" + name + "'");
        results.next();
        company = new Company(name, results.getString("founding_date"), results.getString("company_address"), results.getString("industry"));
        return company;
    }

    public static void removeCompany(String name) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/EMS_DB",
                            "postgres", "1234");
            PreparedStatement statement = connection.prepareStatement("DELETE from Companies WHERE company_name = ?;");
            statement.setString(1, name);
            statement.executeUpdate();

            System.out.println("record removed");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("kkt: " + e.getMessage());
        }
    }

    public static boolean databaseHasRecords() {
        boolean hasRecords = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/EMS_DB",
                            "postgres", "1234");
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT company_id FROM companies");
            if (results.next()) {
                hasRecords = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("cplm :" + e.getMessage());
        }
        return hasRecords;
    }

    public static void updateCompanyDetails(String name, String newName, String newAddress, String newIndustry, String newFoundingDate) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/EMS_DB",
                            "postgres", "1234");
            PreparedStatement statement = connection.prepareStatement("UPDATE companies SET company_name = ?, company_address = ?, founding_date = ?, industry = ? WHERE company_name = ?");
            statement.setString(1, newName);
            statement.setString(2, newAddress);
            statement.setString(3, newFoundingDate);
            statement.setString(4, newIndustry);
            statement.setString(5, name);

            statement.executeUpdate();

            System.out.println("record modified");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("kkt: " + e.getMessage());
        }
    }

    public static ObservableList<Employee> getListOfEmployees(String companyName) {
            ObservableList<Employee> list = FXCollections.observableArrayList();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/EMS_DB",
                            "postgres", "1234");
            PreparedStatement statement = connection.prepareStatement("SELECT * from Employees INNER JOIN Companies on companies.company_id = employees.company_id WHERE company_name = ?");
            statement.setString(1, companyName);
            ResultSet results = statement.executeQuery();
            while (results.next()){
                Employee employee = new Employee(results.getString("first_name"), results.getString("last_name"),
                        results.getString("hiring_date"), results.getFloat("salary"));
                list.add(employee);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("kkt: " + e.getMessage());


        }
        return list;
    }

}

