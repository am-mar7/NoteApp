package NotePackge;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    private static final int encryptor  = 5;
    private Encryption(){}

    public static String hash(String password) {
        String encrypted ="";
        for(int i = 0; i < password.length() ;i++) {
            char c = (char)((int)password.charAt(i) + encryptor);
            encrypted += c;
        }
        try {
            MessageDigest md =  MessageDigest.getInstance("MD5");
            byte [] messagedigest = md.digest(encrypted.getBytes());
            BigInteger BigInt = new BigInteger(1,messagedigest);
            return BigInt.toString(16);

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        return encrypted;
    }
}
