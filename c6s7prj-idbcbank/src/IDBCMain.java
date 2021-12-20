import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IDBCMain
{
    public static void main(String args[]){
        DbOperation dbms = new DbOperation();
        Scanner sc = new Scanner(System.in);
        int choice;
//        java.util.Date dt = new java.util.Date();
//        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        String date1 = date.format(dt);
//        try
//        {
//            dt=date.parse(date1);
//        }
//        catch(ParseException pe)
//        {
//            System.out.println(pe);
//        }

        do {
            System.out.println("1: Check Eligibility To Create Account");
            System.out.println("2: Create Account");
            System.out.println("3: With-Draw Amount");
            System.out.println("4: Deposit Amount");
            System.out.println("5: Check Account Balance ");

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Your Date Of Birth In (yyyy-MM-dd) Format");
                    String date = sc.next();
                    java.util.Date dt = null;
                    try {
                        dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                        boolean result = dbms.checkEligibility(dt);
                    } catch (ParseException pe) {
                        System.out.println(pe);
                    }
                    break;

                case 2:
                    System.out.println("Enter Customer Name:");
                    String customer_name = sc.next();
                    System.out.println("Enter Customer Date Of Birth Date In (yyyy-MM-dd) Format:");
                    String date2 = sc.next();
                    java.util.Date dt2 = null;
                    try {
                        dt2 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                        boolean result = dbms.checkEligibility(dt2);
                    } catch (ParseException pe) {
                        System.out.println(pe);
                    }
                    System.out.println("Enter Customer Phone Number:");
                    int customer_phoNo = sc.nextInt();
                    System.out.println("Enter Customer Email:");
                    String customer_email = sc.next();
                    System.out.println("Enter Customer Address:");
                    String customer_add = sc.next();
                    System.out.println("Enter Account Type:");
                    String account_type = sc.next();
                    System.out.println("Enter Transaction Type:");
                    String transaction_type = sc.next();
                    System.out.println("Enter Transaction Mode:");
                    String transaction_mode = sc.next();

                    List<Object> result = dbms.createAccount(customer_name, dt2, customer_phoNo, customer_email,
                            customer_add, account_type, transaction_type, transaction_mode);
                    break;

                case 3:
                    System.out.println("Enter 12 Digit Account Number:");
                    long account_no = sc.nextLong();
                    System.out.println("Enter Amount To Withdraw:");
                    int withdraw_amount = sc.nextInt();
                    boolean result2 = dbms.withdraw(account_no, withdraw_amount);
                    break;

                case 4:
                    System.out.println("Enter Account Number:");
                    long account_no2 = sc.nextLong();
                    System.out.println("Enter Amount To Deposit:");
                    int deposit_amount = sc.nextInt();
                    boolean result3 = dbms.deposit(account_no2, deposit_amount);
                    break;

                case 5:
                    System.out.println("Enter Account Number:");
                    long account_no3 = sc.nextLong();
                    boolean result4 = dbms.checkBalance(account_no3);
                    break;
            }
        }while(choice!=6);
    }
}
