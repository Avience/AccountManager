package org.avience.accountmanager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//may not use class in final plugin
public class LogReader { //reads in last line of latest.log, janky way to try and test reading console outputs not likely to be touched beyond testing purposes

    public static String getLogMessages() {
        String filePath = AccountManager.getInstance().getConfig().getString("LogFilePath");
        String last = "NOTHING";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                last = line;
            }
            return last;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return last;
    }
}
