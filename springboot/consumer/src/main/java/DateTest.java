import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateTest
 *
 * @author kq
 * @date 2019-05-07
 */
public class DateTest {

    public static void main(String[] args) {
        System.out.printf("now date =%s",getFormat());
    }


    public static String getFormat() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(date);
    }

}
