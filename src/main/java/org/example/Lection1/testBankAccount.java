package org.example.Lection1;

public class testBankAccount {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("Dmitriy");
        BankAccount account2 = new BankAccount("Sergey");
        BankAccount account3 = new BankAccount("Dmitriy");
        System.out.println(account1.withdraw(100));
        System.out.println(account1.deposit(200));
        System.out.println(account1.withdraw(100));
        System.out.println(account1.transfer(account2, 100));
        System.out.println(account1.transfer(account2, 100));
        System.out.println(account2.withdraw(100));
        System.out.println(account1);
        System.out.println(account1.equals(account3));
        System.out.println(account1.hashCode());
        System.out.println(account2.hashCode());
        System.out.println(account3.hashCode());
    }
}
