import java.sql.*;
import java.util.Date;

public class DbOperation
{
    public String createAccount(String customer_name, Date dob, int customer_phoNo,
                                 String customer_email, String customer_add,String account_type,
                                   int account_balance, Date account_open_date,Date transaction_date,
                                   String transaction_type,String transaction_mode
                                    ,int transaction_balance)
    {
        int customer_id=0;
        long account_no=0;
        int transaction_id=0;

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
                    System.out.println(customer_id+":"+rs.getString(2)+":"+rs.getDate(3)
                            +":"+rs.getInt(4)+":"+rs.getString(5)+":"
                            +rs.getString(6));

                    String query2 = "insert into account(account_type,account_balance,account_open_date,customer_id)"+
                            "values(?,?,?,?)";
                    PreparedStatement pst2 = con.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
                    pst2.setString(1,account_type);
                    pst2.setInt(2,account_balance);
                    pst2.setDate(3,new java.sql.Date(account_open_date.getTime()));
                    pst2.setInt(4,customer_id);
                    if((pst2.executeUpdate())==1)
                    {
                        ResultSet rs2 = pst2.getGeneratedKeys();
                        if(rs2.next())
                        {
                            account_no = rs2.getInt(1);
                            System.out.println(account_no+":"+rs2.getString(2)+":"+rs2.getInt(3)
                            +":"+rs2.getDate(4)+":"+rs2.getInt(5));

                            String query3 = "insert into transaction(transaction_date,transaction_type,transaction_mode," +
                                    "transaction_balance,account_no) values(?,?,?,?,?)";
                            PreparedStatement pst3 = con.prepareStatement(query3,Statement.RETURN_GENERATED_KEYS);
                            pst3.setDate(1,new java.sql.Date(transaction_date.getTime()));
                            pst3.setString(2,transaction_type);
                            pst3.setString(3,transaction_mode);
                            pst3.setInt(4,transaction_balance);
                            pst3.setLong(5,account_no);
                            if((pst3.executeUpdate())==1)
                            {
                                ResultSet rs3 = pst3.getGeneratedKeys();
                                if(rs3.next())
                                {
                                    transaction_id = rs3.getInt(1);
                                    System.out.println(transaction_id+":"+rs3.getDate(2)+":"+
                                            rs3.getString(3)+":"+rs3.getString(4)+":"+
                                            rs3.getInt(5)+":"+rs.getLong(6));
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
        return customer_id+":"+account_no+":"+transaction_id; // custoid, accid, trid
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
