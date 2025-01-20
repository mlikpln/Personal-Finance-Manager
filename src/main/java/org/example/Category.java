package org.example;

import java.io.Serializable;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double budget;  // Бюджет для категории
    private double expenses;  // Расходы для категории
    private double income;  // Доходы для категории

    public Category(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.expenses = 0;
        this.income = 0;
    }

    // Получаем оставшийся бюджет
    public double getRemainingBudget() {
        return budget - expenses;  // Бюджет минус расходы
    }

    // Получаем доход
    public double getIncome() {
        return income;
    }

    // Получаем расходы
    public double getExpenses() {
        return expenses;
    }

    // Получаем бюджет
    public double getBudget() {
        return budget;
    }

    // Добавить расход
    public void addExpense(double amount) {
        if (amount > 0) {
            expenses += amount;  // Добавляем расходы
        } else {
            System.out.println("Сумма расхода должна быть положительной.");
        }
    }

    // Добавить доход
    public void addIncome(double amount) {
        if (amount > 0) {
            income += amount;  // Добавляем доход
        } else {
            System.out.println("Сумма дохода должна быть положительной.");
        }
    }

    // Название категории
    public String getName() {
        return name;
    }

    // Установить бюджет
    public void setBudget(double newBudget) {
        if (newBudget >= 0) {
            this.budget = newBudget;  // Устанавливаем новый бюджет
        } else {
            System.out.println("Бюджет не может быть отрицательным.");
        }
    }
}
