package prototype;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class TestClass {

    public static void main(String[] args) throws ParseException {
        // 得到指定日期
        long time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
            .parse("2018-06-30 20:00:00", new ParsePosition(0)).getTime() / 1000;
        System.out.println(time);
    }

}
