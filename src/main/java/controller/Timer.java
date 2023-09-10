package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static Date time;

    // initialization of the timer
    static void init() {
        time = new Date(0, 0, 0, 0, 0, 0);
    }

    // get time in format: hour: minute: second
    static String getTime() {
        return dateFormat.format(time.getTime());
    }

    // updating time by adding 1 second
    static void updateTime() {
        time.setTime(time.getTime() + 1000);
    }
}
