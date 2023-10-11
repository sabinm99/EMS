package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;



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
    private Collection<String> skills;
    private boolean active;
    private int employeeID;

    /**
     *
     * @param firstName
     * @param lastName
     * @param hiringDate
     * @param salary
     */


    public Employee(int id, String firstName, String lastName, String hiringDate, float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.active = true;
        this.employeeID = id;
    }

    public void increaseSalaryByAmount(float amount) {
        salary += amount;
    }

    public void increaseSalaryByPercentage(float percentage) {
        this.salary *= (1 + percentage * (1.0 / 100));
    }

    public String toString() {
        return firstName + " " + lastName + "\n" + "Salary: " + salary + "\n" + "Hiring date: " + hiringDate + "\n" +
                "Is current employee: " + getStatusAsString();
    }

    public boolean getStatus() {
        return active;
    }

    public String getStatusAsString() {
        if (!active)
            return "No";
        return "Yes";
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Collection<String> getSkills() {
        return skills;
    }

    /**
     *
     * @return The first names and last names of employees as an ObservableValue object, for displaying them in JavaFX scenes.
     *
     */
    public ObservableValue<String> getFirstName(){
        return new SimpleStringProperty(this.firstName);
    }
    public ObservableValue<String> getLastName(){
        return new SimpleStringProperty(this.lastName);
    }
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
}
