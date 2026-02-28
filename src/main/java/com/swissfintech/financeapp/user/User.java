package com.swissfintech.financeapp.user;

import com.swissfintech.financeapp.common.base.BaseEntity;
import com.swissfintech.financeapp.company.Company;

import jakarta.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_email_company", columnNames = { "email", "company_id" })
})
public class User extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    protected User() {
    }

    public User(Company company,
            String email,
            String passwordHash,
            Role role) {
        this.company = company;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

}
