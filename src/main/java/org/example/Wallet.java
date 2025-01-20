package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wallet implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Category> categories;

    public Wallet() {
        this.categories = new ArrayList<>();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void addTransaction(Transaction transaction) {
        Category category = getCategoryByName(transaction.getCategory());
        if (category == null) {
            category = new Category(transaction.getCategory(), 0);
            addCategory(category);
        }

        if (transaction.getType() == TransactionType.INCOME) {
            category.addIncome(transaction.getAmount());
        } else if (transaction.getType() == TransactionType.EXPENSE) {
            category.addExpense(transaction.getAmount());
        }
    }

    // Получение общего дохода
    public double getTotalIncome() {
        return categories.stream().mapToDouble(Category::getIncome).sum();
    }

    // Получение общих расходов
    public double getTotalExpenses() {
        return categories.stream().mapToDouble(Category::getExpenses).sum();
    }

    // Получение баланса (доход - расход)
    public double getBalance() {
        // Баланс: общий доход - общий расход
        return getTotalIncome() - getTotalExpenses();
    }
}
