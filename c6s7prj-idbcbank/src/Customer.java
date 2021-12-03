import java.util.Date;

public class Customer
{
    private int customer_id;
    private String customer_name;
    private Date customer_dob;
    private int customer_phoNo;
    private String customer_email;
    private String customer_add;

    //Constructor
    public Customer()
    {

    }
    public Customer(int customer_id, String customer_name, Date customer_dob, int customer_phoNo, String customer_email, String customer_add) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_dob = customer_dob;
        this.customer_phoNo = customer_phoNo;
        this.customer_email = customer_email;
        this.customer_add = customer_add;
    }

    //Getter And Setter
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Date getCustomer_dob() {
        return customer_dob;
    }

    public void setCustomer_dob(Date customer_dob) {
        this.customer_dob = customer_dob;
    }

    public int getCustomer_phoNo() {
        return customer_phoNo;
    }

    public void setCustomer_phoNo(int customer_phoNo) {
        this.customer_phoNo = customer_phoNo;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_add() {
        return customer_add;
    }

    public void setCustomer_add(String customer_add) {
        this.customer_add = customer_add;
    }

    //ToString

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", customer_dob=" + customer_dob +
                ", customer_phoNo=" + customer_phoNo +
                ", customer_email='" + customer_email + '\'' +
                ", customer_add='" + customer_add + '\'' +
                '}';
    }
}
