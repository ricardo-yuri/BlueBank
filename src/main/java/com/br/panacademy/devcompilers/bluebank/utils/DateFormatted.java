package com.br.panacademy.devcompilers.bluebank.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatted {

    public static String currentDateFormattedPtBr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
