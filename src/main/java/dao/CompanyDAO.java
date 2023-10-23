package dao;

import lombok.AllArgsConstructor;
import models.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class CompanyDAO implements DAO {

    private static final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    private final Company company;

    /**
     * @return A collection of all the companies in the database, for populating the JavaFX choicebox.
     */
    public static Collection<String> viewCompanyNames() throws ClassNotFoundException, SQLException {
        List<String> names = new ArrayList<>();
        Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT company_name FROM companies");
        while (results.next()) {
            String companyName = results.getString("company_name");
            names.add(companyName);
        }
        connection.close();
        return names;
    }

    /**
     * @return Company object by the given name, selected in the JavaFX choicebox.
     */

    public static Company getCompanyByName(String name) throws SQLException, ClassNotFoundException {
        Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM companies WHERE company_name = '" + name + "'");
        results.next();
        return new Company(name, results.getString("founding_date"), results.getString("company_address"), results.getString("industry"));
    }

    /**
     * Verify if the database has any companies before viewing them. If not, the program will display an error message to the user.
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

    /**
     * @return List of employees from a certain company in the database, as an ObservableList object, for visualising in JavaFX.
     */


    /**
     * @return Connection to the database.
     */
    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/EMS_DB", "postgres", "1234");
    }

    public void add() {
        try {
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Companies (company_name, company_address, founding_date, industry) VALUES (?, ?, ?, ?)");
            statement.setString(1, company.getName());
            statement.setString(2, company.getAddress());
            statement.setString(3, company.getFoundingDate());
            statement.setString(4, company.getIndustry());
            statement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            logger.error("Failed to add company with name {}", company.getName(), e);
        }

    }

    /**
     * Remove a company from the database by its name.
     */

    public void remove() {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("DELETE from Companies WHERE company_name = ?;");
            statement.setString(1, company.getName());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            logger.debug("Failed to remove company with name {}", company.getName(), e);

        }
    }

    public void update(String oldName) {
        try {
            PreparedStatement statement = connect().prepareStatement("UPDATE companies SET company_name = ?, company_address = ?, founding_date = ?, industry = ? WHERE company_name = ?");
            statement.setString(1, company.getName());
            statement.setString(2, company.getAddress());
            statement.setString(3, company.getFoundingDate());
            statement.setString(4, company.getIndustry());
            statement.setString(5, oldName);

            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            logger.debug(e.getMessage());
        }
    }
}

