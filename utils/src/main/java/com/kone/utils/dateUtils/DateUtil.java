package com.kone.utils.dateUtils;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static Date getDateByString(String dateStr) {

        if (!StringUtils.isEmpty(dateStr)) {
            dateStr += " 00:00:00";
            DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime parse = LocalDateTime.parse(dateStr, ftf);
            long time = LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Date date = new Date(time);
            return date;
        }

        return new Date();
    }
}
