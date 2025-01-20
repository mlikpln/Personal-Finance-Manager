package org.example;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L; // Идентификатор сериализации
    private double amount;
    private String category;
    private TransactionType type;
    private Date date;

    public Transaction(double amount, String category, TransactionType type) {
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = new Date();  // Дата транзакции
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public TransactionType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }
}
