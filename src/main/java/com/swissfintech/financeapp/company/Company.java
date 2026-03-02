package com.swissfintech.financeapp.company;

import com.swissfintech.financeapp.common.base.BaseEntity;
import com.swissfintech.financeapp.user.User;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "registration_number", unique = true)
    private String registrationNumber;

    @Column(nullable = false, length = 2)
    private String country = "CH";

    @Column(nullable = false, length = 3)
    private String currency = "CHF";

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<User> users;

    protected Company() {
    }

    public Company(String name, String country, String currency) {
        this.name = name;
        this.country = country;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }
}