package bankexample;

import java.util.UUID;

public class Account {
    private final UUID id;
    private Double balance;
    private Double interestRate;
    private AccountType accountType;
    private Customer customer;
        
    public Account(AccountType accountType, Double interestRate) {
        this.balance = 0.0;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.id = UUID.randomUUID();
    }
    
    public AccountType getAccountType() {
        return this.accountType;
    }
    
    public double getBalance() {
        return balance;
    }

    public UUID getId() {
        return id;
    }   

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void deposit(double cash) {
        if(cash > 0) {
            this.balance += cash;
        }
    }
    
    public Boolean withdraw(double cash) {
        Boolean success = false;
        
        if(this.balance >= cash && cash > 0 ) {
            this.balance -= cash;
            success = true;
        }
        
        return success;
    }
    
    public void calculateInterest() {
        this.balance += this.balance * this.interestRate;
    }
    
    @Override
    public String toString() {
        return this.id + " | " + this.balance + " balance";
    }
}
