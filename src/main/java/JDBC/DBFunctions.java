package JDBC;

import Models.Company;

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
            if (results.next()){
                hasRecords = true;}
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("cplm :" + e.getMessage());
        }
        return hasRecords;
    }

}

