package org.example;

import java.util.List;

public class FinanceManager {
    private List<User> users;
    private User currentUser;

    public FinanceManager() {
        this.users = FileStorage.loadUsers();  // Загружаем пользователей из файла
    }

    // Регистрация нового пользователя
    public boolean registerUser(String login, String password) {
        if (findUser(login) == null) {
            users.add(new User(login, password));  // Добавляем нового пользователя
            FileStorage.saveUsers(users);  // Сохраняем список пользователей в файл
            return true;
        }
        return false;
    }

    // Вход в систему
    public boolean loginUser(String login, String password) {
        User user = findUser(login);  // Ищем пользователя по логину
        if (user != null && user.authenticate(password)) {  // Проверяем правильность пароля
            currentUser = user;  // Устанавливаем текущего пользователя
            return true;
        }
        return false;
    }

    // Выход из системы
    public void logout() {
        currentUser = null;  // Сбрасываем текущего пользователя
        FileStorage.saveUsers(users);  // Сохраняем обновленный список пользователей в файл
    }

    // Поиск пользователя по логину
    private User findUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    // Получить текущего пользователя
    public User getCurrentUser() {
        return currentUser;
    }
}
