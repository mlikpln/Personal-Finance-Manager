package org.example;

import java.io.Serializable;

public class Category implements Serializable {
    private String name;
    private double budget;
    private double income;
    private double expenses;

    public Category(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.income = 0;
        this.expenses = 0;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getIncome() {
        return income;
    }

    public double getExpenses() {
        return expenses;
    }

    public double getRemainingBudget() {
        return budget - expenses;
    }

    public void addIncome(double amount) {
        this.income += amount;
    }

    public void addExpense(double amount) {
        this.expenses += amount;
    }
}
