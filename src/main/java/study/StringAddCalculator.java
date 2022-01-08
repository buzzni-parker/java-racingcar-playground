package study;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {
    private static List<String> split(String string, String regex) throws NullPointerException {
        if (!regex.isEmpty())
            regex += "|";

        regex += ",|:";

        return Arrays.asList(string.split(regex));
    }

    private static Integer add(List<String> strings) {
        int total = 0;
        for (String string : strings) {
            total += convertNumber(string);
        }
        return total;
    }

    private static Integer convertNumber(String string) {
        Integer number;
        try {
            number = Integer.parseInt(string);
        } catch (Exception e) {
            throw new RuntimeException("negative number");
        }
        if (number < 0)
            throw new RuntimeException("negative number");
        return number;
    }

    public static int splitAndSum(String string) {
        if (string == null)
            return 0;
        if (string.isEmpty())
            return 0;

        String regex = "";
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(string);
        if (m.find()) {
            regex = m.group(1);
            string = m.group(2);
        }

        return add(split(string, regex));
    }

}
