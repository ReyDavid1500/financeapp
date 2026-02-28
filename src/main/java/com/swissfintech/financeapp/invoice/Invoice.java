package com.swissfintech.financeapp.invoice;

import com.swissfintech.financeapp.common.base.BaseEntity;
import com.swissfintech.financeapp.company.Company;
import com.swissfintech.financeapp.payment.Payment;
import com.swissfintech.financeapp.user.User;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import java.math.BigDecimal;

@Entity
@Table(
        name = "invoices",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_invoice_number_per_company",
                        columnNames = {"company_id", "invoice_number"}
                )
        }
)
public class Invoice extends BaseEntity {
    private static final int MONEY_SCALE = 2;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "invoice_number", nullable = false, length = 50)
    private String invoiceNumber;

    @Column(name = "customer_name", nullable = false, length = 150)
    private String customerName;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency = "CHF";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InvoiceStatus status = InvoiceStatus.DRAFT;

    @Column(name = "issued_date", nullable = false)
    private LocalDate issuedDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();

    protected Invoice() { }

    public Invoice(
            Company company,
            String invoiceNumber,
            String customerName,
            BigDecimal amount,
            String currency,
            LocalDate issuedDate,
            LocalDate dueDate,
            User createdBy
    ) {
        this.company = company;
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.amount = amount;
        this.currency = currency;
        this.issuedDate = issuedDate;
        this.dueDate = dueDate;
        this.createdBy = createdBy;
    }
}
