package My_Lib;

public class valdation {

    // The sting "@gmail.com" must be suffix of the gmail you send
    public static boolean checkEmail (String org){
        String sub = "@gmail.com";
        if(org.length() <= sub.length())return false;
        int ptr1 = 0,ptr2 =0;
        for(;ptr1 < org.length(); ptr1++) {
            if(org.charAt(ptr1) == '@')break;
        }
        for(; ptr1 < org.length() && ptr2 < sub.length(); ptr1++, ptr2++) {
            if(org.charAt(ptr1) != sub.charAt(ptr2))return false;
        }
        return ptr1 == org.length();
    }
    // The password must have at least 3 of the following [capital letter - small letter - digit - special char]
    public static boolean checkPassword (String Password ,String confirmPassword){
        if(! Password.equals(confirmPassword) || Password.length() < 4)return false;
        boolean containCapital = false, contiansmall = false, containSpecial = false , containdigit = false;
        for(int i = 0 ; i < Password.length() ; i++) {
            if((int)Password.charAt(i) <= 122 && (int)Password.charAt(i) >= 97)contiansmall = true;
            if((int)Password.charAt(i) <= 90 && (int)Password.charAt(i) >= 65)containCapital = true;
            if((int)Password.charAt(i) <= 57 && (int)Password.charAt(i) >= 48)containdigit = true;
            if((int)Password.charAt(i) <= 47 && (int)Password.charAt(i) >= 33 ||
                    (int)Password.charAt(i) <= 96 && (int)Password.charAt(i) >= 91	)containSpecial = true;
        }
        int count = 0;
        if(containCapital)count++;
        if(containdigit)count++;
        if(containSpecial)count++;
        if(contiansmall)count++;
        System.out.println(count);
        if(count >= 3)return true;
        return false;
    }
    public static boolean checkPassword (String Password){
        return checkPassword(Password,Password);
    }
    //
}
