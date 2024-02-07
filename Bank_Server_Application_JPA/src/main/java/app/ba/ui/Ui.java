package app.ba.ui;
import app.ba.Exception.AccountNotFoundException;
import app.ba.Exception.CustomerNotFoundException;
import app.ba.Exception.TransactionNotFoundException;
import app.ba.bean.Transaction;
import app.ba.service.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class Ui
{
    public static void main(String[] args)
    {
        int t=1,choice=0,accNum = 0,User=0,s = 1,temp = 0,accNum1=0,temp1 = 1,t1 = 1,t3 = 1;
        double amount;
        Scanner scanner = new Scanner(System.in);
        AccountService accountService = new AccountServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        TransactionService transactionService = new TransactionServiceImpl();
        System.out.println("Hey Buddy, Welcome to Our Bank");
        while(temp1 == 1)
        {
            s = 1;
            System.out.println("Press 1. Admin");
            System.out.println("Press 2. User");
            try
            {
                temp = scanner.nextInt();
                switch(temp)
                {
                    case 1:
                        System.out.println("Oops");
                        break;
                    case 2:
                        while (s == 1)
                        {

                            t = 1;
                            t1 = 1;
                            t3 = 1;
                            System.out.println("Press 1 for Account Section");
                            System.out.println("Press 2 for Customer Section");
                            System.out.println("Press 3 for Transaction Section");
                            System.out.println("Press 0 to Exit");
                            try
                            {
                                User = scanner.nextInt();
                                if (User == 0)
                                {
                                    System.out.println("-----Exiting-------");
                                    break;
                                }
                                else
                                {
                                    switch (User)
                                    {
                                        case 1:
                                            while (t == 1)
                                            {
                                                System.out.println("1. Check Balance");
                                                System.out.println("2. Deposit");
                                                System.out.println("3. Withdraw");
                                                System.out.println("4. Transfer");
                                                System.out.println("5. Delete Account");
                                                System.out.println("6. Create Account");
                                                System.out.println("0. Exit");
                                                try
                                                {
                                                    choice = scanner.nextInt();
                                                    if (choice == 0)
                                                    {
                                                        System.out.println("--Exiting from Account Section");
                                                        break;
                                                    }
                                                    else
                                                    {
                                                        switch (choice)
                                                        {
                                                            case 1:
                                                                System.out.println("Enter Account Number : ");
                                                                try
                                                                {
                                                                    accNum = scanner.nextInt();
                                                                    try
                                                                    {
                                                                        System.out.println("Current Balance : "+accountService.checkBalance(accNum));
                                                                    }
                                                                    catch (AccountNotFoundException message)
                                                                    {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            case 2:
                                                                System.out.println("Enter Account Number : ");
                                                                try
                                                                {
                                                                    accNum = scanner.nextInt();
                                                                    while (true)
                                                                    {
                                                                        System.out.println("Enter Amount : ");
                                                                        amount = scanner.nextDouble();
                                                                        if (amount < 0)
                                                                            System.out.println("Amount should positive");
                                                                        else
                                                                            break;
                                                                    }
                                                                    try
                                                                    {
                                                                        System.out.println(accountService.deposit(accNum, amount));
                                                                    }
                                                                    catch (AccountNotFoundException message)
                                                                    {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            case 3:
                                                                System.out.println("Enter Account Number : ");
                                                                try
                                                                {
                                                                    accNum = scanner.nextInt();
                                                                    while (true)
                                                                    {
                                                                        System.out.println("Enter Amount : ");
                                                                        amount = scanner.nextDouble();
                                                                        if (amount < 0)
                                                                            System.out.println("Amount should positive");
                                                                        else
                                                                            break;
                                                                    }
                                                                    try
                                                                    {
                                                                        System.out.println(accountService.withdraw(accNum, amount));
                                                                    }
                                                                    catch (AccountNotFoundException message)
                                                                    {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            case 4:
                                                                try
                                                                {
                                                                    System.out.println("Enter Source Account Number : ");
                                                                    accNum = scanner.nextInt();
                                                                    System.out.println("Enter Destination Account Number : ");
                                                                    accNum1 = scanner.nextInt();
                                                                    while (true)
                                                                    {
                                                                        System.out.println("Enter Amount : ");
                                                                        amount = scanner.nextDouble();
                                                                        if (amount < 0)
                                                                            System.out.println("Amount should positive");
                                                                        else
                                                                            break;
                                                                    }
                                                                    try
                                                                    {
                                                                        accountService.transfer(accNum, accNum1, amount);
                                                                        System.out.println("Updated Account Balance : " + accountService.checkBalance(accNum));
                                                                    }
                                                                    catch (AccountNotFoundException message)
                                                                    {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;

                                                            case 5:
                                                                System.out.println("Enter Account number");
                                                                try
                                                                {
                                                                    accNum = scanner.nextInt();
                                                                    try
                                                                    {
                                                                        accountService.deleteAccount(accNum);
                                                                        System.out.println("Account with "+accNum + " Deleted Successfully");
                                                                    }
                                                                    catch (AccountNotFoundException message)
                                                                    {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            case 6:
                                                                double money;
                                                                System.out.print("Enter Customer Name: ");
                                                                String name = scanner.next();
                                                                System.out.print("Enter Email: ");
                                                                String email = scanner.next();
                                                                while (true)
                                                                {
                                                                    System.out.print("Enter Initial Deposit Amount: ");
                                                                    money = scanner.nextDouble();
                                                                    if (money < 0)
                                                                        System.out.println("Amount should positive");
                                                                    else
                                                                        break;
                                                                }
                                                                System.out.println(accountService.createAccount(name, email, money));
                                                                break;
                                                            default:
                                                                System.out.println("Invalid choice");
                                                                break;
                                                        }//switch
                                                        System.out.println("Press 1 to continue in Account Section");
                                                        System.out.println("Press 0 to exit from Account Section");
                                                        try
                                                        {
                                                            t = scanner.nextInt();
                                                        }
                                                        catch (InputMismatchException e)
                                                        {
                                                            System.out.println("Wrong Input Type!\n Enter Number Only");
                                                            scanner.nextLine();
                                                        }
                                                    }//else
                                                }//switch
                                                catch (InputMismatchException e)
                                                {
                                                    System.out.println("Wrong Input Type!\n Enter Number Only");
                                                    scanner.nextLine();
                                                }
                                            }
                                            break;
                                        case 2:
                                            while (t1 == 1)
                                            {
                                                System.out.println("1. Find Customer by Name");
                                                System.out.println("2. Find Customer by ID");
                                                System.out.println("3. Find Customer by Account Number");
                                                System.out.println("4. Update Customer Name");
                                                System.out.println("5. Update Customer Email");
                                                System.out.println("0. Exit");
                                                int customerChoice = 0;
                                                try
                                                {
                                                    customerChoice = scanner.nextInt();
                                                    if (customerChoice == 0)
                                                    {
                                                        System.out.println("...Exiting from Customer Section.....");
                                                        break;
                                                    }
                                                    else
                                                    {
                                                        switch (customerChoice)
                                                        {
                                                            case 1:
                                                                System.out.println("Enter name");
                                                                String nm = scanner.next();
                                                                try
                                                                {
                                                                    System.out.println(customerService.findCustomerByName(nm));
                                                                }
                                                                catch (CustomerNotFoundException message)
                                                                {
                                                                    System.out.println(message);
                                                                }
                                                                break;
                                                            case 2:
                                                                System.out.println("Enter customer ID");
                                                                try
                                                                {
                                                                    accNum = scanner.nextInt();
                                                                    try
                                                                    {
                                                                        System.out.println(customerService.findCustomerById(accNum));
                                                                    }
                                                                    catch (CustomerNotFoundException message)
                                                                    {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            case 3:
                                                                System.out.println("Enter Account Number");
                                                                try
                                                                {
                                                                    accNum = scanner.nextInt();
                                                                    try
                                                                    {
                                                                        System.out.println(customerService.findCustomerByAccountNumber(accNum));
                                                                    }
                                                                    catch (CustomerNotFoundException message)
                                                                    {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            case 4:
                                                                System.out.println("Enter Customer ID : ");
                                                                try {
                                                                    accNum = scanner.nextInt();
                                                                    System.out.println("Enter new Name to update : ");
                                                                    String name = scanner.next();
                                                                    try {
                                                                        System.out.println(customerService.updateCustomerName(accNum, name));
                                                                    } catch (AccountNotFoundException message) {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            case 5:
                                                                System.out.println("Enter Customer ID : ");
                                                                try {
                                                                    accNum = scanner.nextInt();
                                                                    System.out.println("Enter new Email to update : ");
                                                                    String email = scanner.next();
                                                                    try {
                                                                        System.out.println(customerService.updateCustomerEmail(accNum,email));
                                                                    } catch (AccountNotFoundException message) {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            default:
                                                                System.out.println("Invalid Choice");
                                                                break;
                                                        }//switch
                                                        System.out.println("Press 1 to continue in Customer Section");
                                                        System.out.println("Press 0 to exit from Customer Section");
                                                        try
                                                        {
                                                            t1 = scanner.nextInt();
                                                        }
                                                        catch (InputMismatchException e)
                                                        {
                                                            System.out.println("Wrong Input Type!\n Enter Number Only");
                                                            scanner.nextLine();
                                                        }
                                                    }//else
                                                }//try
                                                catch (InputMismatchException e)
                                                {
                                                    System.out.println("Wrong Input Type!\n Enter Number Only");
                                                    scanner.nextLine();
                                                }
                                            }
                                            break;
                                        case 3:
                                            while (t3 == 1)
                                            {
                                                System.out.println("1. Last 10 Transactions of Account");
                                                System.out.println("2.Get All Transactions of Account");
                                                System.out.println("3.Get Transactions of account by Type");
                                                System.out.println("4.Get Transaction of account by Transaction ID");
                                                System.out.println("5. Get Previous days Transactions of Account");
                                                System.out.println("0. Exit");
                                                int transactionChoice = 0;
                                                try
                                                {
                                                    transactionChoice = scanner.nextInt();
                                                    if (transactionChoice == 0)
                                                    {
                                                        System.out.println(" ----Exiting from Transaction section----");
                                                        break;
                                                    }
                                                    else
                                                    {
                                                        switch (transactionChoice)
                                                        {
                                                            case 1:
                                                                System.out.println("Enter Account Number : ");
                                                                try
                                                                {
                                                                    accNum = scanner.nextInt();
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                try
                                                                {
                                                                    List<Transaction> transactions = transactionService.getLast10Transactions(accNum);
                                                                    for (Transaction transaction : transactions)
                                                                    {
                                                                        if (transaction != null)
                                                                            System.out.println(transaction);
                                                                    }
                                                                }
                                                                catch (TransactionNotFoundException message)
                                                                {
                                                                    System.out.println(message);
                                                                }
                                                                break;
                                                            case 2:
                                                                System.out.println("Enter Account Number");
                                                                try
                                                                {
                                                                    accNum = scanner.nextInt();
                                                                    try
                                                                    {
                                                                        List<Transaction> transactions = transactionService.getAllTransactions(accNum);
                                                                        for (Transaction transaction : transactions)
                                                                        {
                                                                            if (transaction != null)
                                                                                System.out.println(transaction);
                                                                        }
                                                                    }
                                                                    catch (TransactionNotFoundException message)
                                                                    {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            case 3:
                                                                System.out.println("Enter Account Number");
                                                                try
                                                                {
                                                                    accNum = scanner.nextInt();
                                                                    System.out.println("Enter Type of Transaction");
                                                                    String type = scanner.next();
                                                                    try
                                                                    {
                                                                        List<Transaction> transactions = transactionService.getTransactionByType(accNum, type);
                                                                        if(transactions.size()==0)
                                                                            System.out.println("No Transactions found of type "+  type);
                                                                        for (Transaction transaction : transactions)
                                                                        {
                                                                            if (transaction != null)
                                                                                System.out.println(transaction);
                                                                        }
                                                                    }
                                                                    catch (TransactionNotFoundException message)
                                                                    {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            case 4:
                                                                System.out.println("Enter Account Number");
                                                                try
                                                                {
                                                                    accNum = scanner.nextInt();
                                                                    System.out.println("Enter TransactionID : ");
                                                                    int transactionId = scanner.nextInt();
                                                                    try
                                                                    {
                                                                        Transaction transactions = transactionService.getTransactionByTransactionId(accNum, transactionId);
                                                                        System.out.println(transactions);
                                                                    }
                                                                    catch (TransactionNotFoundException message)
                                                                    {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            case 5:
                                                                System.out.println("Enter Account Number");
                                                                try
                                                                {
                                                                    accNum = scanner.nextInt();
                                                                    System.out.println("From Till date enter how many previous days transaction you want ");
                                                                    int days = scanner.nextInt();
                                                                    try
                                                                    {
                                                                        List<Transaction> transactions = transactionService.getPreviousDaysTransactions(accNum, days);
                                                                        for (Transaction transaction : transactions)
                                                                        {
                                                                            if (transaction != null)
                                                                                System.out.println(transaction);
                                                                        }
                                                                    }
                                                                    catch (TransactionNotFoundException message)
                                                                    {
                                                                        System.out.println(message);
                                                                    }
                                                                }
                                                                catch (InputMismatchException e)
                                                                {
                                                                    System.out.println("Account Number Invalid");
                                                                    scanner.nextLine();
                                                                }
                                                                break;
                                                            default:
                                                                System.out.println("Invalid Choice");
                                                                break;
                                                        }
                                                        System.out.println("press 1 to continue in Transactions Section");
                                                        System.out.println("press 0 to exit from Transactions Section");
                                                        try
                                                        {
                                                            t3 = scanner.nextInt();
                                                        }
                                                        catch (InputMismatchException e)
                                                        {
                                                            System.out.println("Wrong Input Type!\n Enter Number Only");
                                                            scanner.nextLine();
                                                        }
                                                    }
                                                }
                                                catch (InputMismatchException e)
                                                {
                                                    System.out.println("Wrong Input Type!\n Enter Number Only");
                                                    scanner.nextLine();
                                                }
                                            }
                                            break;
                                        default:
                                            System.out.println("Invalid Choice");
                                            break;
                                    }
                                    System.out.println("Press 1 to main menu");
                                    System.out.println("Press 0 to exit");
                                    s = scanner.nextInt();
                                }
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Wrong Input Type!\n Enter Number Only");
                                scanner.nextLine();
                            }


                        }
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
                System.out.println("press 1 to ADMIN and USER MENU");
                System.out.println("0 to exit application");
                try
                {
                    temp1 = scanner.nextInt();
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Wrong Input Type!\n Enter Number Only");
                    scanner.nextLine();
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Wrong Input Type!\n Enter Number Only");
                scanner.nextLine();
            }
        }
    }
}
