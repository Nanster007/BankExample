package bankexample;

public class Bank {
    public final static Integer MAX_ACCOUNTS = 4;
    public final static Integer MAX_CUSTOMERS = 10;
    private final static Double INTEREST_RATE = 0.02;
    private final static Double INITIAL_DEPOSIT = 500.00;
    private final static Integer WITHDRAW_COUNT = 4;
    
    private Customer[] customers;
    private Account[] accounts;
    private Vault vault;
    
    private Integer customerIndex;
    
    public Bank() {
        this.accounts = new Account[MAX_CUSTOMERS * MAX_ACCOUNTS];
        this.customers = new Customer[MAX_CUSTOMERS];
        this.vault = new Vault();
        this.customerIndex = 0;
    }
    
    public void start() {
        createAllCustomers();
        createAccountsForAllCustomers();
        
//        outputAllAccounts();
        
        depositMoneyToAllAccounts();
        
//        outputAllAccounts();
        
        withdrawMoneyFromRandomAccounts();
        
//        outputAllAccounts();
    }
    
    public Customer getFirstCustomer() {
        return this.customers[0];
    }
    
    private void createAccountsForAllCustomers() {
        for(int i = 0; i < this.customers.length; i++) {
            for(int k = 0; k < MAX_ACCOUNTS; k++) {
                Account account = createAccount(this.customers[i]);
                this.accounts[MAX_ACCOUNTS * i + k] = account;
            }
        }
    }
    
    private Account createAccount(Customer customer) {
        Account account = new Account(
                AccountType.Checking,
                INTEREST_RATE
        );
        account.setCustomer(customer);
        return account;
    }
    
    private void createAllCustomers() {
        for(int i = 0; i < this.customers.length; i++) {
            Customer customer = new Customer(
                    "FN " + i,
                    "LN " + i,
                    "FN_LN " + i + "@gmail.com",
                    "password"
            );
            this.customers[i] = customer;
        }
    }
    
    private void depositMoneyToAllAccounts() {
        for(int i = 0; i < this.accounts.length; i++) {
            Account account = this.accounts[i];
            account.deposit(INITIAL_DEPOSIT);
            vault.addCash(INITIAL_DEPOSIT);
        }
    }
    
    public void outputAllCustomers() {
        for(int i = 0; i < this.customers.length; i++) {
            System.out.println(this.customers[i]);
        }
    }
    
    public void outputAllAccounts() {
        for (Account account : this.accounts) {
            System.out.println(account);
        }
    }

    private void withdrawMoneyFromRandomAccounts() {
        int start = 0;
        int end = this.accounts.length;
        
        for(int i = 0; i < WITHDRAW_COUNT; i++) {
            int accountIndex = Utilities.getRandomNumber(start, end);
            Account account = this.accounts[accountIndex];
            withdrawMoneyFromAccount(account);
        }
    }
    
    private void withdrawMoneyFromAccount(Account account) {
        Integer amount = Utilities.getRandomNumber(1, (int)account.getBalance());
        account.withdraw(amount);
        this.vault.removeCash((double)amount);
    }

    public Customer getNextCustomer() {
        if(customerIndex < this.customers.length - 1) {
           customerIndex++;
        } 
                
        return this.customers[customerIndex];
    }

    public Customer getPreviousCustomer() {
        if(customerIndex > 0) {
            customerIndex--;
        }
        return this.customers[customerIndex];
    }
    
    public Account[] getAccounts(Customer customer) {
        Integer index = 0;
        Account[] tempAccounts = new Account[MAX_ACCOUNTS];
        for(Account account: this.accounts) {
            if(account.getCustomer().equals(customer)) {
                tempAccounts[index++] = account;
            }
        }
        return tempAccounts;
    }
}
