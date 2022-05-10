package src.Datetest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateday01 {
    public static void main(String[] args) {
        Date nowtime = new Date();
        System.out.println(nowtime);

        SimpleDateFormat date = new SimpleDateFormat("yyyy MM dd HH:mm:ss ss");
        System.out.println(date.format(nowtime));

        String str = "2020 10 23 12:54:22 22";
        try {
            System.out.println(date.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
