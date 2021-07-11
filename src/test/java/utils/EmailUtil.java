package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtil {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(GetProperties.getSpecificProperty("patternEmail"), Pattern.CASE_INSENSITIVE);


    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

//    public static void main (String arge[]){
//        System.out.println(validate("a@a.in"));
//    }
}
