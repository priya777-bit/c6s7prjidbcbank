import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class IDBCMain
{
    public static void main(String args[]){
        DbOperation dbms = new DbOperation();
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date1 = date.format(dt);
        try
        {
            dt=date.parse(date1);
        }
        catch(ParseException pe)
        {
            System.out.println(pe);
        }
        boolean result = dbms.createCustomerId("priya",dt,9999999
                ,"p@p.gmail.com","nasik");
        System.out.println(result);
    }
}
