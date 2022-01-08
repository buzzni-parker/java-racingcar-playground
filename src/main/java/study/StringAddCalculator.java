package study;

import java.util.Arrays;
import java.util.List;

public class StringAddCalculator {

    public StringAddCalculator() {
    }

    public List<String> split(String string, String regex) throws NullPointerException {
        if (regex != null)
            string = string.replaceAll(regex, ",");

        string = string.replaceAll(";", ",");
        
        String[] strings = string.split(",");
        return Arrays.asList(strings);
    }

    public String removeCustomRegex(String string) {
        return string.substring(string.indexOf("\n")+1, string.length());
    }

    public String getCustomRegex(String string) {
        String regex;
        try {
            regex = string.substring(string.indexOf("//") + 2, string.indexOf("\n"));
        } catch (StringIndexOutOfBoundsException e) {
            regex = null;
        }
        return regex;
    }

    public Integer add(List<String> strings) {
        int total = 0;
        for (String string : strings) {
            total += convertNumber(string);
        }
        return total;
    }

    private Integer convertNumber(String string) {
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

    public Integer calculate(String string) {
        if (string.isEmpty())
            return 0;
        String regex = getCustomRegex(string);
        string = removeCustomRegex(string);
        List<String> strings = split(string, regex);
        return add(strings);
    }

}
