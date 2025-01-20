package org.example;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;  // Версия сериализации для обеспечения совместимости
    private String login;
    private String password;
    private Wallet wallet;  // Объект Wallet также должен быть сериализуемым

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.wallet = new Wallet();  // Инициализация кошелька
    }

    public String getLogin() {
        return login;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);  // Проверка пароля
    }

    public Wallet getWallet() {
        return wallet;  // Возвращает объект кошелька
    }
}
