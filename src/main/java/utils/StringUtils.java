package utils;

public class StringUtils {
    public static boolean containsIgnoreCase(String text, String searchTerm) {
        return text != null && searchTerm != null && text.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
