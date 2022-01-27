package danielgarashi.DG_SystemManagement.service;

import java.time.LocalDate;
import java.util.StringTokenizer;

public class ActionHelperService {
    public static int getTodayDateAsInt() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int month = localDate.getDayOfMonth();
        int day = localDate.getDayOfMonth();

        return year * 10000 + month * 100 + day;
    }

    public static String getTodayDate() {
        int today_date = getTodayDateAsInt();

        return getDateAsString(today_date);
    }

    public static String getDateAsString(final int date) {
        int year = date / 10000;
        int month = (date / 100) % 10;
        int day = date % 100;

        return new StringBuilder()
                .append(String.format("%d-", year))
                .append(month > 9 ? month : String.format("0-%d", month))
                .append(day > 9 ? day : String.format("0%d", day))
                .toString();
    }

    public static int getDateAsInt(String date) {
        StringTokenizer tokenizer = new StringTokenizer(date, "-");
        int year = Integer.parseInt(tokenizer.nextToken());
        int month = Integer.parseInt(tokenizer.nextToken());
        int day = Integer.parseInt(tokenizer.nextToken());

        return year * 10000 + month * 100 + day;
    }
}
