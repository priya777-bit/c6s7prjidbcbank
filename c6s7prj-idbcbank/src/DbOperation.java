import java.sql.*;
import java.text.ParseException;
import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DbOperation
{
    public boolean checkEligibility(Date customer_dob) throws ParseException
    {
        boolean result = false;
        int age;

        Instant instant = customer_dob.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());

        LocalDate localdate = zone.toLocalDate();

        Period period = Period.between(localdate,LocalDate.now());

        age=period.getYears();

        if(age>=18)
        {
            System.out.println("Your Eligible To Create Account..");
            result = true;
        }
        return result;
    }

    public List<Object> createAccount(String customer_name, Date dob, int customer_phoNo,
                                      String customer_email, String customer_add, String account_type,
                                      String transaction_type, String transaction_mode)
    {
        int customer_id=0;
        long account_no=0;
        int transaction_id=0;

        int result1=0;
        long result2=0;
        int result3=0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Registered..");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root",
                    "root");
            System.out.println("Connection Success..");

            String query = "insert into customer(customer_name,customer_dob,customer_phoNo,customer_email" +
                    ",customer_add) values(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,customer_name);
            pst.setDate(2,new java.sql.Date(dob.getTime()));
            pst.setInt(3,customer_phoNo);
            pst.setString(4,customer_email);
            pst.setString(5,customer_add);
            if ((pst.executeUpdate()) == 1)
            {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) { // customer records inserted successfully (customerid)
                     customer_id = rs.getInt(1);
                     result1=customer_id;

                    String query2 = "insert into account(account_type,customer_id)"+
                            "values(?,?)";
                    PreparedStatement pst2 = con.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
                    pst2.setString(1,account_type);
                    pst2.setInt(2,customer_id);
                    if((pst2.executeUpdate())==1)
                    {
                        ResultSet rs2 = pst2.getGeneratedKeys();
                        if(rs2.next())
                        {
                            account_no = rs2.getLong(1);
                            result2=account_no;

                            String query3 = "insert into transaction(transaction_type,transaction_mode," +
                                    "account_no) values(?,?,?)";
                            PreparedStatement pst3 = con.prepareStatement(query3,Statement.RETURN_GENERATED_KEYS);
                            pst3.setString(1,transaction_type);
                            pst3.setString(2,transaction_mode);
                            pst3.setLong(3,account_no);
                            if((pst3.executeUpdate())==1)
                            {
                                ResultSet rs3 = pst3.getGeneratedKeys();
                                if(rs3.next())
                                {
                                    transaction_id = rs3.getInt(1);
                                    result3=transaction_id;
                                }
                            }
                        }
                    }

			// insert account record
			// prepared statement : insert account
				// insert  transac
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return Arrays.asList(result1,result2,result3); // custoid, accid, trid
    }

    public boolean withdraw(long account_no,int amount_withdraw)
    {
        boolean result = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Registered..");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root",
                    "root");
            System.out.println("Connection Success..");

            String query = "select account_balance from account where account_no=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1,account_no);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                int account_bal1=rs.getInt(1);
                //int account_balance1=rs.getInt(3);
                System.out.println("Account Balance Is "+account_bal1);
                if(account_bal1>0)
                {account_bal1=account_bal1-amount_withdraw;
                System.out.println("After Withdraw Account Balance Is.."+account_bal1+" Withdraw Amount "+amount_withdraw);
                result=true;

                String query2 = "update account set account_balance=? where account_no=?";
                PreparedStatement pst2 = con.prepareStatement(query2);
                pst2.setLong(2,account_no);
                pst2.setInt(1,account_bal1);
                if((pst2.executeUpdate())==1)
                {
                    System.out.println("Your Amount Debited..");
                }}
                else
                    System.out.println("Account Balance Is Less Than Amount To Withdraw:"+account_bal1);
            }

            String query3 = "select transaction_balance from transaction where account_no=?";
            PreparedStatement pst3 = con.prepareStatement(query3);
            pst3.setLong(1,account_no);
            ResultSet rs2 = pst3.executeQuery();
            while(rs2.next())
            {
                int trans_bal=rs2.getInt(1);
                //int transaction_id=rs.getInt(1);
                System.out.println("Before/Last Transaction:"+ trans_bal);

                String query4 = "update transaction set transaction_balance=? where account_no=?";
                PreparedStatement pst4 = con.prepareStatement(query4);
                pst4.setLong(2,account_no);
                pst4.setInt(1,amount_withdraw);
                if((pst4.executeUpdate())==1)
                {
                    System.out.println("Transaction Is Successful..");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public boolean deposit(long account_no,int amount_deposit)
    {
        boolean result = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Registered..");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root",
                    "root");
            System.out.println("Connection Success..");

            String query = "select account_balance from account where account_no=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1,account_no);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                int account_bal1=rs.getInt(1);
                //int account_balance1=rs.getInt(3);
                //System.out.println("Account Number "+account_bal1);
                System.out.println("Account Balance Is "+account_bal1);
                account_bal1=account_bal1+amount_deposit;
                System.out.println("After Deposit Account Balance.."+account_bal1+" Deposited Amount "+amount_deposit);
                result=true;

                String query2 = "update account set account_balance=? where account_no=?";
                PreparedStatement pst2 = con.prepareStatement(query2);
                pst2.setLong(2,account_no);
                pst2.setInt(1,account_bal1);
                if((pst2.executeUpdate())==1)
                {
                    System.out.println("Your Amount Credited..");
                }
            }

            String query3 = "select transaction_balance from transaction where account_no=?";
            PreparedStatement pst3 = con.prepareStatement(query3);
            pst3.setLong(1,account_no);
            ResultSet rs2 = pst.executeQuery();
            while(rs2.next())
            {
                int transaction_bal=rs2.getInt(1);
                //int transaction_id=rs.getInt(1);
                System.out.println("Before/Last Transaction:"+transaction_bal);

                String query4 = "update transaction set transaction_balance=? where account_no=?";
                PreparedStatement pst4 = con.prepareStatement(query4);
                pst4.setLong(2,account_no);
                pst4.setInt(1,amount_deposit);
                if((pst4.executeUpdate())==1)
                {
                    System.out.println("Transaction Is Successful..");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public boolean checkBalance(long account_no)
    {
        boolean result = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Registered..");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root",
                    "root");
            System.out.println("Connection Success..");

            String query = "select account_balance from account where account_no=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1,account_no);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                long account_bal = rs.getLong(1);
                //int account_balance = rs.getInt(3);
                System.out.println("Your Account Balance IS: "+account_bal);
                result = true;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

//    public boolean accountNumber( int customer_id)
//    {
//        boolean result = false;
//
//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver Registered..");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root",
//                    "root");
//            System.out.println("Coonection Success..");
//
//
//            PreparedStatement pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
//            if((pst.executeUpdate())==1)
//            {
//                ResultSet rs = pst.getGeneratedKeys();
//                if(rs.next())
//                {
//                    result = true;
//
//                    account_no=rs.getLong(1);
//                    System.out.println(account_no+":"+rs.getString(2)+":"+rs.getInt(3)+":"
//                            +rs.getDate(4)+":"+rs.getInt(5));
//                }
//
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//        }
//        return result;
//    }
//
//    public boolean createTransactionId,long account_no)
//    {
//        boolean result = false;
//
//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver Registered..");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root",
//                    "root");
//            System.out.println("Coonection Success..");
//
//            String query =
//            PreparedStatement pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
//            if((pst.executeUpdate())==1)
//            {
//                result = true;
//
//                ResultSet rs = pst.getGeneratedKeys();
//                if(rs.next())
//                {
//                    transaction_id = rs.getInt(1);
//                    System.out.println(transaction_id+":"+rs.getDate(2)+":"+rs.getString(3)
//                    +":"+rs.getString(4)+":"+rs.getInt(5)+":"+rs.getLong(6));
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//        }
//        return result;
//    }
}
