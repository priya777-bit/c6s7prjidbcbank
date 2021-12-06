import java.sql.*;
import java.util.Date;

public class DbOperation
{
    int customer_id;
    long account_no;
    int transaction_id;

    public boolean createCustomerId(String customer_name, Date dob, int customer_phoNo,
                                 String customer_email, String customer_add)
    {
        boolean result = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Registered..");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root",
                    "root");
            System.out.println("Coonection Success..");

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
                if (rs.next()) {
                     customer_id = rs.getInt(1);
                    System.out.println(customer_id+":"+rs.getString(2)+":"+rs.getDate(3)
                            +":"+rs.getInt(4)+":"+rs.getString(5)+":"
                            +rs.getString(6));
                }
            }
            result = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public boolean accountNumber(String account_type,int account_balance, Date account_open_date, int customer_id)
    {
        boolean result = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Registered..");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root",
                    "root");
            System.out.println("Coonection Success..");

            String query = "insert into account(account_type,account_balance,account_open_date,customer_id)"+
                    "values (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            if((pst.executeUpdate())==1)
            {
                ResultSet rs = pst.getGeneratedKeys();
                if(rs.next())
                {
                    result = true;

                    account_no=rs.getLong(1);
                    System.out.println(account_no+":"+rs.getString(2)+":"+rs.getInt(3)+":"
                            +rs.getDate(4)+":"+rs.getInt(5));
                }

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return result;
    }

    public boolean createTransactionId(Date transaction_date, String transaction_type,String transaction_mode
            ,int transaction_balance,long account_no)
    {
        boolean result = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Registered..");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root",
                    "root");
            System.out.println("Coonection Success..");

            String query = "insert into transaction(transaction_date,transaction_type,transaction_mode," +
                    "transaction_balance,account_no)";
            PreparedStatement pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            if((pst.executeUpdate())==1)
            {
                result = true;

                ResultSet rs = pst.getGeneratedKeys();
                if(rs.next())
                {
                    transaction_id = rs.getInt(1);
                    System.out.println(transaction_id+":"+rs.getDate(2)+":"+rs.getString(3)
                    +":"+rs.getString(4)+":"+rs.getInt(5)+":"+rs.getLong(6));
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return result;
    }
}
