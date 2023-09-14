import java.time.LocalDate;
import java.util.ArrayList;
public class Employee {
    private String firstName;
    private String lastName;
    private float salary;
    private LocalDate hiringDate;
    private Employee manager;
    private ArrayList<String> skills;
    private boolean active;


    public Employee(String fullName, String hiringDate, float salary) {
        String[] nameSplit = fullName.split(" ");
        this.firstName = nameSplit[0];
        this.lastName = nameSplit[1];
        this.hiringDate = LocalDate.parse(hiringDate);
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
}
