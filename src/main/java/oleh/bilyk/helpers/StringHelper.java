package oleh.bilyk.helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 20/05/2020
 * #Comments:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringHelper {
    //<editor-fold desc="Public Methods">
    public static Matcher matchString(String regex, String str) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(str);
        m.matches();
        return m;
    }

    public static String getMatchedGroup(String regex, String str, int groupNumber) {
        try {
            return matchString(regex, str).group(groupNumber);
        } catch (IllegalStateException e) {
            throw new IllegalStateException(
                    String.format("String '%s' does not have group '%s' matched by pattern '%s'.",
                            str, groupNumber, regex));
        }
    }

    public static String removeWhitespaces(String input) {
        return input.replaceAll("\\s+", "");
    }

    public static Double parseStringToDouble(String number) {
        number = number.trim();
        number = number.replace(",", ".");
        return Double.parseDouble(number);
    }

    public static Map<Object, Object> build(Object... data) {
        Map<Object, Object> result = new HashMap<>();

        if (data.length % 2 != 0)
            throw new IllegalArgumentException("Odd number of arguments");

        Object key = null;
        Integer step = -1;

        for (Object value : data) {
            step++;
            switch (step % 2) {
                case 0:
                    if (value == null)
                        throw new IllegalArgumentException("Null key value");
                    key = value;
                    continue;
                case 1:
                    result.put(key, value);
                    break;
            }
        }
        return result;
    }

    public static boolean isStringMatched(String regex, String str) {
        return Pattern.matches(regex, str);
    }

    public static boolean isStringMatchedIgnoringSpacesLineBreaks(String regex, String str) {
        String newRegex = regex.replaceAll("\\s", "").replaceAll("\n", "").trim();
        String string = str.replaceAll("\\s", "").replaceAll("\n", "").trim();
        return Pattern.matches(newRegex, string);
    }
    //</editor-fold>
}
