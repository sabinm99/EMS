package Models;

import java.util.ArrayList;

public class Company {
    private String name;
    private String foundingDate;
    private String address;
    private String industry;
    private ArrayList<Employee> employees;

    public Company(String name, String foundingDate, String address, String industry) {
        this.name = name;
        this.foundingDate = foundingDate;
        this.address = address;
        this.industry = industry;
    }

    public int getNumberOfEmployees() {
        return employees.size();
    }

    public String getName() {
        return this.name;
    }
    public String getFoundingDate(){
        return this.foundingDate;
    }
    public String getIndustry(){
        return this.industry;
    }
    public String getAddress(){
        return this.address;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public String toString() {
        return this.name + ", " + this.industry + ", " + this.address;
    }


}
