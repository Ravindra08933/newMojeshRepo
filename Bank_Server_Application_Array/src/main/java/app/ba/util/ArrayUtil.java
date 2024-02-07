package app.ba.util;
import app.Generator.NumberGenerator;
import app.ba.exception.AccountNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.bean.Transaction;
import java.time.LocalDate;
public class ArrayUtil {

    static int size = 10;
    public static Customer[] customers = new Customer[size];
    public static Account[] accounts = new Account[size];
    static {
        customers[0] = new Customer(1001, "James","James@gmail.com");
        customers[1] = new Customer(1002, "Parley","parley@hotmail.com");
        customers[2] = new Customer(1003, "Steve","steve@outlook.com");
        accounts[0] = new Account(121, 500, "Savings", customers[0]);
        accounts[1] = new Account(122, 500, "Savings", customers[1]);
        accounts[2] = new Account(123, 500, "Current", customers[2]);


        Transaction[] transactions = new Transaction[10];
        Transaction transaction = new Transaction(new NumberGenerator().generateNumber(),LocalDate.now().minusDays(2),"Deposit",500);
        transactions[0] = transaction;
        accounts[0].setTransactions(transactions);

        Transaction[] transactions1 = new Transaction[10];
        Transaction transaction1 = new Transaction(new NumberGenerator().generateNumber(),LocalDate.now().minusDays(2),"Deposit",500);
        transactions1[0] = transaction1;
        accounts[1].setTransactions(transactions1);

        Transaction[] transactions2 = new Transaction[10];
        Transaction transaction2 = new Transaction(new NumberGenerator().generateNumber(),LocalDate.now().minusDays(2),"Deposit",500);
        transactions2[0] = transaction2;
        accounts[2].setTransactions(transactions2);
    }

    public static void createAccount(int accountNumber, double initialBalance, String accountType, Customer customer)
    {
        int customerIndex = findAvailableCustomerIndex();
        customers[customerIndex]= customer;
        if (customer != null)
        {
            int accountIndex = findAvailableAccountIndex();
            if (accountIndex == -1)
            {
                resizeAccountsArray();
                accountIndex = findAvailableAccountIndex();
            }
            Account newAccount = new Account(accountNumber, initialBalance, accountType, customer);
            Transaction transaction  = new Transaction(new NumberGenerator().generateNumber(), LocalDate.now(),"Deposit",initialBalance);
            Transaction[] transactions = new Transaction[10];
            transactions[0] = transaction;
            newAccount.setTransactions(transactions);
            accounts[accountIndex] = newAccount;
            System.out.println("Account created successfully!");
        }
    }

    public static String deleteAccount(int accountNumber) throws AccountNotFoundException
    {
            Customer customer = null;
            int accountIndex = findAccountIndexByNumber(accountNumber);
            for(Account account:accounts)
            {
                if(account!=null && account.getAccountNumber() == accountNumber)
                {
                    customer = account.getCustomer();
                }
            }
            int index = findCustomerIndexById(customer.getCustomerId());
            customers[index] = null;
            if (accountIndex != -1)
            {
                accounts[accountIndex] = null;
                return "Account with number " + accountNumber + " deleted successfully!";
            }
            else {
                return "Account with number " + accountNumber + " not found.";
            }
    }
    public static Customer updateAccount(int accNum, String name) {
        Customer customer = null;
        for(Account account:accounts)
        {
            if(account!=null && account.getAccountNumber() == accNum)
            {
                customer = account.getCustomer();
            }
        }
        if(customer!=null) {
            customer.setName(name);
            int index = findAccountIndexByNumber(accNum);
            accounts[index].setCustomer(customer);
            int index1 = findCustomerIndexById(customer.getCustomerId());
            customers[index1] = customer;
            return customer;
        }
        return null;
    }
    public static Customer updateAccountEmail(int accNum, String name) {
        Customer customer = null;
        for(Account account:accounts)
        {
            if(account!=null && account.getAccountNumber() == accNum)
            {
                customer = account.getCustomer();
            }
        }
        if(customer!=null) {
            customer.setEmail(name);
            int index = findAccountIndexByNumber(accNum);
            accounts[index].setCustomer(customer);
            int index1 = findCustomerIndexById(customer.getCustomerId());
            customers[index1] = customer;
            return customer;
        }
        return null;
    }
    private static int findAccountIndexByNumber(int accountNumber)
    {
        for (int i = 0; i < size; i++)
        {
            if (accounts[i] != null && accounts[i].getAccountNumber() == accountNumber)
            {
                return i;
            }
        }
        return -1;
    }
    private static void resizeAccountsArray()
    {
        int newSize = size * 2;
        Account[] newAccounts = new Account[newSize];
        System.arraycopy(accounts, 0, newAccounts, 0, size);
        accounts = newAccounts;
        size = newSize;
    }

    private static int findAvailableAccountIndex() {
        for (int i = 0; i < size; i++) {
            if (accounts[i] == null) {
                return i;
            }
        }
        return -1;
    }
    private static int findAvailableCustomerIndex() {
        for (int i = 0; i < size; i++) {
            if (customers[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private static Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer != null && customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }
    private static int findCustomerIndexById(int num)
    {
        Customer customer = findCustomerById(num);
        for (int i = 0;i<customers.length;i++) {
            if(customers[i] !=null && customers[i].getCustomerId()==customer.getCustomerId())
                return i;
        }
        return -1;
    }
}
