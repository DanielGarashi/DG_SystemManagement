package danielgarashi.DG_SystemManagement.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.StringTokenizer;

@Service
@AllArgsConstructor
@Data
public class ActionHelperService {
    public static int getTodayDateAsInt() {
        final String TODAY_DATE = getTodayDate();
        StringTokenizer token = new StringTokenizer(TODAY_DATE, "-");
        int year = Integer.parseInt(token.nextToken());
        int month = Integer.parseInt(token.nextToken());
        int day = Integer.parseInt(token.nextToken());

        return year * 10000 + month * 100 + day;
    }

    public static String getTodayDate() {
        return LocalDate.now().toString();
    }

    public static String getDateAsString(final int date) {
        int year = date / 10000;
        int month = (date / 100) % 100;
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
