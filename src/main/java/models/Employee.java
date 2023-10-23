package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import lombok.Getter;
import lombok.Setter;


/**
 * Model class to hold information for employees.
 */
@Getter
@Setter
public class Employee {
    private String firstName;
    private String lastName;
    private float salary;
    private String hiringDate;
    private Employee manager;
    private boolean active;
    private int employeeID;

    /**
     * @param firstName
     * @param lastName
     * @param hiringDate
     * @param salary
     */


    public Employee(int id, String firstName, String lastName, String hiringDate, float salary) {
        this.employeeID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.active = true;
    }

    public Employee(int id) {
        this.employeeID = id;
    }

    public Employee(String firstName, String lastName, String hiringDate, float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.active = true;
    }

    public String toString() {
        return firstName + " " + lastName + "\n" + "Salary: " + salary + "\n" + "Hiring date: " + hiringDate + "\n" +
                "Is current employee: " + getStatusAsString();
    }

    public String getStatusAsString() {
        if (!active)
            return "No";
        return "Yes";
    }

    /**
     * @return The first names and last names of employees as an ObservableValue object, for displaying them in JavaFX scenes.
     */
    public ObservableValue<String> getObservableFirstName() {
        return new SimpleStringProperty(this.firstName);
    }

    public ObservableValue<String> getObservableLastName() {
        return new SimpleStringProperty(this.lastName);
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
