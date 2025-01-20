package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wallet implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Category> categories = new ArrayList<>();
    private double totalIncome = 0;
    private double totalExpenses = 0;

    // Получить список всех категорий
    public List<Category> getCategories() {
        return categories;
    }

    // Добавить категорию
    public void addCategory(Category category) {
        categories.add(category);
    }

    // Найти категорию по имени
    public Category getCategoryByName(String categoryName) {
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                return category;
            }
        }
        return null;  // Если категория не найдена
    }

    // Добавить доход
    public void addIncome(double amount, String categoryName) {
        Category category = getCategoryByName(categoryName);
        if (category == null) {
            category = new Category(categoryName, 0);
            addCategory(category);
        }
        category.addIncome(amount);
        totalIncome += amount;  // Обновляем общий доход
    }

    // Добавить расход
    public void addExpense(double amount, String categoryName) {
        Category category = getCategoryByName(categoryName);
        if (category == null) {
            category = new Category(categoryName, 0);
            addCategory(category);
        }
        category.addExpense(amount);
        totalExpenses += amount;  // Обновляем общий расход
    }

    // Получить общий доход
    public double getTotalIncome() {
        return totalIncome;
    }

    // Получить общий расход
    public double getTotalExpenses() {
        return totalExpenses;
    }

    // Получить общий баланс (доход - расход)
    public double getBalance() {
        return totalIncome - totalExpenses;
    }
}
