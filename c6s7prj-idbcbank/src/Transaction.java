import java.util.Date;

public class Transaction
{
    private int transaction_id;
    private Date transaction_date;
    private String transaction_type;
    private String transaction_mode;
    private int transaction_balance;
    private long account_no;

    //Constructor
    public Transaction()
    {

    }
    public Transaction(int transaction_id, Date transaction_date, String transaction_type, String transaction_mode, int transaction_balance, long account_no) {
        this.transaction_id = transaction_id;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
        this.transaction_mode = transaction_mode;
        this.transaction_balance = transaction_balance;
        this.account_no = account_no;
    }

    //Getter And Setter
    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_mode() {
        return transaction_mode;
    }

    public void setTransaction_mode(String transaction_mode) {
        this.transaction_mode = transaction_mode;
    }

    public int getTransaction_balance() {
        return transaction_balance;
    }

    public void setTransaction_balance(int transaction_balance) {
        this.transaction_balance = transaction_balance;
    }

    public long getAccount_no() {
        return account_no;
    }

    public void setAccount_no(long account_no) {
        this.account_no = account_no;
    }

    //ToString

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", transaction_date=" + transaction_date +
                ", transaction_type='" + transaction_type + '\'' +
                ", transaction_mode='" + transaction_mode + '\'' +
                ", transaction_balance=" + transaction_balance +
                ", account_no=" + account_no +
                '}';
    }
}
