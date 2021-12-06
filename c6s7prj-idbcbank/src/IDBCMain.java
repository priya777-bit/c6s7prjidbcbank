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
        DateTimeFormatter date1 = null;
        try {
            String s = "yyyy/MM/dd HH:mm:ss";
            date1 = new DateTimeFormatter.ofPattern(s);
            LocalDateTime now = LocalDateTime.now();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean result = dbms.createCustomerId("priya",date1.format(now),9999999
                ,"p@p.gmail.com","nasik");
    }
}
