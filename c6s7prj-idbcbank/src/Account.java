import java.util.Date;

public class Account
{
    private long account_no;
    private String account_type;
    private int account_balance;
    private Date account_open_date;
    private int customer_id;

    //Constructor
    public Account()
    {

    }
    public Account(long account_no, String account_type, int account_balance, Date account_open_date, int customer_id) {
        this.account_no = account_no;
        this.account_type = account_type;
        this.account_balance = account_balance;
        this.account_open_date = account_open_date;
        this.customer_id = customer_id;
    }

    //Getter And Setter
    public long getAccount_no() {
        return account_no;
    }

    public void setAccount_no(long account_no) {
        this.account_no = account_no;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public int getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(int account_balance) {
        this.account_balance = account_balance;
    }

    public Date getAccount_open_date() {
        return account_open_date;
    }

    public void setAccount_open_date(Date account_open_date) {
        this.account_open_date = account_open_date;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    //ToString

    @Override
    public String toString() {
        return "Account{" +
                "account_no=" + account_no +
                ", account_type='" + account_type + '\'' +
                ", account_balance=" + account_balance +
                ", account_open_date=" + account_open_date +
                ", customer_id=" + customer_id +
                '}';
    }
}
