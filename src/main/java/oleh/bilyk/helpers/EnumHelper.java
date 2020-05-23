package oleh.bilyk.helpers;

/**
 * #Summary:
 * #Author: Oleh Bilyk
 * #Author’s Email:
 * #Creation Date: 22/05/2020
 * #Comments:
 */
public class EnumHelper {
    private EnumHelper() {
    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String value) {
        T[] array = enumType.getEnumConstants();
        for (T item : array) {
            if (item.toString().equalsIgnoreCase(value.toLowerCase())) {
                return item;
            }
        }
        throw new RuntimeException(String.format("No such value '%s' in enum '%s'", value, enumType.getName()));
    }
}
