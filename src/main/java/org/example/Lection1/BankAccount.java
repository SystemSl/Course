package org.example.Lection1;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class BankAccount {
    private String name;
    private int balance;
    private LocalDateTime openingData;
    private boolean blocked;

    public BankAccount(String name) {
        this.name = name;
        this.balance = 0;
        this.openingData = now();
        this.blocked = false;
    }

    public boolean deposit(int amount) {
        if (!this.blocked) {
            this.balance += amount;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean withdraw(int amount) {
        if (!this.blocked && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean transfer(BankAccount otherAccount, int amount) {
        return this.withdraw(amount) && (otherAccount.deposit(amount) || !this.deposit(amount));
    }


}
