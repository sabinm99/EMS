package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@ToString
public class Company {
    private String name;
    private String foundingDate;
    private String address;
    private String industry;

    public Company(String name) {
        this.name = name;
    }

}
