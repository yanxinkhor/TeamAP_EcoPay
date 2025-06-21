package com.example.teamap_ecopay.Models;

public class Wallet {
    private double balance;
    private double income;
    private double spent;

    public Wallet(double balance, double income, double spent) {
        this.balance = balance;
        this.income = income;
        this.spent = spent;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getSpent() {
        return spent;
    }

    public void setSpent(double spent) {
        this.spent = spent;
    }
}


