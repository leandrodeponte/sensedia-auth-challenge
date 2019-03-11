package br.com.sensedia.StepsAuthenticator.utils;

import java.time.LocalDateTime;

public class DateUtils {

    public static LocalDateTime getCurrentTimeNoMiliseconds() {

        return LocalDateTime.now().withNano(0);

    }
}
