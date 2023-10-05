package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Company implements ICompany {
    private String name;
    private String foundingDate;
    private String address;
    private String industry;
    private Collection<Employee> employees;

    public Company(String name, String foundingDate, String address, String industry) {
        this.name = name;
        this.foundingDate = foundingDate;
        this.address = address;
        this.industry = industry;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfEmployees() {
        return employees.size();
    }
}
