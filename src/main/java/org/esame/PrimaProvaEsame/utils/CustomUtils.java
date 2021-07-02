package org.esame.PrimaProvaEsame.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomUtils {

    public static class Utils {
        public static String getCurrentDate() {
            return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
}
