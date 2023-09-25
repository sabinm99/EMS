package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
public class Employee {
    private String firstName;
    private String lastName;
    private float salary;
    private String hiringDate;
    private Employee manager;
    private ArrayList<String> skills;
    private boolean active;


    public Employee(String firstName, String lastName, String hiringDate, float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.active = true;
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

    public void setStatusInactive() {
        this.active = false;
    }

    public void setStatusActive() {
        this.active = true;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public ObservableValue<String> getFirstName(){
        return new SimpleStringProperty(this.firstName);
    }
    public ObservableValue<String> getLastName(){
        return new SimpleStringProperty(this.lastName);
    }
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
    public float getSalary(){
        return this.salary;
    }
}
