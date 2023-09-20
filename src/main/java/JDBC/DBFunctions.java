package JDBC;

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
            System.out.println("Record added");

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


}

