package org.example.Lection1;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

import static java.time.LocalDateTime.now;

public class BankAccount {
    private String name;
    private int balance;
    private LocalDateTime openingData;
    private boolean blocked;
    private String number;
    private Random random;

    public BankAccount(String name) {
        this.random = new Random();
        this.name = name;
        this.balance = 0;
        this.openingData = now();
        this.blocked = false;
        this.number = String.format("%09d", random.nextInt((int) 1e10));
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

    public String toString() {
        return "Name: " + this.name + "\nBalance: " + this.balance + "\nOpening date: " +
                this.openingData.toLocalDate() + "\nBlocked: " + (this.blocked ? "yes" : "no") + "\nNumber: " + this.number;
    }

    public boolean equals(BankAccount otherAccount) {
        return (this.name.equals(otherAccount.name) && this.balance == otherAccount.balance);
    }

    public int hashCode() {
        return 31 * this.name.hashCode() + this.balance;
    }
}
