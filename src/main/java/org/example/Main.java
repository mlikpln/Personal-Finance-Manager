package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceManager financeManager = new FinanceManager();

        while (true) {
            System.out.println("1. Регистрация");
            System.out.println("2. Вход");
            System.out.println("3. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считывание символа новой строки

            if (choice == 1) {
                // Регистрация пользователя
                System.out.print("Введите логин: ");
                String login = scanner.nextLine();
                System.out.print("Введите пароль: ");
                String password = scanner.nextLine();

                if (financeManager.registerUser(login, password)) {
                    System.out.println("Пользователь успешно зарегистрирован!");
                } else {
                    System.out.println("Пользователь с таким логином уже существует.");
                }

            } else if (choice == 2) {
                // Вход в систему
                System.out.print("Введите логин: ");
                String login = scanner.nextLine();
                System.out.print("Введите пароль: ");
                String password = scanner.nextLine();

                if (financeManager.loginUser(login, password)) {
                    System.out.println("Добро пожаловать, " + login + "!");
                    // Открываем меню кошелька
                    handleWallet(scanner, financeManager.getCurrentUser());
                } else {
                    System.out.println("Неверный логин или пароль.");
                }

            } else if (choice == 3) {
                System.out.println("До свидания!");
                break;
            } else {
                System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    private static void handleWallet(Scanner scanner, User currentUser) {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Внести доход");
            System.out.println("2. Внести расход");
            System.out.println("3. Посмотреть доход");
            System.out.println("4. Посмотреть расходы");
            System.out.println("5. Доход по категориям");
            System.out.println("6. Расход по категориям");
            System.out.println("7. Бюджет по категориям");
            System.out.println("8. Внести бюджет");
            System.out.println("9. Баланс");
            System.out.println("10. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считывание символа новой строки

            if (choice == 1) {
                addIncome(scanner, currentUser);

            } else if (choice == 2) {
                addExpense(scanner, currentUser);

            } else if (choice == 3) {
                System.out.println("Общий доход: " + currentUser.getWallet().getTotalIncome());

            } else if (choice == 4) {
                System.out.println("Общие расходы: " + currentUser.getWallet().getTotalExpenses());

            } else if (choice == 5) {
                displayIncomesByCategory(currentUser);

            } else if (choice == 6) {
                displayExpensesByCategory(currentUser);

            } else if (choice == 7) {
                displayBudgetsByCategory(currentUser);

            } else if (choice == 8) {
                addBudget(scanner, currentUser);

            } else if (choice == 9) {
                System.out.println("Баланс: " + currentUser.getWallet().getBalance());

            } else if (choice == 10) {
                System.out.println("Выход из кошелька...");
                break;

            } else {
                System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void addIncome(Scanner scanner, User currentUser) {
        System.out.print("Введите категорию дохода: ");
        String category = scanner.nextLine();
        System.out.print("Введите сумму дохода: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Считывание символа новой строки

        currentUser.getWallet().addIncome(amount, category);
        System.out.println("Доход добавлен в категорию '" + category + "'.");
    }

    private static void addExpense(Scanner scanner, User currentUser) {
        System.out.print("Введите категорию расхода: ");
        String category = scanner.nextLine();
        System.out.print("Введите сумму расхода: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Считывание символа новой строки

        currentUser.getWallet().addExpense(amount, category);
        System.out.println("Расход добавлен в категорию '" + category + "'.");
    }

    private static void addBudget(Scanner scanner, User currentUser) {
        System.out.print("Введите название категории: ");
        String categoryName = scanner.nextLine();
        System.out.print("Введите бюджет для категории: ");
        double budget = scanner.nextDouble();
        scanner.nextLine(); // Считывание символа новой строки

        Category category = currentUser.getWallet().getCategoryByName(categoryName);
        if (category == null) {
            category = new Category(categoryName, budget);
            currentUser.getWallet().addCategory(category);
            System.out.println("Категория '" + categoryName + "' добавлена с бюджетом " + budget + ".");
        } else {
            category.setBudget(budget);
            System.out.println("Бюджет для категории '" + categoryName + "' обновлен.");
        }
    }

    private static void displayIncomesByCategory(User currentUser) {
        System.out.println("\nДоходы по категориям:");
        for (Category category : currentUser.getWallet().getCategories()) {
            System.out.printf("Категория: %s, Доход: %.2f%n", category.getName(), category.getIncome());
        }
    }

    private static void displayExpensesByCategory(User currentUser) {
        System.out.println("\nРасходы по категориям:");
        for (Category category : currentUser.getWallet().getCategories()) {
            System.out.printf("Категория: %s, Расходы: %.2f%n", category.getName(), category.getExpenses());
        }
    }

    private static void displayBudgetsByCategory(User currentUser) {
        System.out.println("\nБюджет по категориям:");
        for (Category category : currentUser.getWallet().getCategories()) {
            double remainingBudget = category.getRemainingBudget();  // Получаем оставшийся бюджет
            System.out.printf("Категория: %s, Бюджет: %.2f, Оставшийся бюджет: %.2f%n",
                    category.getName(), category.getBudget(), remainingBudget);
        }
    }
}
